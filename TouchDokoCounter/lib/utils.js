
function loadURL(url) {
    var oRequest = new XMLHttpRequest();
    oRequest.open('GET', url, false);
    oRequest.setRequestHeader("User-Agent", navigator.userAgent);
    oRequest.send(null)

    return oRequest.responseText;
};


function scaleSvg(svgDomId, width, height){
    var svg = Ext.get(svgDomId);
    var svgWidth = svg.getAttribute("width");
    var svgHeight = svg.getAttribute("height");
    var g = svg.down("g");
    g.dom.setAttribute("transform", "scale(0.75 0.75)");
    console.log("svg props(w,h)", svgWidth, svgHeight)
}