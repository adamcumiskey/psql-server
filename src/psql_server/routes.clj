(ns psql-server.routes
  (:require [compojure.core :refer [defroutes GET POST ANY]]
            [compojure.coercions :refer [as-int]]
            [mount.core :refer [defstate]]
            [psql-server.db :as db]))

(defstate routes
  :start (defroutes r
           (GET "/users" request (db/get-all-users))
           (GET "/users/:id" [id :<< as-int] {:body (db/user-by-id {:id id})})
           (POST "/echo" request (str request))
           (GET "/health" [] "OK")))

