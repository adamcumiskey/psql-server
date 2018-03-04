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
                   (env :postgres-name) "?"
                   "user=" (env :postgres-user)
                   "&password=" (env :postgres-password)))

(defstate ^:dynamic connection
  :start (conman/connect! {:jdbc-url jdbc-url})
  :stop (conman/disconnect! connection))

(def query-files (->> (clojure.java.io/file "resources/queries")
                      (file-seq)
                      (filter #(.isFile %))
                      (map #(str "queries/" (.getName %)))))
(eval `(conman/bind-connection connection ~@query-files))

(defn seed
  ([file connection]
    (try
      (sql/db-do-commands connection true (clojure.string/split (slurp file) #";"))
      (catch Exception e
        (log/error (.getNextException e)))))
  ([file]
    (conman/with-transaction [connection]
      ((seed file connection)))))
