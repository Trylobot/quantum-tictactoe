(ns quantum-tictactoe.subs
  (:require
   [re-frame.core :as f]
   [reagent.ratom :refer-macros [reaction]]))

(f/reg-sub-raw
  :screen
  (fn [db]
    (reaction (:screen @db)) ) )

(f/reg-sub-raw
  :board
  (fn [db]
    (reaction (:board @db)) ) )

; (f/reg-sub-raw
;   :name
;   (fn [db]
;     (reaction (:name @db))))

; (f/reg-sub-raw
;   :items
;   (fn [db]
;     (reaction (:items @db))))

; (f/reg-sub-raw
;   :drag
;   (fn [db]
;     (reaction (:drag @db))))
