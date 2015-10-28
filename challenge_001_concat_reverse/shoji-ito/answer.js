function reverse(lst) {
  return reverse_(lst, []);
}
private function reverse_(lst1, lst2) {
  if (empty(lst1)) return lst2;
  return reverse_(rest(lst1), cons(first(lst1), lst2));
}


function concat(lst1, lst2) {
  return concat_(reverse(lst1), lst2);
}

private function concat_(lst1, lst2) {
  if (empty(lst1)) return lst2;
  return concat_(rest(lst1), cons(first(lst1), lst2));
}
