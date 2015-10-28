(defn add_first [list1 list2]
  (if (empty? list2) list1 (add_first (cons (first list2) list1) (rest list2)))
)

(defn reverse_yoshida [list]
  (add_first () list)
)

(defn concat_yoshida [list1 list2]
  (add_first (reverse_yoshida list2) (reverse_yoshida list1))
)
