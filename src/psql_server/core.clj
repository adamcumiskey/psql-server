(ns psql-server.core
  (:require [mount.core :as mount]
            [psql-server.db]
            [psql-server.server])
  (:gen-class))

(defn -main []
  (mount/start))

