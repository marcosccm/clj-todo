(ns clj-todo.handler-test
  (:require [cheshire.core :refer [parse-string]]
            [clj-todo.todos :refer [add-todo]]
            [clj-todo.helpers :refer [clean-redis-collections]])
  (:use midje.sweet
        clj-todo.handler
        ring.mock.request))

(def sample-todo "I am a sample")

(defn setup-test-data []
  (do 
    (clean-redis-collections)
    (add-todo sample-todo)))

(against-background [(before :contents (setup-test-data))]
  (fact "GET /api returns the todo collection"
    (let [response (app (request :get "/api"))]
      (parse-string (:body response)) => (contains {"1" sample-todo })
      (:status response) => 200)))

