(ns psql-server.auth
  (:require [buddy.sign.jwt :as jwt]
            [buddy.core.keys :as keys]
            [clj-time.core :as time]
            [environ.core :refer [env]]
            [psql-server.db :as db]))

(def rsa-private-key (keys/private-key "resources/rsa/private.pem" (env :ssh-password)))
(def rsa-public-key (keys/public-key "resources/rsa/public.pem"))

(defn generate-claim [user]
  (-> {:sub (:id user)
       :exp (time/plus (time/now) (time/days 1))
       :iat (time/now)}))

(defn sign [claim]
  (jwt/sign claim rsa-private-key {:alg :ps512}))

(defn validate-token [token]
  (jwt/unsign token rsa-public-key {:alg :ps512}))

(defn authorize [params]
  (let [user (db/user-by-email db/connection params)
        is_valid (:is_valid (db/validate-password db/connection params))]
    (if is_valid
      (-> (generate-claim user) (sign)))))
