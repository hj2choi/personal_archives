/*
 * PoisonPlant.cpp
 *
 *  Created on: Mar 10, 2015
 *      Author: HongJoon
 */

#include "PoisonPlant.h"


double PoisonPlant::nutritionValue() const
{
	return -weight*lifePoint*poison;
}
