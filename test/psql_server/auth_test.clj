(ns psql-server.auth-test
  (:require [clojure.test :refer :all]
            [psql-server.auth :as auth]
            [psql-server.core-test :refer [start-db seed-test-data]]))

(use-fixtures :once start-db)
(use-fixtures :each seed-test-data)

(def adam {:email "adam.cumiskey@gmail.com" :password "password"})

(deftest authorize
  (testing "successful authorization returns a valid jwt"
    (let [token (auth/authorize adam)]
      (is (string? token))
      (is (not (nil? (auth/validate-token token)))))))
