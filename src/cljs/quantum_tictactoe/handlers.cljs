(ns quantum-tictactoe.handlers
  (:require
   [re-frame.core :as f]))

(def default-db {
  ; :screen {
  ;   :width  (.-innerWidth  js/window),
  ;   :height (.-innerHeight js/window) }
  :board {} })

(f/reg-event-db :initialize-db
  (fn [_ _] default-db) )

; (f/reg-event-db :window-resize
;   (fn [db size]
;     (assoc db :screen size) ) ) ; {:height _, :width _}


; (f/reg-event-db
;   :mouse-event
;   (fn [{drag :drag :as db} [_ {:keys [type pos] :as event}]]
;     (let [button-state (if drag :was-down :was-up)]
;       (case [type button-state]
;         [:mousedown :was-up]
;         (assoc db :drag {:start pos :pos pos})
;         [:mousemove :was-down]
;         (assoc-in db [:drag :pos] pos)
;         [:mouseup :was-down]
;         (do
;           (f/dispatch [:finish-drag])
;           (f/dispatch [:create-item (assoc drag :pos pos)])
;           db)
;         db))))

; (f/reg-event-db
;   :finish-drag
;   (fn [db [_]]
;     (dissoc db :drag)))

; (f/reg-event-db
;   :create-item
;   (fn [{:keys [next-id] :as db} [_ {:keys [start pos]}]]
;     (let [item {:id (keyword (str "i" next-id))
;                 :start start
;                 :pos pos}]
;       (-> db
;           (update-in [:next-id] inc)
;           (update-in [:items] conj item)))))

; (f/reg-event-db
;   :clear-items
;   (fn [db [_]]
;     (assoc db :items [])))
