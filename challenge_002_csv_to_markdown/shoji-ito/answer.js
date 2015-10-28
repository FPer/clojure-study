javascript
function func(data) {
  firstArray = first(data);
  if empty(firstArray) return;

  // markdown
  (print '|') (print join '|' firstArray) (print '|')

  // 見出しどうする？　「|:-:|:-:|:-:|」

  // 再帰
  return func(rest(data))
}
