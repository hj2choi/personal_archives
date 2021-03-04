/*
 * Plant.cpp
 *
 *  Created on: Mar 10, 2015
 *      Author: HongJoon
 */
#include "Plant.h"

#include <iostream>
using namespace std;

Plant::Plant(double lP, int weight) : Organism(lP), weight(weight){}

double Plant::getLifePoint() const
{
	return lifePoint;
}
void Plant::setLifePoint(double value)
{
	lifePoint=value;
}

void Plant::photosynthesis(double degree)
{
	cout << "photosynthesis " << weight << endl;
	weight +=weight*(degree/100.0);
	cout << "photosynthesis " << degree << endl;
	cout << "photosynthesis " << weight << endl;
}
