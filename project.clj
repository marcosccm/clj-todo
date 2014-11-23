(defproject clj-todo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-ring "0.8.12"]
            [lein-environ "1.0.0"]
            [lein-midje "3.1.3"]]
  :ring {:handler clj-todo.handler/app}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.2.0"]
                 [hiccup "1.0.5"]
                 [environ "1.0.0"]
                 [com.taoensso/timbre "3.3.1"]
                 [com.taoensso/carmine "2.7.0"  :exclusions [org.clojure/clojure]]
                 [ring/ring-defaults "0.1.2"]
                 [midje "1.6.3"]])
