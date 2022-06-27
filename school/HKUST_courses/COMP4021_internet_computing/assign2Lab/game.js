Player.prototype.isOnPlatform = function() {
    //console.log("player.prototype.isOnPlatform()");
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
            this.position.y + PLAYER_SIZE.h == y) return true;
    }
    if (this.position.y + PLAYER_SIZE.h == SCREEN_SIZE.h) return true;

    return false;
}

Player.prototype.collidePlatform = function(position) {
    //console.log("player.prototype.collidePlatform()");
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
    //console.log("player.prototype.collideScreen()");
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
// This function removes all/certain nodes under a group
//
function cleanUpGroup(id, textOnly) {
    console.log("cleanUpGroup()");
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


function moveBullets() {
    // Go through all bullets
    var bullets = svgdoc.getElementById("bullets");
    for (var i = 0; i < bullets.childNodes.length; i++) {
        var node = bullets.childNodes.item(i);
        // Update the position of the bullet
        //console.log("bullet node "+i);
        //console.log(node);
        var x = parseInt(node.getAttribute("x"));
        var y = parseInt(node.getAttribute("y"));

        node.setAttribute("x", x+BULLET_SPEED);

        // If the bullet is not inside the screen delete it from the group
        if (x < 0 || x > SCREEN_SIZE.w || y < 0 || y > SCREEN_SIZE.h) {
            bullets.removeChild(node);
        }
    }
}

function collisionDetection() {
    // Check whether the player collides with a monster
    var monsters = svgdoc.getElementById("monsters");
    for (var i = 0; i < monsters.childNodes.length; i++) {
        var monster = monsters.childNodes.item(i);

        // For each monster check if it overlaps with the player
        // if yes, stop the game
        var monster_x = parseInt(monster.getAttribute("x"));
        var monster_y = parseInt(monster.getAttribute("y"));
        var monster_pos = new Point(monster_x, monster_y);
        // first check for right and left borders of player position.
        /*monster_x < player.position.x+PLAYER_SIZE.w && player.position.x < monster_x+MONSTER_SIZE.w*/
        if (intersect(monster_pos, MONSTER_SIZE, player.position, PLAYER_SIZE)) {
            console.log("PLAYER_MONSTER COLLISION");
            clearInterval(gameInterval);
        }
    }

    // Check whether a bullet hits a monster
    var bullets = svgdoc.getElementById("bullets");
    for (var i = 0; i < bullets.childNodes.length; i++) {
        var bullet = bullets.childNodes.item(i);
        // For each bullet check if it overlaps with any monster
        // if yes, remove both the monster and the bullet
        var monsters = svgdoc.getElementById("monsters");
        for (var j = 0; j < monsters.childNodes.length; j++) {
            var monster = monsters.childNodes.item(j);

            // For each monster check if it overlaps with the player
            // if yes, stop the game
            var monster_x = parseInt(monster.getAttribute("x"));
            var monster_y = parseInt(monster.getAttribute("y"));
            var monster_pos = new Point(monster_x, monster_y);
            var bullet_x = parseInt(bullet.getAttribute("x"));
            var bullet_y = parseInt(bullet.getAttribute("y"));
            var bullet_pos = new Point(bullet_x, bullet_y);

            // first check for right and left borders of player position.
            if (intersect(monster_pos, MONSTER_SIZE, bullet_pos, BULLET_SIZE)) {

                    console.log("BULLET HIT");
                    bullets.removeChild(bullet);
                    monsters.removeChild(monster);
                    updateScore(5);
            }
        }

    }
}

function updateScore(increment) {
  game_score+=increment*zoom;
  svgdoc.getElementById("score").firstChild.data = game_score;
}

function zoomGameArea() {
    zoom=2.0;
}

//
// This function updates the position and motion of the player in the system
//
function gamePlay() {
    //console.log("gamePlay()");
    // Check whether the player is on a platform
    var isOnPlatform = player.isOnPlatform();

    // Update player position
    var displacement = new Point();

    // Move left or right
    if (player.motion == motionType.LEFT)
        displacement.x = -MOVE_DISPLACEMENT;
    if (player.motion == motionType.RIGHT)
        displacement.x = MOVE_DISPLACEMENT;

    // Fall
    if (!isOnPlatform && player.verticalSpeed <= 0) {
        displacement.y = -player.verticalSpeed;
        player.verticalSpeed -= VERTICAL_DISPLACEMENT;
    }

    // Jump
    if (player.verticalSpeed > 0) {
        displacement.y = -player.verticalSpeed;
        player.verticalSpeed -= VERTICAL_DISPLACEMENT;
        if (player.verticalSpeed <= 0)
            player.verticalSpeed = 0;
    }

    // Get the new position of the player
    var position = new Point();
    position.x = player.position.x + displacement.x;
    position.y = player.position.y + displacement.y;

    // Check collision with platforms and screen
    player.collidePlatform(position);
    player.collideScreen(position);

    // Set the location back to the player object (before update the screen)
    player.position = position;


    moveBullets();
    collisionDetection();
    updateScreen();
}

//
// This function updates the position of the player's SVG object and
// set the appropriate translation of the game screen relative to the
// the position of the player
//
function updateScreen() {
    //console.log("updateScreen()");
    // Transform the player
    player.node.setAttribute("transform", "translate(" + player.position.x + "," + player.position.y + ")");

    // Calculate the scaling and translation factors

    // Add your code here

    if (zoom!=1.0) {
        var gamearea = svgdoc.getElementById("gamearea");
        var player_cx = - (player.position.x + PLAYER_SIZE.w / 2);//*zoom;
        var player_cy = - (player.position.y + PLAYER_SIZE.h / 2);//*zoom;

        //var gamearea_margin_x = ;
        //var gamearea_margin_x = ;

        gamearea_translate_x = player_cx;// + SCREEN_SIZE.w/2
        gamearea_translate_y = player_cy;// + SCREEN_SIZE.h/2

        //console.log("x = "+player_cx+", y = "+player_cy);
        gamearea.setAttribute("transform", "translate("+gamearea_translate_x+", "+gamearea_translate_y+") scale("+zoom+")");
    }


}
