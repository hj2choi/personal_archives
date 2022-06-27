/*
 * Plant.h
 *
 *  Created on: Mar 10, 2015
 *      Author: HongJoon
 */

#ifndef PLANT_H_
#define PLANT_H_
#include "Organism.h"

class Plant : public Organism
{
public:
	Plant(double lP, int weight);
	//~Plant();
	virtual double nutritionValue() const =0;
	double getLifePoint() const;
	void setLifePoint(double value);
	void photosynthesis(double degree);

protected:
	int weight;
};



#endif /* PLANT_H_ */
