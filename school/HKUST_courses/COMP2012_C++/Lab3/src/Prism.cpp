/*
 * Prism.cpp
 *
 *  Created on: Mar 3, 2015
 *      Author: HongJoon
 */


#include "Prism.h"
#include <iostream>

using namespace std;

Prism::Prism() : Polygon(), height(0) {cout << "Prism constructor" << endl;};

Prism::Prism(const Point points[], int numPoints, int height) : Polygon(points, numPoints), height(height) {};

Prism::~Prism(){cout << "Prism's destructor" << endl;}

void Prism::print() const
{
	Polygon::print();
	cout << "Height: " << height << endl;
}
