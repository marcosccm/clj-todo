(ns clj-todo.handler
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.util.response :refer [redirect]]
            [clj-todo.views :as views]
            [clj-todo.todos :as todos]
            [hiccup.middleware :refer [wrap-base-url]])
  (:use [hiccup.core]))

(defroutes app-routes
  (GET "/" [] (views/index (todos/todo-list)))
  (POST "/" [todo] (do (todos/add-todo todo) (redirect "/"))))

(def app
  (-> (routes app-routes)
      (handler/site)
      (wrap-base-url)))
