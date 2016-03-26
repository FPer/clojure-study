var inData = [
  {"hosp" : "a" , "data": 1},
  {"hosp" : "a" , "data": 2},
  {"hosp" : "a" , "data": 3},
  {"hosp" : "b" , "data": 1},
  {"hosp" : "b" , "data": 2},
  {"hosp" : "b" , "data": 3},
  {"hosp" : "c" , "data": 1},
  {"hosp" : "c" , "data": 2},
  {"hosp" : "c" , "data": 3}
]

var outData = _.map(_.uniqBy(inData, 'hosp'), (val) => {
  val.data = _.map(_.filter(inData, {'hosp':val.hosp}), 'data');
  return val;
});
console.log(JSON.stringify(outData));