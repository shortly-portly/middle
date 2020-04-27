(ns middle.middleware
  (:require [integrant.core :as ig]))

(defn aaa [handler]
  (fn [request]
    (println "aaa: Process Request")
    (let [response (handler request)]
      response)))

(defn bbb [handler]
  (fn [request]
    (println "bbb: Process Request")
    (let [response (handler request)]
      response)))

(defmethod ig/init-key :middle.middleware/global [_ _]
  (fn [handler]
    (fn [request]
      (println "Global: Process Request")
      (let [response (handler request)]
        (println "Global: Process Response")
        response))))

(defmethod ig/init-key :middle.middleware/secure [_ _]
  (fn [handler]
    (fn [request]
      (println "Secure: Process Request")
      (let [req (handler request)]
        (println "Secure: Process Response")
        req))))

(defmethod ig/init-key :middle.middleware/insecure [_ _]
  (fn [handler]
    (-> handler
        bbb
        aaa
        )))

(defn foo [handler]
  (fn [request]
    (println "foo: Process Request")
    (let [response (handler request)]
      (println "foo: Process Response")
      response)))

(defn bar [handler]
  (fn [request]
    (println "bar: Process Request")
    (let [response (handler request)]
      (println "bar: Process Response")
      response)))

(defmethod ig/init-key :middle.middleware/first [_ _]
  (fn [handler]
    (-> handler
        foo
        bar)))
