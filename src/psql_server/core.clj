(ns psql-server.core
  (:require [psql-server.routes :refer [routes]]
            [psql-server.env :as env]
            [ring.adapter.jetty :as ring]) 
  (:gen-class))

(defn -main []
  (ring/run-jetty #'routes {:port env/port
                            :join? false}))

