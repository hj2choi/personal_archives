using UnityEngine;
using System.Collections;

public class VulnerableUnit : MonoBehaviour {

	[System.Serializable]public class UnitStats {
		public int team = 1;		//0=player, 1=ally, 2=enemy1, 3=enemy2, 4=platform(neutral)
		public float health = 100f;
	}

	public UnitStats unitStats = new UnitStats();


	// Use this for initialization
	void Start () {
		GameMaster.reportVulnerableUnit (this);
		Setup.setLayerByTeam (this.gameObject,unitStats.team);
	}
	
	// Update is called once per frame
	void Update () {
		if (transform.position.y <= -30)
		{
			damageUnit (Mathf.Infinity);
		}
	}

	public void damageUnit(float damage){
		unitStats.health -= damage;
		if (unitStats.health<=0)
		{
			Debug.Log("Player is dead!! health below 0");

			GameMaster.killUnit(this);
		}
	}
}
