(use '[clojure.string :only (join)])

(defn next_seats_state [seats]
  (map #(if (= %1 3) 0 (if (> %1 0) (inc %1) %1)) seats)
)

(defn get_seats_state [seats]
  (join (map #(if (zero? %1) 0 1) seats))
)

(defn sit_down [empty_indexs seats]
  (reduce #(assoc %1 %2 1) (vec seats) (vec empty_indexs))
)

(defn get_empty_seat_indexs_main [customer_num round_table empty_seat_indexs index]
  (if (= customer_num (count empty_seat_indexs)) empty_seat_indexs
    (if (zero? (nth round_table index)) (cons (mod index 8) empty_seat_indexs) [])
  )
)

(defn get_empty_seat_indexs [customer_num seats]
  (reduce #(get_empty_seat_indexs_main customer_num (concat seats seats) %1 %2) [] (range 0 16))
)

(defn visit [seats customer_num]
  (if (not= ( count (get_empty_seat_indexs customer_num (next_seats_state seats))) customer_num)
    (visit (next_seats_state seats) customer_num)
    (sit_down (get_empty_seat_indexs customer_num (next_seats_state seats)) (next_seats_state seats))
  )
)

(defn main [customer_nums]
  (get_seats_state (reduce #(visit %1 (Character/getNumericValue %2)) [0 0 0 0 0 0 0 0] (vec customer_nums)))
)

(defn assert_equals [expect result]
  (if (= expect result) "OK" "NG")
)

(println (assert_equals "11111110" (main "3316")))
(println (assert_equals "11111111" (main "3342153")))
(println (assert_equals "11111111" (main "8")))
(println (assert_equals "11110110" (main "232624")))
(println (assert_equals "10000000" (main "1")))
(println (assert_equals "11111000" (main "224673432")))
(println (assert_equals "11111110" (main "123464334")))
(println (assert_equals "11111111" (main "44372332")))
(println (assert_equals "11111111" (main "2344")))
(println (assert_equals "11111100" (main "6448476233")))
(println (assert_equals "11111111" (main "4345174644")))
(println (assert_equals "11111110" (main "77743")))
(println (assert_equals "10000000" (main "2136524281")))
(println (assert_equals "11100000" (main "344373")))
(println (assert_equals "11111111" (main "434226")))
(println (assert_equals "11111110" (main "7433223")))
(println (assert_equals "11110111" (main "2246534")))
(println (assert_equals "11111110" (main "634")))
(println (assert_equals "11111100" (main "2222")))
(println (assert_equals "11111111" (main "2442343238")))
(println (assert_equals "11111111" (main "7243344")))
(println (assert_equals "10111111" (main "26147234")))
(println (assert_equals "10011111" (main "34424")))
(println (assert_equals "11011111" (main "6334")))
(println (assert_equals "11011110" (main "3828342")))
(println (assert_equals "11110000" (main "4431")))
(println (assert_equals "11111111" (main "2843212125")))
(println (assert_equals "11000000" (main "333336482")))
(println (assert_equals "11110000" (main "374")))
(println (assert_equals "11100111" (main "4382333")))
