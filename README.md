# ESM 関数型勉強会課題提出用リポジトリ

## Projectの作成の仕方
以下のように、コマンドを打ってプロジェクトを作ります。
```
> lein new [projectname]
```


## Projectの設定
まずは、カレントディレクトリをプロジェクトディレクトリ直下に移動します。
```
> cd [projectname]
```
project.cljを変数してプロジェクトを設定します。プロジェクトディレクトリ直下にあります。

```clojure
(defproject nakana "0.1.0-SNAPSHOT"
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
あと、org.clojure/tools.namespaceを入れておくとREPLで少し楽になります。後述します。

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
replを起動します。必ずプロジェクトディレクトリの直下で行ってください。そうすると設定した依存ライブラリが自動的に読み込まれます。
```
> lein repl
```


本格的に理解するには、clojureの名前空間について理解する必要がありますが、まずは以下のURLを参考にしてください。
[Leiningen REPLを再起動せずにリフレッシュする](http://qiita.com/arakaji/items/db0b97c873d477151796)

## テストコード
まずは [Clojureでテストを書く (with TDD)](http://naokirin.hatenablog.com/entry/20111214/1323542003) を手で実際に書いてみて理解するのがいいです。

test/[projectName]/core_test.cljを雛形にしてください。
