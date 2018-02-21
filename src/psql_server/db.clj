(ns psql-server.db
  (:require [clojure.java.jdbc :as sql]
            [clojure.tools.logging :as log]
            [clojure.string :as string]
            [psql-server.env :as env]))

(def db-spec {:connection-uri (str "jdbc:" env/db-url 
                              "?user=" env/db-user 
                              "&password=" env/db-password)})

(defn do-query [query]
  (sql/query db-spec [query]))

(defn get-objects [table]
  (do-query (str "select * from " table)))

(defn get-object-by-id [table id]
  (do-query (str "select * from " table " as t where t.id = \"" id "\"")))

(defn get-person [id]
  (get-object-by-id "person" id))

(defn get-people []
  (get-objects "person"))
