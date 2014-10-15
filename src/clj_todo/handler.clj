(ns clj-todo.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route])
  (:use [hiccup.core]))

(def todos
  ["Buy some milk"
   "Wash the dishes"
   "conquer the world"])

(defn todo-list
  []
  (html
    [:body
     [:h1 "Todo List"]
     [:ul
      (map (fn [todo] [:li todo]) todos)]]))

(defroutes app
  (GET "/" [] (todo-list)))
