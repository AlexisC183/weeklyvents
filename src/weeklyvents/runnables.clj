(ns weeklyvents.runnables
  (:require [clojure.java.io :refer [file reader]]
            [com.github.alexisc183.forctional.core :refer [lines surround-with-html within-closed-range?]]
            [weeklyvents.structs :refer [event-to-string parse-event]]
            [weeklyvents.ui.state :refer [output-label]])
  (:import [java.time LocalDate LocalTime]
           [java.time.temporal ChronoUnit]))

(defn read-on-loop
  []
  (loop [events-file (file "events.txt")]
    (if (. events-file exists)
      (with-open [reader_ (reader events-file)]
        (try
          (let [current-day-of-week (. (LocalDate/now) getDayOfWeek)
                current-time (. (LocalTime/now) (truncatedTo ChronoUnit/SECONDS))
                lower-bound (if (= (. current-time getHour) 0)
                              LocalTime/MIN
                              (. current-time (minusHours 1)))
                upper-bound (if (= (. current-time getHour) 23)
                              LocalTime/MAX
                              (. current-time (plusHours 1)))
                filtered-events (->> (lines reader_)
                                     (map parse-event)
                                     (distinct)
                                     (filter (fn [e] (and
                                                      (= (:initial-day-of-week e) current-day-of-week)
                                                      (within-closed-range? (:initial-time e) lower-bound upper-bound)))))]
            (. output-label (setText (if (empty? filtered-events)
                                       ""
                                       (->> filtered-events
                                            (sort-by (fn [e] (:initial-time e)))
                                            (map event-to-string)
                                            (reduce (fn [f s] (str f "<br>" s)))
                                            (surround-with-html))))))
          (catch Exception e (. output-label (setText (surround-with-html "events.txt may be corrupted"))))))
      (. events-file createNewFile))
    (recur events-file)))