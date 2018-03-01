(ns user
  (:require [clojure.tools.namespace.repl :refer (refresh refresh-all)]
            [mount.core :as mount]
            [mount-up.core :refer [on-upndown log]]
            [migratus.core :as migratus]
            [environ.core :refer [env]] 
            [psql-server.db :as db]
            [conman.core :as conman]
            [clojure.java.jdbc :as sql]))

;; Log mount state changes
(on-upndown :info log :before)

(defn seed [file]
  (conman/with-transaction [db/connection]
    (sql/db-do-commands db/connection true (clojure.string/split (slurp file) #";"))))

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

