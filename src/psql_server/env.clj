(ns psql-server.env
  (:require [mount.core :refer [defstate]]))

(defstate env
  :start {:port (Integer. (get (System/getenv) "PORT" "3000"))
          :clj-env (get (System/getenv) "CLJ_ENV" "dev"))
          :db-url (get (System/getenv) "DB_URL" "postgresql://db:5432/postgres")
          :db-user (get (System/getenv) "POSTGRES_USER" "postgres")
          :db-password (get (System/getenv) "POSTGRES_PASSWORD" "")})
