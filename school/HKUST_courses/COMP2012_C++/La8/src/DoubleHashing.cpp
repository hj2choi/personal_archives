/*
 * DoubleHashing.cpp
 *
 *  Created on: Apr 21, 2015
 *      Author: HongJoon
 */



#include "DoubleHashing.h"

#include <iostream>
using namespace std;

DoubleHashing::DoubleHashing(int size) : OpenAddressing(size){}
DoubleHashing::~DoubleHashing()
{

}

int DoubleHashing::hash2(int value)
{

	return 5-value%5;
}

int DoubleHashing::hash(int value)
{
	return value%currSize;
}

int DoubleHashing::nextEmpty(int nonEmptyIndex, int value)
{
	int i=0;
	for (; data[(hash(value)+i*hash2(value))%currSize]!=EMPTY; i++);
	return (hash(value)+i*hash2(value))%currSize;
}

