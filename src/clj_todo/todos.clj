(ns clj-todo.todos
  (:require [taoensso.carmine :as car]))

(defmacro redis* [& body] `(car/wcar { :pool {}, :spec {}} ~@body))

(defn todo-list []
  (apply hash-map (redis* (car/hgetall "todos"))))

(defn add-todo [todo]
  (let [id (redis* (car/incr "todos.count"))]
    (redis*
      (car/hmset "todos" id  todo))))

(defn remove-todo [id]
  (redis* (car/hdel "todos" id)))
