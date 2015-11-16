(ns nakana.core-test
  (:use clojure.core)
  (:use clojure.test))

(defn say[n] 1)

(is (= 1 (say 1)))


