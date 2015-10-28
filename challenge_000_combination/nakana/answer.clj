; 直積
(for [x (range 1 4) y (range 1 4) z (range 1 4)] [x y z] )

;　組み合わせ
(for [x (range 1 4) y (range 1 4) z (range 1 4) :when (<= x y z)] [x y z] )

;　組み合わせ　別解
(set (map sort (for [x (range 1 4) y (range 1 4) z (range 1 4)] [x y z])))
