(ns clj-todo.views
  (:require [hiccup.form :refer :all]
            [hiccup.page :refer [include-js]]
            [hiccup.element :refer :all])
  (:use [hiccup.core]
        [ring.util.anti-forgery]))

(defn layout [& content]
  (html
    [:head
      [:title "Clj Todo!"]
      (include-js "http://fb.me/react-0.11.1.js")
      (include-js "js/out/goog/base.js")
      (include-js "js/cljtodo.js")
    [:body content]]))

(defn show-todo [[id todo]]
  [:li todo
    (form-to [:delete (str "/" id)]
            (anti-forgery-field)
            (submit-button "done!"))])

(defn index [todos]
  (layout
    [:div {:id "content"}]
    [:h1 "Todo List"]
    [:ul
      (map show-todo todos)]
    [:h1 "New Todo"]
    [:form { :action "/" :method "post"}
      (anti-forgery-field)
      [:label { :for "todo" } "Todo"]
      (text-field "todo")
      (submit-button "submit")]
    [:script {:type "text/javascript"} "goog.require('clj_todo.core');"]))
