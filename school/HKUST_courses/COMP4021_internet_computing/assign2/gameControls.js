
//
// This function shoots a bullet from the player
//
function shootBullet() {
    // Disable shooting for a short period of time
    playSound("swoosh.wav");
    canShoot = false;
    setTimeout("canShoot = true", SHOOT_INTERVAL);
    // Create the bullet using the use node
    var bullet = svgdoc.createElementNS("http://www.w3.org/2000/svg", "use");
    bullet.setAttribute("x", player.position.x + PLAYER_SIZE.w / 2+12);
    bullet.setAttribute("y", player.position.y + PLAYER_SIZE.h / 3 - BULLET_SIZE.h / 2);
    bullet.setAttribute("age", 0);
    bullet.setAttributeNS("http://www.w3.org/1999/xlink", "xlink:href", "#bullet");
    if (player.dir) {
      bullet.setAttribute("facing_right", 1);
    }
    else {
      bullet.setAttribute("facing_right", -1);
      bullet.setAttribute("x", player.position.x - PLAYER_SIZE.w / 2);
    }
    svgdoc.getElementById("bullets").appendChild(bullet);
}


//
// This is the keydown handling function for the SVG document
//
function keydown(evt) {
    var keyCode = (evt.keyCode)? evt.keyCode : evt.getKeyCode();

    switch (keyCode) {
        case "A".charCodeAt(0):
            player.motion = motionType.LEFT;
            player.dir=0;
            break;

        case "D".charCodeAt(0):
            player.motion = motionType.RIGHT;
            player.dir=1;
            break;

        case "W".charCodeAt(0):
            if (player.isOnPlatform()) {
                player.verticalSpeed = JUMP_SPEED;
            }
            break;

        case 32:
            if (canShoot && cheat_mode) {
                shootBullet();
            }
            else if (canShoot && ammo>0) {
                shootBullet();
                ammo-=1;
                svgdoc.getElementById("ammo").firstChild.data = ammo;
            }
            break;

        case "C".charCodeAt(0):
            cheat_mode = true;
            svgdoc.getElementById("player").style.setProperty("opacity",0.5,null);
            break;
        case "V".charCodeAt(0):
            cheat_mode = false;
            svgdoc.getElementById("player").style.setProperty("opacity",1,null);
            break;

    }
}


//
// This is the keyup handling function for the SVG document
//
function keyup(evt) {
    // Get the key code
    var keyCode = (evt.keyCode)? evt.keyCode : evt.getKeyCode();

    switch (keyCode) {
        case "A".charCodeAt(0):
            if (player.motion == motionType.LEFT) player.motion = motionType.NONE;
            break;

        case "D".charCodeAt(0):
            if (player.motion == motionType.RIGHT) player.motion = motionType.NONE;
            break;
    }
}
