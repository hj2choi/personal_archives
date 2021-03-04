/*
 * QuadraticProbing.cpp
 *
 *  Created on: Apr 21, 2015
 *      Author: HongJoon
 */


#include "QuadraticProbing.h"

#include <iostream>
using namespace std;

QuadraticProbing::QuadraticProbing(int size) : OpenAddressing(size){}
QuadraticProbing::~QuadraticProbing()
{

}

int QuadraticProbing::hash(int value)
{
	return value%currSize;
}

int QuadraticProbing::nextEmpty(int nonEmptyIndex, int value)
{
	int i=0;
	for (; data[(hash(value)+i*i)%currSize]!=EMPTY; i++);
	return (hash(value)+i*i)%currSize;
}


