/*
 * HealthyPlant.cpp
 *
 *  Created on: Mar 10, 2015
 *      Author: HongJoon
 */
#include "HealthyPlant.h"



//HealthyPlant::HealthyPlant(double lP, int weight):Plant(lP,weight){}


double HealthyPlant::nutritionValue() const
{
	return weight*getLifePoint();
}
