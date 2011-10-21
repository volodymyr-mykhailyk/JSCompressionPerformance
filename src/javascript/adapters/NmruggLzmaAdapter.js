function NmruggLzmaAdapter(level) {
    AbstractAdapter.call(this, "nmrugg-LZMA-JS Level("+level+")");
    var compressor = LZMA;

    this.compress = function(data, callback) {
        callback(compressor.compress(data, level));
    };

    this.decompress = function(data, callback) {
        callback(compressor.decompress(data));
    }
}