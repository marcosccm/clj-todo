(ns clj-todo.todos)

(def todo-list
  (ref ["Buy some milk"
        "Wash the dishes"
        "conquer the world"]))

(defn add-todo
  [todo]
  (dosync (alter todo-list conj todo)))
