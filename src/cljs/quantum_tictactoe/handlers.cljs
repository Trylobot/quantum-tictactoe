(ns quantum-tictactoe.handlers
  (:require
   [re-frame.core :as f]
   [quantum-tictactoe.rules :as rules]))

(def default-db {
  :board {
    :cells (into [] (repeat rules/cell-count nil)) ; [ nil nil nil  nil nil nil  nil nil nil ] ; tic-tac-toe, 9 cells
    :history [] ; [{ :player <:x,:o>, :type <:classic,:entangle,:collapse>, :position <number|[number,number]> }]
  } })

(f/reg-event-db :initialize-db
  (fn [_ _] default-db) )

(f/reg-event-db :board-click
  (fn [db [_ {:keys [position]}]]
    (let [next-turn (rules/next-turn (:board db))
          history-count (count (get-in db [:board :history]))]
      (-> db
        (assoc-in [:board :cells position]
          next-turn)
        (assoc-in [:board :history history-count]
          {:player next-turn, :type :classic, :position position}) ) )))
