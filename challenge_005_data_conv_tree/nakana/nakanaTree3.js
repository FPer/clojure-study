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

var dataAry2 = {
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


var expected = [
  {
    ctKey : 1, ctName : 'a', ctValues : [
      {
        groupKey : 1, date : '01/01', groupValues : [
          {itemKey : 'aaa', amount : 100, count : 1, },
          {itemKey : 'bbb', amount : 200, count : 2, },
          {itemKey : 'ccc', amount : 300, count : 3, }
        ]
      },
      {
        groupKey : 2, date : '01/02', groupValues : [
          {itemKey : 'aaa', amount : 100, count : 1, },
          {itemKey : 'bbb', amount : 200, count : 2, }
        ]
      },
      {
        groupKey : 3, date : '01/03', groupValues : [
          {itemKey : 'ccc', amount : 300, count : 3, }
        ]
      }
    ]
  },
  {
    ctKey : 2, ctName : 'b', ctValues : [
      {
        groupKey : 4, date : '02/01', groupValues : [
          {itemKey : 'aaa', amount : 100, count : 1, },
          {itemKey : 'bbb', amount : 200, count : 2, }
        ]
      }
    ]
  },
  {
    ctKey : 3, ctName : 'c', ctValues : [
      {
        groupKey : 5, date : '03/01', groupValues : [
          {itemKey : 'ccc', amount : 300, count : 3, }
        ]
      }
    ]
  }
  ;


  

var result = _(dataAry)
    .transform(function(accum, line){
      
    }, {})
    .value();



console.log(JSON.stringify(result)));
