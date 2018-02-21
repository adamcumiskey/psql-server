(ns psql-server.db
  (:require [clojure.java.jdbc :as sql]
            [clojure.tools.logging :as log]
            [clojure.string :as string]
            [mount.core :refer [defstate]]
            [psql-server.env :refer [env]])
  (:import com.mchange.v2.c3p0.ComboPooledDataSource))

(def db-spec {:connection-uri (str "jdbc:" (:db-url env)
                              "?user=" (:db-user env) 
                              "&password=" (:db-password env))
              :classname "org.postgresql.Driver"
              :subprotocol "postgresql"
              :subname (:db-url env)
              :db-user (:db-user env)
              :db-password (:db-password env)})

(defstate db
  :start (let [pool (doto (ComboPooledDataSource.)
                      (.setDriverClass (:classname db-spec))
                      (.setJdbcUrl (str "jdbc" (:subprotocol db-spec) ":" (:subname db-spec)))
                      (.setUser (:db-user db-spec))
                      (.setPassword (:db-password db-spec))
                      (.setMaxIdleTimeExcessConnections (* 30 60))
                      (.setMaxIdleTime (* 3 60 60)))]
           {:datasource pool}))

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
