(ns data-conv-clj.core)

(defn to-map
  [result-map data]
  (let
    [
      key            (data "hosp"),
      value          (data "data"),
      data-container (vec (result-map key))
    ]
    (assoc result-map key (conj data-container value))
  ))

(defn convert-to-map
  [input]
  (->>
      (reduce to-map (sorted-map) input)
      (map (fn [data] {"hosp" (first data), "data" (second data)}))
      (vec)
  ))
