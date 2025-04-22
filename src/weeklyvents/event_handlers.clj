(ns weeklyvents.event-handlers
  (:require [clojure.java.io :refer [file reader writer]]
            [clojure.string :refer [trim]]
            [com.github.alexisc183.forctional.core :refer [lines matches? throws?]]
            [weeklyvents.runnables :refer :all]
            [weeklyvents.structs :refer [event event-to-serialized-string]]
            [weeklyvents.ui.state :refer [elapsed-time-field event-name-field events-file-field]])
  (:import [java.time LocalDate LocalTime]
           [java.time.temporal ChronoUnit]
           [javax.swing JOptionPane]))

(defn register-event
  [e]
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
                (. writer_ (write (str (-> (struct event
                                                   (-> (LocalTime/now)
                                                       (.truncatedTo ChronoUnit/SECONDS)
                                                       (.minusMinutes (. elapsed-time getMinute))
                                                       (.minusSeconds (. elapsed-time getSecond)))
                                                   event-name
                                                   (. (LocalDate/now) getDayOfWeek))
                                           (event-to-serialized-string)) \newline))))))))


(defn start-reading-loop
  [e]
  (doto (new Thread read-on-loop)
    (. start)))

(defn read-events-file
  [e]
  (with-open [reader_ (reader (file "events.txt"))]
    (. events-file-field (setText (->> (lines reader_)
                                       (reduce #(str %1 %2 \newline) ""))))))

(defn update-events-file
  [e]
  (with-open [writer_ (writer (file "events.txt"))]
    (. writer_ (write (. events-file-field getText)))))