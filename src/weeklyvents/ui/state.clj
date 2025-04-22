(ns weeklyvents.ui.state
  (:require [weeklyvents.ui.reusable :refer [wrapper-text-area]])
  (:import [javax.swing JLabel]))

; BEGIN controls UI

(def event-name-field (wrapper-text-area))
(def elapsed-time-field (wrapper-text-area))

; END controls UI

; BEGIN output UI

; MUTATED AT weeklyvents.event_handlers$read-events-file
(def output-label (doto (new JLabel)
                        (. setVerticalAlignment JLabel/TOP)))

; END output UI


; BEGIN events-file-ui state

; MUTATED AT weeklyvents.event-handlers$read-events-file
(def events-file-field (wrapper-text-area))

; END events-file-ui state