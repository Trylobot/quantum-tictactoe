(ns quantum-tictactoe.views
  (:require
    [reagent.core :as r]
    [re-frame.core :as f]
    [quantum-tictactoe.events :as e]))

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

(defn board-view [board]
  [:g [:rect {:fill "#202020" :x 0 :y 0 :width 1200 :height 1200 }]
      [:line {:x1 400 :y1 50 :x2 400 :y2 1150 
              :stroke-width 13 :stroke "#606060" :stroke-linecap "round" }]
      [:line {:x1 800 :y1 50 :x2 800 :y2 1150 
              :stroke-width 13 :stroke "#606060" :stroke-linecap "round" }]
      [:line {:x1 50 :y1 400 :x2 1150 :y2 400 
              :stroke-width 13 :stroke "#606060" :stroke-linecap "round" }]
      [:line {:x1 50 :y1 800 :x2 1150 :y2 800 
              :stroke-width 13 :stroke "#606060" :stroke-linecap "round" }] ])

(defn button-array []
  [:g (map (fn [i] 
        (let [r (int (/ i 3))
              c (mod i 3)]
          [:rect {:x (* c 400) :y (* r 400) :width 400 :height 400 
                  :fill "transparent"
                  :on-click (e/mouse-click-handler i) 
                  :pointer-events "bounding-box" 
                  :key i }] )) 
      (range 9)) ] )

; (defn svg-pane
(defn main-panel []
  (let [;drag (f/subscribe [:drag])
        ;items (f/subscribe [:items])
        ;mouse-handler (e/mouse-event-handler)
        board (f/subscribe [:board]) ]
    (fn []
      [:svg {:class "main-panel"
             :view-box "0 0 1200 1200" }
             ; :style {:background "#202020" }}
             ; :on-mouse-up mouse-handler
             ; :on-mouse-down mouse-handler
             ; :on-mouse-move mouse-handler}
        [board-view board]
        [button-array]
        ; (concat
        ;  (when-let [{:keys [start pos]} @drag]
        ;    [(rect {:id :creating
        ;            :start start
        ;            :pos pos})])
        ;  (into [] (map rect @items)))

      ])))

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
