(ns clj-todo.todos
  (:require [environ.core :refer [env]]
            [taoensso.carmine :as car]))

(def todo-collection (env :todo-collection))

(def todo-id (str todo-collection ".count"))

(defmacro redis* [& body] `(car/wcar { :pool {}, :spec {}} ~@body))

(defn todo-list []
  (apply hash-map (redis* (car/hgetall todo-collection))))

(defn add-todo [todo]
  (let [id (redis* (car/incr todo-id))]
    (redis*
      (car/hmset todo-collection id  todo))))

(defn remove-todo [id]
  (redis* (car/hdel todo-collection id)))
