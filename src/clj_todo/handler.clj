(ns clj-todo.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [clj-todo.views :as views]
            [clj-todo.todos :as todos]
            [taoensso.timbre :as timbre]
            [ring.util.response :refer [redirect]]
            [ring.middleware.defaults :refer :all]
            [hiccup.middleware :refer [wrap-base-url]])
  (:use [hiccup.core]))

(defn wrap-with-logging [handler]
  (fn [req]
    (timbre/debug req)
    (handler req)))

(defroutes app-routes
  (GET "/" [] (views/index (todos/todo-list)))
  (POST "/" [todo] (do (todos/add-todo todo) (redirect "/")))
  (DELETE "/:id" [id] (do (todos/remove-todo id) (redirect "/")))
  (route/resources "/"))

(def app
  (-> (routes app-routes)
      (wrap-defaults site-defaults)
      (wrap-base-url)
      (wrap-with-logging)))
