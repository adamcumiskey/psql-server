(ns psql-server.env
  (:require [mount.core :refer [defstate]]))

(defstate env
  :start {:port (Integer. (get (System/getenv) "PORT" "3000"))
          :clj-env (get (System/getenv) "CLJ_ENV" "dev")
          :jdbc-url (str "jdbc:postgresql://"
                         (get (System/getenv) "DB_HOST") ":"
                         (get (System/getenv) "DB_PORT") "/"
                         (get (System/getenv) "DB_NAME") "?"
                         "user=" (get (System/getenv) "POSTGRES_USER") "&"
                         "password=" (get (System/getenv) "POSTGRES_PASSWORD"))
          :db-url (get (System/getenv) "DB_URL" "db:5432/postgres")
          :db-name (get (System/getenv) "DB_NAME")
          :db-user (get (System/getenv) "POSTGRES_USER" "postgres")
          :db-password (get (System/getenv) "POSTGRES_PASSWORD" "")})
