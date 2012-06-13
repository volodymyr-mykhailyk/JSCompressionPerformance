function CompressionUtils() {
    this.adler32 = function(data) {
        var MOD_ADLER = 65521;
        var a = 1, b = 0;
        var index;

        /* Process each byte of the data in order */
        for (index = 0; index < data.length; ++index) {
            a = (a + data.charCodeAt(index)) % MOD_ADLER;
            b = (b + a) % MOD_ADLER;
        }

        var adler = a | (b << 16);

        return String.fromCharCode(((adler >> 24) & 0xff),
            ((adler >> 16) & 0xff),
            ((adler >> 8) & 0xff),
            ((adler >> 0) & 0xff));
    };

    this.toByteArray = function(str) {
    var b = [], i, unicode;
    for(i = 0; i < str.length; i++) {
        unicode = str.charCodeAt(i);
        // 0x00000000 - 0x0000007f -> 0xxxxxxx
        if (unicode <= 0x7f) {
            b.push(String.fromCharCode(unicode));
        // 0x00000080 - 0x000007ff -> 110xxxxx 10xxxxxx
        } else if (unicode <= 0x7ff) {
            b.push(String.fromCharCode((unicode >> 6) | 0xc0));
            b.push(String.fromCharCode((unicode & 0x3F) | 0x80));
        // 0x00000800 - 0x0000ffff -> 1110xxxx 10xxxxxx 10xxxxxx
        } else if (unicode <= 0xffff) {
            b.push(String.fromCharCode((unicode >> 12) | 0xe0));
            b.push(String.fromCharCode(((unicode >> 6) & 0x3f) | 0x80));
            b.push(String.fromCharCode((unicode & 0x3f) | 0x80));
        // 0x00010000 - 0x001fffff -> 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
        } else {
            b.push(String.fromCharCode((unicode >> 18) | 0xf0));
            b.push(String.fromCharCode(((unicode >> 12) & 0x3f) | 0x80));
            b.push(String.fromCharCode(((unicode >> 6) & 0x3f) | 0x80));
            b.push(String.fromCharCode((unicode & 0x3f) | 0x80));
        }
    }

    return b.join("");
}

}