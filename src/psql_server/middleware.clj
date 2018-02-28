(ns psql-server.middleware
  (:require [clojure.data.json :as json]))

(extend-type java.sql.Timestamp
  json/JSONWriter
  (-write [date out]
    (json/-write (str date) out)))

(defn content-type-response [response content-type]
  (assoc-in response [:headers "Content-Type"] content-type))
(defn wrap-content-type [handler content-type]
  (fn
    ([request]
     (-> (handler request) (content-type-response content-type)))
    ([request respond raise]
     (-> (handler request #(respond (content-type-response % content-type)) raise)))))

(defn json-response [response]
  (assoc response :body (json/write-str (:body response))))
(defn wrap-json [handler]
  (fn
    ([request]
     (-> (handler request) (json-response)))
    ([request respond raise]
     (-> (handler request #(respond (json-response %)) raise)))))

(defn data-response [response]
  (assoc response :body {:data (:body response)}))
(defn wrap-data [handler]
  (fn
    ([request]
     (-> (handler request) (data-response)))
    ([request respond raise]
     (-> (handler request #(respond (data-response %)) raise)))))

