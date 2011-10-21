function AbstractAdapter(id) {
    this.getId = function() {
        return id;
    };

    this.compress = function() {
        throw "Not implemented compress";
    };

    this.decompress = function() {
        throw "Not implemented decompress";
    }
}