using UnityEngine;
using System.Collections;

public class BulletTrail : MonoBehaviour {

	public int moveSpeed = 150;

	// Update is called once per frame
	void Update () {
		transform.Translate (Vector3.right * Time.deltaTime * moveSpeed);
		Destroy (this.gameObject, 0.2f);
	}
}
