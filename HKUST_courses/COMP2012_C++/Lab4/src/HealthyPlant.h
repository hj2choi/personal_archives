/*
 * HealthyPlant.h
 *
 *  Created on: Mar 10, 2015
 *      Author: HongJoon
 */

#ifndef HEALTHYPLANT_H_
#define HEALTHYPLANT_H_
#include "Plant.h"

class HealthyPlant : public Plant
{
public:
	HealthyPlant(double lP, int weight) : Plant(lP, weight){};
	virtual double nutritionValue() const;


private:
};



#endif /* HEALTHYPLANT_H_ */
