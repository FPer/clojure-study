(ns clojure-iekei.core-test
  (:require [clojure.test :refer :all]
            [clojure-iekei.core :refer :all]))

(deftest step-test
  (testing "step"
    (is (= (step [0 1 2 3]) [0 2 3 0]))))

(deftest logical_table-test
  (testing "logical_table"
    (is (= (logical_table [0 1 2 3]) [0 1 2 3 0 1 2 3]))))

(deftest has_empty-test
  (testing "has empty"
    (is (= (has_empty [1 2 0 0 1 2 3] 2) true)))
  (testing "not has empty"
    (is (= (has_empty [1 2 0 0 1 2 3] 3) false)))
  )

(deftest logical_attache-test
  (testing "logical attache"
    (is (= (logical_attache [0 2 2 3 0 0], 3) [0 2 2 3 1 1 1 2 2 3 0 0]))))

(deftest attache-test
  (testing "attache"
    (is (= (attache [0 2 3 1 1 2 3 0]) [1 2 3 1]))))

(deftest enqueue-test
  (testing "enqueue"
    (is (= (enqueue [1 2 3 0] 3) [3 1 1 1]))))

(deftest status-test 
  (testing "status"
    (is (= (status [0 1 2 3 0 1 2 3]) "01110111"))))

(deftest comming-test
  (testing "comming"
    (is (= (comming "3316")) "11111110")
    (is (= (comming "3316")) "11111110")
    (is (= (comming "3342153")) "11111111")
    (is (= (comming "8")) "11111111")
    (is (= (comming "232624")) "11110110")
    (is (= (comming "1")) "10000000")
    (is (= (comming "224673432")) "11111000")
    (is (= (comming "123464334")) "11111110")
    (is (= (comming "44372332")) "11111111")
    (is (= (comming "2344")) "11111111")
    (is (= (comming "6448476233")) "11111100")
    (is (= (comming "4345174644")) "11111111")
    (is (= (comming "77743")) "11111110")
    (is (= (comming "2136524281")) "10000000")
    (is (= (comming "344373")) "11100000")
    (is (= (comming "434226")) "11111111")
    (is (= (comming "7433223")) "11111110")
    (is (= (comming "2246534")) "11110111")
    (is (= (comming "634")) "11111110")
    (is (= (comming "2222")) "11111100")
    (is (= (comming "2442343238")) "11111111")
    (is (= (comming "7243344")) "11111111")
    (is (= (comming "26147234")) "10111111")
    (is (= (comming "34424")) "10011111")
    (is (= (comming "6334")) "11011111")
    (is (= (comming "3828342")) "11011110")
    (is (= (comming "4431")) "11110000")
    (is (= (comming "2843212125")) "11111111")
    (is (= (comming "333336482")) "11000000")
    (is (= (comming "374")) "11110000")
    (is (= (comming "4382333")) "11100111")
  ))
