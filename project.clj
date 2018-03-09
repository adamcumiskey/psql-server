(defproject psql-server "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/java.jdbc "0.6.1"]
                 [org.clojure/tools.logging "0.3.1"]
                 [org.clojure/data.json "0.2.6"]
                 [buddy/buddy-auth "2.1.0"]
                 [buddy/buddy-sign "2.2.0"]
                 [com.layerware/hugsql "0.4.8"]
                 [conman "0.7.6"]
                 [clj-time "0.14.2"]
                 [environ "1.1.0"]
                 [org.slf4j/slf4j-log4j12 "1.7.9"]
                 [log4j/log4j "1.2.17" :exlusions [javax.mail/mail
                                                   javax.jms/jms
                                                   com.sun.jmdk/jmxtools
                                                   com.sun.jmx/jmxri]]
                 [clj-logging-config "1.9.12"]
                 [migratus "1.0.6"]
                 [mount "0.1.12"]
                 [tolitius/mount-up "0.1.1"]
                 [org.postgresql/postgresql "9.4-1201-jdbc41"]
                 [ring/ring-core "1.6.3"]
                 [ring/ring-json "0.4.0"]
                 [ring-logger "0.7.7"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [compojure "1.4.0"]]
  :main ^:skip-aot psql-server.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :dev {:dependencies [[org.clojure/tools.namespace "0.3.0-alpha1"]
                                  [tolitius/mount-up "0.1.1"]
                                  [ring/ring-mock "0.3.2"]]
                   :source-paths ["dev"]
                   :resource-paths ["resources"]
                   :plugins [[migratus-lein "0.5.7"]
                            [lein-auto "0.1.3"]
                            [lein-eftest "0.5.0"]]
                   :migratus {:store :database
                              :migration-dir "migrations"
                              :db ~(str "jdbc:postgresql://"
                                        (get (System/getenv) "DB_HOST") ":"
                                        (get (System/getenv) "DB_PORT") "/"
                                        (get (System/getenv) "POSTGRES_NAME") "?"
                                        "user=" (get (System/getenv) "POSTGRES_USER") "&"
                                        "password=" (get (System/getenv) "POSTGRES_PASSWORD"))}}})
