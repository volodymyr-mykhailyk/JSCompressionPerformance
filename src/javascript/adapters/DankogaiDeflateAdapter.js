function DankogaiDeflateAdapter(level) {
    AbstractAdapter.call(this, "dankogai-js-deflate Level("+level+")");

    this.compress = function(data, callback) {
        callback(RawDeflate.deflate(data, level));
    };

    this.decompress = function(data, callback) {
        callback(RawDeflate.inflate(data));
    }
}