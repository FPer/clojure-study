(use '[clojure.string :only (join)])

(defn next_seats_state [seats]
  (for [seat seats]
    (if (= seat 3) 0
      (if (> seat 0) (inc seat) seat)
    )
  )
)

(defn get_seats_state [seats]
  (join 
    (for [seat seats]
      (if (= seat 0) 0 1)
    )
  )
)

(defn contains_index? [empty_indexs index]
  (if (empty? empty_indexs) false
    (if (= (first empty_indexs) index)
      true
      (contains_index? (rest empty_indexs) index)
    )
  )
)

(defn sit_down [empty_indexs seats]
  (for [i (range 0 (count seats))]
    (if (contains_index? empty_indexs i) 1 (nth seats i))
  )
)

(defn get_empty_seat_indexs_main [customer_num empty_seat_indexs round_table index]
  (if (= index (count round_table)) '()
    (if (= customer_num (count empty_seat_indexs)) empty_seat_indexs 
      (get_empty_seat_indexs_main 
        customer_num 
        (if (= (nth round_table index) 0) (cons (mod index 8) empty_seat_indexs) [])
        round_table
        (inc index)
      )
    )
  )
)

(defn get_empty_seat_indexs [customer_num seats]
  (get_empty_seat_indexs_main customer_num [] (concat seats seats) 0)
)

(defn visit_main [customer_num seats]

  (if (empty? (get_empty_seat_indexs customer_num (next_seats_state seats)))
    (visit_main customer_num (next_seats_state seats))
    (sit_down (get_empty_seat_indexs customer_num (next_seats_state seats)) (next_seats_state seats))
  )

)

(defn visit [customer_nums seats]
  (if (empty? customer_nums) seats
    (visit (rest customer_nums) (visit_main (first customer_nums) seats))
  )
)

(defn convert_int [str]
  (for [s (seq str)]
    (Character/getNumericValue s)
  )
)

(defn main [customer_nums]
  (get_seats_state (visit (convert_int customer_nums) '(0 0 0 0 0 0 0 0)))
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
