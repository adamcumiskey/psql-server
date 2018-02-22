(ns psql-server.db
  (:require [clojure.java.jdbc :as sql]
            [clojure.tools.logging :as log]
            [clojure.string :as string]
            [mount.core :refer [defstate]]
            [psql-server.env :refer [env]])
  (:import com.mchange.v2.c3p0.ComboPooledDataSource
           org.postgresql.Driver))

(defstate db
  :start (let [pool (doto (ComboPooledDataSource.)
                      (.setDriverClass "org.postgresql.Driver")
                      (.setJdbcUrl (str "jdbc:postgresql://" (:db-url env)))
                      (.setUser (:db-user env))
                      (.setPassword (:db-password env))
                      (.setMaxIdleTimeExcessConnections (* 30 60))
                      (.setMaxIdleTime (* 3 60 60)))]
           {:datasource pool})
  :stop (.close (:datasource db)))

(defn do-query [query]
  (sql/query db [query]))

(defn get-objects [table]
  (do-query (str "select * from " table)))

(defn get-object-by-id [table id]
  (do-query (str "select * from " table " as t where t.id = \"" id "\"")))

(defn get-person [id]
  (get-object-by-id "person" id))

(defn get-people []
  (get-objects "person"))
