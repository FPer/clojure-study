console.log('hello,world');

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

var treeAry = [
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
];

var params = {
  def : {
    key : {name:'ctKey', value:'id',},
    elements : [
      {name:'ctName', value:'name',},
    ],
    children : {
      name : 'ctValues',
      values : [
        {
          key : {name:'groupKey', value:'order'},
          elements : [
            {name:'date', value:'date',},
          ],
          children : {
            name:'groupValues',
            values : [
              {
                key : {name:'itemKey',value:'item',},
                elements : [
                  {name:'count', value:'count',},
                  {name:'amount', value:'amount',},
                ],
              },
            ],
          },
        },
      ],
    },
  },
};

var TreeConverter = function() {};
TreeConverter.prototype.createTreeAry = function(dataAry, params) {
  var self = this;
  var ary = [];
  if (Array.isArray(dataAry)) {
    dataAry.forEach(function(data, index, array) {
      self.createObj(ary, data, params, params.def);
    });
  }
  return ary;
};
TreeConverter.prototype.createObj = function(ary, data, params, def) {
  var self = this;
  var keyName = self.getName(data, params, def, def.key.name);
  var keyValue = self.getValue(data, params, def, def.key.value);
  var obj = _.find(ary, function(obj, index, array){
    return obj[keyName] == keyValue;
  });
  if (!obj) {
    obj = {};
    obj[keyName] = keyValue;
    if (Array.isArray(def.elements)) {
      def.elements.forEach(function(element, index, array){
        obj[self.getName(data, params, def, element.name)] = self.getValue(data, params, def, element.value);
      });
    }
    if (def.children) {
      obj[self.getName(data, params, def, def.children.name)] = [];
    }
    ary.push(obj);
  }
  if (def.children) {
    var name = self.getName(data, params, def, def.children.name);
    def.children.values.forEach(function(def_, index, array){
      self.createObj(obj[name], data, params, def_);
    });
  }
};
TreeConverter.prototype.getName = function(data, params, def, name) {
  return name;
};
TreeConverter.prototype.getValue = function(data, params, def, value) {
  return data[value];
};

var myCnv = new TreeConverter();
var result = myCnv.createTreeAry(dataAry, params);
console.log(_.isEqual(treeAry, result));
console.log('== result ==', result);
