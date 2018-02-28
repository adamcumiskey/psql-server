(ns psql-server.db
  (:require [clojure.java.jdbc :as sql]
            [clojure.tools.logging :as log]
            [clojure.string :as string]
            [conman.core :as conman]
            [mount.core :refer [defstate]]
            [environ.core :refer [env]]))

(def jdbc-url (str "jdbc:postgresql://" 
                   (env :db-host) ":"
                   (env :db-port) "/"
                   (env :db-name) "?"
                   "user=" (env :postgres-user)
                   "&password=" (env :postgres-password)))

(defstate ^:dynamic *db*
  :start (conman/connect! {:jdbc-url jdbc-url})
  :stop (conman/disconnect! *db*))

(def sql-files (->> (clojure.java.io/file "src/sql")
                       file-seq
                       (filter #(.isFile %))
                       (map #(str "sql/" (.getName %)))))
(eval `(conman/bind-connection *db* ~@sql-files))

