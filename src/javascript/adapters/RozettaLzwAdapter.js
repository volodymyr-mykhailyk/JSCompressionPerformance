function RozettaLzwAdapter() {
    AbstractAdapter.call(this, "rozetta-lzw");

    this.compress = function(data, callback) {
        callback(LZW.compress(data));
    };

    this.decompress = function(data, callback) {
        callback(LZW.decompress(data));
    }
}