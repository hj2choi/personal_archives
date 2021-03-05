var blueData = Array.apply(null, Array(10)).map(Number.prototype.valueOf,0);   //Initialize an zero array with length 10
var redData = Array.apply(null, Array(10)).map(Number.prototype.valueOf,0);   //Initialize an zero array with length 10
var NS = "http://www.w3.org/2000/svg"; //The svg namespace
//DEFINE YOUR VARIABLES UP HERE
var barWidth = 20;
var color = ['#0000ff', '#ff0000'];

//

randomizer(blueData);
randomizer(redData);

//Generate array data randomly
function randomizer(d) {
    for (var i = 0, len = d.length; i < len; i++) {
        d[i] = Math.random();
    }
}

//the horizontal timeline
var xAxisSVG = function(startP, length, width, stroke){
    var tempSVG = document.createElementNS(NS, 'line');
    tempSVG.setAttribute('x1', startP.x);
    tempSVG.setAttribute('y1', startP.y);
    tempSVG.setAttribute('x2', startP.x + length);
    tempSVG.setAttribute('y2', startP.y);
    tempSVG.style.stroke = stroke;
    tempSVG.style.strokeWidth = width;
    return tempSVG;
};

//bar
var barSVG = function(startP, width, height, fill){
    //PUT YOUR DISPLAYING CODE BELOW
    var SVGObj = document.createElementNS(NS, 'rect');
    SVGObj.setAttribute('x',startP.x);
    SVGObj.setAttribute('y',startP.y);
    SVGObj.setAttribute('height',height);
    SVGObj.setAttribute('width',width);
    SVGObj.style.fill = fill;
    return SVGObj

}

var rect = function(x, y, h, w, fill){
  var SVGObj = document.createElementNS(NS, 'rect');
  SVGObj.setAttribute('x',x);
  SVGObj.setAttribute('y',y);
  SVGObj.setAttribute('height',h);
  SVGObj.setAttribute('width',w);
  SVGObj.style.fill = fill;
  return SVGObj;
}

//The display function
function display(){
    var svgContainer = document.getElementById('svg');
    var barHeightTotal = svgContainer.clientHeight;
    var barWidthTotal = svgContainer.clientWidth;

    var svg = document.createElementNS(NS, 'svg');  //create an svg element
    svg.setAttribute('width', barWidthTotal);
    svg.setAttribute('height', barHeightTotal);
    svgContainer.appendChild(svg);  //append the svg element to the container

    //draw the horizontal timeline
    var xAxis = xAxisSVG({x: 0, y: barHeightTotal}, barWidthTotal, 5, '#000000');
    svg.appendChild(xAxis);

    //draw bars
    //PUT YOUR DISPLAYING CODE BELOW
    var myRect = rect(0,0,10,20,"#0000ff");
    svg.appendChild(myRect);

    for (var i=0; i<10; i++)
    {
      var barHeight = blueData[i]*300;
      var myBar = barSVG({x: barWidth*3*i, y: barHeightTotal-barHeight}, barWidth, barHeight, "#0000ff");
      svg.appendChild(myBar);
      barHeight = redData[i]*300;
      myBar = barSVG({x: barWidth*3*i+barWidth, y: barHeightTotal-barHeight}, barWidth, barHeight, "#ff0000");
      svg.appendChild(myBar);
    }
    var barHeight = 300;
    //var myBar = barSVG({x: 0, y: barHeightTotal-barHeight}, 20, barHeight, "#0000ff");
    //svg.appendChild(myBar);

//
}
