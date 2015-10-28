(loop [result [] xs (range 1 4)]
  (let [x (first xs)]
    (if (nil? x)
      result
      (recur (loop [result result ys (range 1 4)]
               (let [y (first ys)]
                 (if (nil? y)
                   result
                   (recur (loop [result result zs (range 1 4)]
                            (let [z (first zs)]
                              (if (nil? z)
                                result
                                (recur (cons [x y z] result) (rest zs)))))
                          (rest ys)))))
             (rest xs)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(loop [result [] xs (range 1 4)]
  (let [x (first xs)]
    (if (nil? x)
      result
      (recur (loop [result result ys (range x 4)]
               (let [y (first ys)]
                 (if (nil? y)
                   result
                   (recur (loop [result result zs (range y 4)]
                            (let [z (first zs)]
                              (if (nil? z)
                                result
                                (recur (cons [x y z] result) (rest zs)))))
                          (rest ys)))))
             (rest xs)))))
