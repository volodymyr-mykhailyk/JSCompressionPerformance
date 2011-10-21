function JslzjbAdapter() {
    AbstractAdapter.call(this, "jslzjb");

    this.compress = function(data, callback) {
        callback(Iuppiter.compress(data));
    };

    this.decompress = function(data, callback) {
        callback(Iuppiter.decompress(data));
    }
}