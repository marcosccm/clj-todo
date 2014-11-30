(ns clj-todo.core
  (:require-macros [cljs.core.async.macros :refer [go alt!]])
  (:require [cljs.core.async :refer [put! <! >! chan timeout]]
            [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [cljs-http.client :as http]))

(enable-console-print!)

(def app-state (atom {}))

(defn fetch-todos [url]
  (let [c (chan)]
    (go (let [{todos :body} (<! (http/get url))]
          (>! c (vec todos))))
    c))

(defn todo-view [[id todo] owner]
  (om/component
    (dom/li nil todo)))

(defn todo-list [data owner opts]
  (reify
    om/IInitState
    (init-state [_]
      {:todos []})
    om/IWillMount
    (will-mount [_]
      (go (let [todos (<! (fetch-todos (:url opts)))]
            (om/update! data :todos todos))))
    om/IRender
    (render [_]
      (apply dom/ul nil (om/build-all todo-view (:todos data))))))

(defn app [data owner]
  (om/component
    (dom/div nil
             (dom/h1 nil "Omm Todos!")
             (om/build todo-list data {:opts {:url "/api"}}))))

(om/root app app-state
         {:target (. js/document (getElementById "content"))})
