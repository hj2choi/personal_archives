/*
 * LinearProbing.h
 *
 *  Created on: Apr 21, 2015
 *      Author: HongJoon
 */

#ifndef LINEARPROBING_H_
#define LINEARPROBING_H_

#include "OpenAdressing.h"



class LinearProbing : public OpenAddressing {
public:
	LinearProbing(int size);
	virtual ~LinearProbing();

	virtual int hash(int value);                         // the primary hash function
	virtual int nextEmpty(int nonEmptyIndex, int value); // probe to the next empty location


};



#endif /* LINEARPROBING_H_ */
