(ns psql-server.core
  (:require [mount.core :as mount])
  (:gen-class))

(defn -main []
  (mount/start))

