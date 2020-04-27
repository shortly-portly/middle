(ns middle.handler.middle
  (:require [ataraxy.core :as ataraxy]
            [ataraxy.response :as response]
            [integrant.core :as ig]))

(defmethod ig/init-key :middle.handler.middle/index [_ _]
  (fn [_]
    (println "Index: Process Req/Response")
    [::response/ok "Hi from Index"]))
