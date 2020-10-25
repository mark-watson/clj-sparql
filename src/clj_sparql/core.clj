(ns clj-sparql.core
  (:require [clj-http.client :as client]))

(require '[cemerick.url :refer (url url-encode)])
(require '[clojure.data.csv :as csv])

(defn- graphdb-helper [host port graph-name sparql-query]
  (let [q (str host ":" port "/repositories/" graph-name "?query=" (url-encode sparql-query))
        response (client/get q)
        body (:body response)]
    (csv/read-csv body)))

(defn graphdb
  ([graph-name sparql-query] (graphdb-helper "http://127.0.0.1" 7200 graph-name sparql-query))
  ([host port graph-name sparql-query] (graphdb-helper host port graph-name sparql-query)))

(defn- fuseki-helper [host port graph-name sparql-query]
  (let [q (str host ":" port "/" graph-name "/sparql?output=csv&query=" (url-encode sparql-query))
        _ (println q)
        response (client/get q)
        body (:body response)]
    (csv/read-csv body)))

(defn fuseki
  ([graph-name sparql-query] (fuseki-helper "http://127.0.0.1" 3030 graph-name sparql-query))
  ([host port graph-name sparql-query] (fuseki-helper host port graph-name sparql-query)))