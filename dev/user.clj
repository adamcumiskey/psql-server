(ns user
  (:require [clojure.tools.namespace.repl :refer (refresh refresh-all)]
            [mount.core :as mount]
            [mount-up.core :refer [on-upndown log]]
            [migratus.core :as migratus]
            [psql-server.env :refer [env]]
            [psql-server.db :refer [db]]))

(mount/start)

(def migratus-config {:store :database
                      :migration-dir "db/migrations"
                      :db (:jdbc-url env)})

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

;; Log mount state changes
(on-upndown :info log :before)
