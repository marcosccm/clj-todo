(defproject clj-todo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :plugins [[lein-ring "0.8.12"]
            [lein-environ "1.0.0"]
            [lein-midje "3.1.3"]
            [lein-cljsbuild "1.0.3"]
            [lein-pdo "0.1.1"]]

  :cljsbuild {
    :builds [{:source-paths ["src-cljs"]
              :compiler {:output-to "resources/public/js/cljtodo.js"
                         :output-dir "resources/public/js/out"
                         :optimizations :none
                         :source-map true}}]}

  :ring {:handler clj-todo.app/app}

  :aliases {"up" ["pdo" "cljsbuild" "auto" "dev," "ring" "server-headless"]}

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2202"]
                 [compojure "1.2.0"]
                 [hiccup "1.0.5"]
                 [environ "1.0.0"]
                 [cheshire "5.3.1"]
                 [com.taoensso/timbre "3.3.1"]
                 [com.taoensso/carmine "2.7.0"  :exclusions [org.clojure/clojure]]
                 [ring/ring-json "0.3.1"]
                 [ring/ring-defaults "0.1.2"]
                 [midje "1.6.3"]
                 [ring-mock "0.1.5"]
                 [om "0.8.0-alpha2"]])
