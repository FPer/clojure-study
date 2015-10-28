(use '[clojure.string :only (join)])

(defn covert_markdown [data]
  (join "\n" 
    (for [i (range 0 (count data))] 
      (str "|" (join "|" (data i)) "|")
    )
  )
)

(defn insert_second_row [data]
  (into [] (cons (first data) (cons (repeat (count (data 0)) ":-:") (rest data))))
)

(defn main_covert_markdown [data isHeader]
  (if (true? isHeader)
    (covert_markdown (insert_second_row data))
    (covert_markdown data)
  )
)



(def data1 [
           ["見出し１" "見出し２" "見出し３"]
           ["11" "12" "13"]
           ["21" "22" "23"]
           ["31" "32" "33"]
           ])

(def data2 [
           ["見出し１" "見出し２" "見出し３" "見出し４" "見出し５"]
           ["11" "12" "13" "14" "15"]
           ["21" "22" "23" "24" "25"]
           ["31" "32" "33" "34" "35"]
           ["41" "42" "43" "44" "45"]
           ["51" "52" "53" "54" "55"]
           ])

(println (main_covert_markdown data1 true))
(println (main_covert_markdown data1 false))
(println (main_covert_markdown data2 true))
(println (main_covert_markdown data2 false))
