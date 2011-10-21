function StuckJSZipAdapter() {
    AbstractAdapter.call(this, "Stuck-jszip");

    this.compress = function(data, callback) {
        var zip = new JSZip("DEFLATE");
        zip.add("data", data);
        callback(zip.generate(false));
    };

    this.decompress = function(data, callback) {
        callback("");
    }
}