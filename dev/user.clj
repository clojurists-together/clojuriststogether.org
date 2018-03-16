(ns user
  (:require [org.clojurists-together.site.core :refer [config new-system]]
            [reloaded.repl :refer [system init start stop go reset reset-all]]))

(reloaded.repl/set-init! #(new-system :dev))
