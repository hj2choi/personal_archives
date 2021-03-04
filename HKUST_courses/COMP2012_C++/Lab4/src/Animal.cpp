/*
 * Animal.cpp
 *
 *  Created on: Mar 10, 2015
 *      Author: HongJoon
 */
#include "Animal.h"



Animal::Animal(double lP) : Organism(lP){}

double Animal::nutritionValue() const
{
	return getLifePoint();
}

void Animal::eat(const Organism* food)
{
	lifePoint += food->nutritionValue();
}
