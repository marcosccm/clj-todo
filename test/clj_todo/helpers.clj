(ns clj-todo.helpers
  (:require [environ.core :refer [env]]
            [taoensso.carmine :as car]))

(defmacro redis* [& body] `(car/wcar { :pool {}, :spec {}} ~@body))

(def todo-collection (env :todo-collection))

(def todo-id (str todo-collection ".count"))

(defn clean-redis-collections []
    (redis*
      (car/del todo-collection)
      (car/del todo-id)))

