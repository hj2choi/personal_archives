using UnityEngine;
using System.Collections;

public class Library : MonoBehaviour {

	public static bool verifyIfLayerMaskContains(GameObject obj, LayerMask mask){
		return ((mask.value & (1 << obj.layer)) > 0);
	}
}
