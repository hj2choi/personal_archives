using UnityEngine;
using System.Collections;

public class Bullet : MonoBehaviour {

	public float travelSpeed = 30;
	public float damage = 10;
	//public LayerMask target;
	public float lifeSpanInSeconds = 2f;
	public int team=4;
	public Transform hitEffectFab;

	// Use this for initialization
	void Start () {
		//rigidbody2D.AddForce (Vector3.right*travelSpeed);
		Destroy (this.gameObject, lifeSpanInSeconds);
	}
	
	// Update is called once per frame
	void Update () {
		transform.Translate(Vector3.right * Time.deltaTime * travelSpeed);
	}

	void OnCollisionEnter2D (Collision2D collision)
	{
		int colliderTeam = collision.collider.GetComponent<VulnerableUnit>().unitStats.team;
		//if (collision.collider.GetComponent<VulnerableUnit>()==null)
		//	Destroy (this.gameObject);
		if (Setup.areEnemy (this.team, colliderTeam) || colliderTeam==4/*Library.verifyIfLayerMaskContains(collision.collider.gameObject, target)*/)
		{
			Debug.Log ("Bullet Collision detected : "+collision.collider.name);
			VulnerableUnit unit = (VulnerableUnit)collision.collider.GetComponent ("VulnerableUnit");
			unit.damageUnit (damage);
			Transform hitFab = (Transform)Instantiate (hitEffectFab,collision.contacts[0].point,this.transform.rotation);
			Destroy (hitFab.gameObject, 0.1f);
			Destroy (this.gameObject);

		}
	}
}
