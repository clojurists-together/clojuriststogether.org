(ns org.clojurists-together.site.views.index
  (:require [clojure.string :as string]
            [org.clojurists-together.site.views.common :as common-views]
            [org.clojurists-together.site.utils :refer [html5]]
            [markdown.core :as md]
            [clojure.java.io :as io]
            [hiccup.util :as util]))


(defn index []
  (html5 {:lang "en"}
    [:head
     [:title "Clojurists Together"]
     (common-views/common-head)]
    [:body
     (common-views/header)
     [:main.col (util/raw-string (md/md-to-html-string (slurp (io/resource "markdown/index.md"))))]
     (common-views/common-footer)]))
