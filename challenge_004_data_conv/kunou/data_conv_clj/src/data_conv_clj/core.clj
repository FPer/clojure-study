(ns data-conv-clj.core)

(defn to-map
  [result-map data]
  (let [key (data "hosp"), value (data "data")]
    (assoc result-map key (conj (vec (result-map key)) value))))

(defn convert-to-map
  [input]
  (->>
      (reduce to-map (sorted-map) input)
      (map (fn [data] {"hosp" (first data), "data" (second data)}))
      (vec)))
