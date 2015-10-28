function concat(front, back) {
  if(empty(front)) return back;
  return cons(first(front), concat(rest(front), back));
}

function reverse(list) {
  if(empty(list)) return list;
  return concat(reverse(rest(list)), [first(list)]);
}
