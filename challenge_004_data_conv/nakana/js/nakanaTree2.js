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
    .groupBy(function(data){
      return data.id + "@" +  data.order;
    })
    .map(function(v, k){
      return {
	ctKey: v[0].id,
	ctName: v[0].name,
	groupKey: v[0].order,
	date : v[0].date,
	groupValues : _(v)
	  .transform(function(result, d){
	    result.push({itemKey: d.item, amount: d.amount, count: d.count});
	  },[])
	  .value()
      };
    })
//   .tap( function(d) { console.log(JSON.stringify(d)); })
    .groupBy(function(data){
      return data.ctKey;
    })
    .map(function(v, k){
      return {
	ctKey: v[0].ctKey,
	ctName: v[0].ctName,
	ctValues: _(v)
	  .transform(function(result, d){
	    result.push({groupKey: d.groupKey, date: d.date, gorupValues: d.groupValues});
	  },[])
	  .value()
      };
    })
    .tap( function(d) { console.log(JSON.stringify(d)); })
    .value()
;
//console.log(JSON.stringify(result, null, 2));







