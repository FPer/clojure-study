(def in [
  {:id 1, :name "a", :order 1, :date "01/01", :item "aaa", :count 1, :amount 100}
  {:id 1, :name "a", :order 1, :date "01/01", :item "bbb", :count 2, :amount 200}
  {:id 1, :name "a", :order 1, :date "01/01", :item "ccc", :count 3, :amount 300}
  {:id 1, :name "a", :order 2, :date "01/02", :item "aaa", :count 1, :amount 100}
  {:id 1, :name "a", :order 2, :date "01/02", :item "bbb", :count 2, :amount 200}
  {:id 1, :name "a", :order 3, :date "01/03", :item "ccc", :count 3, :amount 300}
  {:id 2, :name "b", :order 4, :date "02/01", :item "aaa", :count 1, :amount 100}
  {:id 2, :name "b", :order 4, :date "02/01", :item "bbb", :count 2, :amount 200}
  {:id 3, :name "c", :order 5, :date "03/01", :item "ccc", :count 3, :amount 300}
])

(defn create-item [in-data]
 {:itemKey (:item in-data), :amount (:amount in-data), :count (:count in-data)}
)

(defn create-group [in-data]
 {:groupKey (:order in-data), :date (:date in-data), :groupValues []}
)

(defn create-ct [in-data]
  {:ctKey (:id in-data), :ctName (:name in-data), :ctValues []}
)

(defn get-data [result-data-list in-data in-data-key result-data-key func]
  (let [value (first (filter #(= (in-data in-data-key) (%1 result-data-key)) result-data-list))]
    (if (nil? value) (func in-data) value)
  )
)

(defn update-list-data [result-data-list in-data key]
  (conj (filter #(not= (in-data key) (%1 key)) result-data-list) in-data)
)

(defn convert [in-data out]
  (let [ct (get-data out in-data :id :ctKey create-ct), ctValues (:ctValues ct)]
    (let [group (get-data ctValues in-data :order :groupKey create-group), groupValues (:groupValues group)]
      (update-list-data out 
        (assoc ct :ctValues 
          (update-list-data ctValues (assoc group :groupValues (conj groupValues (create-item in-data))) :groupKey)
        )
        :ctKey
      )
    )
  )
)

(println (reduce #(convert %2 %1) [] in))
