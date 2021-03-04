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
}


//
// Below are constants used in the game
//
var PLAYER_SIZE = new Size(40, 40);         // The size of the player
var SCREEN_SIZE = new Size(600, 560);       // The size of the game screen
var PLAYER_INIT_POS  = new Point(0, 0);     // The initial position of the player
var MONSTER_SIZE = new Size(40, 40);       // The size of a monster
var BULLET_SIZE = new Size(10,10);          // the size of a bullet

var MOVE_DISPLACEMENT = 5;                  // The speed of the player in motion
var JUMP_SPEED = 15;                        // The speed of the player jumping
var VERTICAL_DISPLACEMENT = 1;              // The displacement of vertical speed

var BULLET_SIZE = new Size(10, 10);         // size of a bullet
var BULLET_SPEED = 10.0;                    // speed of a bullet
var SHOOT_INTERVAL = 200.0

var GAME_INTERVAL = 25;                     // The time interval of running the game

var GAME_MAP = new Array(
  "                             ",
  "                             ",
  "                             ",
  "###                          ",
  "  ##                         ",
  "       ###############       ",
  "   #       ##                ",
  "           ##                ",
  "         #####               ",
  "                             ",
  "                        #####",
  "                      #######",
  "           ####      ########",
  "                             ",
  "                             ",
  "                             ",
  "#################            ",
  "                       ######",
  "                             ",
  "                    #########",
  "                             ",
  "#######       ###############",
  "                             ",
  "         #######             ",
  "###                ###       ",
  "######                       ",
  "#############################",
  "#############################"
);


//
// Variables in the game
//
var motionType = {NONE:0, LEFT:1, RIGHT:2}; // Motion enum

var svgdoc = null;                          // SVG root document node
var player = null;                          // The player object
var gameInterval = null;                    // The interval
var game_score = 0;                         // The game_score



var can_shoot = true;                       // flag indicating whether player can shoot a bullet
var zoom = 1.0;                             // The zoom level of the screen


//
// function to create platform when the game is loaded.
//
function createPlatforms() {
  console.log("createPlatforms()");
  var platforms = svgdoc.getElementById("platforms");

  console.log(SCREEN_SIZE);
  for (var y=0; y<(SCREEN_SIZE.h/20); ++y) {
    var start=null, end=null;

    for (var x=0; x<=(SCREEN_SIZE.w/20); ++x) {
      if (start==null && GAME_MAP[y].charAt(x) == '#')
        start = x;
      if (start!=null && GAME_MAP[y].charAt(x) == ' ')
        end = x-1;
      if (start!=null && x == (SCREEN_SIZE.w/20))
        end = x;
      if (start!=null && end!=null) {
        console.log("new platform");
        var newPlatform = svgdoc.createElementNS("http://www.w3.org/2000/svg", "rect");

        newPlatform.setAttribute("x", start*20);
        newPlatform.setAttribute("y", y*20);
        newPlatform.setAttribute("width", (end-start)*20);
        newPlatform.setAttribute("height", 20);
        newPlatform.setAttribute("style", "fill:red");

        platforms.appendChild(newPlatform);

        start = end =null;
      }
    }
  }

}

function spawnMonster(target_x, target_y) {
    console.log("spawnMonster()");
    var monster = svgdoc.createElementNS("http://www.w3.org/2000/svg", "use");

    monster.setAttributeNS("http://www.w3.org/1999/xlink", "xlink:href", "#monster");
    monster.setAttribute("x", target_x);
    monster.setAttribute("y", target_y);

    svgdoc.getElementById("monsters").appendChild(monster);
}

//
// The load function for the SVG document
//
function load(evt) {
    console.log("load()");

    // Set the root node to the global variable
    svgdoc = evt.target.ownerDocument;
    createPlatforms();
    spawnMonster(200, 30);
    spawnMonster(360, 300);
    // Attach keyboard events
    svgdoc.documentElement.addEventListener("keydown", keydown, false);
    svgdoc.documentElement.addEventListener("keyup", keyup, false);

    // Remove text nodes in the 'platforms' group
    cleanUpGroup("platforms", true);

    // Create the player
    player = new Player();

    // Start the game interval
    gameInterval = setInterval("gamePlay()", GAME_INTERVAL);
}
