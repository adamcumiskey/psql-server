(defproject psql-server "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/java.jdbc "0.6.1"]
                 [org.clojure/tools.logging "0.3.1"]
                 [org.clojure/data.json "0.2.6"]
                 [com.mchange/c3p0 "0.9.5.2"]
                 [mount "0.1.12"]
                 [org.postgresql/postgresql "9.4-1201-jdbc41"] 
                 [ring/ring-core "1.6.3"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [compojure "1.4.0"]]
  :main ^:skip-aot psql-server.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
