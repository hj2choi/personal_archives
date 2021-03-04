/*
 * DoubleHashing.h
 *
 *  Created on: Apr 21, 2015
 *      Author: HongJoon
 */

#ifndef DOUBLEHASHING_H_
#define DOUBLEHASHING_H_

#include "OpenAdressing.h"



class DoubleHashing : public OpenAddressing {
public:
	DoubleHashing(int size);
	virtual ~DoubleHashing();

	virtual int hash(int value);                         // the primary hash function
	int hash2(int value);								// the secondary hash2 function
	virtual int nextEmpty(int nonEmptyIndex, int value); // probe to the next empty location


};




#endif /* DOUBLEHASHING_H_ */
