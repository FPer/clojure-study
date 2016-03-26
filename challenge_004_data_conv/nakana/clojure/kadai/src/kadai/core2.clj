(ns kadai.core2
  (:require [clojure.data.json :as json]))

(def data "[
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

(def result
  (->> (json/read-str data :key-fn clojure.core/keyword)
       (group-by :hosp)
       (reduce-kv (fn [m k v]
                    (conj m (assoc {} :hosp k :data (map :data v))))
                  [])
       (json/write-str)))





