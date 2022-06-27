var ave = 0;
var arrayData = Array.apply(null, Array(10)).map(Number.prototype.valueOf,0);   //Initialize an zero array with length 10
//DEFINE YOUR VARIABLES UP HERE


//

randomizer(arrayData);

//Generate array data randomly
function randomizer(d) {
    for (var i = 0, len = d.length; i < len; i++) {
        d[i] = Math.random();
    }
}

//The averaging function
function averager(d){
//PUT YOUR AVERAGE CODE BELOW
    for (var i=0; i<d.length; i++)
    {
      ave+=d[i];
    }
    ave/=d.length;
    console.log(ave);
//
}

//The display function
function display(){
//PUT YOUR DISPLAYING CODE BELOW
    var listHtml="<ul>";
    for (var i=0; i<arrayData.length; i++)
    {
      listHtml=listHtml+"<li>"+arrayData[i]+"</li>";
    }
    listHtml+="</ul>";
    document.getElementById("array").innerHTML=listHtml;

    averager(arrayData);

//
}
