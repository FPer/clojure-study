(ns taiju.core-test
  (:require [clojure.test :refer :all]
            [taiju.core]))

(deftest test1
  (testing "pure/concat"
    (is (= (taiju.core/concat nil nil) '()))
    (is (= (taiju.core/concat '(1 2) '(3 4)) '(1 2 3 4)))))

(deftest test2
  (testing "pure/reverse"
   (is (= (taiju.core/reverse '()) '()))
   (is (= (taiju.core/reverse '(1 2 3 4 5)) '(5 4 3 2 1)))))
