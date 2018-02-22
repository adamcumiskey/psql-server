(ns user
  (:require [clojure.tools.namespace.repl :refer (refresh refresh-all)]
            [mount.core :as mount]
            [mount.tools.graph :refer [states-with-deps]]
            [mount-up.core :refer [on-upndown log]]
            [psql-server.db]
            [psql-server.server]))

(on-upndown :info log :before)
