function getData() {
    var f = document.f;
    var data;

    /* deflate */
    data = f.source.value;
    return data;
}
function doit() {
    var data = getData();
    var adapter = new RozettaLzwAdapter();
    adapter.compress(data, function(result) {
        var div = document.getElementById("results_div");
        div.appendChild(document.createTextNode(result));
    });
}