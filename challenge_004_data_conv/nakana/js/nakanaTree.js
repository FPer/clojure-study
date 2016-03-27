var _ = require('lodash');

var dataAry = [
  {id:1,name:'a',order:1,date:'01/01',item:'aaa',count:1,amount:100,},
  {id:1,name:'a',order:1,date:'01/01',item:'bbb',count:2,amount:200,},
  {id:1,name:'a',order:1,date:'01/01',item:'ccc',count:3,amount:300,},
  {id:1,name:'a',order:2,date:'01/02',item:'aaa',count:1,amount:100,},
  {id:1,name:'a',order:2,date:'01/02',item:'bbb',count:2,amount:200,},
  {id:1,name:'a',order:3,date:'01/03',item:'ccc',count:3,amount:300,},
  {id:2,name:'b',order:4,date:'02/01',item:'aaa',count:1,amount:100,},
  {id:2,name:'b',order:4,date:'02/01',item:'bbb',count:2,amount:200,},
  {id:3,name:'c',order:5,date:'03/01',item:'ccc',count:3,amount:300,},
];

var result = _(dataAry)
  .transform(function(result, data){
    var lvl1 = _.find(result, function(d) {
      return d.ctKey == data.id;
    });
    if(!lvl1){
      lvl1 = {ctKey : data.id, ctName : data.name, ctValues: []};
      result.push(lvl1);
    }
    var lvl2 = _.find(lvl1.ctValues, function(d) {
      return d.groupKey == data.order;
    });
    if(!lvl2){
      lvl2 = {groupKey : data.order, date : data.date, groupValues : []};
      lvl1.ctValues.push(lvl2);
    }
    lvl2.groupValues.push({itemKey:data.item, amount: data.amount, count: data.count});
  }, [])
//  .tap( function(d) { console.log(JSON.stringify(d)); })
  .value()
;
console.log(JSON.stringify(result, null, 2));



