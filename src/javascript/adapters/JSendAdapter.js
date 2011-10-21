function JSendAdapter() {
    AbstractAdapter.call(this, "jsend");

    this.compress = function(data, callback) {
        callback($.jSEND(data));
    };

    this.decompress = function(data, callback) {
        callback("");
    }
}