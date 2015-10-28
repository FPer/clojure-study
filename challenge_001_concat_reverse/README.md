clojureには、リスト同士を結合するconcatとリストの要素を逆転するreverseという関数が既に用意されています。
これを純LISPの部品だけを使って自作してみましょう。

concatとreverseの具体例は、以下のようになります。

```clojure
user=> (concat '(1 2) '(3 4 5))
(1 2 3 4 5)
```

```clojure
user=> (reverse '(1 2 3 4 5))
(5 4 3 2 1)
```

純リスプの部品をclojureの世界で翻訳すると以下のようになります。

```
atom   -> nil?
eq     -> ==
car    -> first
cdr    -> rest
cons   -> cons
if     -> if
quote  -> quote
lamda  -> fn
define -> def
```

つまり、これらの部品だけを使ってconcatとreverseを自作しましょう。
扱うデータ対象は、ベクタではなくリストとしましょう。
もちろん、関数定義の引数にはベクタを使わざるをえないですが。
また、純LISPという制限を若干緩めて、empty?、list、defnも使っていいことにします。
