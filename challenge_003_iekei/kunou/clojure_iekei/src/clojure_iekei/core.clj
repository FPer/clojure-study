(ns clojure-iekei.core)
(use '[clojure.string :only (join replace-first split)])

(defn step
  [table]
  (map #(if (or (= %1 1) (= %1 4)) 0 %1) (map inc table)))

(defn logical_table
  [table]
  (concat table table))

(defn has_empty
  [table num]
  (if (nil?
    (re-find
      (re-pattern (join (repeat num "0")))
      (join (logical_table table))))
    false true))

(defn logical_attache
  [table num]
  (map
    #(Integer/parseInt %1)
    (split
      (replace-first
        (join (logical_table table))
        (join (repeat num "0"))
        (join (repeat num "1")))
      #"")))
  
(defn attache 
  [table]
  (map 
    #(if (= 1 (second %1))
       1
       (first %1))
    (apply map vector (split-at (/ (count table) 2) table))))

(defn enqueue
  [table num]
  (#(if (has_empty %1 %2)
    (attache (logical_attache %1 %2))
    (enqueue %1 %2))
  (step table) num))

(defn status
  [table]
  (join (map #(if (= %1 0) %1 1) table)))

(defn comming
  [customers]
  (fn handle
    [customers table]
    (if (> (count customers) 0)
      (handle (rest customers) (enqueue table (first customers)))
      (status table)))
  (map #(Integer/parseInt %1) (split customers #""))
  (repeat 8 0))
