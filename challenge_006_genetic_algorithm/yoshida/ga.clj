(def data-num 10)
(def gene-num 10)
(def value-range 3)
(def dice-num 2)
(def answer [2 2 1 3 1 3 1 2 3 3])

(defn mark [gene]
  (assoc gene :SCORE )
)

(defn create-gene-data []
  (repeatedly data-num #(+ (rand-int value-range) 1))
)

(defn create-gene [data generation]
  {:DATA data, :GENERATION generation, :SCORE (reduce + (for [i (range 0 data-num)] (if (= (nth data i) (answer i)) 1 0)))}
)

(defn get-init-gene-data []
  (repeatedly gene-num #(create-gene (create-gene-data) 1))
)

(defn sort-gene [gene-list]
  (sort #(compare (:SCORE %2) (:SCORE %1)) gene-list)
)

(defn mutation-data [gene position]
  (let [data (nth gene position)]
    (if (= data 3) 1 (+ data 1))
  )
)

(defn mutation-gene [gene position]
  (assoc (vec gene) position (mutation-data gene position))
)

(defn mutation [gene]
  (let [position (reduce + (repeatedly dice-num #(+ (rand-int 6) 1)))]
    (if (= position 11) (mutation-gene gene 0)
      (if (= position 12) gene (mutation-gene gene (- position 1)))
    )
  )
)

(defn cross-over [position gene1 gene2]
  (mutation (concat (first (split-at position (:DATA gene1))) (second (split-at position (:DATA gene2)))))
)

(defn cross-over-list [gene-list generation]
  (let [position (reduce + (repeatedly dice-num #(+ (rand-int 6) 1)))]
    (cons (create-gene (cross-over position (second gene-list) (first gene-list)) generation)
      (cons (create-gene (cross-over position (first gene-list) (second gene-list)) generation) gene-list)
    )
  )
)

(defn print-gene [gene]
  (println gene)
  (if (= (:SCORE gene) data-num) (System/exit 0))
)

(defn print-gene-list [gene-list]
  (println "####################")
  (doseq [gene gene-list] (print-gene gene))
)


(defn main [gene-list generation]
  (print-gene-list gene-list)
  (let [new-generation (+ generation 1)]
    (main (cross-over-list (butlast (butlast (sort-gene gene-list))) new-generation) new-generation)
  )
)

(println (main (get-init-gene-data) 1))
