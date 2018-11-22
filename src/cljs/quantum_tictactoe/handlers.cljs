(ns quantum-tictactoe.handlers
  (:require
   [re-frame.core :as f]
   [quantum-tictactoe.rules :as rules]))

(def default-db {
  :board {
    :cells (into [] (repeat rules/cell-count nil)) ; [ nil nil nil  nil nil nil  nil nil nil ] ; tic-tac-toe, 9 cells
    :history [] ; list of moves, first move first
  } })

(f/reg-event-db :initialize-db
  (fn [_ _] default-db) )

(f/reg-event-db :board-click
  (fn [db [_ {:keys [position]}]]
    ; TODO: examine board state to decide what gets changed, if anything, when a cell is clicked
    (assoc-in db [:board :cells position] :x) ) )
