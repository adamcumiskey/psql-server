(ns psql-server.routes
  (:require [compojure.core :refer [defroutes GET POST ANY]]
            [compojure.coercions :refer [as-uuid]]
            [mount.core :refer [defstate]]
            [psql-server.db :refer :all]
            [psql-server.middleware :as middleware]))

(defroutes all-routes*
  (GET "/people" [] (get-people))
  (GET "/people/:id" [id :<< as-uuid] (get-person id))
  (POST "/echo" request (str request))
  (GET "/health" [] "OK"))

(def all-routes
  (-> #'all-routes*
      (middleware/wrap-data)
      (middleware/wrap-content-type "application/json")
      (middleware/wrap-json)))

(defstate routes
  :start (defroutes all (ANY "*" [] all-routes)))
