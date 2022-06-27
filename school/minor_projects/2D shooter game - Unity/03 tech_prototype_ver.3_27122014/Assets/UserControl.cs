using UnityEngine;
//using UnitySampleAssets.CrossPlatformInput;

/*namespace UnitySampleAssets._2D
{
*/
    [RequireComponent(typeof (MobileCharacter))]
	//[RequireComponent(typeof (Weapon))]
	//[RequireComponent(typeof (ArmRotation))]
    public class UserControl : MonoBehaviour
    {
        private MobileCharacter character;
		private Weapon weapon;
		private ArmRotation arm;
        private bool jump;

        private void Awake()
        {
            character = GetComponent<MobileCharacter>();
			weapon = GetComponentInChildren<Weapon> ();
			arm = GetComponentInChildren<ArmRotation>();
        }

        private void Update()
        {
            if(!jump)
            // Read the jump input in Update so button presses aren't missed.
			jump = Input.GetKeyDown(UserInputManager.jumpKey);
        }

        private void FixedUpdate()
        {
            // Read the inputs.
			bool crouch = Input.GetKey(UserInputManager.crouchKey);
			float h = Input.GetAxis("Horizontal");
            // Pass all parameters to the character control script.
            character.Move(h, crouch, jump);
            jump = false;

			if (arm != null)
			{
				arm.aimTarget (Camera.main.ScreenToWorldPoint(Input.mousePosition));
			}
			if (weapon != null)
			{
				if (Input.GetKey (UserInputManager.fireKey))
					weapon.fire ();
				if (Input.GetKey(UserInputManager.reloadKey))
					weapon.reloadMagazine ();
			}
        }
    }
//}