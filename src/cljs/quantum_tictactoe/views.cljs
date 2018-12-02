(ns quantum-tictactoe.views
  (:require
    [reagent.core :as r]
    [re-frame.core :as f]
    [goog.string :as gstring]
    [goog.string.format]
    [quantum-tictactoe.events :as e]
    [quantum-tictactoe.rules :as rules] ))


(defn group [elements]
  (into [] (concat '(:g) elements)) )

(defn cell-view [cell i]
  (let [row (int (/ i rules/board-height))
        col (mod i rules/board-width)
        cell-size 400
        cell-center (/ cell-size 2)
        margin 25
        x-offset (* col cell-size)
        y-offset (* row cell-size)
        p1-color "#b6f97b"
        p2-color "#fd94fc"]
    (case cell
      ; classic "X"
      :x [:g [:line {:x1 (+ x-offset margin) :y1 (+ y-offset margin) :x2 (- (+ cell-size x-offset) margin) :y2 (- (+ cell-size y-offset) margin) :stroke-width 30 :stroke p1-color :stroke-linecap "round" }]
             [:line {:x1 (- (+ cell-size x-offset) margin) :y1 (+ y-offset margin) :x2 (+ x-offset margin) :y2 (- (+ cell-size y-offset) margin) :stroke-width 30 :stroke p1-color :stroke-linecap "round" }] ] 
      ; classic "O"
      :o [:g [:circle {:cx (+ cell-center x-offset) :cy (+ cell-center y-offset) :r (- (/ cell-size 2) margin) :stroke-width 30 :stroke p2-color :stroke-linecap "round" :fill "none" }]]
      ; default (empty)
      nil )))

(defn board-view
  ([board [translate-x translate-y] scale opacity]
    [:g {:transform (gstring/format "translate(%d %d) scale(%d %d)" translate-x translate-y scale scale)
         :opacity opacity}
      ; main board background
      [:g
          [:rect {:x 0 :y 0 :width 1200 :height 1200 :fill "#202020"}]
          [:line {:x1 400 :y1 50 :x2 400 :y2 1150 :stroke-width 13 :stroke "#606060" :stroke-linecap "round" }]
          [:line {:x1 800 :y1 50 :x2 800 :y2 1150 :stroke-width 13 :stroke "#606060" :stroke-linecap "round" }]
          [:line {:x1 50 :y1 400 :x2 1150 :y2 400 :stroke-width 13 :stroke "#606060" :stroke-linecap "round" }]
          [:line {:x1 50 :y1 800 :x2 1150 :y2 800 :stroke-width 13 :stroke "#606060" :stroke-linecap "round" }] ]
      ; cells
      (let [cells (:cells board)]
        (group (map cell-view cells (range rules/cell-count))) )
    ])
  ([board]
    (board-view board [0 0] 1 1) ))

(defn button-array []
  [:g (map (fn [i] 
        (let [r (int (/ i rules/board-height))
              c (mod i rules/board-width)]
          [:rect {:x (* c 400) :y (* r 400) :width 400 :height 400 
                  :fill "transparent"
                  :on-click (e/mouse-click-handler i) 
                  :pointer-events "bounding-box" 
                  :key i }] )) 
      (range rules/cell-count)) ])

(defn main-panel []
  (let [db (f/subscribe [:db])]
    (fn []
      [:div
        [:svg {:class "main-panel"
               :view-box "0 0 1200 1200" }
          [board-view (:board @db)]
          [button-array] ]
        ; [:pre {:style {:color "#fff"}} (with-out-str (cljs.pprint/pprint @db))]
      ] )))

; (def color-background "rgba(32,32,32,1)")

; (defn title []
;   (let [name (f/subscribe [:name])]
;     (fn []
;       [c/title
;        :label (str "Hello from " @name)
;        :level :level1])))

; (defn points-to-rect
;   "returns a pair of pairs: top-left and (positive) width-height for
;   the rectangle that encloses p0 and p1"
;   [[x0 y0] [x1 y1]]
;   (let [width (Math/abs (- x1 x0))
;         height (Math/abs (- y1 y0))
;         left (min x1 x0)
;         top (min y1 y0)]
;     [[left top] [width height]]))

; (defn rect
;   [{:keys [id start pos]}]
;   (let [[[left top] [width height]] (points-to-rect start pos)]
;     [:rect {:key id
;             :style {:fill "rgba(0,0,128,0.7)"
;                     :stroke-width 3
;                     :stroke :black}
;             :x left
;             :y top
;             :width width
;             :height height}]))

; (defn clear-button []
;   (let [items (f/subscribe [:items])]
;     (fn []
;       [c/button
;        :label "Clear"
;        :class "btn btn-primary"
;        ;; :tooltip "Remove all items
;        ;; :tooltip-position :right-center
;        :disabled? (empty? @items)
;        :on-click #(f/dispatch [:clear-items])])))


; (defn svg-controls []
;   [c/v-box
;    :gap "1em"
;    :size "50px"
;    :margin "10px"
;    :align :center
;    :children [[clear-button]]])

; (defn svg-area []
;   [c/h-box
;    :gap "1em"
;    :size "auto"
;    :margin "10px"
;    :children [[svg-pane]]])

; (defn main-panel []
;   (fn []
;     [c/v-box
;      :height "100%"
;      :children [[svg-area]]]))

; (defn main-panel []
;   (fn []
;     [svg-pane]))
