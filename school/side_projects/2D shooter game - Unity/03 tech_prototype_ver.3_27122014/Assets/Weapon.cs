using UnityEngine;
using System.Collections;

public class Weapon : MonoBehaviour {

	public KeyCode reload;
	public float fireRate = 0;
	public int bulletsPerShot=1;
	public float damage = 10;
	[Range(0,100)] public float angularAccuracy = 100;//min +- 0 degrees, max +- 90 degrees
	public int magazineSize = 30;
	public int ammo = 150;
	public float reloadSpeed = 1f;	// in seconds
	public bool castProjectile = false;
	public float range = 30;
	public float projectileSpeed=60;

	public Transform bulletTrailPrefab;
	public Transform MuzzleFlashPrefab;
	public Transform bulletPrefab;

	private LayerMask validTargetList;
	private int currentMagazine=0;
	private float timeToFire=0;
	private float timeToCompleteReload = 0;
	private bool reloading = false;
	private Transform firePoint;

	private VulnerableUnit weaponHolder;

	public int getCurrentMagazine()
	{
		return currentMagazine;
	}
	public bool getReloadingStatus()
	{
		return reloading;
	}
	public LayerMask getValidTargetList()
	{
		return validTargetList;
	}

	public void fire()
	{
		if (!reloading)
		{
			if (currentMagazine<=0)
			{
				reloadMagazine();
				return;
			}
			if (Time.time >= timeToFire) {
				timeToFire = Time.time + 1/fireRate;
				for (int i=0; i<bulletsPerShot; i++)
					shoot();
				currentMagazine--;
			}
		}
	}
	
	
	
	
	public void reloadMagazine()
	{
		if (!reloading && ammo>0 && currentMagazine<magazineSize)
		{
			timeToCompleteReload = Time.time + reloadSpeed;
			reloading = true;
			Debug.Log ("reloading...");
		}
	}



	void Awake() {
		if (projectileSpeed > 100)
			castProjectile = false;
		//autogenerate firePoint
		GameObject temp = new GameObject ();
		Vector3 newPosition = new Vector3 (this.renderer.bounds.center.x + this.renderer.bounds.size.x/2, this.transform.position.y, this.transform.position.z);
		firePoint = (Transform)Instantiate (temp.transform, newPosition,this.transform.rotation);
		firePoint.name = "FirePoint";
		firePoint.parent = this.transform;
		Destroy (temp);

		// initial ammo, magazine configuration
		ammo -= magazineSize;
		currentMagazine = magazineSize;
		if (ammo < 0)
		{
			currentMagazine = ammo + magazineSize;
			ammo = 0;
		}
		//Debug.Log (""+effectSpawnRate);

		//get weaponHolder, as vulnerableUnit class type
		weaponHolder = (VulnerableUnit)this.GetComponentInParent<VulnerableUnit>();
		validTargetList = Setup.layerMaskByTeam (weaponHolder.unitStats.team);
		Debug.Log (weaponHolder.name+"'slayerMask"+validTargetList.value);
	}

	
	// Update is called once per frame.
	void Update () {
		if (reloading)
		{
			if (Time.time>timeToCompleteReload)
			{
				reloading=false;
				int requiredAmmo = (magazineSize-currentMagazine);
				ammo-=requiredAmmo;
				if (ammo>=0)
					currentMagazine=magazineSize;
				else
				{
					currentMagazine+=ammo+requiredAmmo;
					ammo=0;
				}
			}
		}


	}



	
	private void shoot()	//shoot
	{
		//configure fire direction and angular unceratinty caused by accuracy value
		//Debug.Log ("ammo = "+currentMagazine+" / "+magazineSize+"    total = "+ammo);
		Debug.Log ("weapon fire");
		Vector2 firePointPosition = new Vector2 (firePoint.position.x, firePoint.position.y);
		Vector2 directionIntent = firePointPosition - new Vector2(this.transform.position.x, this.transform.position.y);//targetPosition - firePointPosition;	// intent direction in vector format
		float directionIntentAngle = Mathf.Atan2 (directionIntent.y,directionIntent.x);//intent direction in radian
		float angularUncertainty = (Random.Range (-0.004f,0.004f)*(100-angularAccuracy));//angular uncertainty in radian

		if (!castProjectile)
		{
			//randomized Rotation
			directionIntent.x = directionIntent.x*Mathf.Cos (angularUncertainty) - directionIntent.y*Mathf.Sin (angularUncertainty);
			directionIntent.y = directionIntent.x*Mathf.Sin (angularUncertainty) + directionIntent.y*Mathf.Cos (angularUncertainty);
			//raycast onto intent direction, compensated by angular uncertainty
			RaycastHit2D hit = Physics2D.Raycast (firePointPosition, directionIntent, range, validTargetList);

			// cast effect
			castBulletTrail(Mathf.Rad2Deg*(directionIntentAngle+angularUncertainty));
			//Debug.DrawLine (firePoint.position, firePointPosition+directionIntent, Color.cyan);

			if (hit.collider!=null)
			{
				rayCastHit ( hit.collider);
			}
		}
		else if (castProjectile)
			spawnProjectile ((Mathf.Rad2Deg*(directionIntentAngle+angularUncertainty)));
	}

	void rayCastHit(Collider2D collider)
	{
		VulnerableUnit hitTarget = (VulnerableUnit) collider.GetComponent ("VulnerableUnit");
		hitTarget.damageUnit (this.damage);
	}

	void spawnProjectile(float directionAngle)
	{
		Transform bulletRef = (Transform) Instantiate (bulletPrefab,firePoint.position,Quaternion.Euler (0f, 0f, directionAngle));
		//bulletRef.GetComponent<Bullet>().target = this.attackableTarget;
		bulletRef.GetComponent<Bullet>().damage = this.damage;
		bulletRef.GetComponent<Bullet>().travelSpeed = this.projectileSpeed;
		bulletRef.GetComponent<Bullet>().lifeSpanInSeconds = this.range / this.projectileSpeed;
		bulletRef.GetComponent<Bullet> ().team = weaponHolder.unitStats.team;
		//bulletRef.GetComponent<Bullet> ().gameObject.layer = weaponHolder.gameObject.layer;
		muzzleFlash ();
	}

	void castBulletTrail(float directionAngle)//Angle in degrees
	{
		Instantiate (bulletTrailPrefab,firePoint.position,Quaternion.Euler (0f, 0f, directionAngle));
		muzzleFlash ();
	}

	void muzzleFlash()
	{
		Transform muzzleFlashClone = (Transform) Instantiate (MuzzleFlashPrefab, firePoint.position, firePoint.rotation);
		muzzleFlashClone.parent = firePoint;
		float size = Random.Range (0.3f, 0.9f);
		muzzleFlashClone.localScale *= size;
		Destroy (muzzleFlashClone.gameObject, 0.02f);


	}
}
