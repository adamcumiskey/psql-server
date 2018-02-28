(ns psql-server.server
  (:require [mount.core :refer [defstate]]
            [ring.adapter.jetty :as ring]
            [environ.core :refer [env]]
            [psql-server.routes :refer [routes]])
  (:gen-class))

(defstate server 
  :start (ring/run-jetty #'routes {:port (Integer. (env :port))
                                   :join? false})
  :stop (.stop server))
