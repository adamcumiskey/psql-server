(ns psql-server.auth
  (:require [clj-jwt.core :refer :all]
            [clj-jwt.key :refer [private-key public-key]]
            [clj-time.core :refer [now plus days]]
            [environ.core :refer [env]]))

(def rsa-private-key (private-key "rsa/private.pem" (env :ssh-password)))
(def rsa-public-key (public-key "rsa/public.pem" (env :ssh-password)))
