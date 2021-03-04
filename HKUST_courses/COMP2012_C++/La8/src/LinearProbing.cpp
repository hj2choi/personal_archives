/*
 * LinearProbing.cpp
 *
 *  Created on: Apr 21, 2015
 *      Author: HongJoon
 */
#include "LinearProbing.h"

#include <iostream>
using namespace std;

LinearProbing::LinearProbing(int size) : OpenAddressing(size){}
LinearProbing::~LinearProbing()
{

}

int LinearProbing::hash(int value)
{
	return value%currSize;
}

int LinearProbing::nextEmpty(int nonEmptyIndex, int value)
{
	int i=0;
	for (; data[(hash(value)+i)%currSize]!=EMPTY; i++);
	return (hash(value)+i)%currSize;
}
