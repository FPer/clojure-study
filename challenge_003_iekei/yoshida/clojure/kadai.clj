(use '[clojure.string :only (join)])

(defn next_seat_state [seat]
  (if (= seat 3) 0
    (if (> seat 0) (inc seat) seat)
  )
)

(defn next_seats_state [seats]
  (for [seat seats] (next_seat_state seat))
)

(defn get_seat_state [seat]
  (if (= seat 0) 0 1)
)

(defn get_seats_state [seats]
  (join (for [seat seats] (get_seat_state seat)))
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

(defn create_round_table [seats]
  (concat seats seats)
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
  (get_empty_seat_indexs_main customer_num [] (create_round_table seats) 0)
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

(println (main "3316"))
(println (main "3342153"))
(println (main "8"))
(println (main "232624"))
(println (main "1"))
(println (main "224673432"))
(println (main "123464334"))
(println (main "44372332"))
(println (main "2344"))
(println (main "6448476233"))
(println (main "4345174644"))
(println (main "77743"))
(println (main "2136524281"))
(println (main "344373"))
(println (main "434226"))
(println (main "7433223"))
(println (main "2246534"))
(println (main "634"))
(println (main "2222"))
(println (main "2442343238"))
(println (main "7243344"))
(println (main "26147234"))
(println (main "34424"))
(println (main "6334"))
(println (main "3828342"))
(println (main "4431"))
(println (main "2843212125"))
(println (main "333336482"))
(println (main "374"))
(println (main "4382333"))
