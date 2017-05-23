(defn solution1 []
  (->
   (for [x (set '(1 2 3))
         y (set '(1 2 3))
         z (set '(1 2 3))]
     [x y z])
   sort))

(defn solution2 []
  (->>
   (solution1)
   (group-by sort)
   (keys)))

(solution1)
(solution2)
