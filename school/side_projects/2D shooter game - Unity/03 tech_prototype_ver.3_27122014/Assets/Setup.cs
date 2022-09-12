using UnityEngine;
using System.Collections;

public class Setup : MonoBehaviour {

	public static void setLayerByTeam(GameObject unit,int team)	// set layer according to team status
	{
		switch (team)
		{
		case 0:
			unit.layer = LayerMask.NameToLayer ("Player");
			break;
		case 1:
			unit.layer = LayerMask.NameToLayer ("AI_allied");
			break;
		case 2:
			unit.layer = LayerMask.NameToLayer ("AI_enemy1");
			break;
		case 3:
			unit.layer = LayerMask.NameToLayer ("AI_enemy2");
			break;
		case 4:
			unit.layer = LayerMask.NameToLayer ("Platform");
			break;
		default:
			break;
		}
		Debug.Log (unit.layer);
			
	}

	public static LayerMask layerMaskByTeam(int team)	// set attackable layers accroding to team status
	{
		LayerMask mask = 0;
		mask += 1 << LayerMask.NameToLayer ("Platform");
		if (team == 0)
		{
			mask += 1 << LayerMask.NameToLayer ("AI_enemy1");
			mask += 1 << LayerMask.NameToLayer ("AI_enemy2");
		}
		else if (team == 1)
		{
			mask += 1 << LayerMask.NameToLayer ("AI_enemy1");
			mask += 1 << LayerMask.NameToLayer ("AI_enemy2");
		}
		else if (team == 2)
		{
			mask += 1 << LayerMask.NameToLayer ("Player");
			mask += 1 << LayerMask.NameToLayer ("AI_allied");
			mask += 1 << LayerMask.NameToLayer ("AI_enemy2");
		}
		else if (team == 3)
		{
			mask += 1 << LayerMask.NameToLayer ("Player");
			mask += 1 << LayerMask.NameToLayer ("AI_allied");
			mask += 1 << LayerMask.NameToLayer ("AI_enemy1");
		}
		else if (team == 4)
		{
			mask += 1 << LayerMask.NameToLayer ("Platform");
			mask += 1 << LayerMask.NameToLayer ("Player");
			mask += 1 << LayerMask.NameToLayer ("AI_allied");
			mask += 1 << LayerMask.NameToLayer ("AI_enemy1");
			mask += 1 << LayerMask.NameToLayer ("AI_enemy2");
		}
		Debug.Log ("changed layerMask"+mask.value);
		return mask;
	}

	public static bool areEnemy(int team1, int team2)	//platform is excluded
	{
		switch (team1)
		{
		case 0:
			return team2==2 || team2==3;
		case 1:
			return team2==2 || team2==3;
		case 2:
			return team2==0 || team2==1 || team2==3;
		case 3:
			return team2==0 || team2==1 || team2==2;
		case 4:
			return team2==0 || team2==1 || team2==2 || team2==3;
		default:
			return false;
		}
	}
	// Use this for initialization
	void Start () {


	}
	
	// Update is called once per frame
	void Update () {
	
	}
}
