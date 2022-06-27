// The point and size class used in this program
function Point(x, y) {
    this.x = (x)? parseFloat(x) : 0.0;
    this.y = (y)? parseFloat(y) : 0.0;
}

function Size(w, h) {
    this.w = (w)? parseFloat(w) : 0.0;
    this.h = (h)? parseFloat(h) : 0.0;
}

// Helper function for checking intersection between two rectangles
function intersect(pos1, size1, pos2, size2) {
    return (pos1.x < pos2.x + size2.w && pos1.x + size1.w > pos2.x &&
            pos1.y < pos2.y + size2.h && pos1.y + size1.h > pos2.y);
}


// The player class used in this program
function Player() {
    this.node = svgdoc.getElementById("player");
    this.position = PLAYER_INIT_POS;
    this.motion = motionType.NONE;
    this.verticalSpeed = 0;
    this.dir = 1;
}


Player.prototype.isOnPlatform = function() {
    var platforms = svgdoc.getElementById("platforms");
    for (var i = 0; i < platforms.childNodes.length; i++) {
        var node = platforms.childNodes.item(i);
        if (node.nodeName != "rect") continue;

        var x = parseFloat(node.getAttribute("x"));
        var y = parseFloat(node.getAttribute("y"));
        var w = parseFloat(node.getAttribute("width"));
        var h = parseFloat(node.getAttribute("height"));

        if (((this.position.x + PLAYER_SIZE.w > x && this.position.x < x + w) ||
             ((this.position.x + PLAYER_SIZE.w) == x && this.motion == motionType.RIGHT) ||
             (this.position.x == (x + w) && this.motion == motionType.LEFT)) &&
            this.position.y + PLAYER_SIZE.h == y) {
            if (node.getAttribute("type")=="disappearing") {
                removePlatform(node);
            }
            return true;
        }
    }
    if (this.position.y + PLAYER_SIZE.h == SCREEN_SIZE.h) return true;

    return false;
}

Player.prototype.collidePlatform = function(position) {
    var platforms = svgdoc.getElementById("platforms");
    for (var i = 0; i < platforms.childNodes.length; i++) {
        var node = platforms.childNodes.item(i);
        if (node.nodeName != "rect") continue;

        var x = parseFloat(node.getAttribute("x"));
        var y = parseFloat(node.getAttribute("y"));
        var w = parseFloat(node.getAttribute("width"));
        var h = parseFloat(node.getAttribute("height"));
        var pos = new Point(x, y);
        var size = new Size(w, h);

        if (intersect(position, PLAYER_SIZE, pos, size)) {
            position.x = this.position.x;
            if (intersect(position, PLAYER_SIZE, pos, size)) {

                if (this.position.y >= y + h)
                    position.y = y + h;
                else
                    position.y = y - PLAYER_SIZE.h;
                this.verticalSpeed = 0;
            }
        }
    }
}

Player.prototype.collideScreen = function(position) {
    if (position.x < 0) position.x = 0;
    if (position.x + PLAYER_SIZE.w > SCREEN_SIZE.w) position.x = SCREEN_SIZE.w - PLAYER_SIZE.w;
    if (position.y < 0) {
        position.y = 0;
        this.verticalSpeed = 0;
    }
    if (position.y + PLAYER_SIZE.h > SCREEN_SIZE.h) {
        position.y = SCREEN_SIZE.h - PLAYER_SIZE.h;
        this.verticalSpeed = 0;
    }
}


//
// Below are constants used in the game
//
var GAME_TIME = 80;
var PLAYER_SIZE = new Size(40, 40);         // The size of the player
var SCREEN_SIZE = new Size(600, 560);       // The size of the game screen
var PLAYER_INIT_POS  = new Point(0, 420);   // The initial position of the player
var MOVE_DISPLACEMENT = 5;                  // The speed of the player in motion
var JUMP_SPEED = 15;                        // The speed of the player jumping
var VERTICAL_DISPLACEMENT = 1;              // The displacement of vertical speed
var GAME_INTERVAL = 25;                     // The time interval of running the game
var BULLET_SIZE = new Size(20, 35);         // The speed of a bullet
var BULLET_SPEED = 10.0;                    // The speed of a bullet
                                            //  = pixels it moves each game loop
var SHOOT_INTERVAL = 500.0;                 // The period when shooting is disabled
var MONSTER_SIZE = new Size(35, 35);
var GOODSTUFF_SIZE = new Size(30, 30);

var GAME_MAP = new Array(
  "                             ",
  "                             ",
  "                             ",
  "###                          ",
  "####                         ",
  "####   ###############       ",
  "########   ##                ",
  "           ##                ",
  "       #######               ",
  "                             ",
  "                        #####",
  "                      #######",
  "#                     #######",
  "####          #              ",
  "###       #   #              ",
  "###       ########           ",
  "###          ###             ",
  "                      #######",
  "                      #######",
  "####                #########",
  "                           ##",
  "        ####               ##",
  "       ###########       ####",
  "         ##              #   ",
  "##                   ##  #   ",
  "###           ##             ",
  "####   ##    ####            ",
  "#############################"
);

//
// function to create platform when the game is loaded.
//
function createPlatforms() {
  //console.log("createPlatforms()");
  var platforms = svgdoc.getElementById("platforms");

  //console.log(SCREEN_SIZE);
  for (var y=0; y<(SCREEN_SIZE.h/20); ++y) {
    var start=null, end=null;

    for (var x=0; x<=(SCREEN_SIZE.w/20); ++x) {
      if (start==null && GAME_MAP[y].charAt(x) == '#')
        start = x;
      if (start!=null && GAME_MAP[y].charAt(x) == ' ')
        end = x;
      if (start!=null && x == (SCREEN_SIZE.w/20))
        end = x;
      if (start!=null && end!=null) {
        //console.log("new platform");
        var newPlatform = svgdoc.createElementNS("http://www.w3.org/2000/svg", "rect");

        newPlatform.setAttribute("x", start*20);
        newPlatform.setAttribute("y", y*20);
        newPlatform.setAttribute("width", (end-start)*20);
        newPlatform.setAttribute("height", 20);
        newPlatform.setAttribute("style", "fill:grey");
        //newPlatform.setAttribute("style", "fill:url(#disc_gradient)");

        platforms.appendChild(newPlatform);

        start = end =null;
      }
    }
  }


  var newPlatform = svgdoc.createElementNS("http://www.w3.org/2000/svg", "rect");
  newPlatform.setAttribute("x", 25*20);
  newPlatform.setAttribute("y", 3*20);
  newPlatform.setAttribute("width", 5*20);
  newPlatform.setAttribute("height", 20);
  newPlatform.setAttribute("style", "fill:url(#disc_gradient);stroke:black;stroke-width:2");
  newPlatform.setAttribute("type", "disappearing");
  newPlatform.style.setProperty("opacity", 1, null);
  platforms.appendChild(newPlatform);
  newPlatform = svgdoc.createElementNS("http://www.w3.org/2000/svg", "rect");
  newPlatform.setAttribute("x", 21*20);
  newPlatform.setAttribute("y", 25*20);
  newPlatform.setAttribute("width", 5*20);
  newPlatform.setAttribute("height", 20);
  newPlatform.setAttribute("style", "fill:url(#disc_gradient);stroke:black;stroke-width:2");
  newPlatform.setAttribute("type", "disappearing");
  newPlatform.style.setProperty("opacity", 1, null);
  platforms.appendChild(newPlatform);
  newPlatform = svgdoc.createElementNS("http://www.w3.org/2000/svg", "rect");
  newPlatform.setAttribute("x", 8*20);
  newPlatform.setAttribute("y", 12*20);
  newPlatform.setAttribute("width", 8*20);
  newPlatform.setAttribute("height", 20);
  newPlatform.setAttribute("style", "fill:url(#disc_gradient);stroke:black;stroke-width:2");
  newPlatform.setAttribute("type", "disappearing");
  newPlatform.style.setProperty("opacity", 1, null);
  platforms.appendChild(newPlatform);

  start = end =null;
}

//
// Variables in the game
//
var motionType = {NONE:0, LEFT:1, RIGHT:2}; // Motion enum

var svgdoc = null;                          // SVG root document node
var player = null;                          // The player object
var gameInterval = null;                    // The interval
var game_timer = null;
var zoom = 1.0;                             // The zoom level of the screen
var score = 0;                              // The score of the game
var game_time = GAME_TIME;                  // time left
var player_name = "Anonymous";                       // player name
var ammo = 8;
var canShoot = true;                        // A flag indicating whether the player can shoot a bullet
var cheat_mode = false;
var enemy_canshoot = true;

function normalModeLoad(evt) {
  zoom=1.0;
  load(evt);
}

function zoomModeLoad(evt) {
  setZoom();
  load(evt);
}
//
// This function sets the zoom level to 2
//
function setZoom() {
    zoom = 2.0;
}

function retryGame() {
    score=0;
    updateScore(0);
    game_time=GAME_TIME;
    svgdoc.getElementById("timer").firstChild.data = game_time;
    svgdoc.getElementById("timer_bar").setAttribute("width",(game_time/GAME_TIME)*140);
    ammo=8;
    svgdoc.getElementById("ammo").firstChild.data = ammo;
    cheat_mode=false;
    cleanUpGroup("platforms", false);
    cleanUpGroup("goodstuffs", false);
    cleanUpGroup("monsters", false);
    cleanUpGroup("bullets", false);
    cleanUpGroup("highscoretext", false);
    svgdoc.getElementById("highscoretable").style.setProperty("visibility","hidden");
    svgdoc.getElementById("landing_screen").style.setProperty("visibility","visible");

}

//
// The load function for the SVG document
//

function playBackgroundMusic() {
    console.log("start_background_music");
    var audio = new Audio("free-rap-instrumentals.mp3");
    audio.volume = 0.5;
    console.log(audio);
    audio.play();
    setTimeout("playBackgroundMusic()",184000);
}

var loadedAlready = false;
function load(evt) {


    var nameTemp=prompt("enter your character name");
    if (nameTemp) {
      player_name = nameTemp
    }
    console.log("playing as: " + player_name);

    if (!loadedAlready) {
        playBackgroundMusic();
        loadedAlready=true;
    }
    // Set the root node to the global variable
    svgdoc = evt.target.ownerDocument;


    createPlatforms();
    svgdoc.getElementById("player_name").textContent = (player_name);


    // Attach keyboard events
    svgdoc.documentElement.addEventListener("keydown", keydown, false);
    svgdoc.documentElement.addEventListener("keyup", keyup, false);

    // Remove text nodes in the 'platforms' group
    cleanUpGroup("platforms", true);
    //cleanUpGroup("landing_screen", false);
    svgdoc.getElementById("landing_screen").style.setProperty("visibility","hidden");

    // Create the player
    player = new Player();

    // Create the monsters
    if (Math.random()<0.7) {
        createMonster(200, 40, 80, 320, 1, true, false);
        createMonster(360, 140, 320, 500, 1, false, false);
    } else {
        createMonster(360, 140, 280, 340, 1, true, false);
        createMonster(200, 40, 80, 320, 1, false, false);
    }

    if (Math.random()<0.5) {
        createMonster(400, 290, 380, 500, -1, false, false);
    } else {
        createMonster(100, 290, 60, 140, -1, false, false);
    }

    createMonster(50, 180, 30, 150, -1, false, false);
    if (Math.random()<0.5) {
        createMonster(400, 400, 400, 460, -1, false, false);
    } else {
        createMonster(160, 480, 100, 240, -1, false, false);
    }
    createMonster(160, 380, 120, 340, 1, false, false);

    createGoodStuff(40, 220);
    createGoodStuff(540, 500);
    // some randomness in dumb way
    var goodStuffCount=6;
    if (Math.random()<0.35 && goodStuffCount>0){
        createGoodStuff(220, 500);goodStuffCount--;}
    if (Math.random()<0.4 && goodStuffCount>0){
        createGoodStuff(240, 260);goodStuffCount--;}
    if (Math.random()<0.45 && goodStuffCount>0){
        createGoodStuff(200, 380);goodStuffCount--;}
    if (Math.random()<0.5 && goodStuffCount>0){
        createGoodStuff(560, 300);goodStuffCount--;}
    if (Math.random()<0.55 && goodStuffCount>0){
        createGoodStuff(240, 180);goodStuffCount--;}
    if (Math.random()<0.6 && goodStuffCount>0){
        createGoodStuff(220, 500);goodStuffCount--;}
    if (Math.random()<0.7 && goodStuffCount>0){
        createGoodStuff(420, 180);goodStuffCount--;}
    if (Math.random()<0.7 && goodStuffCount>0){
        createGoodStuff(260, 120);goodStuffCount--;}
    if (Math.random()<0.8 && goodStuffCount>0){
        createGoodStuff(80, 300);goodStuffCount--;}
    if (Math.random()<1 && goodStuffCount>0){
        createGoodStuff(0, 340);goodStuffCount--;}
    if (Math.random()<1 && goodStuffCount>0){
        createGoodStuff(300, 400);goodStuffCount--;}
    if (Math.random()<1 && goodStuffCount>0){
        createGoodStuff(460, 400);goodStuffCount--;}
    if (Math.random()<1 && goodStuffCount>0){
        createGoodStuff(560, 80);goodStuffCount--;}

    // Start the game interval
    gameInterval = setInterval("gamePlay()", GAME_INTERVAL);
    svgdoc.getElementById("timer").firstChild.data = game_time;
    game_timer = setInterval("countdown()", 1000);
}


//
// This function removes all/certain nodes under a group
//
function cleanUpGroup(id, textOnly) {
    var node, next;
    var group = svgdoc.getElementById(id);
    node = group.firstChild;
    while (node != null) {
        next = node.nextSibling;
        if (!textOnly || node.nodeType == 3) // A text node
            group.removeChild(node);
        node = next;
    }
}


//
// This function creates the monsters in the game
//
function createMonster(x, y, border_left, border_right, dir, isSpecial, isBullet) {
    var monster = svgdoc.createElementNS("http://www.w3.org/2000/svg", "use");
    var spawn_x = border_left + (border_right-border_left)*Math.random();
    monster.setAttribute("x", spawn_x);
    monster.setAttribute("y", y);
    monster.setAttribute("real_x", spawn_x);
    monster.setAttribute("real_y", y);
    monster.setAttribute("border_right", border_right);
    monster.setAttribute("border_left", border_left);
    monster.setAttribute("dir", dir);

    if (isBullet) {
        monster.setAttribute("type", "bullet");
        monster.setAttributeNS("http://www.w3.org/1999/xlink", "xlink:href", "#enemy_bullet");
    }
    else {
        monster.setAttributeNS("http://www.w3.org/1999/xlink", "xlink:href", "#monster");
        monster.setAttribute("animateTimer", "20");
    }
    if (isSpecial) {
        monster.setAttributeNS("http://www.w3.org/1999/xlink", "xlink:href", "#special_monster");
        monster.setAttribute("type", "special");
    }
    monster.setAttribute("y", y);

    svgdoc.getElementById("monsters").appendChild(monster);
}

function createGoodStuff(x, y) {
  var goodstuff = svgdoc.createElementNS("http://www.w3.org/2000/svg", "use");
  goodstuff.setAttribute("x", x);
  goodstuff.setAttribute("y", y);
  goodstuff.setAttributeNS("http://www.w3.org/1999/xlink", "xlink:href", "#goodstuff");
  svgdoc.getElementById("goodstuffs").appendChild(goodstuff);
}


function removePlatform(node) {
    console.log("removePlatform");
    var platformOpacity = parseFloat(node.style.getPropertyValue("opacity"));
    platformOpacity -= 0.1;
    node.style.setProperty("opacity", platformOpacity, null);
    if (platformOpacity <=0) {
        var platforms = svgdoc.getElementById("platforms");
        platforms.removeChild(node);
    }
}
