function OniciosDeflateAdapter(level) {
    AbstractAdapter.call(this, "onicios-deflate Level("+level+")");
    var deflate = new OniciosDeflate();
    var inflate = new OniciosInflate();

    this.compress = function(data, callback) {
        callback(deflate.zip_deflate(data, level));
    };

    this.decompress = function(data, callback) {
        callback(inflate.zip_inflate(data));
    }
}