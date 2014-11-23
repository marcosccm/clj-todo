(ns clj-todo.views
  (:require [hiccup.form :refer :all]
            [hiccup.element :refer :all])
  (:use [hiccup.core]
        [ring.util.anti-forgery]))

(defn layout [& content]
  (html
    [:head
      [:title "Clj Todo!"]]
    [:body content]))

(defn index [todos]
  (layout
    [:h1 "Todo List"]
    [:ul
      (map (fn [[id todo]]
             [:li
              todo
              (form-to [:delete (str "/" id)]
                       (anti-forgery-field)
                       (submit-button "done!"))]) todos)]
    [:h1 "New Todo"]
    [:form { :action "/" :method "post"}
      (anti-forgery-field)
      [:label { :for "todo" } "Todo"]
      (text-field "todo")
      (submit-button "submit")]))
