(ns org.clojurists-together.site.web
  (:require [clojure.string :as str]
            [clojure.tools.logging :as log]
            [com.stuartsierra.component :as component]
            [compojure.core :refer [GET HEAD POST routes]]
            [hiccup.middleware :refer [wrap-base-url]]
            [org.clojurists-together.site.views.index :as index-view]
            [org.clojurists-together.site.views.markdown :as md]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.conditional :as cond]
            [ring.middleware.defaults :refer :all]
            [ring.middleware.json :refer [wrap-json-response]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.ssl :as ssl]
            [ring.util.response :as resp]
            [compojure.route :as route]
            [sentry-clj.core :as sentry]
            [sentry-clj.ring :as sentry-ring])
  (:import java.text.SimpleDateFormat
           [java.util Locale TimeZone]))

(def last-modified-formatter "EEE, dd MMM yyyy HH:mm:ss zzz")

(defn- ^SimpleDateFormat formatter [format]
  (doto (SimpleDateFormat. ^String format Locale/US)
    (.setTimeZone (TimeZone/getTimeZone "GMT"))))

(defn last-modified []
  (.format (formatter last-modified-formatter) (java.util.Date.)))


(defn- repo-redirect [{:keys [params]}]
  (log/info params)
  (resp/redirect (str "/" (:repo-url params))))

(defn app-routes
  [redis]
  (routes
   (GET "/" [] (index-view/index))
   (GET "/companies/" [] (md/markdown-view "companies.md"))
   (GET "/contact/" [] (md/markdown-view "companies.md"))
   (GET "/developers/" [] (md/markdown-view "developers.md"))
   (GET "/faq/" [] (md/markdown-view "faq.md"))
   (GET "/members/" [] (md/markdown-view "members.md"))
   (GET "/open-source/" [] (md/markdown-view "open-source.md"))
   (GET "/team/" [] (md/markdown-view "team.md"))
   (GET "/transparency/" [] (md/markdown-view "transparency.md"))
   (GET "/news/" [] (md/markdown-view "news.md"))

   (route/resources "public")
   (route/not-found "404")))

(defn production? [req]
  (= (:server-name req) "www.clojuriststogether.org"))

(defn wrap-referrer-policy
  [handler]
  (fn [request]
    (let [response (handler request)]
      (assoc-in response [:headers "Referrer-Policy"] "strict-origin"))))

(def csp (str/join "; " ["object-src 'none'"
                         "script-src 'strict-dynamic' 'unsafe-inline' http: https:;"
                         "base-uri 'none';"
                         "report-uri https://e33d8929ff48e13fdc2abfafda55bd99.report-uri.com/r/d/csp/enforce"]))

(defn wrap-content-security-policy
  [handler]
  (fn [request]
    (let [response (handler request)]
      (assoc-in response [:headers "Content-Security-Policy"] csp))))

(defn wrap-redirect-slash [handler]
  (fn [{:keys [uri] :as req}]
    (if (.endsWith uri "/")
      (handler req)
      (resp/redirect (str uri "/")))))

(defn- default-error
  "A very bare-bones error message. Ignores the request and exception."
  [req e]
  (log/error e (str "Error in" (:uri req)))
  (-> (str "<html><head><title>Error</title></head>"
           "<body><p>Clojurists Together Internal Server Error</p></body></html>")
      (resp/response)
      (resp/content-type "text/html")
      (resp/status 500)))

(defn app
  [redis]
  (-> (app-routes redis)
      (wrap-json-response)
      (wrap-resource "public")
      (wrap-base-url)
      (wrap-referrer-policy)
      (wrap-redirect-slash)
      #_(wrap-content-security-policy)
      (wrap-defaults (assoc-in site-defaults [:security :anti-forgery] false))
      (cond/if production? ssl/wrap-ssl-redirect)
      (cond/if production? ssl/wrap-hsts)
      (ssl/wrap-forwarded-scheme)
      (sentry-ring/wrap-report-exceptions nil {:error-fn default-error})))

(defn run-app
  [redis host port]
  (-> (app redis)
      (run-jetty {:join? false
                  #_ #_ :host host
                  :port port})))

(defrecord JettyWebServer [redis host port]
  component/Lifecycle
  (start [component]
    (if (:jetty component)
      component
      (assoc component :jetty (run-app redis host port))))
  (stop [component]
    (when-let [jetty (:jetty component)]
      (.stop jetty))
    (dissoc component :jetty)))

(defn new-jetty-web-server []
  (map->JettyWebServer {}))
