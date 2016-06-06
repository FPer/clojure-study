(ns tree-clj.core-test
  (:require [clojure.test :refer :all]
            [tree-clj.core :refer :all]))

(def in {:id 1, :name "a", :order 1, :date "01/01", :item "aaa", :count 1, :amount 100})

(def tree1 {1 {:id 1, :attr {:name "a"}, :children {1 {:id 1, :attr {:date "01/01"}, :children {"aaa" {:id "aaa", :attr {:count 1, :amount 100}}}}}}})
(def tree2 {1 {:id 1, :attr {:name "a"}, :children {1 {:id 1, :attr {:date "01/01"}, :children {"bbb" {:id "bbb", :attr {:count 2, :amount 200}}}}}}})
(def tree3 {1 {:id 1, :attr {:name "a"}, :children {1 {:id 1, :attr {:date "01/01"}, :children {"aaa" {:id "aaa", :attr {:count 1, :amount 100}}, "bbb" {:id "bbb", :attr {:count 2, :amount 200}}}}}}})

(deftest line2tree-test
  (testing "line2tree"
    (is (= (line2tree in) tree1))))

(deftest merge-tree-test
  (testing "merge-tree"
    (is (= (merge-tree nil {"bbb" {:id "bbb", :attr {:count 2, :amount 200}}}) {"bbb"  {:id "bbb", :attr {:count 2, :amount 200}}}))

    (is (= (merge-tree {"aaa" {:id "aaa", :attr {:count 1, :amount 100}}} {"bbb" {:id "bbb", :attr {:count 2, :amount 200}}}) {"aaa" {:id "aaa", :attr {:count 1, :amount 100}}, "bbb"  {:id "bbb", :attr {:count 2, :amount 200}}}))

    (is (= (merge-tree
            {1 {:id 1, :attr {:date "01/01"}, :children {"aaa" {:id "aaa", :attr {:count 1, :amount 100}}}}}
            {1 {:id 1, :attr {:date "01/01"}, :children {"bbb" {:id "bbb", :attr {:count 2, :amount 200}}}}})
            {1 {:id 1, :attr {:date "01/01"}, :children {"aaa" {:id "aaa", :attr {:count 1, :amount 100}}, "bbb" {:id "bbb", :attr {:count 2, :amount 200}}}}}))

    (is (= (merge-tree
            {9 {:id 9, :attr {:name "a"}, :children {1 {:id 1, :attr {:date "01/01"}, :children {"aaa" {:id "aaa", :attr {:count 1, :amount 100}}}}}}}
            {9 {:id 9, :attr {:name "a"}, :children {1 {:id 1, :attr {:date "01/01"}, :children {"bbb" {:id "bbb", :attr {:count 2, :amount 200}}}}}}})
            {9 {:id 9, :attr {:name "a"}, :children {1 {:id 1, :attr {:date "01/01"}, :children {"aaa" {:id "aaa", :attr {:count 1, :amount 100}}, "bbb" {:id "bbb", :attr {:count 2, :amount 200}}}}}}}))
    
    (is (= (merge-tree
      {1 {:id 1, :attr {:name "a"}, :children {1 {:id 1, :attr {:date "01/01"}, :children {"aaa" {:id "aaa", :attr {:count 1, :amount 100}}, "bbb" {:id "bbb", :attr {:count 2, :amount 200}}}}}}}
      {1 {:id 1, :attr {:name "a"}, :children {2 {:id 2, :attr {:date "01/02"}, :children {"aaa" {:id "aaa", :attr {:count 1, :amount 100}}}}}}})
      {1 {:id 1, :attr {:name "a"}, :children {
        1 {:id 1, :attr {:date "01/01"}, :children {"aaa" {:id "aaa", :attr {:count 1, :amount 100}}, "bbb" {:id "bbb", :attr {:count 2, :amount 200}}}}, 
        2 {:id 2, :attr {:date "01/02"}, :children {"aaa" {:id "aaa", :attr {:count 1, :amount 100}}}}
      }}}))
  ))

(deftest convert-test
  (testing "convert"
    (is (= (convert {1 {:id 1, :attr {:name "a"}}}) `({:ctKey 1, :ctName "a", :ctValues []})))
    (is (= (convert {1 {:id 1, :attr {:name "a"}}, :2 {:id 2, :attr{:name "b"}}}) `({:ctKey 1, :ctName "a", :ctValues []}, {:ctKey 2, :ctName "b", :ctValues []})))
    (is (= (convert {1 {:id 1, :attr {:name "a"}, :children {1 {:id 1, :attr{:date "01/01"}}}}}) `({:ctKey 1, :ctName "a", :ctValues [{:groupKey 1, :date "01/01", :groupValues []}]})))
    (is (= (convert {1 {:id 1, :attr {:name "a"}, :children {1 {:id 1, :attr{:date "01/01"}, :children {"aaa" {:id "aaa", :attr{:amount 100, :count 1}}}}}}}) `({:ctKey 1, :ctName "a", :ctValues [{:groupKey 1, :date "01/01", :groupValues [{:itemKey "aaa", :amount 100, :count 1}]}]})))
  ))

(deftest convert-tree-test
  (testing "convert-tree"
    (is (= (convert-tree [
      {:id 1, :name "a", :order 1, :date "01/01", :item "aaa", :count 1, :amount 100}
      {:id 1, :name "a", :order 1, :date "01/01", :item "bbb", :count 2, :amount 200}
    ]) 
      [{:ctKey 1, :ctName "a", :ctValues [{:groupKey 1, :date "01/01", :groupValues [{:itemKey "aaa", :amount 100, :count 1}, {:itemKey "bbb", :amount 200, :count 2}]}]}]))
  ))

