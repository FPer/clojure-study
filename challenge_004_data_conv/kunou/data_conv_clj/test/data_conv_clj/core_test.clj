(ns data-conv-clj.core-test
  (:require [clojure.test :refer :all]
            [data-conv-clj.core :refer :all]))

(def input [
      {"hosp" "a", "data" 1},
      {"hosp" "a", "data" 2},
      {"hosp" "a", "data" 3},
      {"hosp" "b", "data" 1},
      {"hosp" "b", "data" 2},
      {"hosp" "b", "data" 3},
      {"hosp" "c", "data" 1},
      {"hosp" "c", "data" 2},
      {"hosp" "c", "data" 3}
  ])

(def expect [
      {"hosp" "a", "data" [1 2 3]},
      {"hosp" "b", "data" [1 2 3]},
      {"hosp" "c", "data" [1 2 3]}
  ])

(deftest to-map_test
  (testing "to map"
    (is (= (to-map {} {"hosp" "a", "data" 1})        {"a" [1]}))
    (is (= (to-map {"a" [1]} {"hosp" "b", "data" 1}) {"a" [1], "b" [1]}))
    (is (= (to-map {"a" [1], "b" [1]} {"hosp" "a", "data" 2}) {"a" [1 2], "b" [1]}))
  ))

(deftest convert-to-map_test
  (testing "convert to map"
    (is (= (convert-to-map input) expect))
  ))
