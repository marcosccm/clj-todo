(ns clj-todo.core
  (:require [om.core :as om :include-macros true]
            [sablono.core :as html :refer-macros [html]]))

(enable-console-print!)

(def app-state (atom {:todos { 1 "Wash the dishes"}}))

(defn renderer [data owner]
  (om/component
    (html [:div
            [:h1 "Ommmm"]
            [:ul
              (map (fn [[id todo]] [:li todo]) (:todos data))]])))

(om/root renderer app-state
   {:target (. js/document (getElementById "content"))})
