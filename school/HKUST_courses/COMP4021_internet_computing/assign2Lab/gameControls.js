
function shootBullet() {
    console.log("shoot");
    //Disable shooting for short period of time
    can_shoot = false;
    setTimeout("can_shoot = true", SHOOT_INTERVAL);

    // create bullet
    var bullet = svgdoc.createElementNS("http://www.w3.org/2000/svg", "use");

    // set the position of the bullet
    bullet.setAttributeNS("http://www.w3.org/1999/xlink", "xlink:href", "#bullet");
    bullet.setAttribute("x", player.position.x + PLAYER_SIZE.w / 2);
    bullet.setAttribute("y", player.position.y + PLAYER_SIZE.h / 2);


    // set the herf of the use node to the bullet defined in the defs node


    // Append the bullet to the bullet group


    svgdoc.getElementById("bullets").appendChild(bullet);

}


//
// This is the keydown handling function for the SVG document
//
function keydown(evt) {
    //console.log("keydown()");
    var keyCode = (evt.keyCode)? evt.keyCode : evt.getKeyCode();

    switch (keyCode) {
        case 'A'.charCodeAt(0):
            player.motion = motionType.LEFT;
            break;

        case 'D'.charCodeAt(0):
            player.motion = motionType.RIGHT;
            break;


        // Add your code here
        case 'W'.charCodeAt(0):
            if (player.isOnPlatform()) {
                //Set the player vertical speed
                player.verticalSpeed = JUMP_SPEED;
            }
            break;

        case 32:
            if (can_shoot)
                shootBullet();
            break;
        //32 is for space
    }
}


//
// This is the keyup handling function for the SVG document
//
function keyup(evt) {
    //console.log("keyup()");
    // Get the key code
    var keyCode = (evt.keyCode)? evt.keyCode : evt.getKeyCode();

    switch (keyCode) {
        case 'A'.charCodeAt(0):
            if (player.motion == motionType.LEFT) player.motion = motionType.NONE;
            break;

        case 'D'.charCodeAt(0):
            if (player.motion == motionType.RIGHT) player.motion = motionType.NONE;
            break;
    }
}
