(ns quantum-tictactoe.rules)

(def board-width 3)
(def board-height 3)
(def cell-count (* board-width board-height))

(defn next-turn [board]
  (:next-turn board))
