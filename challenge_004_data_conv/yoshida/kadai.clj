(def in [
  {:hosp "a", :data 1}
  {:hosp "a", :data 2}
  {:hosp "a", :data 3}
  {:hosp "b", :data 1}
  {:hosp "b", :data 2}
  {:hosp "b", :data 3}
  {:hosp "c", :data 1}
  {:hosp "c", :data 2}
  {:hosp "c", :data 3}
])

(defn add_value [out_element element]
  (if (= (:hosp out_element) (:hosp element))
    (assoc {:hosp (:hosp out_element)} :data (conj (:data out_element) (:data element)))
    out_element
  )
)

(defn convert [out element]
  (map #(add_value %1 element) out)
)

(def out (distinct (map #(assoc (select-keys % [:hosp]) :data []) in)))

(println (reduce #(convert %1 %2) out in))
