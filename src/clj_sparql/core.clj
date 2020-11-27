(ns clj-sparql.core
  (:require [clj-http.client :as client])
  (:require [clojure.data.json :as json]))

(use '[clojure.java.shell :only [sh]])

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

(defn dbpedia [sparql-query]
  (let [q (str "https://dbpedia.org//sparql?output=csv&query=" (url-encode sparql-query))
        _ (println q)
        response (client/get q)
        body (:body response)]
    (csv/read-csv body)))

(defn wikidata [sparql-query]
  "note: WikiData currently does not return /text/csv values, even when requested"
  (let [q (str "http://query.wikidata.org/bigdata/namespace/wdq/sparql?query=" (url-encode sparql-query))
        response (client/get q {:accept "application/sparql-results+json"})
        body (json/read-str (:body response))
        vars ((body "head") "vars")
        values (map
                 (fn [x]
                   (for [v vars]
                     ((x v) "value")))
                 ((body "results") "bindings"))]
    (cons vars values)))

(defn- agraph-helper [user passwd host port graph-name sparql-query]
  (let [q
        (str user ":" passwd "@" host ":" port "/repositories/" graph-name "?accept=text/csv&query="
             (.replaceAll
               (url-encode sparql-query) "%20" "+"))
        response (:out (sh "curl" q "-s")) ]
    (csv/read-csv response)))

(defn agraph
  ([graph-name sparql-query] (agraph-helper "mark" "mark" "127.0.0.1" 10035 graph-name sparql-query))
  ([user passwd host port graph-name sparql-query] (agraph-helper user passwd host port graph-name sparql-query)))

