using UnityEngine;
using System.Collections;

public class ArmRotation : MonoBehaviour {	// Recommended to be a parent of Weapon class

	private float targetAngle=0;
	private float armScale_y=1;
	private float nextFlip = 0;

	// Use this for initialization
	void Start () {
		armScale_y = transform.localScale.y;
	}


	public void aimTarget(Vector3 targetPosition){
		Vector3 difference = targetPosition - transform.position;
		targetAngle = Mathf.Atan2 (difference.y, difference.x) * Mathf.Rad2Deg;
		difference.Normalize ();
		transform.rotation = Quaternion.Euler (0f, 0f, targetAngle);
		//Debug.Log ((targetAngle));
		//float currentScale_y = transform.localScale.y;
		if (nextFlip<Time.time && Mathf.Abs (targetAngle) > 90)
		{
			transform.localScale = new Vector3 (transform.localScale.x, armScale_y * -1, 1);
			nextFlip=Time.time+0.2f;
		}
		else if (nextFlip<Time.time)
		{
			transform.localScale = new Vector3(transform.localScale.x,armScale_y,1);
			nextFlip=Time.time+0.3f;
		}

	}

}
