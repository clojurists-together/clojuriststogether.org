(ns org.clojurists-together.site.views.index
  (:require [clojure.string :as string]
            [org.clojurists-together.site.views.common :as common-views]
            [org.clojurists-together.site.utils :refer [html5]]))


(defn index []
  (html5 {:lang "en"}
    [:head
     [:title "Deps Versions: identify out of date dependencies"]
     (common-views/common-head)]
    [:body
     (common-views/header)
     [:h2 "Funding critical Clojure open source software"]

     (common-views/common-footer)]))
