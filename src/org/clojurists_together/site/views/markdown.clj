(ns org.clojurists-together.site.views.markdown
  (:require [org.clojurists-together.site.views.common :as common]
            [markdown.core :as md]
            [hiccup.util]
            [clojure.java.io :as io]
            [clojure.string :as str]))

(defn markdown-view [filename]
  (when-let [md (some-> (io/resource (str "markdown/" filename))
                        (slurp)
                        (md/md-to-html-string-with-meta
                          :inhibit-separator "%"
                          :heading-anchors true))]
    (let [title (str/trim (first (:title (:metadata md))))]
      (common/template title
        [:main [:h1 title]
         (hiccup.util/raw-string (:html md))]))))
