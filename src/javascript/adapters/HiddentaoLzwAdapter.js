function HiddentaoLzwAdapter() {
    AbstractAdapter.call(this, "hiddentao-lzw-async");

    this.compress = function(data, callback) {
        LZWAsync.compress({
            input: data,
            output: callback
        });
    };

    this.decompress = function(data, callback) {
        LZWAsync.decompress({
            input: data,
            output: callback
        });
    }
}