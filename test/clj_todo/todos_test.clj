(ns clj-todo.todos-test
  (:use midje.sweet)
  (:require [clj-todo.todos :refer :all]
            [clj-todo.helpers :refer [clean-redis-collections]]))

(fact "add-todo adds todos to the database"
      (add-todo "wash the dishes") => "OK"
      (against-background [(before :checks (clean-redis-collections))]))

(fact "todo-list returns all todos on the database"
      (do
        (add-todo "mow the lawn")
        (todo-list)) => {"1" "mow the lawn"}
      (against-background [(before :checks (clean-redis-collections))]))

(fact "remove todo eliminates todos by key"
      (do
        (add-todo "mow the lawn")
        (add-todo "do the dishes")
        (remove-todo 1)
        (todo-list)) => {"2" "do the dishes"}
      (against-background [(before :checks (clean-redis-collections))]))
