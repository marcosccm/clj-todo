(ns clj-todo.handler
  (:require [clj-todo.views :as views]
            [clj-todo.todos :as todos]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.util.response :refer [redirect response]]))

(defroutes app-routes
  (GET "/" [] (views/index (todos/todo-list)))
  (POST "/" [todo] (do (todos/add-todo todo) (redirect "/")))
  (DELETE "/:id" [id] (do (todos/remove-todo id) (redirect "/"))))

(defroutes resource-routes
  (route/resources "/"))

(defroutes api-routes
  (GET "/api" [] (response (todos/todo-list))))

(def all-routes (routes app-routes api-routes resource-routes))
