#pragma strict

var moveUp : KeyCode;
var moveDown : KeyCode;
var moveLeft : KeyCode;
var moveRight : KeyCode;
var speed : float = 5;

function Start () {

}

function halt()
{
	rigidbody2D.velocity.y=0;
	rigidbody2D.velocity.x=0;
}


function Update () {
	halt();
	if (Input.GetKey(moveUp))
		rigidbody2D.velocity.y=speed;
	if (Input.GetKey(moveDown))
		rigidbody2D.velocity.y=-speed;
	if (Input.GetKey(moveLeft))
		rigidbody2D.velocity.x=-speed;
	if (Input.GetKey(moveRight))
		rigidbody2D.velocity.x=speed;
}