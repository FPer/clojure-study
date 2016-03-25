console.log('hello,world');

var input = [
  {'hosp' : 'a', 'data' : 1, },
  {'hosp' : 'a', 'data' : 2, },
  {'hosp' : 'a', 'data' : 3, },
  {'hosp' : 'b', 'data' : 1, },
  {'hosp' : 'b', 'data' : 2, },
  {'hosp' : 'b', 'data' : 3, },
  {'hosp' : 'c', 'data' : 1, },
  {'hosp' : 'c', 'data' : 2, },
  {'hosp' : 'c', 'data' : 3, },
];

var output = [
  {'hosp' : 'a', 'data' : [1, 2, 3], },
  {'hosp' : 'b', 'data' : [1, 2, 3], },
  {'hosp' : 'c', 'data' : [1, 2, 3], },
];

var obj = _.reduce(input, function(result, value, key) {
  if (!result.map[value.hosp]) {
    var element = {
      'hosp' : value.hosp,
      'data' : [],
    };
    result.ary.push(element);
    result.map[value.hosp] = element;
  }
  result.map[value.hosp].data.push(value.data);
  return result;
}, {
  map : {},
  ary : [],
});

console.log(_.isEqual(output, obj.ary));

console.log('== obj ==', obj);
