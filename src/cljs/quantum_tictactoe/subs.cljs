(ns quantum-tictactoe.subs
  (:require
   [re-frame.core :as f]
   [reagent.ratom :refer-macros [reaction]]))

(f/reg-sub-raw :board
  (fn [db]
    (reaction (:board @db)) ) )
