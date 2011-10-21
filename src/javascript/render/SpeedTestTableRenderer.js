function SpeedTestTableRenderer(table) {
    this.render = function(results) {
        var doc = document;
        var row = table.insertRow(table.rows.length);
        var idCell = doc.createElement("td");
        idCell.appendChild(doc.createTextNode(results.getId()));
        var sizeCell = doc.createElement("td");
        sizeCell.appendChild(doc.createTextNode(results.getDataSize()));
        var compressionCell = doc.createElement("td");
        compressionCell.appendChild(doc.createTextNode(results.getCompression()));
        var successCell = doc.createElement("td");
        successCell.appendChild(doc.createTextNode(results.getSuccess()));
        var timeCell = doc.createElement("td");
        timeCell.appendChild(doc.createTextNode(results.getTime()));
        row.appendChild(idCell);
        row.appendChild(sizeCell);
        row.appendChild(compressionCell);
        row.appendChild(successCell);
        row.appendChild(timeCell);

    }
}