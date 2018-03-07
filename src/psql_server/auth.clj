(ns psql-server.auth
  (:require [clj-jwt.core :refer :all]
            [clj-jwt.key :refer [private-key public-key]]
            [clj-time.core :refer [now plus days]]
            [environ.core :refer [env]]
            [psql-server.db :as db]))

(defn get-rsa-private-key [] (private-key "resources/rsa/private.pem" (env :ssh-password)))
(defn get-rsa-public-key [] (public-key "resources/rsa/public.pem" (env :ssh-password)))

(defn authorize [email password]
  (let [user (db/user-by-email {:email email})]
    (println user)))
