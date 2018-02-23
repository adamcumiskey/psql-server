(ns user
  (:require [clojure.tools.namespace.repl :refer (refresh refresh-all)]
            [mount-up.core :refer [on-upndown log]]
            [psql-server.env :refer [env]]
            [psql-server.db :refer [db]])
  (:import com.mchange.v2.c3p0.ComboPooledDataSource))

;; Log mount state changes
(on-upndown :info log :before)

