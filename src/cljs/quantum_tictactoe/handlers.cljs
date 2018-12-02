(ns quantum-tictactoe.handlers
  (:require
   [re-frame.core :as f]
   [quantum-tictactoe.rules :as rules]))

(def default-cells (into [] (repeat rules/cell-count nil)) )

(def default-db {
  :board {
    :cells default-cells ; [ nil nil nil  nil nil nil  nil nil nil ] ; tic-tac-toe, 9 cells
    :history [] ; [{ :player <:x,:o>, :type <:entangle,:collapse>, :position <number|[number,number]> }]
    :next-turn :x ; <:x,:o>
  } })

(f/reg-event-db :initialize-db
  (fn [_ _] default-db) )

(f/reg-event-db :board-click
  (fn [db [_ {:keys [position]}]]
    (let [history-count (count (get-in db [:board :history]))
          existing-mark (get-in db [:board :cells position])
          previous-turn (last (get-in db [:board :history]))
          [first-position second-position] (:position previous-turn)
          next-turn-mark (rules/next-turn (:board db))]
      (cond
        ; 2nd click of an entanglement ?
        (and (= (:type previous-turn) :entangle) ; turn type is entanglement
             (= next-turn-mark (:player previous-turn)) ; player is expected to finish this turn
             (not (nil? first-position)) ; turn already specifies a position
             (nil? second-position) ; turn has not specified both positions
             (not= position first-position) ) ; a different cell has been specified than the first position of this turn

      (cond
        ; nothing at that position
        (nil? existing-mark)
          (let [next-turn-data {:player next-turn-mark, :type :entangle, :position [position nil]}]
            ; insert a partial entanglement marker (part 1)
            (-> db
              (assoc-in [:board :cells position] default-cells)
              (assoc-in [:board :history history-count] next-turn-data) ))
        ; classic mark at that position
        (keyword? existing-mark)
          ; do nothing; this position is locked and cannot be changed now
          db
        ; vector at that position (existing entangled mark)
        (vector? existing-mark)
          ; ?
          db ))))
