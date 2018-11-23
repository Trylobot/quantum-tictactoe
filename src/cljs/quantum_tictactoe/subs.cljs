(ns quantum-tictactoe.subs
  (:require
   [re-frame.core :as f]
   [reagent.ratom :refer-macros [reaction]]))

(f/reg-sub-raw :db
  (fn [db]
    (reaction @db) ))

