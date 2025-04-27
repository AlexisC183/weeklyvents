(ns weeklyvents.core
  (:require [weeklyvents.ui :refer [main-frame]])
  (:gen-class))

(defn -main
  [& _]
  (main-frame))