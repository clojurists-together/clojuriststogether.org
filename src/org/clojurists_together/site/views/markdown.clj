(ns org.clojurists-together.site.views.markdown
  (:require [org.clojurists-together.site.views.common :as common]))

(defn markdown-view [filename]
  (common/template
    [:h1 filename]))
