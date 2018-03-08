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

(def adam {:first_name "Adam"
           :last_name "Cumiskey"
           :email "adam.cumiskey@gmail.com"
           :password "password"})
(def linda {:first_name "Linda"
           :last_name "Smith"
           :email "linda.smith@gmail.com"
           :password "gopherfan89"})
(def david {:first_name "David"
            :last_name "Jones"
            :email "david.jones@gmail.com"
            :password "starman"})

(deftest get-all-users
  (testing "get all users"
    (is (= 3
           (count (db/get-all-users db/connection))))))

(deftest get-user-by-email
  (testing "get user by email"
    (is (= (dissoc (db/user-by-email db/connection {:email "adam.cumiskey@gmail.com"}) :id :password_hash)
           (dissoc adam :password)))))

(deftest create-user
  (testing "create user"
    (let [user (try (db/insert-user db/connection david)
                    (catch java.sql.BatchUpdateException e
                      (throw (.getNextException e))))]
      (print user)
      (is (not (nil? (db/user-by-id db/connection user)))))))

(deftest create-users
  (testing "can batch create users"
    (db/insert-users db/connection {:users (map #(vals %) [linda david])})
    (is (= 5
           (count (db/get-all-users db/connection))))))

(deftest validate-password
  (testing "can validate a password"
    (is (= {:is_valid true}
           (db/validate-password db/connection {:email "adam.cumiskey@gmail.com"
                                                :password "password"}))))
  (testing "cannot validate invalid password"
    (is (= {:is_valid false}
           (db/validate-password db/connection {:email "adam.cumiskey@gmail.com"
                                                :password "jetsfan97"})))))

