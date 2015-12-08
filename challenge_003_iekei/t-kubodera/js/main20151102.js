
// 状態を示すコード
var STATUS_VACANCY = 0;     // 空席
var STATUS_ORDERED = 1;     // 調理待ち
var STATUS_EATING = 2;      // 食事中
var STATUS_REST = 3;        // 一休み

/** 客席の状態. */
var seats_ = null;

/**
 * メイン関数.
 * @param arg 引数(客の人数)
 * @returns 最終的な席の状態を示す文字列
 */
function mainFunc(arg) {
    // 初期化(8席)
    seats_ = [];
    for (var i = 0; i < 8; i++) {
        seats_.push(STATUS_VACANCY);
    }
    // 待ち行列を作成
    var guests = arg.split('');
    do {
        // 客を席に着かせる
        var result = putGuests(parseInt(guests[0]), (guests.length === 1));
        if (result) {
            // 席に着けたら待ち行列から削除
            guests.shift();
        }
    } while(guests.length > 0);
    console.log(seats_);

    // 結果を作成
    var resultAry = [];
    _.each(seats_, function(item) {
        var value = (item === STATUS_VACANCY) ? '0' : '1';
        resultAry.push(value);
    });
    return resultAry.join('');
}

/**
 * 客を席に着かせる.
 * 人数分並んでいる空席を探し、席を埋める。
 * @param numbers 客の人数
 * @param isLast 最終客か否か
 * @return 席に着けたか否か(true:着席成功/false:空き無し)
 */
function putGuests(numbers, isLast) {
    var vacancySeatNo = findVacancy(numbers);
    if (vacancySeatNo >= 0) {
        // 新しい客が座った席の番号
        var skipList = [];
        // 新たに案内したグループを席に着かせる
        for (var i = 0; i < numbers; i++) {
            var index = vacancySeatNo + i;
            if (index > (seats_.length - 1)) {
                index -= seats_.length;
            }
            seats_[index] = STATUS_ORDERED;
            skipList.push(index);
        }
        // 最後の客以外ならば、既存客の状態を遷移させる
        if (!isLast) {
            updateStatus(skipList);
        }
        return true;
    } else {
        // 全席の状態を遷移させる
        updateStatus();
        return false;
    }
}

/**
 * 空席の探索.
 * 必要な数だけ連続して空いている席を探す。
 * @param numbers 客の人数
 * @return 空席の席番号(見つからない場合は-1)
 */
function findVacancy(numbers) {
    // 開始位置
    var startPos = 0;
    // 1番の席から順に、空いている席の場所を探す(詰めて座らせる)
    for (var i = 0; i < seats_.length; i++) {
        if (seats_[index] === STATUS_VACANCY) {
            startPos = i;
            break;
        }
    }

    // 客の数だけ連続している空席があるか探す
    var ret = -1;
    for (var i = startPos; i < seats_.length; i++) {
        var checkFlg = true;
        for (var j = 0; j < numbers; j++) {
            var index = i + j;
            if (index > (seats_.length - 1)) {
                index -= seats_.length;
            }
            if (seats_[index] !== STATUS_VACANCY) {
                checkFlg = false;
                break;
            }
        }
        if (checkFlg) {
            ret = i;
            break;
        }
    }
    return ret;
}

/**
 * 状態の更新.
 * 客席の状態を1段階先に進める。空席に対しては何もしない。
 * @param skipList 状態遷移の対象外席番号(省略可)
 */
function updateStatus(skipList) {
    for (var i = 0; i < seats_.length; i++) {
        if (skipList && _.indexOf(skipList, i) >= 0) {
            continue;
        }
        switch (seats_[i]) {
            case STATUS_ORDERED:
                seats_[i] = STATUS_EATING;
                break;
            case STATUS_EATING:
                seats_[i] = STATUS_REST;
                break;
            case STATUS_REST:
                seats_[i] = STATUS_VACANCY;
                break;
        }
    }
}

/**
 * テストスイート.
 */
function testSuite() {
    test('3316', '11111110');
    test('3342153', '11111111');
    test('8', '11111111');
    test('232624', '11110110');
    test('1', '10000000');
    test('224673432', '11111000');
    test('123464334', '11111110');
    test('44372332', '11111111');
    test('2344', '11111111');
    test('6448476233', '11111100');
    test('4345174644', '11111111');
    test('77743', '11111110');
    test('2136524281', '10000000');
    test('344373', '11100000');
    test('434226', '11111111');
    test('7433223', '11111110');
    test('2246534', '11110111');
    test('634', '11111110');
    test('2222', '11111100');
    test('2442343238', '11111111');
    test('7243344', '11111111');
    test('26147234', '10111111');
    test('34424', '10011111');
    test('6334', '11011111');
    test('3828342', '11011110');
    test('4431', '11110000');
    test('2843212125', '11111111');
    test('333336482', '11000000');
    test('374', '11110000');
    test('4382333', '11100111');
}

/**
 * テスト用ルーティン.
 */
function test(input, expect) {
    var actual = mainFunc(input);
    if (expect === actual) {
        console.log('success! input:', input, ',output:', actual);
    } else {
        console.log('failure. input:', input, 'expected:', expect, ', but actual:', actual);
    }
}
