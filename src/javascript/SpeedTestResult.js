function TestResult(id) {
    var size;
    var time;
    var compression;
    var success;

    this.getId = function() {
        return id;
    };

    this.setDataSize = function(theSize) {
        size = theSize;
    };

    this.getDataSize = function() {
        return size;
    };

    this.setTime = function(theTime) {
        time = theTime;
    };

    this.getTime = function() {
        return time;
    };

    this.setCompression = function(theCompression) {
        compression = (theCompression*100).toFixed(3)+"%";
    };

    this.getCompression = function() {
        return compression;
    };

    this.setSuccess = function(theSuccess) {
        success = theSuccess ? "OK" : "FAILURE";
    };

    this.getSuccess = function() {
        return success;
    };
}
