var resultsTable = document.getElementById("results_table");

function createDeflateAdapters(constructor, array) {
//    array.push(new constructor(2));
//    array.push(new constructor(4));
//    array.push(new constructor(5));
    array.push(new constructor(6));
//    array.push(new constructor(7));
//    array.push(new constructor(8));
}

var adapters = [];
createDeflateAdapters(DankogaiDeflateAdapter, adapters);
createDeflateAdapters(OniciosDeflateAdapter, adapters);
adapters.push(new StuckJSZipAdapter());
adapters.push(new HiddentaoLzwAdapter());
adapters.push(new LzwJSAdapter());
adapters.push(new RozettaLzwAdapter());
adapters.push(new SawLzwAdapter());
adapters.push(new JSendAdapter());
adapters.push(new JslzjbAdapter());
//createDeflateAdapters(NmruggLzmaAdapter, adapters);
//adapters.push(new OlleLz77Adapter());

var dataArr=[];
dataArr.push(Utf8.encode(originalData5));
dataArr.push(Utf8.encode(originalData4));
dataArr.push(Utf8.encode(originalData3));
dataArr.push(Utf8.encode(originalData2));
dataArr.push(Utf8.encode(originalData1));

var testsExecutor = new AsyncTestsExecutor(adapters, new SpeedTestTextRenderer(document.getElementById("results_div")));
testsExecutor.runTests(dataArr, 50);

