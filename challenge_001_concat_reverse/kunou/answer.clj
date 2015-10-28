(defn my_concat
  [src1 src2]
  (if (empty? (rest src1))
    (cons (first src1) src2)
    (cons (first src1) (my_concat (rest src1) src2))
  )
)

(defn my_reverse
  [src]
  (if (empty? (rest src))
    (list (first src))
    (my_concat (my_reverse(rest src)) (list (first src)))
  )
)
