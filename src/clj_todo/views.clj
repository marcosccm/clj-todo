(ns clj-todo.views
  (:require [hiccup.form :refer :all])
  (:use [hiccup.core]))

(defn layout
  [& content]
  (html
    [:head
      [:title "Clj Todo!"]]
    [:body content]))

(defn todo-list
  [todos]
  (layout
    [:h1 "Todo List"]
    [:ul
      (map (fn [todo] [:li todo]) @todos)]
    [:h1 "New Todo"]
    [:form { :action "/" :method "post"}
      [:label { :for "todo" } "Todo"]
      (text-field "todo")
      (submit-button "submit")]))
