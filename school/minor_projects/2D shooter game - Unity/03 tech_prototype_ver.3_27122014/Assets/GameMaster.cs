using UnityEngine;
using System.Collections;
using System.Collections.Generic;

public class GameMaster : MonoBehaviour {

	public static GameMaster gm;
	public static List<VulnerableUnit> unitList = new List<VulnerableUnit>();
	public static int killCount = 0;


	public static void reportVulnerableUnit(VulnerableUnit unit)
	{
		unitList.Add (unit);
		Debug.Log (unit.name + " reported to gameMaster");
	}

	public static VulnerableUnit findPlayer()
	{
		return unitList.Find (x => x.unitStats.team==0);
	}

	public static void killUnit(VulnerableUnit unit)
	{
		if (unit.unitStats.team == 2 || unit.unitStats.team == 3)
		{
			killCount++;
			gm.StartCoroutine(gm.reSpawnUnit());
		}

		unitList.Remove (unit);
		Destroy (unit.gameObject);
		Transform temp = (Transform)Instantiate (gm.spawnParticles, unit.transform.position, unit.transform.rotation);
		Destroy (temp.gameObject, 0.5f);
	}

	public Transform unitPrefab;
	public Transform spawnPoint;
	public int spawnDelay = 3;
	public Transform spawnParticles;

	// Use this for initialization
	void Start ()
	{

		if (gm==null)
			gm = GameObject.FindGameObjectWithTag ("GameMaster").GetComponent <GameMaster>();
		gm.audio.Play ();
	}

	public IEnumerator reSpawnUnit()
	{
		yield return new WaitForSeconds (spawnDelay);
		Vector3 random = new Vector3(Random.Range (-20f,20f),0,0);
		Transform unitReference = (Transform)Instantiate (unitPrefab, spawnPoint.position+random, spawnPoint.rotation);
		unitReference.GetComponent<VulnerableUnit> ().unitStats.health = (int)Random.Range (150, 300);
		unitReference.GetComponent<MobileCharacter>().setMaxSpeed (Random.Range (2f,4f));
		unitReference.GetComponentInChildren<Weapon> ().damage = (int)Random.Range (5, 12);
		unitReference.GetComponentInChildren<Weapon> ().fireRate = Random.Range (0.5f, 4f);
		Debug.Log ("Create particles! "+ unitReference.GetComponent<VulnerableUnit> ().unitStats.health);
	}

	void OnGUI()
	{
		GUI.Label (new Rect (Screen.width / 2, 20, 100, 100), "KillCount = " + killCount );
		VulnerableUnit player = findPlayer ();
		Weapon playerWeapon= (Weapon)player.GetComponentInChildren<Weapon>();
		GUI.Label (new Rect (50, Screen.height-100, 100, 100), "Health = " + player.unitStats.health );
		GUI.Label (new Rect (50, Screen.height-60, 200, 25), "Magazine = " + playerWeapon.getCurrentMagazine () +" / " + playerWeapon.magazineSize);
		GUI.Label (new Rect (50, Screen.height-40, 200, 100), "Ammo = " + playerWeapon.ammo);
		GUI.Label (new Rect (50, Screen.height-20, 200, 100), (playerWeapon.getReloadingStatus())?("RELOADING..."):(""));
	}



}
