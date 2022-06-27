/*
 * QuadraticProbing.h
 *
 *  Created on: Apr 21, 2015
 *      Author: HongJoon
 */

#ifndef QUADRATICPROBING_H_
#define QUADRATICPROBING_H_

#include "OpenAdressing.h"



class QuadraticProbing : public OpenAddressing {
public:
	QuadraticProbing(int size);
	virtual ~QuadraticProbing();

	virtual int hash(int value);                         // the primary hash function
	virtual int nextEmpty(int nonEmptyIndex, int value); // probe to the next empty location


};



#endif /* QUADRATICPROBING_H_ */
