function AsyncTestsExecutor(adapters, renderer) {
    var currentAdapater = 0;
    var count = 0;
    var dataList = [];
    var dataLength = 0;
    var progressID = "progress-bar";

    function generateProgressString(currentProgress, originalProgress) {
        var result = "";
        for (var i = 0; i < originalProgress; i++) {
            result+= (i < currentProgress) ? "+" : "-";
        }
        return result;
    }

    function renderProgress() {
        var progressBar = document.getElementById(progressID);
        if (!progressBar) {
            progressBar = document.createElement("div");
            progressBar.setAttribute("id", progressID);
            progressBar.appendChild(document.createTextNode(""));
            document.body.insertBefore(progressBar, document.body.childNodes[0]);
        }
        var total = dataLength * adapters.length;
        var current = (dataLength - dataList.length - 1) * adapters.length + currentAdapater;
        progressBar.childNodes[0].nodeValue = generateProgressString(current, total);
    }

    function nextTest(data, count) {
        currentAdapater++;
        renderProgress();
        if (currentAdapater < adapters.length) {
            setTimeout(function() {
                runTests(data, count);
            }, 100);
        } else {
            nextData();
        }
    }

    function createRenderer(data, count) {
        return new FeedbackRenderer(renderer, function() {
            nextTest(data, count);
        })
    }

    function nextData() {
        currentAdapater = 0;
        var data = dataList.pop();
        if (data) {
            runTests(data, count);
        }
    }

    function runTests(data, count) {
        var adapter = adapters[currentAdapater];
        var testRunner = new SpeedTestRunner(adapter, createRenderer(data, count));
        testRunner.run(data, count);
    }

    this.runTests = function(data, theCount) {
        count = theCount;
        dataList = data;
        dataLength = dataList.length;
        renderProgress();
        nextData();
    };
}