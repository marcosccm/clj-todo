(ns clj-todo.core
  (:require [om.core :as om :include-macros true]
            [sablono.core :as html :refer-macros [html]]))

(enable-console-print!)

(def app-state (atom {:todos { 1 "Wash the dishes"}}))

(defn todo-view [[id todo] owner]
  (om/component
    (html [:li todo
           (html/form-to [:delete (str "/" id)]
                         (html/submit-button "done!"))])))

(defn renderer [data owner]
  (om/component
    (html [:div
           [:h1 "Ommmm"]
           [:ul (om/build-all todo-view (:todos data))]])))

(om/root renderer app-state
         {:target (. js/document (getElementById "content"))})
