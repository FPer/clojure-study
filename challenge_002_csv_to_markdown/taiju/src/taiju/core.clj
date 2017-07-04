(ns taiju.core
  (:require [clojure.pprint :refer [cl-format]]))

(defn- insert-separator
  [[header-cell & data-cells]]
  (apply list header-cell (repeat (count header-cell) ":-:") data-cells))

(defn convert-seq-into-md-table
  [xs & {:keys [data-cell-only]
         :or {:data-cell-only false}}]
  (->> (for [ys (if data-cell-only xs (insert-separator xs))]
         (cl-format nil "~{|~a~}|" ys))
       (cl-format nil "~{~a\n~}")))
