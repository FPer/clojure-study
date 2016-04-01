var dataAry = [
  {"hosp" : "a" , "data": 1},
  {"hosp" : "a" , "data": 2},
  {"hosp" : "a" , "data": 3},
  {"hosp" : "b" , "data": 1},
  {"hosp" : "b" , "data": 2},
  {"hosp" : "b" , "data": 3},
  {"hosp" : "c" , "data": 1},
  {"hosp" : "c" , "data": 2},
  {"hosp" : "c" , "data": 3}
];

function convert(data) {
  var converted = _.reduce(_.groupBy(data, data['hosp']), function(result, value, key) {
    result.push({'hosp': key, 'data': _.map(value, 'data')});
    return result;
  }, []);
  return converted;
}

