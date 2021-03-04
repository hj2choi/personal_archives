using UnityEngine;
using System.Collections.Generic;

[RequireComponent(typeof (MobileCharacter))]
//[RequireComponent(typeof (VulnerableUnit))]
//[RequireComponent(typeof (Weapon))]
//[RequireComponent(typeof (ArmRotation))]

public class AICombatUnitControl : MonoBehaviour {

	private int sightRange = 20;
	private float minApproachRange = 2;	//safe distance
	private int offendRange = 8;
	
	private float batchInterval = 0.1f;
	private float timeToProcessBatch = 0;

	private MobileCharacter character;
	private Weapon weapon;
	private ArmRotation arm;
	private VulnerableUnit unit;

	private int state;		//order of offensiveness: 1 = guard, 2 = defend, 3 = search and destroy
	private VulnerableUnit targetUnit=null;
	
	private float nextSearchTime=0;

	VulnerableUnit getNearestVisibleTarget()
	{
		VulnerableUnit target=null;
		//Debug.LogWarning ("Target candidates searched");
		List<VulnerableUnit> candidates = GameMaster.unitList.FindAll (x=>Setup.areEnemy(unit.unitStats.team,x.unitStats.team));
		//Debug.LogWarning (candidates.Count);

		float minimumDistance = sightRange+1;
		for (int i=0;i<candidates.Count; i++ )
		{
			if ((this.transform.position-candidates[i].transform.position).magnitude < minimumDistance-2)
			{
				VulnerableUnit unitBlockingPath = firstUnitInPathwayTo(candidates[i]);
				int blockingTeam=unitBlockingPath.unitStats.team;
				if (unitBlockingPath==null || blockingTeam!=4)//!Setup.areEnemy(this.unit.unitStats.team, blockingTeam))
				{
					minimumDistance=(this.transform.position-candidates[i].transform.position).magnitude;
					target=candidates[i];
				}
				else if (target==null)
					target=unitBlockingPath;
				//else if (target==null)
				//	target=candidates[i];
			}
		}
		//Debug.LogWarning (minimumDistance);

		return target;
	}
	 
	VulnerableUnit /*bool*/ firstUnitInPathwayTo(VulnerableUnit unit)
	{
		RaycastHit2D hit = Physics2D.Raycast (this.transform.position, unit.transform.position-this.transform.position,sightRange,this.weapon.getValidTargetList());
		Debug.DrawLine (this.transform.position, hit.collider.transform.position , Color.cyan, 0.3f);
		return hit.collider.GetComponent<VulnerableUnit> ();//.unitStats.team != 4;

		//return true;

	}



	void Awake() {
		character = GetComponent<MobileCharacter>();
		weapon = GetComponentInChildren<Weapon> ();
		arm = GetComponentInChildren<ArmRotation>();
		unit = GetComponentInChildren<VulnerableUnit>();
		minApproachRange = Mathf.Min (minApproachRange,weapon.range-1f);
		offendRange = (int)(offendRange*Random.Range (0.5f, 1f));/*weapon.angularAccuracy * 0.01f*/;
	}

	// Use this for initialization
	void Start () {

	}
	
	// Update is called once per frame
	void FixedUpdate () {
		if (nextSearchTime<=Time.time) {
			targetUnit = getNearestVisibleTarget ();
			nextSearchTime = Time.time + 1f;
		}
		if (targetUnit == null) {
			nextSearchTime -= 0.1f;
			return;
		}


		Vector3 difference = targetUnit.transform.position - unit.transform.position;
		float distance = difference.magnitude;

		if (distance<minApproachRange)
			character.Move (-difference.x/Mathf.Abs (difference.x), false, false);
		else if (distance>offendRange/**weapon.angularAccuracy*0.01f*/)
			character.Move (difference.x/Mathf.Abs (difference.x), false, false);


		if (timeToProcessBatch < Time.time) {
			arm.aimTarget (targetUnit.transform.position);
			Debug.DrawLine (this.transform.position, targetUnit.transform.position , Color.red, 0.2f);

			timeToProcessBatch=Time.time+batchInterval;
		}

		if (weapon.range>=distance-0.5f)
			weapon.fire ();


	}

}
