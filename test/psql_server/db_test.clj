(ns psql-server.db-test
  (:require [clojure.test :refer :all]
            [conman.core :as conman]
            [mount.core :as mount]
            [psql-server.db :as db]))

(defn start-db [f]
  (mount/start)
  (f)
  (mount/stop))

(defn seed-data [f]
  (conman/with-transaction [db/connection]
    (db/seed "resources/seeds/test.down.sql" db/connection)
    (db/seed "resources/seeds/test.up.sql" db/connection)
    (f)
    (db/seed "resources/seeds/test.down.sql" db/connection)))

(use-fixtures :once start-db)
(use-fixtures :each seed-data)

; (deftest get-user-by-id
;   (testing "get user by id"
;     (is (= {:id 1
;             :name "Adam Cumiskey"
;             :email "adam.cumiskey@gmail.com"}
;            (db/user-by-id {:id 1})))))

; (deftest get-all-users
;   (testing "get all users"
;     (is (= [{:id 1
;              :name "Adam Cumiskey"
;              :email "adam.cumiskey@gmail.com"}
;             {:id 2
;              :name "John Smith"
;              :email "john.smith@gmail.com"}
;             {:id 3
;              :name "Jane Doe"
;              :email "jane.doe@gmail.com"}]
;            (db/get-all-users)))))

; (deftest create-user
;   (testing "create user"
;     (let [user {:name "Dave Jones" :email "dave.jones@gmail.com"}
;           expected (assoc user :id 4)]
;       (db/insert-user user)
;       (is (= expected
;              (db/user-by-id {:id 4})))
;       (is (not= -1
;                 (.indexOf (db/get-all-users)
;                           expected))))))
