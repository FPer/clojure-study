(ns fp-study.core
  (:use [clojure.java.io]
        [clojure.pprint :only [pprint]]
        [clojure.string :only [join]]))

(def data [
           ["見出し１" "見出し２" "見出し３"]
           ["11" "12" "13"]
           ["21" "22" "23"]
           ["31" "32" "33"]
           ])

(defn kugiri [n]
  (take n (repeat ":-:")))

(defn push-element-n [data n elm]
  (concat (subvec data 0 n) [elm] (subvec data n)))

(defn add-fence [elms]
  (str "|" (join "|" elms) "|"))

(defn markdownize [data]
  (join "\n" (map add-fence (push-element-n data 1 (kugiri (count (nth data 0)))))))

(defn markdownize2 [data]
  (-> (nth data 0)
      (count)
      (kugiri)
      (as-> x (push-element-n data 1 x))
      (as-> x (map add-fence x))
      (as-> x (join "\n" x))))

(defn markdownize3 [data]
  (-> (nth data 0)
      (count)
      (kugiri)
      (->> (push-element-n data 1)
           (map add-fence)
           (join "\n"))))

(defn -main
  [& args]
  (println (markdownize3 data)))
