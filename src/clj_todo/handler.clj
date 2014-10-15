(ns clj-todo.handler
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [hiccup.form :refer :all]
            [hiccup.middleware :refer [wrap-base-url]])
  (:use [hiccup.core]))

(def todos
  (ref ["Buy some milk"
   "Wash the dishes"
   "conquer the world"]))

(defn todo-list
  []
  (html
    [:body
      [:h1 "Todo List"]
      [:ul
        (map (fn [todo] [:li todo]) @todos)]
      [:h1 "New Todo"]
      [:form { :action "/" :method "post"}
        [:label { :for "todo" } "Todo"]
        (text-field "todo")
        (submit-button "submit")]]))

(defn add-todo
  [todo]
  (dosync (alter todos conj todo)))

(defroutes app-routes
  (GET "/" [] (todo-list))
  (POST "/" [todo] (do (add-todo todo) (todo-list))))

(def app
  (-> (routes app-routes)
      (handler/site)
      (wrap-base-url)))
