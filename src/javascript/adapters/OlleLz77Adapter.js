function OlleLz77Adapter() {
    AbstractAdapter.call(this, "olle-lz77-kit");
    var compressor = new LZ77();

    this.compress = function(data, callback) {
        callback(compressor.compress(data));
    };

    this.decompress = function(data, callback) {
        callback(compressor.decompress(data));
    }
}