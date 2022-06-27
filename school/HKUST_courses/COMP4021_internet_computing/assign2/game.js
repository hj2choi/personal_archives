function playSound(soundfile) {
    var audio = new Audio(soundfile);
    audio.play();
}


function updateScore(add) {
  score+=add*zoom;
  svgdoc.getElementById("score").firstChild.data = score;d

function countdown() {
  if (game_time<=0) {
    playSound("gunhit.wav");
    gameOver("time's up!!");
  }
  else {
    game_time-=1;
    svgdoc.getElementById("timer").firstChild.data = game_time;
    svgdoc.getElementById("timer_bar").setAttribute("width",(game_time/GAME_TIME)*140);
  }
}

function gameOver(message) {
  // Clear the game interval
  clearInterval(gameInterval);
  clearInterval(game_timer);

  var highScoreTable = getHighScoreTable();

  setHighScoreTable(highScoreTable);

  showHighScoreTable(highScoreTable);
  svgdoc.getElementById("gameoverMessage").textContent = message;

}

//
// This function checks collision
//
function collisionDetection() {
    // Check whether the player enters the portal
    if (intersect(new Point(560, 20), new Size(30,30), player.position, PLAYER_SIZE)) {
        player.position.x = 20;
        player.position.y = 160;
    }


    // Check whether the player collides with a goodstuff
    var goodstuffs_there = false;
    var goodstuffs = svgdoc.getElementById("goodstuffs");
    for (var i = 0; i < goodstuffs.childNodes.length; i++) {
        goodstuffs_remain = true;
        var goodstuff = goodstuffs.childNodes.item(i);
        var x = parseInt(goodstuff.getAttribute("x"));
        var y = parseInt(goodstuff.getAttribute("y"));

        if (intersect(new Point(x, y), GOODSTUFF_SIZE, player.position, PLAYER_SIZE)) {
            updateScore(24);
            playSound("pin_drop.wav");
            goodstuffs.removeChild(goodstuff);
            i--;
            return;
        }
    }


    // Check whether the player collides with a monster
    var monsters = svgdoc.getElementById("monsters");
    for (var i = 0; i < monsters.childNodes.length; i++) {
        var monster = monsters.childNodes.item(i);
        var x = parseInt(monster.getAttribute("real_x"));
        var y = parseInt(monster.getAttribute("real_y"));

        if (!cheat_mode && intersect(new Point(x, y), MONSTER_SIZE, player.position, PLAYER_SIZE)) {
            playSound("gunhit.wav");
            gameOver("you were killed..");
            return;
        }
    }


    if (!goodstuffs_there && intersect(new Point(20, 20), new Size(30,30), player.position, PLAYER_SIZE)) {
        updateScore(game_time+100/zoom);
        playSound("cinematic-impact.wav");
        gameOver("VICTORY!!!");
    }

    // Check whether a bullet hits a monster
    var bullets = svgdoc.getElementById("bullets");
    for (var i = 0; i < bullets.childNodes.length; i++) {
        var bullet = bullets.childNodes.item(i);
        var x = parseInt(bullet.getAttribute("x"));
        var y = parseInt(bullet.getAttribute("y"));

        for (var j = 0; j < monsters.childNodes.length; j++) {
            var monster = monsters.childNodes.item(j);
            var mx = parseInt(monster.getAttribute("real_x"));
            var my = parseInt(monster.getAttribute("real_y"));
            if (monster.getAttribute("type")=="bullet") {
                continue;
            }

            if (intersect(new Point(x, y), BULLET_SIZE, new Point(mx, my), MONSTER_SIZE)) {
                playSound("punch.wav");
                monsters.removeChild(monster);
                j--;
                bullets.removeChild(bullet);
                i--;

                //write some code to update the score
                updateScore(35);
            }
        }
    }
}


//
// This function updates the position of the bullets
//
function moveBullets() {
    // Go through all bullets
    var bullets = svgdoc.getElementById("bullets");
    for (var i = 0; i < bullets.childNodes.length; i++) {
        var node = bullets.childNodes.item(i);

        // Update the position of the bullet
        var x = parseInt(node.getAttribute("x"));
        var dir = parseInt(node.getAttribute("facing_right"));
        var age = parseInt(node.getAttribute("age"));
        node.setAttribute("age",age+1);
        node.setAttribute("x", x + age/50*(dir*BULLET_SPEED));
        // size as well

        // If the bullet is not inside the screen delete it from the group
        if (x > SCREEN_SIZE.w || x<0) {
            bullets.removeChild(node);
            i--;
        }
    }
}

//
// THIS function moves monster in random manner
// SERIOUSLY DO NOT MESS UP WITH THIS CODE
function moveMonsters() {
    // Go through all bullets
    var monsters = svgdoc.getElementById("monsters");
    for (var i = 0; i < monsters.childNodes.length; i++) {
        var node = monsters.childNodes.item(i);

        // Update the position of the bullet
        var real_x = parseFloat(node.getAttribute("real_x"));
        var real_y = parseFloat(node.getAttribute("real_y"));
        var border_left = parseInt(node.getAttribute("border_left"));
        var border_right = parseInt(node.getAttribute("border_right"));
        var dir = parseInt(node.getAttribute("dir"));

        // if bullet
        if (node.getAttribute("type")=="bullet") {

            node.setAttribute("real_x", real_x + (dir*3));
            node.setAttribute("x", real_x);
            if (real_x > SCREEN_SIZE.w || real_x<0) {
                monsters.removeChild(node);
                i--;
            }

            break;
        }
        node.setAttribute("real_x", real_x + (dir*2));


        // animateTimer
        var animateTimer = parseInt(node.getAttribute("animateTimer"));
        if (animateTimer==50) {
            node.setAttribute("y", real_y+2);
        }
        if (animateTimer==0) {
            node.setAttribute("y", real_y-2);
            animateTimer=100;
        }
        animateTimer--;
        node.setAttribute("animateTimer", animateTimer);


        // change direction randomly
        if (Math.random() < 0.012) {
          node.setAttribute("dir", -1);
        }
        else if (Math.random() < 0.024) {
          node.setAttribute("dir", 1);
        }
        else if (Math.random() < 0.04) {
          node.setAttribute("dir", 0);
        }
        if (dir==0) {
          dir =1;
        }

        var scale_modifier_translate=0;
        if (dir==-1) {
          scale_modifier_translate = MONSTER_SIZE.w;
        }

        node.setAttribute("transform", "scale("+dir+",1)");

        // If the bullet is not inside the screen delete it from the group

        if (real_x > border_right) {
          node.setAttribute("dir", -dir);
          node.setAttribute("real_x", border_right);
        }
        if (real_x < border_left) {
          node.setAttribute("dir", -dir);
          node.setAttribute("real_x", border_left);
        }


        // render
        node.setAttribute("x", dir*real_x-scale_modifier_translate);

        // if special, shoot occationally
        if (node.getAttribute("type")=="special" && dir==1) {
            if (Math.random() < 0.05 && enemy_canshoot) {
                enemy_canshoot = false;
                setTimeout("enemy_canshoot = true", 3500);
                console.log("spawn enemy bullet");
                createMonster(real_x+30, real_y+20, real_x+30, real_x+31, 1, false, true);
            }
        }

    }
}


//
// This function updates the position and motion of the player in the system
//
function gamePlay() {
    // Check collisions
    collisionDetection();

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

    // Move the bullets
    moveBullets();
    moveMonsters();
    updateScreen();
}


//
// This function updates the position of the player's SVG object and
// set the appropriate translation of the game screen relative to the
// the position of the player
//
function updateScreen() {
    // Transform the player
    var face_direction=1;
    var face_dir_translate=0;
    if (!player.dir) {
      face_direction=-1;
      face_dir_translate=PLAYER_SIZE.w;
      svgdoc.getElementById("player_name").setAttribute("transform","translate(" + (0+face_dir_translate) + "," + 0 + ")scale(-1,1)");
    }
    else {
      svgdoc.getElementById("player_name").setAttribute("transform","translate(" + (0+face_dir_translate) + "," + 0 + ")scale(1,1)");
    }
    player.node.setAttribute("transform", "translate(" + (player.position.x+face_dir_translate) + "," + player.position.y + ") scale(" + face_direction+",1)");

    // Calculate the scaling and translation factors
    var scale = new Point(zoom, zoom);
    var translate = new Point();

    translate.x = SCREEN_SIZE.w / 2.0 - (player.position.x + PLAYER_SIZE.w / 2) * scale.x;
    if (translate.x > 0)
        translate.x = 0;
    else if (translate.x < SCREEN_SIZE.w - SCREEN_SIZE.w * scale.x)
        translate.x = SCREEN_SIZE.w - SCREEN_SIZE.w * scale.x;

    translate.y = SCREEN_SIZE.h / 2.0 - (player.position.y + PLAYER_SIZE.h / 2) * scale.y;
    if (translate.y > 0)
        translate.y = 0;
    else if (translate.y < SCREEN_SIZE.h - SCREEN_SIZE.h * scale.y)
        translate.y = SCREEN_SIZE.h - SCREEN_SIZE.h * scale.y;


    // Transform the game area
    svgdoc.getElementById("gamearea").setAttribute("transform", "translate(" + translate.x + "," + translate.y + ") scale(" + scale.x + "," + scale.y + ")");
}
