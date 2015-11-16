(ns nakana.core-test
  (:require [clojure.test :refer :all]
            [nakana.core :refer :all]
            [clojure.tools.namespace.repl :only (refresh)]))


(deftest unit-test
  (testing
   (testing "num-vec"
     (are
         [str vec]
         (= (num-vec str) vec)
       "123" [1 2 3]
       "234" [2 3 4]))

   (testing "num-vec-stringize"
     (are
         [vec expected]
         (= (num-vec-stringize vec) expected)
       [1 2 3] "123"
       ))

   (testing "binarize"
     (are
         [vec expected]
         (= (binarize vec) expected)
       [0 0 0] [0 0 0]
       [1 2 3] [1 1 1]
       ))
   
   (testing "double-table"
     (are
         [test expected]
         (= (double-table test) expected)
       
       [0 1 2 3 4 5 6 7]
       [[0 0] [1 1] [2 2] [3 3] [4 4] [5 5] [6 6] [7 7]
        [0 0] [1 1] [2 2] [3 3] [4 4] [5 5] [6 6] [7 7]]
       
       ["s0" "s1" "s2" "s3" "s4" "s5" "s6" "s7"]
       [["s0" 0] ["s1" 1] ["s2" 2] ["s3" 3] ["s4" 4] ["s5" 5] ["s6" 6] ["s7" 7]
        ["s0" 0] ["s1" 1] ["s2" 2] ["s3" 3] ["s4" 4] ["s5" 5] ["s6" 6] ["s7" 7]]
       ))

   (testing "select-seat-from-list"
     (are
         [data grpnum expected]
         (= (select-seat-from-list data grpnum) expected)
       [3 4] 2 [3 4]
       [5 6 7 0 1] 2 [0 1]
       [5 6 7 0 1] 3 [5 6 7]
       ))

   (testing "find-seats"
     (are
         [table-lst grpnum expected]
         (= (find-seats table-lst grpnum) expected)

       [0 0 0 0 0 0 0 0] 3 [0 1 2]
       [0 0 1 0 0 0 0 0] 3 [3 4 5]
       [1 1 1 1 1 1 1 1] 3 [99 0]
       ))

   (testing "sitdown"
     (are
         [table-lst seats expected]
         (= (sitdown table-lst seats) expected)

       [0 0 0 0 0 0 0 0] [0 1 2] [1 1 1 0 0 0 0 0]
       [0 0 0 0 0 0 0 1] [0 1 2] [1 1 1 0 0 0 0 1]
       ))

   
   (testing "do-one-step"
     (are
         [table-lst grpnum expected]
         (= (do-one-step table-lst grpnum) expected)

       [0 0 0 0 0 0 0 0] 3 [[1 1 1 0 0 0 0 0] true]
       [1 1 1 1 1 1 1 1] 3 [[2 2 2 2 2 2 2 2] false]
       ))


   (testing "do-until-sitdown"
     (are
         [table-lst grpnum expected]
         (= (do-until-sitdown table-lst grpnum) expected)

       [0 0 0 0 0 0 0 0] 3 [1 1 1 0 0 0 0 0]
       [1 1 1 1 1 1 1 1] 3 [1 1 1 0 0 0 0 0]
       [1 2 2 2 1 1 1 1] 3 [3 1 1 1 3 3 3 3]
       ))
   ))




(deftest senario-test
  (testing "test...."
    (are
        [waiting expected]
        (= (process waiting) expected)
      "3316"       "11111110"
      "3342153"    "11111111"
      "8"          "11111111"
      "232624"     "11110110"
      "1"          "10000000"
      "224673432"  "11111000"
      "123464334"  "11111110"
      "44372332"   "11111111"
      "2344"       "11111111"
      "6448476233" "11111100"
      "4345174644" "11111111"
      "77743"      "11111110"
      "2136524281" "10000000"
      "344373"     "11100000"
      "434226"     "11111111"
      "7433223"    "11111110"
      "2246534"    "11110111"
      "634"        "11111110"
      "2222"       "11111100"
      "2442343238" "11111111"
      "7243344"    "11111111"
      "26147234"   "10111111"
      "34424"      "10011111"
      "6334"       "11011111"
      "3828342"    "11011110"
      "4431"       "11110000"
      "2843212125" "11111111"
      "333336482"  "11000000"
      "374"        "11110000"
      "4382333"    "11100111")))

          



            




