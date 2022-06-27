//
// A score record JavaScript class to store the name and the score of a player
//
function ScoreRecord(name, score) {
    this.name = name;
    this.score = score;
}
var wheresMyScore =0;

//
// This function reads the high score table from the cookies
//
function getHighScoreTable() {
  console.log("getHighScoreTable()");
    var table = new Array();

    for (var i = 0; i < 10; i++) {
        // Contruct the cookie name
        var cookieName = "player"+i;
        // Get the cookie value using the cookie name
        var cookieValue = getCookie(cookieName);
        console.log(cookieValue);
        // If the cookie does not exist exit from the for loop
        if(cookieValue==null){
            break;
        }
        // Extract the name and score of the player from the cookie value
        var nameTemp2 = cookieValue.split("~")[0];
        var scoreTemp2 = cookieValue.split("~")[1];

        table.push(new ScoreRecord(nameTemp2,scoreTemp2));
        // Add a new score record at the end of the array
    }
    var counter = 0;
    if(!table[0]){
        wheresMyScore=0;
        table.push(new ScoreRecord(player_name,score));
        return table;
    }
    while(table[counter].score>=score){
        counter++;
        if(!table[counter]){break;}
    }
    wheresMyScore=counter;
    table.splice(counter,0,new ScoreRecord(player_name,score));

    console.log(document.cookie);
    return table;
}


//
// This function stores the high score table to the cookies
//
function setHighScoreTable(table) {
  console.log("setHighScoreTable()");
    for (var i = 0; i < 10; i++) {
        // If i is more than the length of the high score table exit
        // from the for loop
        if (i >= table.length) break;

        // Contruct the cookie name
        var cookieName = "player"+i;
        var cookieData = table[i].name+"~"+table[i].score;
        setCookie(cookieName,cookieData);
        // Store the ith record as a cookie using the cookie name
    }
}


//
// This function adds a high score entry to the text node
//
function addHighScore(record, i) {
  console.log("addHighScoreTable()");
    console.log(record);
    //console.log(node);
    // Create the name text span
    var name = svgdoc.createElementNS("http://www.w3.org/2000/svg", "tspan");

    // Set the attributes and create the text
    name.setAttribute("x",120);
    name.setAttribute("y",60+i*35);
    name.textContent = record.name;
    if (i==wheresMyScore) {
        name.style.setProperty("fill","blue");
    }
    //name.setAttributeNS("http://www.w3.org/1999/xlink","xlink:href","#highscoretext");
    svgdoc.getElementById("highscoretext").appendChild(name);
    // Add the name to the text node

    // Create the score text span
    var score = svgdoc.createElementNS("http://www.w3.org/2000/svg", "tspan");
    score.setAttribute("x",360);
    score.setAttribute("y",60+i*35);
    score.textContent = record.score;
    if (i==wheresMyScore) {
        score.style.setProperty("fill","blue");
    }
    //name.setAttributeNS("http://www.w3.org/1999/xlink","xlink:href","#highscoretext");
    svgdoc.getElementById("highscoretext").appendChild(score);
    // Set the attributes and create the text

    // Add the name to the text node
}


//
// This function shows the high score table to SVG
//
function showHighScoreTable(table) {
    console.log("my High score is found at: "+wheresMyScore);
    // Show the table
    console.log("showhighscoretable");
    var node = svgdoc.getElementById("highscoretable");
    node.style.setProperty("visibility", "visible", null);

    // Get the high score text node
    var node = svgdoc.getElementById("highscoretext");

    for (var i = 0; i < 10; i++) {
        // If i is more than the length of the high score table exit
        // from the for loop
        if (i >= table.length) break;

        // Add the record at the end of the text node
        addHighScore(table[i], i);
    }
}


//
// The following functions are used to handle HTML cookies
//

//
// Set a cookie
//
function setCookie(name, value, expires, path, domain, secure) {
    var curCookie = name + "=" + escape(value) +
        ((expires) ? "; expires=" + expires.toGMTString() : "") +
        ((path) ? "; path=" + path : "") +
        ((domain) ? "; domain=" + domain : "") +
        ((secure) ? "; secure" : "");
    document.cookie = curCookie;
}


//
// Get a cookie
//
function getCookie(name) {
    var dc = document.cookie;
    var prefix = name + "=";
    var begin = dc.indexOf("; " + prefix);
    if (begin == -1) {
        begin = dc.indexOf(prefix);
        if (begin != 0) return null;
    } else
        begin += 2;
    var end = document.cookie.indexOf(";", begin);
    if (end == -1)
        end = dc.length;
    return unescape(dc.substring(begin + prefix.length, end));
}


//
// Delete a cookie
//
function deleteCookie(name, path, domain) {
    if (get_cookie(name)) {
        document.cookie = name + "=" +
        ((path) ? "; path=" + path : "") +
        ((domain) ? "; domain=" + domain : "") +
        "; expires=Thu, 01-Jan-70 00:00:01 GMT";
    }
}
