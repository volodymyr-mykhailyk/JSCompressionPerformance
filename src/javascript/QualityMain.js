var Utf8 = {

	// public method for url encoding
	encode : function (string) {
		string = string.replace(/\r\n/g,"\n");
		var utftext = "";

		for (var n = 0; n < string.length; n++) {

			var c = string.charCodeAt(n);

			if (c < 128) {
				utftext += String.fromCharCode(c);
			}
			else if((c > 127) && (c < 2048)) {
				utftext += String.fromCharCode((c >> 6) | 192);
				utftext += String.fromCharCode((c & 63) | 128);
			}
			else {
				utftext += String.fromCharCode((c >> 12) | 224);
				utftext += String.fromCharCode(((c >> 6) & 63) | 128);
				utftext += String.fromCharCode((c & 63) | 128);
			}

		}

		return utftext;
	},

	// public method for url decoding
	decode : function (utftext) {
		var string = "";
		var i = 0;
		var c = c1 = c2 = 0;

		while ( i < utftext.length ) {

			c = utftext.charCodeAt(i);

			if (c < 128) {
				string += String.fromCharCode(c);
				i++;
			}
			else if((c > 191) && (c < 224)) {
				c2 = utftext.charCodeAt(i+1);
				string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
				i += 2;
			}
			else {
				c2 = utftext.charCodeAt(i+1);
				c3 = utftext.charCodeAt(i+2);
				string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
				i += 3;
			}

		}

		return string;
	}

};


function my_adler(data) {
    var MOD_ADLER = 65521;
    var a = 1, b = 0;
    var index;

    /* Process each byte of the data in order */
    for (index = 0; index < data.length; ++index)
    {
        a = (a + data.charCodeAt(index)) % MOD_ADLER;
        b = (b + a) % MOD_ADLER;
    }

    var adler = a | (b << 16);
    var str = String.fromCharCode(((adler >> 24) & 0xff),
                ((adler >> 16) & 0xff),
                ((adler >> 8) & 0xff),
                ((adler >> 0) & 0xff))
    return str;
}

function doit() {
    var f = document.f;
    var data;

    /* deflate */
    data = f.source.value;
    var adapter = new DankogaiDeflateAdapter(8);
    var data1 = utf16to8(data);
    adapter.compress(data1, function(result) {
        var div = document.getElementById("results_div");
        var dataWithHeader = String.fromCharCode(120, 218) + result;
        div.appendChild(document.createTextNode(window.btoa("a")));
        div.appendChild(document.createTextNode(window.btoa(Utf8.encode("\u043a"))));
        div.appendChild(document.createTextNode(window.btoa(Utf8.encode("\u0435"))));
    });
}