/*
 * Organism.cpp
 *
 *  Created on: Mar 10, 2015
 *      Author: HongJoon
 */
#include "Organism.h"



Organism::Organism(int lP)
{
	lifePoint=lP;

}

Organism::~Organism()
{

}


double Organism::getLifePoint() const
{
	return lifePoint;
}

void Organism::setLifePoint(double value)
{
	lifePoint = value;
}
