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

;; Log mount state changes
(mount-up/on-upndown :info mount-up/log :before)

(defn start []
  (mount/start))

(defn stop []
  (mount/stop))

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

