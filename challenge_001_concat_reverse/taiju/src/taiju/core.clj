(ns taiju.core)

(def reverse
  (fn [s]
    (cond
      (nil? (first s)) '()
      :else (-reverse s '()))))

(def -reverse
  (fn [s1 s2]
    (cond
      (nil? (first s1)) s2
      :else (-reverse (rest s1) (cons (first s1) s2)))))

(def concat
  (fn [s1 s2]
    (-concat (reverse s1) s2)))

(def -concat
  (fn [s1 s2]
    (cond (nil? (first s2)) (reverse s1)
          :else (-concat (cons (first s2) s1) (rest s2)))))
