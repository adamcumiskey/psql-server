(ns psql-server.env)

(def port (Integer. (get (System/getenv) "PORT" "3000")))
(def clj-env (get (System/getenv) "CLJ_ENV" "dev"))
(def db-url (get (System/getenv) "DB_URL" "postgresql://db:5432/postgres"))
(def db-user (get (System/getenv) "POSTGRES_USER" "postgres"))
(def db-password (get (System/getenv) "POSTGRES_PASSWORD" ""))
