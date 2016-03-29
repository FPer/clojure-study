以下のINデータをOUTデータへ変換するコンバータを書いてください。
順番は考慮しなくていいです。
課題4と似ていますが、本課題は木構造になっています。


```

var inData = [
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

var outData = [
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
]



```
