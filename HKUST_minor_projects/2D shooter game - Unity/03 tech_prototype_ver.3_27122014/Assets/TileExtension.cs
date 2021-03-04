using UnityEngine;
using System.Collections;

[RequireComponent (typeof(SpriteRenderer))]

public class TileExtension : MonoBehaviour {

	public int offsetX = 2;				// offset to prevent weird errors
	public int maximumExtension = 1048;	// maximum number of extension
	private bool hasRightExtension = false;
	private bool hasLeftExtension = false;

	public bool reverseScale =true;	// used if an object is not tilable

	private float spriteWidth = 0f;
	private Camera cam;
	private Transform myTransform;

	void Awake() {	// do all refrencing between scripts
		cam = Camera.main;
		myTransform = transform;
	}

	// Use this for initialization
	void Start () {
		//SpriteRenderer sRenderer = GetComponent <SpriteRenderer>();
		spriteWidth = myTransform.renderer.bounds.size.x;//sRenderer.sprite.bounds.size.x * sRenderer.transform.localScale.x;//myTransform.localScale.x;
	}
	
	// Update is called once per frame
	void Update () {
		if (!(hasRightExtension && hasLeftExtension) && maximumExtension>0) {
			float camHorizontalLength = cam.orthographicSize * Screen.width/Screen.height;
			float edgeVisiblePositionRight = (myTransform.position.x + spriteWidth/2) - camHorizontalLength;
			float edgeVisiblePositionLeft = (myTransform.position.x - spriteWidth/2) + camHorizontalLength;

			if (cam.transform.position.x >= edgeVisiblePositionRight - offsetX && !hasRightExtension)
			{
				createNewExtension(1);
				hasRightExtension=true;
			}
			else if (cam.transform.position.x <= edgeVisiblePositionLeft + offsetX && !hasLeftExtension)
			{
				createNewExtension(-1);
				hasLeftExtension=true;
			}
		}
	}

	void createNewExtension(int direction) {	// 1=right, -1=left
		// calculate position for new extension
		Vector3 newPosition = new Vector3 (myTransform.position.x + spriteWidth * direction, myTransform.position.y, myTransform.position.z);
		Transform newExtension = (Transform)Instantiate (myTransform, newPosition, myTransform.rotation);

		// if not tilable, reverse scale of object for natural transition
		if (reverseScale)
		{
			newExtension.localScale = new Vector3 (newExtension.localScale.x*-1,newExtension.localScale.y,newExtension.localScale.z);
		}

		newExtension.parent = myTransform.parent;
		newExtension.GetComponent<TileExtension> ().maximumExtension = this.maximumExtension - 1;
		if (direction > 0) {
			newExtension.GetComponent<TileExtension>().hasLeftExtension=true;
		}
		else
			newExtension.GetComponent<TileExtension>().hasRightExtension=true;
	}

}
