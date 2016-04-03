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
//  return f(t.node, _.map(t.rose, function(e) { return _.curryRight(reduceRose)(f)(e); })); //OK
//  return f(t.node, _.map(t.rose, _.curryRight(reduceRose, 2)(f))); // NG
  return f(t.node, _.map(t.rose, _.partial(reduceRose, _, f)));
}

var sum = _.partial(_.reduce, _, function(e, a){ return e + a; }, 0);
var result = reduceRose(data, function(n, ns) { return 1 + sum(ns); });

println(result);

