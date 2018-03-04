(ns psql-server.db-test
  (:require [clojure.test :refer :all]
            [mount.core :as mount]
            [psql-server.db :as db]))

(defn setup-db [f]
  (mount/start)
  (db/seed "resources/seeds/test.up.sql")
  (f)
  (db/seed "resources/seeds/test.down.sql"))

(use-fixtures :once setup-db)

(deftest get-user-by-id
  (testing "get user by id"
    (is (= {:id 1
           :name "Adam Cumiskey"
           :email "adam.cumiskey@gmail.com"}
          (db/user-by-id {:id 1})))))

(deftest get-all-users
  (testing "get all users"
    (is (= [{:id 1
             :name "Adam Cumiskey"
             :email "adam.cumiskey@gmail.com"}
            {:id 2
             :name "John Smith"
             :email "john.smith@gmail.com"}
            {:id 3
             :name "Jane Doe"
             :email "jane.doe@gmail.com"}]
           (db/get-all-users)))))
