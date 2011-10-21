function LzwJSAdapter() {
    AbstractAdapter.call(this, "lzwjs");
    var offset;

    this.compress = function(data, callback) {
        var output = new OutStream();
        var compressor = new LZWCompressor(output);
        compressor.compress(data);
        offset = output.offset;
        callback(output.bytestream);
    };

    this.decompress = function(data, callback) {
        var instream = new InStream(data, offset);
        var decompressor = new LZWDecompressor(instream);
        callback(decompressor.decompress());
    }
}