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
    (common/template
      [:main [:h1 (str/trim (first (:title (:metadata md))))]
       (hiccup.util/raw-string (:html md))])))
