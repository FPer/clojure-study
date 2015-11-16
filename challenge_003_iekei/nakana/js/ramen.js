var _ = require('lodash/index.js');

function makeWaitList(waitLine) {
  var waitList = [];
  for(i = 0; i < waitLine.length; i++){
    waitList.push(parseInt(waitLine.charAt(i)));
  }
  return waitList;
}

function makeBinary(lst){
  return _.reduce(_.map(lst, function(n) {
    if(n === 0) { return '0';}
    return '1';
  }),function(a, b) {return a + b;}, '');
}

function rotate(lst){
  return _.takeRight(lst, lst.length - 1).concat(lst[0]);
}

function timepassed(lst) {
  return _.map(lst, function(sts){
    return {
      0 : 0,
      1 : 2,
      2 : 3,
      3 : 0,
    }[sts];
  });
}

function findSeat(lst, grp){
  var i;
  var result = lst;
  for(i = 1; i < grp; i++){
    result = _.zipWith(result, rotate(result), function(a, b){
      if(a === 0 && b === 0) return 0;
      return 1;
    });
  }
  return _.findIndex(result, function(n) { return n === 0});
}


function sitdown(seats, seatNo, grp){
  var i;
  var result = _.clone(seats);
  if(seatNo + grp - 1 <= 7) {
    for(i = seatNo; i < seatNo + grp    ; i++) { result[i] = 1; }
  } else {
    for(i = 0     ; i < seatNo + grp - 8; i++) { result[i] = 1; }
    for(i = seatNo; i <= 7              ; i++) { result[i] = 1; }
  }
  return result;
}

function doProcessUntilSitdown(seats, grp){
  var isFound = false;
  var seatNo = -1;
  var result = _.clone(seats);
  while(seatNo == -1){
    result = timepassed(result);
    seatNo = findSeat(result, grp);
  }
  result = sitdown(result, seatNo, grp);
  return result;
}

function doSenario(waitList){
  var seats = [0, 0, 0, 0, 0, 0, 0, 0];
  while(!_.isEmpty(waitList)){
    seats = doProcessUntilSitdown(seats, waitList.shift());
  }
  return makeBinary(seats);
}

// console.log(makeWaitList("3316"));
// console.log(rotate([1 ]));
// console.log(rotate([1, 2, 3]));
// console.log(rotate([1, 2, 3, 4]));
// console.log(timepassed([0, 1, 2, 3, 0, 1, 1, 0]));
// console.log(findSeat([0, 0, 1, 0, 0, 0, 0, 0], 8));
// console.log(makeBinary([0, 1, 2, 0, 1, 2]));
// console.log(sitdown([0, 0, 0, 0, 0, 0, 0, 0], 3, 4));
// console.log(sitdown([0, 0, 0, 0, 0, 0, 0, 0], 7, 2));
// console.log(doSenario([3,3,4,2,1,5,3]));
