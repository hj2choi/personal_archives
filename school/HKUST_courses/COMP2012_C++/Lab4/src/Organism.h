/*
 * Organism.h
 *
 *  Created on: Mar 10, 2015
 *      Author: HongJoon
 */

#ifndef ORGANISM_H_
#define ORGANISM_H_

class Organism
{
public:

	Organism(int lP);
	virtual ~Organism();

	double getLifePoint() const;
	void setLifePoint(double value);
	virtual double nutritionValue() const = 0;

protected:
	double lifePoint;

};



#endif /* ORGANISM_H_ */
