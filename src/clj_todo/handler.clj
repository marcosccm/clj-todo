(ns clj-todo.handler
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [clj-todo.views :as views]
            [hiccup.middleware :refer [wrap-base-url]])
  (:use [hiccup.core]))

(def todos
  (ref ["Buy some milk"
        "Wash the dishes"
        "conquer the world"]))

(defn add-todo
  [todo]
  (dosync (alter todos conj todo)))

(defroutes app-routes
  (GET "/" [] (views/todo-list todos))
  (POST "/" [todo] (do (add-todo todo) (views/todo-list todos))))

(def app
  (-> (routes app-routes)
      (handler/site)
      (wrap-base-url)))
