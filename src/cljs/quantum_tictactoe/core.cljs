(ns quantum-tictactoe.core
  (:require
    [reagent.core :as r]
    [re-frame.core :as f]
    [quantum-tictactoe.events :as events]
    [quantum-tictactoe.handlers :as handlers]
    [quantum-tictactoe.subs :as subs]
    [quantum-tictactoe.views :as views]
    [quantum-tictactoe.rules :as rules] ))

(enable-console-print!)

(defn init []
  (f/dispatch-sync [:initialize-db])
  (r/render [views/main-panel] (.getElementById js/document "app")) )

(init)

