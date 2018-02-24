(ns psql-server.migrations
  (:require [migratus.core :as migratus]
            [psql-server.env :refer [env]]))

(def db-config {:store :database
                :migration-dir "migrations/"
                :migration-table-name "schema_migrations"
                :db {:db-type "postgresql"
                     :db-name "postgres"
                     :classname "org.postgresql.Driver"
                     :host "db"
                     :port 5432
                     :user (:db-user env)
                     :password (:db-password env)}
                :db-type "postgresql"
                :db-name "postgres"
                :classname "org.postgresql.Driver"
                :host "db"
                :port 5432
                :user (:db-user env)
                :password (:db-password env)})

(defn initialize [] 
  (migratus/init db-config))

(defn migrate []
  (migratus/migrate db-config))

(defn rollback []
  (migratus/rollback db-config))

(defn bring-up [id]
  (migratus/up db-config id))

(defn bring-down [id]
  (migratus/down db-config id))

(defn create [name]
  (migratus/create db-config name))
