以下のデータコンバータを描きたい。擬似JSON

```
IN
[
	{hosp : a , data: 1},
	{hosp : a , data: 2},
	{hosp : a , data: 3},
	{hosp : b , data: 1},
	{hosp : b , data: 2},
	{hosp : b , data: 3},
	{hosp : c , data: 1},
	{hosp : c , data: 2},
	{hosp : c , data: 3}
]



OUT
[
	{hosp : a,  data : [1, 2, 3]},
	{hosp : b,  data : [1, 2, 3]},
	{hosp : c,  data : [1, 2, 3]}, 
]
```
