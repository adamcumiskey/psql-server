(ns psql-server.db
  (:require [clojure.java.jdbc :as sql]
            [clojure.tools.logging :as log]
            [clojure.string :as string]
            [conman.core :as conman]
            [mount.core :refer [defstate]]
            [psql-server.env :refer [env]]))

(defstate ^:dynamic *db*
  :start (conman/connect! {:jdbc-url (:jdbc-url env)})
  :stop (conman/disconnect! *db*))

(def command-files (->> (clojure.java.io/file "./db/commands")
                       file-seq
                       (filter #(.isFile %))
                       (map #(str "commands/" (.getName %)))))
(eval `(conman/bind-connection *db* ~@command-files))

