(ns clj-todo.app
  (:require [clj-todo.handler :as handler]
            [taoensso.timbre :as timbre]
            [ring.middleware.defaults :refer :all]
            [ring.middleware.json :refer [wrap-json-response]]
            [hiccup.middleware :refer [wrap-base-url]]))

(defn wrap-with-logging [handler]
  (fn [req]
    (timbre/debug req)
    (handler req)))

(def app
  (-> handler/all-routes
      (wrap-json-response)
      (wrap-defaults site-defaults)
      (wrap-base-url)
      (wrap-with-logging)))
