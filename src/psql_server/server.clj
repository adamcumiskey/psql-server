(ns psql-server.server
  (:require [mount.core :refer [defstate]]
            [ring.adapter.jetty :as ring]
            [psql-server.env :refer [env]]
            [psql-server.routes :refer [routes]])
  (:gen-class))

(defstate server 
  :start (ring/run-jetty #'routes {:port (:port env)
                                   :join? false})
  :stop (.stop server))
