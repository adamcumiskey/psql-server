(ns psql-server.routes
  (:require [compojure.core :refer [defroutes GET POST ANY]]
            [compojure.coercions :refer [as-uuid]]
            [psql-server.db :as db]
            [psql-server.middleware :as middleware]))

(defroutes all-routes*
  (GET "/people" [] (db/get-people))
  (GET "/people/:id" [id :<< as-uuid] (db/get-person id))
  (POST "/echo" request (str request)))

(def all-routes
  (-> #'all-routes*
      (middleware/wrap-data)
      (middleware/wrap-content-type "application/json")
      (middleware/wrap-json)))

(defroutes routes
  (ANY "*" [] all-routes))
