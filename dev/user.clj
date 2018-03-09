(ns user
  (:require [clojure.tools.namespace.repl :refer (refresh refresh-all)]
            [clojure.tools.logging :as log]
            [clj-logging-config.log4j :as log-config]
            [mount.core :as mount]
            [mount-up.core :as mount-up]
            [migratus.core :as migratus]
            [environ.core :refer [env]]
            [psql-server.db :as db]
            [conman.core :as conman]
            [clojure.java.jdbc :as sql]))

(require '[psql-server.db :as db])
(require '[psql-server.auth :as auth])

;; Log mount state changes

(mount-up/on-upndown :info mount-up/log :before)


;; DB commands

(defn start []
  (mount/start))

(defn stop []
  (mount/stop))

(defn seed-db []
  (db/seed "resources/seeds/test.up.sql"))

(defn clean-db []
  (db/seed "resources/seeds/test.down.sql"))


;; Migrations

(def migratus-config {:store :database
                      :migration-dir "migrations"
                      :db db/jdbc-url})

(defn migrate []
  (migratus/migrate migratus-config))

(defn rollback []
  (migratus/rollback migratus-config))

(defn bring-up [id]
  (migratus/up migratus-config id))

(defn bring-down [id]
  (migratus/down migratus-config id))

(defn create [name]
  (migratus/create migratus-config name))


;; Sample data

(def person {:first_name "Adam"
             :last_name "Cumiskey"
             :email "adam.cumiskey@gmail.com"
             :password "password"})
