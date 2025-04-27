(ns weeklyvents.events-file-ui
  (:require [com.github.alexisc183.forctional.core :refer [surround-with-html]]
            [weeklyvents.event-handlers :refer [read-events-file update-events-file]]
            [weeklyvents.ui.reusable :refer [auto-vertical-scroll-pane]]
            [weeklyvents.ui.state :refer [events-file-field]])
  (:import [java.awt BorderLayout]
           [javax.swing JButton JFrame]
           [weeklyvents ActionEventConsumer WindowOpenedConsumer]))

(defn save-button
  []
  (doto (new JButton (surround-with-html "Save"))
    (. addActionListener (new ActionEventConsumer update-events-file))))

(defn events-file-frame
  []
  (doto (new JFrame)
    (. setBounds 20 20 400 600)
    (. setDefaultCloseOperation JFrame/DISPOSE_ON_CLOSE)
    (. add (auto-vertical-scroll-pane events-file-field) BorderLayout/CENTER)
    (. add (save-button) BorderLayout/SOUTH)
    (. addWindowListener (new WindowOpenedConsumer read-events-file))
    (. setVisible true)))