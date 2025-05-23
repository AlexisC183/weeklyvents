(ns weeklyvents.event-handlers
  (:require [clojure.java.io :refer [file reader writer]]
            [clojure.string :refer [trim]]
            [com.github.alexisc183.forctional.core :refer [lines matches? surround-with-html throws? within-closed-range?]]
            [weeklyvents.structs :refer [event-to-serialized-string event-to-string parse-event]]
            [weeklyvents.ui.state :refer [elapsed-time-field event-name-field events-file-field output-label]])
  (:import [java.time LocalDate LocalTime]
           [java.time.temporal ChronoUnit]
           [javax.swing JOptionPane]
           [weeklyvents.structs event]))

(defn register-event
  [_]
  (let [event-name (. event-name-field getText)
        elapsed-time-raw (. elapsed-time-field getText)]
    (cond
      ; Checking if the fields have no meaningful text
      (or
       (empty? (trim event-name))
       (empty? (trim elapsed-time-raw))) nil

      ; Validating the elapsed time
      (or
       (not (matches? elapsed-time-raw #"^\d\d:\d\d$"))
       (throws? (fn [] (LocalTime/parse (str "00:" elapsed-time-raw))))) (JOptionPane/showMessageDialog elapsed-time-field
                                                                                                        "Illegal elapsed time format"
                                                                                                        "Illegal format"
                                                                                                        JOptionPane/WARNING_MESSAGE)
      
      :else (with-open [writer_ (writer (file "events.txt") :append true)]
              (let [elapsed-time (LocalTime/parse (str "00:" elapsed-time-raw))]
                (. writer_ (write (str (-> (new event
                                                (-> (LocalTime/now)
                                                    (.truncatedTo ChronoUnit/SECONDS)
                                                    (.minusMinutes (. elapsed-time getMinute))
                                                    (.minusSeconds (. elapsed-time getSecond)))
                                                event-name
                                                (. (LocalDate/now) getDayOfWeek))
                                           (event-to-serialized-string)) \newline))))))))


(defn read_
  [_]
  (let [events-file (file "events.txt")]
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
          (catch Exception _ (. output-label (setText (surround-with-html "events.txt may be corrupted"))))))
      (. events-file createNewFile))))

(defn read-events-file
  [_]
  (with-open [reader_ (reader (file "events.txt"))]
    (. events-file-field (setText (->> (lines reader_)
                                       (reduce #(str %1 %2 \newline) ""))))))

(defn update-events-file
  [_]
  (with-open [writer_ (writer (file "events.txt"))]
    (. writer_ (write (. events-file-field getText)))))