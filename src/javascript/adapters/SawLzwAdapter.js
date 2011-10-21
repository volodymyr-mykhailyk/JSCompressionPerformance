function SawLzwAdapter() {
    AbstractAdapter.call(this, "saw-JS_LZW");

    this.compress = function(data, callback) {
        callback(saw.lzw.encode(data));
    };

    this.decompress = function(data, callback) {
        callback(saw.lzw.decode(data));
    }
}