(ns kadai.core)

(def data [
           {:hosp "a" :data  1 }
           {:hosp "a" :data  2 }
           {:hosp "a" :data  3 }
           {:hosp "b" :data  1 }
           {:hosp "b" :data  2 }
           {:hosp "b" :data  3 }
           {:hosp "c" :data  1 }
           {:hosp "c" :data  2 }
           {:hosp "c" :data  3 }
           ])

;; (def tmp (group-by :hosp data))
;; (def result
;;   (reduce-kv (fn [m k v]
;;                (conj m (assoc {} :hosp k :data (map :data v))))
;;              []
;;              tmp))

(def result
  (->> data
       (group-by :hosp)
       (reduce-kv (fn [m k v]
                    (conj m (assoc {} :hosp k :data (map :data v))))
                  []
                  )))

(def tmp "[
	{\"hosp\" : \"a\" , \"data\": 1},
	{\"hosp\" : \"a\" , \"data\": 2},
	{\"hosp\" : \"a\" , \"data\": 3},
	{\"hosp\" : \"b\" , \"data\": 1},
	{\"hosp\" : \"b\" , \"data\": 2},
	{\"hosp\" : \"b\" , \"data\": 3},
	{\"hosp\" : \"c\" , \"data\": 1},
	{\"hosp\" : \"c\" , \"data\": 2},
	{\"hosp\" : \"c\" , \"data\": 3}
]")



