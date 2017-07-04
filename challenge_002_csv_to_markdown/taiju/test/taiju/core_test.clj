(ns taiju.core-test
  (:require [clojure.test :refer :all]
            [taiju.core :refer :all]))

(deftest test-convert-seq-into-md-table
  (testing "include header"
    (let [in [["見出し１" "見出し２" "見出し３"]
              ["11" "12" "13"]
              ["21" "22" "23"]
              ["31" "32" "33"]]
          out "|見出し１|見出し２|見出し３|
|:-:|:-:|:-:|
|11|12|13|
|21|22|23|
|31|32|33|
"]
      (is (= (convert-seq-into-md-table in) out))))
  (testing "not include header"
    (let [in [["見出し１" "見出し２" "見出し３"]
              ["11" "12" "13"]
              ["21" "22" "23"]
              ["31" "32" "33"]]
          out "|見出し１|見出し２|見出し３|
|11|12|13|
|21|22|23|
|31|32|33|
"]
     (is (= (convert-seq-into-md-table in :data-cell-only true) out)))))
