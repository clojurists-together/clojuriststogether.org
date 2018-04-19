(ns org.clojurists-together.site.signup
  (:require [org.clojurists-together.site.views.common :as common]))

(defn thanks []
  (common/template "Thanks"
    [:h1 "Thanks!"]
    [:p "Thanks for signing up for Clojurists Together. Your name will show up soon on the list of " [:a {:href "/members/"} "members"] " once we process your payment."]
    [:p "We'll be in touch with news and updates as they come, along with quarterly surveys on what you'd like to see us fund."]
    [:p [:a {:href "/contact/"} "Get in touch"] " if you have any questions or problems."]))
