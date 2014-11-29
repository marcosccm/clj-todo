(ns clj-todo.core
  (:require-macros [cljs.core.async.macros :refer [go alt!]])
  (:require [goog.events :as events]
            [cljs.core.async :refer [put! <! >! chan timeout]]
            [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(enable-console-print!)

(def app-state (atom {:todos { 1 "Wash the dishes"}}))

(defn renderer [data owner]
  (om/component
    (apply dom/ul nil
      (map (fn [[id todo]] (dom/li nil todo)) (:todos data)))))

(om/root renderer app-state
   {:target (. js/document (getElementById "content"))})
