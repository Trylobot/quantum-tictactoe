(ns quantum-tictactoe.rules)

(def cell-count 9)
(def board-width 3)
(def board-height 3)

(defn next-turn [board]
  (case (:player (last (:history board)))
    :x :o
    :o :x
    :x )) ; default first move
