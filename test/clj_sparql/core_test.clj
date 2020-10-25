(ns clj-sparql.core-test
  (:require [clojure.test :refer :all]
            [clj-sparql.core :refer :all])
  (:use clojure.pprint))

(deftest a-test
  (testing "FIXME."
    (is (= 0 0))))

(comment
  (deftest graphdb-test
  (testing "simple query")
  (pprint (graphdb "KBS" "select ?s ?p ?o { ?s ?p ?o } limit 20"))))

(deftest fuseki-test
  (testing "simple query")
  (pprint (fuseki "news" "select ?s ?p ?o { ?s ?p ?o } limit 20")))