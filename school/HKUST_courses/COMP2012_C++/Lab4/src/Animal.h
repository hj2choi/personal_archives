/*
 * Animal.h
 *
 *  Created on: Mar 10, 2015
 *      Author: HongJoon
 */

#ifndef ANIMAL_H_
#define ANIMAL_H_
#include "Organism.h"

class Animal : public Organism
{
public:
	Animal(double lP);
	virtual double nutritionValue() const;
	void eat(const Organism* food);

private:
};


#endif /* ANIMAL_H_ */
