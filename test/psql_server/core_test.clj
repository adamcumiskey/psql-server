(ns psql-server.core-test
  (:require [clojure.test :refer :all]
            [conman.core :as conman]
            [mount.core :as mount]
            [psql-server.core :refer :all]
            [psql-server.db :as db]))

(defn start-db [f]
  (mount/start-without #'psql-server.server/server)
  (f)
  (mount/stop))

(defn seed-test-data [f]
  (conman/with-transaction [db/connection]
    (db/seed "resources/seeds/test.down.sql" db/connection)
    (db/seed "resources/seeds/test.up.sql" db/connection)
    (f)
    (db/seed "resources/seeds/test.down.sql" db/connection)))
