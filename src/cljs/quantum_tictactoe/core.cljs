(ns quantum-tictactoe.core
  (:require
    [reagent.core :as r]
    [re-frame.core :as f]
    [quantum-tictactoe.handlers]
    [quantum-tictactoe.subs]
    [quantum-tictactoe.views :as v]))

(enable-console-print!)

(defn init []
  (f/dispatch-sync [:initialize-db])
  (r/render [v/main-panel]
            (.getElementById js/document "app")))

(init)

