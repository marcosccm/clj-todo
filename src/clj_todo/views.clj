(ns clj-todo.views
  (:require [hiccup.form :refer :all])
  (:use [hiccup.core]))

(defn layout
  [& content]
  (html
    [:head
      [:title "Clj Todo!"]]
    [:body content]))

(defn index
  [todos]
  (layout
    [:h1 "Todo List"]
    [:ul
      (map (fn [todo] [:li (last todo)]) todos)]
    [:h1 "New Todo"]
    [:form { :action "/" :method "post"}
      [:label { :for "todo" } "Todo"]
      (text-field "todo")
      (submit-button "submit")]))