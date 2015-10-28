function concat(lst1, lst2){
    if(empty(lst1)) return lst2;
    return cons(first(lst1), concat(rest(lst1), lst2));
}

function reverse(lst){
    if(empty(lst)) return [];
    return cons(first(lst1), concat(rest(lst1), lst2));
}
