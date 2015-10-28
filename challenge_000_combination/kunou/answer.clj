(for [x [1 2 3] y [1 2 3] z[1 2 3]] [x y z])

(for [x [1 2 3] y [1 2 3] z[1 2 3] :when (>= z y x)] [x y z])
