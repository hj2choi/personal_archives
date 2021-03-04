/*
 * Point.cpp
 *
 *  Created on: Mar 3, 2015
 *      Author: HongJoon
 */




/* COMP2012 2015S Lab02 */

#include <iostream>
#include "Point.h"
using namespace std;

// Default constructor
Point::Point(): x(0), y(0) { }

// Construct a point with given coordinates
Point::Point(int ix, int iy): x(ix), y(iy) {  }

// Conversion constructor
Point::Point(int ix): x(ix), y(0) { }

// Copy constructor
Point::Point(const Point& p)
{
  x = p.x;
  y = p.y;
}

// Check if *this == p
bool Point::equal(const Point& p) const
{
    return (x == p.x && y == p.y);
}

// Print the x-coordinate and y-coordinate
void Point::print() const
{
    cout << "(" << x << ", " << y << ")";
}

