(ns csv-to-markdonw.core)

(def input [
           ["見出し１" "見出し２" "見出し３"]
           ["11" "12" "13"]
           ["21" "22" "23"]
           ["31" "32" "33"]
           ])

(defn join
  [cols, separator]
  (str
    (first cols)
    (apply str
      (map
        (fn [x] (str separator x))
        (rest cols)))))

(defn table_row
  [cols]
  (str "|" (join cols "|") "|"))

(defn separator [size]
  ((fn gen_separators [size]
    (if (> size 0)
      (cons ":-:" (gen_separators (- size 1)))
      []))
  size))

(defn to_mkdown
  [input & {:keys [heading] :or {heading true}}]
  (join
    (if heading
      (map table_row
        (concat
          (list (first input))
          (list (separator (count (first input))))
          (rest input)))
      (map table_row input))
    "\n"))
