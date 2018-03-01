(ns psql-server.routes
  (:require [compojure.core :refer [defroutes GET POST ANY]]
            [compojure.coercions :refer [as-int]]
            [ring.logger :as logger]
            [mount.core :refer [defstate]]
            [psql-server.db :as db]
            [psql-server.middleware :as middleware]))

(defroutes all-routes*
  (GET "/users" request (db/get-all-users))
  (GET "/users/:id" [id :<< as-int] (str (db/user-by-id {:id id})))
  (POST "/echo" request (str request))
  (GET "/health" [] "OK"))

(def all-routes
  (-> #'all-routes*
      (middleware/wrap-data)
      (middleware/wrap-content-type "application/json")
      (middleware/wrap-json)
      (logger/wrap-with-logger)))

(defstate routes
  :start (defroutes all (ANY "*" [] all-routes)))

