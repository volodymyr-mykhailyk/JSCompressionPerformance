function RozettaLzwAdapter() {
    AbstractAdapter.call(this, "rozetta-lzw");

    this.compress = function(data, callback) {
        var utils = new CompressionUtils();
        data = utils.toByteArray(data);
        var compressed = LZW.compress(data);

        callback(compressed.join(","));
    };

    this.decompress = function(data, callback) {
        callback(LZW.decompress(data));
    }
}