(ns clj-todo.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route])
  (:use [hiccup.core]))

(defn todo-list
  []
  (html
    [:body
     [:h1 "Potato"]]))

(defroutes app
  (GET "/" [] (todo-list)))
