(ns tree-clj.core)

(use 'clojure.pprint)

(defn line2tree
  "convert line to tree"
  [line]
  {
    (:id line)
    {
      :id (:id line),
      :attr {:name (:name line)},
      :children {
        (:order line) {
          :id (:order line),
          :attr {:date (:date line)},
          :children {
            (:item line) {
              :id (:item line),
              :attr {:count (:count line), :amount (:amount line)}
            }
          }
        }
      }
    }
  })

(defn nil-to-empty-map
  [map]
  (if (map? map) map {}))

(defn merge-tree
  [left right]
  (let [r-element (first (vals right)),
        r-id (:id r-element),
        l-element ((nil-to-empty-map left) r-id)]
    (if (contains? r-element :children)
      (assoc left r-id {:id r-id :attr (:attr r-element) :children (merge-tree (:children l-element) (:children r-element))})
      (assoc left r-id r-element))))

(defn convert-item
  [node]
    (let [values (vals node)]
      (map (fn [val] {:itemKey (:id val), :amount (:amount (:attr val)), :count, (:count (:attr val))}) values)))

(defn convert-group
  [node]
    (let [values (vals node)]
      (map (fn [val] {:groupKey (:id val), :date (:date (:attr val)), :groupValues (vec (convert-item (:children val)))}) values)))
  
(defn convert
  [tree]
    (let [values (vals tree)]
      (map (fn [val] {:ctKey (:id val), :ctName (:name (:attr val)), :ctValues (vec (convert-group (:children val)))}) values)))

(defn convert-tree
  [input]
    (->>
      (map line2tree input)
      (reduce merge-tree {})
      (convert)))
