/*
 * Point.h
 *
 *  Created on: Mar 3, 2015
 *      Author: HongJoon
 */

#ifndef POINT_H_
#define POINT_H_

/* COMP2012 2015S Lab02 */

// Point.h is the header file for the class Point


class Point
{
   public:
      Point();  // Default constructor
      Point(int x, int y);  // Construct a point with given coordinates
      Point(int x);  // Conversion constructor
      Point(const Point& p);  // Copy constructor
      bool equal(const Point& p) const; // Check if *this == p
      void print() const; // Print the point

   private:
      int x, y; // Coordinates

};



#endif /* POINT_H_ */
