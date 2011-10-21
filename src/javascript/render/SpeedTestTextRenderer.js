function SpeedTestTextRenderer(div) {
    this.render = function(results) {
        var doc = document;
        var buffer = "";
        buffer += results.getId();
        buffer += "\t";
        buffer += results.getDataSize();
        buffer += "\t";
        buffer += results.getCompression();
        buffer += "\t";
        buffer += results.getSuccess();
        buffer += "\t";
        buffer += results.getTime();
        buffer += "\n";
        div.appendChild(doc.createTextNode(buffer));
        div.appendChild(doc.createElement("br"));
    }
}