
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
];

var outData = [
	{"hosp" : "a", "data" : [1, 2, 3]},
	{"hosp" : "b", "data" : [1, 2, 3]},
	{"hosp" : "c", "data" : [1, 2, 3]} 
];




// /* 書きなぐり */
// // とりあえずreduce関数ゴリ押し。
// // 関数脳と手続き脳が、戦って折衷しました的な。
// var tmp = _.reduce(inData, function(result, d){
//   if(!result[d.hosp]){
//     result[d.hosp] = {};
//     result[d.hosp].data = [];
//   } 
//   result[d.hosp].data.push(d.data);
//   return result;
// }, {});
// console.log(tmp);
// var res = _.reduce(tmp, function(result, v, k){
//   result.push({hosp : k, data : v.data});
//   return result;
// }, []);
// console.log(res);



// /* 書きなぐりをリファクタリング */
// var tmp = _.reduce(inData, function(result, d){
//   if(!result[d.hosp]){ result[d.hosp] = []; } 
//   result[d.hosp].push(d.data);
//   return result;
// }, {});
// console.log(tmp);
// var res = _.reduce(tmp, function(result, v, k){
//   result.push({hosp : k, data : v});
//   return result;
// }, []);
// console.log(res);



// /* リファレンスみて、SQLのgroupBy的なものを探して書いてみた的な。 */
// var tmp = _.groupBy(inData, function(d) { return d.hosp; });
// console.log(tmp);
// var result = _.map(tmp, function(v, k) { 
// //   console.log('-------');
// //   console.log(e);
// //   console.log(v);
//   return {hosp: k, data : _.map(v, function(d) { return d.data; })}; 
// });
// console.log(result);



// /* ぷよぷよの連鎖みたいにつなげてスッキリ的な。 */
// // 関数プログラミングの王道的なパイプライン処理に近い感じ。
// var result = _(inData)
//   .groupBy(function(d) { return d.hosp; })
//   .map(function(v, k) { return {hosp: k, data : _.map(v, function(d) { return d.data; })}; })
//   .value();
// console.log(result);



// /* tapさせて、加工途中をのぞき込んで通報されちゃう的なオマケ。 */
// var result = _(inData)
//   .groupBy(function(d) { return d.hosp; })
//   .tap(function(d) { console.log(d);})
//   .map(function(v, k) { return {hosp: k, data : _.map(v, function(d) { return d.data; })}; })
//   .value();



/* ついでなので書きなぐりのchain化 */
// 書きなぐりもchainingしようと思ったが、reduce関数はchainingできないので、transformを使う。
// transformは累積変数のreturn不要（途中脱出用のfalseリターンがある）。そこがreduce関数と違う。
// つまりreduceはあくまでも値を求めるもので、transformは副作用が主目的。非純粋性を全面におすなぁ。
// 副作用島倉千代子だが、外から見れば副作用を閉じ込めているので副作用していない処理とみなすことは可能ではある。
// ちなみに、Haskellは純粋なのでこんなことはできない。けど、一周回ってできるように見せかけることが可能。
// Stateモナド使えばね的な。
var result = _(inData)
  .transform(function(result, d){
    if(!result[d.hosp]){ result[d.hosp] = []; } 
    result[d.hosp].push(d.data);
  }, {})
  .transform(function(result, v, k){
    result.push({hosp : k, data : v});
  }, [])
  .value();
console.log(result);




// /* undefinedだったら新規オブジェクトを突っ込むというコードがいけないので改良的な */
// // ifもswitchも大嫌いみたいな。
// var result = _(inData)
//   .transform(function(result, d){
//     result = _.defaults(result, _.set({}, d.hosp, []));
//     result[d.hosp].push(d.data);
//   }, {})
//   .transform(function(result, v, k){
//     result.push({hosp : k, data : v});
//   }, [])
//   .value();
// console.log(result);
