(ns user
  (:require [clojure.tools.namespace.repl :refer (refresh refresh-all)]
            [mount.core :as mount]
            [mount-up.core :refer [on-upndown log]]
            [migratus.core :as migratus]
            [psql-server.db :as db]))

;; Log mount state changes
(on-upndown :info log :before)

(defn start []
  (mount/start))

(defn stop []
  (mount/stop))

(def migratus-config {:store :database
                      :migration-dir "resources/migrations"
                      :db db/jdbc-url})

(defn migrate []
  (migratus/migrate migratus-config))

(defn rollback []
  (migratus/migrate migratus-config))

(defn bring-up [id]
  (migratus/up migratus-config id))

(defn bring-down [id]
  (migratus/down migratus-config id))

(defn create [name]
  (migratus/create migratus-config name))

