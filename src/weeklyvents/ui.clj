(ns weeklyvents.ui
  (:require [com.github.alexisc183.forctional.core :refer [surround-with-html]]
            [weeklyvents.event-handlers :refer :all]
            [weeklyvents.events-file-ui :refer [events-file-frame]]
            [weeklyvents.ui.reusable :refer [auto-vertical-scroll-pane]]
            [weeklyvents.ui.state :refer [elapsed-time-field event-name-field output-label]])
  (:import [java.awt GridLayout]
           [javax.swing JButton JFrame JLabel JPanel]))

; BEGIN controls UI

(defn field-label
  [text]
  (doto (new JLabel (surround-with-html text))
    (. setHorizontalAlignment JLabel/CENTER)
    (. setVerticalAlignment JLabel/BOTTOM)))

(defn register-event-button
  []
  (doto (new JButton (surround-with-html "Register event"))
    (. addActionListener (new ActionEventConsumer register-event))))

(defn edit-events-file-button
  []
  (doto (new JButton (surround-with-html "Edit events file"))
    (. addActionListener (new ActionEventConsumer (fn [e] (events-file-frame))))))

(defn controls
  []
  (doto (new JPanel)
    (. setLayout (new GridLayout 0 1))
    (. add (field-label "Event name:"))
    (. add (auto-vertical-scroll-pane event-name-field))
    (. add (field-label "Elapsed time from beginning (mm:ss format):"))
    (. add (auto-vertical-scroll-pane elapsed-time-field))
    (. add (register-event-button))
    (. add (edit-events-file-button))))

; END controls UI

; BEGIN output UI

(defn output
  []
  (doto (new JPanel)
    (. setLayout (new GridLayout 0 1))
    (. add (auto-vertical-scroll-pane output-label))))

; END output UI

(defn main-frame
  []
  (doto (new JFrame)
    (. setBounds 10 10 500 500)
    (. setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
    (. setLayout (new GridLayout 0 2))
    (. add (controls))
    (. add (output))
    (. addWindowListener (new WindowOpenedConsumer start-reading-loop))
    (. setVisible true)))