(ns weeklyvents.structs
  (:require [clojure.string :refer [split]])
  (:import [com.github.alexisc183.forconsol FormatException]
           [java.time DayOfWeek LocalTime]
           [java.time.temporal ChronoUnit]))

(defrecord event [initial-time name initial-day-of-week])

(defn event-to-string
  [{initial-time :initial-time
    name :name}]
  (str initial-time " " name))

(defn event-to-serialized-string
  [{initial-time :initial-time
    name :name
    initial-day-of-week :initial-day-of-week}]
  (str initial-time \¦ name \¦ initial-day-of-week))

(defn parse-event
  [string]
  (let [fields (split string #"\¦")]
    (if (= (count fields) 3)
      (new event
           (. (LocalTime/parse (nth fields 0)) (truncatedTo ChronoUnit/SECONDS))
           (nth fields 1)
           (DayOfWeek/valueOf (nth fields 2)))
      (throw (new FormatException)))))