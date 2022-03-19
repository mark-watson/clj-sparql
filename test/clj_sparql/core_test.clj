(ns clj-sparql.core-test
  (:require [clojure.test :refer :all]
            [clj-sparql.core :refer :all]
            [clojure.pprint :use pprint]))
;;  (:use clojure.pprint))

(comment

  (deftest graphdb-test
    (testing "GraphDB query")
    (pprint (graphdb "KBS" "select ?s ?p ?o { ?s ?p ?o } limit 20")))

  (deftest fuseki-test
    (testing "Fuseki query")
    (pprint (fuseki "news" "select ?s ?p ?o { ?s ?p ?o } limit 20")))

  (deftest dbpedia-test
    (testing "DBPedia query")
    (pprint (dbpedia "select ?s ?p ?o { ?s ?p ?o } limit 20")))

  (deftest wikidata-test
    (testing "WikiDaa query")
    (pprint (wikidata "select ?s ?p ?o { ?s ?p ?o } limit 5")))
  (deftest agraph-test
    (testing "AllegroGraph query")
    (pprint (agraph "news" "select ?s ?p ?o { ?s ?p ?o } limit 20")))
  )
<<<<<<< HEAD
=======
  

>>>>>>> 2c8146c72d49ad8a1964eb7e37fd2b6cb6386849
