(ns nakana.core
  (:use [clojure.java.io]
        [clojure.pprint :only [pprint]]
        [clojure.string :only [join]]))

(defn num-vec [str]
  (map #(- (int %) (int \0)) (seq str)))

(defn num-vec-stringize [vec]
  (clojure.string/join (map str vec)))

(defn binarize [vec]
  (map #(if (= 0 %) 0 1) vec))


(defn time-pass-by' [status]
  (cond
    (= status 0) 0
    (= status 1) 2
    (= status 2) 3
    (= status 3) 0))

(defn time-pass-by [table-lst]
  (map time-pass-by' table-lst))


(defn double-table [table-lst]
"
[s0 s1 s2 s3 s4 s5 s6 s7]
-> [[s0 0] [s1 1] [s2 2] [s3 3] [s4 4] [s5 5] [s6 6] [s7 7]
    [s0 0] [s1 1] [s2 2] [s3 3] [s4 4] [s5 5] [s6 6] [s7 7]]
"
  (map vector
      (take 16 (cycle table-lst))
      (take 16 (cycle (range 0 8)))))


(defn select-seat-from-list [data grpnum]
" 
0が含まれないリストの場合は、先頭の座席番号を返す。
0が含まれる0以降の要素数がグループ人数を越えない場合0を返す。
0が含まれる0以降の要素数がグループ人数を越える場合はリストの先頭の座席番号を返す。
[3 4]      , 2  ->  [3 4]
[5 6 7 0 1], 2  ->  [0 1]
[5 6 7 0 1], 3  ->  [5 6 7]
"
  (let [lst (drop-while #(not= 0 %) data)]
    (if (>= (count lst) grpnum)
      (take grpnum (iterate inc 0))
      (take grpnum data))))



(defn find-seats [table-lst grpnum]
"テーブルの状態リストとグループ人数を引数にとり、着席可能な空席を返す。
着席できない場合は[99 0]を返す。
"
  (->> (partition-by #(nth % 0) (double-table table-lst)) ; 同状態席のグルーピング
       (filter #(and (= (nth (nth % 0) 0) 0) (>= (count %) grpnum))) ; グループ人数を満たす空席グループを取り出し候補とする
       (map (fn [x] (map (fn [y] (nth y 1)) x))); 状態を捨てて座席番号のみのリストとする
       (map #(select-seat-from-list % grpnum)); 仕様に合わせて絞る
       (reduce #(if (< (nth %1 0) (nth %2 0)) %1 %2) '(99 0))))

(defn sitdown [table-lst seats]
"指定した席に着席させる。指定した席以外の席の状態は変化しない。"
  (reduce #(assoc %1 %2 1) (vec table-lst) seats))

(defn do-one-step [table-lst grpnum]
  (let [table-lst' (time-pass-by table-lst)
        seats (find-seats table-lst' grpnum)]
    (if (= seats [99 0])
      [table-lst' false]
      [(sitdown table-lst' seats) true])))

(defn do-until-sitdown [table-lst grpnum]
  (let [table (do-one-step table-lst grpnum)]
       (if (nth table 1) (nth table 0)
           (do-until-sitdown (nth table 0) grpnum))))

(defn process' [accum wait-line]
  (if (empty? wait-line)
    accum
    (process' (do-until-sitdown accum (first wait-line)) (rest wait-line))))

(defn process [wait-line]
  (num-vec-stringize (binarize (process' [0 0 0 0 0 0 0 0] (num-vec wait-line)))))



