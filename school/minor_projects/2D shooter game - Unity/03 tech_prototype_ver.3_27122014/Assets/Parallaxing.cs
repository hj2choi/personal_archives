using UnityEngine;
using System.Collections;


/*
for background maximum z axis value = 55
*/

public class Parallaxing : MonoBehaviour {

	public Transform[] backgrounds;		// array of all background and foregrounds to be parallaxed
	private float[] parallaxScales;		// proportion of camera's movement to move background by
	public float smoothing = 1f;				// how smooth the parallex going to be

	private Transform cam;			// reference to main camera transform
	private Vector3 previousCamPos;	// the position of camera in previous frame


	// is called before start()
	void Awake(){
		cam = Camera.main.transform;

	}


	// Use this for initialization
	void Start () {
		previousCamPos = cam.position;

		// assign corresponding parallax scales
		parallaxScales = new float[backgrounds.Length];
		for (int i=0; i<backgrounds.Length; i++)
			parallaxScales[i] = -backgrounds[i].position.z;

	}
	
	// Update is called once per frame
	void Update () {
		for (int i=0; i<backgrounds.Length; i++){
			float parallax = (previousCamPos.x - cam.position.x) * parallaxScales[i];
			float backgroundTargetPosX = backgrounds[i].position.x+parallax;
			parallax = (previousCamPos.y - cam.position.y) * parallaxScales[i];
			float backgroundTargetPosY = backgrounds[i].position.y+parallax;
			Vector3 backgroundTargetPos = new Vector3 (backgroundTargetPosX, backgroundTargetPosY, backgrounds[i].position.z);

			backgrounds[i].position = Vector3.Lerp (backgrounds[i].position, backgroundTargetPos, smoothing*Time.deltaTime);
		}
		previousCamPos = cam.position;
	}
}
