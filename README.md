# ESM 関数型勉強会課題提出用リポジトリ

## プロジェクトを作成する。
以下のように、コマンドを打ってプロジェクトを作ります。
```
> lein new [project名]
```


## プロジェクトを設定する。
まずは、カレントディレクトリをプロジェクトディレクトリ直下に移動します。
```
> cd [project名]
```
project.cljを変数してプロジェクトを設定します。プロジェクトディレクトリ直下にあります。

```clojure
(defproject [project名] "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
                 [org.clojure/clojure "1.6.0"]
                 [org.clojure/tools.namespace "0.2.7"]
                 ])
```

必要なライブラリを見つけたらdependenciesの中を、適宜増やしていけば良いと思います。
[clojars](https://clojars.org/)で検索かけると良いです。
あとorg.clojure/tools.namespaceを入れておくとREPLで少し楽になります。後述します。

##プログラムを編集する。
src/[projectName]/core.cljを雛形にしてください。
冒頭に名前空間が書いてあると思います。
```
(ns [project名].[ファイル名から拡張子を除いたもの])
```
名前空間を理解できていないうちは、このパターンは必ず守ってください。
ファイルやフォルダ名が_（アンダースコア）でも、名前空間では-（ハイフン）という対応関係があったりします。Lispでは、アンダースコアよりハイフンを多用しますが、Javaではハイフンがパッケージに使えないことがこういった仕様になっていると思われます。

好きなライブラリを使いたい場合は、[Clojureの忘れっぽいrequire、use、import](http://d.hatena.ne.jp/Kazuhira/20130913/1379087775)を参考にしてください。



## REPLを起動する。
replを起動します。必ずプロジェクトディレクトリの直下で行ってください。そうすると設定した依存ライブラリが自動的に読み込まれます。厳密にいうと読み込まれるというのはうそで、classpathが設定される程度といったところです。読み込むには、後述するuse, requireすることが必要です。
```
> lein repl
```

エディタでプログラムを編集した内容をREPLに反映させるのは、なかなか面倒な作業だと思います。名前空間をしっかりと理解できていればいいのですが理解していなくても、以下のURLを参考にすれば、少しは楽になるのではないでしょうか。

[Leiningen REPLを再起動せずにリフレッシュする](http://qiita.com/arakaji/items/db0b97c873d477151796)

## テストコードを書く。
まずは [Clojureでテストを書く (with TDD)](http://naokirin.hatenablog.com/entry/20111214/1323542003) を手で実際に書いてみて理解するのが良いと思います。

test/[project名]/core_test.cljを雛形にして、まずは書いて実行してみてください。

# ポイント
- namespaceの単語間はピリオドだが、namespaceと関数の間はスラッシュである。
  - ◯◯◯.△△△.□□□/関数
  - とりあえず、どの空間にいても絶対パス的に上記のような書き方をすればコールできるはずである。
    - javaだと、全くimportせずに、ルートのパッケージからすべてクラスを指定したりメソッドを指定したりするようなもの
    - それでも、ファイルのロードをする必要がある。load-file関数やload関数を使うことになる。
    - が、通常は、requireやuseを使ってファイルをロードするついでに、別名を与えたり、利用する関数を絞ったりする。
  - カレントの名前空間を移動すれば、その空間に収まっている関数はコールできるものの、通常は他のライブラリをインポートする。
- namespaceの一単語は、一ディレクトリに相当させているデザインのようである。
- requireやuseで、ファイルがロードされる。
- > leiningen repl直後はクラスパスが通ってはいても、ファイルがロードされている状態ではない。原則として、ファイルのロードはREPL上で、reuiquireやuseを使って手動で実施する必要がある。
- 名前空間を移動するマクロはnsだが、オプションでrequireとかuseができるので、名前空間を移動すると同時にファイルをロードすることもできる。
- リロードするときはrequireやuseなどで:reloadオプションを使うとできるようだが、tools.namespaceでrefreshをかけた方が楽である。
