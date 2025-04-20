(ns weeklyvents.ui.reusable
  (:import [javax.swing JScrollPane JTextArea]))

(defn auto-vertical-scroll-pane
    [child]
    (new JScrollPane
         child
         JScrollPane/VERTICAL_SCROLLBAR_AS_NEEDED
         JScrollPane/HORIZONTAL_SCROLLBAR_NEVER))

(defn wrapper-text-area
  []
  (doto (new JTextArea)
    (. setLineWrap true)
    (. setWrapStyleWord true)))