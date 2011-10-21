function SpeedTestRunner(adapter, renderer) {
    var originalData = "";
    var compressedData = "";
    var elapsedTime = 0;
    var iterationsCount = 0;

    function getTime() {
        return (new Date()).getTime();
    }

    function validateDecompression(decompressed) {
        renderResults(decompressed);
    }

    function renderResults(decompressed) {
        var testResult = new TestResult(adapter.getId());
        testResult.setCompression(compressedData.length / originalData.length);
        testResult.setDataSize(originalData.length);
        testResult.setTime(elapsedTime/iterationsCount);
        testResult.setSuccess(originalData === decompressed);
        renderer.render(testResult);
    }

    function performTest(data, count, renderFunction) {
        var start = getTime();
        function resultsCallBack(result) {
            elapsedTime += getTime()-start;
            compressedData = result;
            if (count > 0) {
                count--;
                performTest(data, count, renderFunction);
            } else {
                adapter.decompress(compressedData, validateDecompression);
            }
        }

            adapter.compress(data, resultsCallBack);
    }

    function init(data, count) {
        originalData = data;
        compressedData = "";
        elapsedTime = 0;
        iterationsCount = count;
    }

    this.run = function(data, count) {
        init(data, count);
        performTest(data, count, renderResults);
    }
}