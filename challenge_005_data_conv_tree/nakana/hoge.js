var _ = require('lodash');

var data = {
  node: {},
  rose: [
    {
      node: {id: 1, name: 'a'},
      rose: [
	{
	  node : {order: 1, date: '01/01'},
	  rose : [
	    {node: {item: 'aaa', count: 1, amount: 100,}, rose: []},
	    {node: {item: 'bbb', count: 2, amount: 200,}, rose: []},
	    {node: {item: 'ccc', count: 3, amount: 300,}, rose: []},
	  ]
	},
	{
	  node: {order:2, date: '01/02'},
	  rose : [
	    {node: {item: 'aaa', count: 1,amount: 100,}, rose: []},
	    {node: {item: 'bbb', count: 2,amount: 200,}, rose: []},
	  ]
	},
	{
	  node: {order: 3, date: '01/03'},
	  rose: [       
	    {node : {item: 'ccc', count: 3, amount: 300,}, rose: []},
	  ]
	},
      ]
    },
    {
      node: {id: 2, name: 'b'},
      rose: [
	{
	  node: {order: 4, date: '02/01'},
	  rose: [
	    {node: {item: 'aaa', count:	1, amount: 100,}, rose: []},
	    {node: {item: 'bbb', count:	2, amount: 200,}, rose: []},
	  ]
	}
      ]
    },
    {
      node: {id: 3, name: 'c'},
      rose: [
	{
	  node: {order: 5, date: '03/01'},
	  rose: [
	    {node: {item: 'ccc', count: 3, amount: 300,}, rose: []},
	  ]
	}
      ]
    },
  ]
};

var println = function(x) { console.log(x); }
var printlnJSON = function(x, n) {
  var indent = n === undefined ? 0 : n;
  console.log(JSON.stringify(x, null, _.repeat(' ', indent))); 
}

var constf = function(x) { return x; }

var mapTree = function(t, f){
  if(_.isEmpty(t.rose)) return {node: f(t.node), rose: []};
  return {
    node: f(t.node), 
    rose: _.map(t.rose, function(e) { return mapTree(e, f); })
  };
};

var reduceTree = function(t, f, a) {
  if(_.isEmpty(t.rose)) return f(t, a);
  return f(t, _.reduce(
    t.rose, 
    function(a, t) {return f(t, reduceTree(t, f, a)); },
    a));
};

var foldTree = reduceTree; // alias

// mapTree(data, function(n) { console.log(n); });
// println('------------');
// println(constf(12, 19, 20));
// println('------------');
// //println(JSON.stringify(mapTree(data, function(t) { return constf(1, t); }), null, '  '));
// //println(JSON.stringify(mapTree(data, _.curry(constf, 2)(1)), null, '  '));
// //printlnJSON(mapTree(data, _.curry(constf, 2)(1)), 2);
// //printlnJSON(mapTree(data, _.curry(constf, 2)(1)));
// var tmp = mapTree(data, _.curry(constf, 2)(1));
// printlnJSON(tmp);
// println(foldTree(tmp, function(t, a) { return a + t.node; }, 0));



//var foldRose = reduceRose; // alias
//var reduceRose = function(f, t){
//  return f(t.node, _.map(t.rose, function(e) { return _.curry(reduceRose)(f)(e); }));
//}

var reduceRose = function(t, f){
  return f(t.node, _.map(t.rose, _.curry(reduceRose)(_, f))); //OK
//  return f(t.node, _.map(t.rose, _.curryRight(reduceRose)(f))); // NG why?
//  return f(t.node, _.map(t.rose, _.partial(reduceRose, _, f))); //OK
}

var reduceRose2 = function(f, t){
  return f(t.node, _.map(t.rose, _.curry(reduceRose2)(f)));
}


var node = function(n, t) {
  return {node: n, rose: t};
}

var g = function(f, x, xs) {
  return node(f(x), xs);
}
var mapRose2 = function(f, t){
//  return reduceRose2(_.flowRight(node, f), t);
//  return reduceRose2(function(){return node(f(t.node), t.rose);}, t);
  return reduceRose2(_.flowRight(_.curry(node), _.curry(f)), t);
}


var sum = _.partial(_.reduce, _, function(e, a){ return e + a; }, 0);
var result = reduceRose(data, function(n, ns) { return 1 + sum(ns); });
var result2 = reduceRose2(function(n, ns) { return 1 + sum(ns); }, data);
//println(result);
//println(result2);
printlnJSON(mapRose2(function(n) { return 1; }, data));

//println((5)([3,4]));
var tmp = _.curry(node);
var tmp2 = tmp(3);

var f = function(n){ return n };
var add = function(a, b){ return a + b; };
var add1 = _.curry(add)(1);

println(f(66));
println(add(66, 33));
println(add1(66));
println(_.flowRight(f, add)(66, 33));
println(_.flowRight(add1, add)(66, 33));
println(_.curry(add)(f(66))(33));
println(_.map([1, 2, 3], add1));
println(_.flowRight(add, f)(66, 33));
//println(_.flowRight(_.curry(node), f)(22, [2,3]));
