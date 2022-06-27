/*
 * PoisonPlant.h
 *
 *  Created on: Mar 10, 2015
 *      Author: HongJoon
 */

#ifndef POISONPLANT_H_
#define POISONPLANT_H_
#include "Plant.h"

class PoisonPlant : public Plant
{
public:
	PoisonPlant(double poison, int weight, double lP) : Plant(lP, weight), poison(poison){};
	virtual double nutritionValue() const;


private:
	double poison;
};




#endif /* POISONPLANT_H_ */
