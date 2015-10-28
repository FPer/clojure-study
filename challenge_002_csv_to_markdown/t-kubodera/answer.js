// 改行
var LF = '\n';

/*
 * 2次元配列をMarkdown形式に変換する。
 * @param data 2次元配列データ
 * @param firstRowIndex 先頭行見出しフラグ(true:見出し/false:通常の行)
 * @returns Markdown形式文字列
 */
function csv2markdown(data, firstRowIndex) {
    var result = '';
    var alignSetting = '';

    _.each(data, function(rowArray, i) {
        _.each(rowArray, function(item, j) {
            result += ((j === 0)? '|' : '') + item + '|';
            if (i === 0) {
                alignSetting += ((j === 0)? '|' : '') + ':-:|';
            }
        });
        result += LF;
        if (i === 0 && firstRowIndex) {
            result += alignSetting + LF;
        }
    });
    return result;
}
