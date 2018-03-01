(ns psql-server.server
  (:require [mount.core :refer [defstate]]
            [ring.adapter.jetty :as ring]
            [environ.core :refer [env]]
            [ring.middleware.json :refer [wrap-json-response]]
            [ring.logger :refer [wrap-with-logger]]
            [psql-server.middleware :as middleware]
            [psql-server.routes :refer [routes]])
  (:gen-class))

(def app
  (-> #'routes
      (middleware/wrap-data)
      (middleware/wrap-content-type "application/json")
      (wrap-json-response)
      (wrap-with-logger)))

(defstate server 
  :start (ring/run-jetty #'app {:port (Integer. (env :port))
                                :join? false})
  :stop (.stop server))
