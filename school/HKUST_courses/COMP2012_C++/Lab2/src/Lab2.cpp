//============================================================================
// Name        : Lab2.cpp
// Author      : CHOI, Hong Joon
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================
#include "Polygon.h"
#include <iostream>
using namespace std;



int main() {
	cout << "Polygon:" << endl;
   Polygon polygon; // Initialized by the default constructor
   polygon.print();
   cout << endl;

   Point pts[3] = {Point(0, 0), Point(2, 2), Point(4, 4) };

   cout << "Polygon2:" << endl;
   Polygon *polygon2 = new Polygon(pts, 3); // Initialized by the parameterized constructor
   polygon2->print();
   cout << endl;

   cout << "Polygon3:" << endl;
   Polygon polygon3(*polygon2); // Initialized by the copy constructor

   delete polygon2;

   polygon3.addPoint(Point(12, 21));
   polygon3.print();
   cout << endl;

   /* Setting the boolalpha format flag so that bool values will be
	* displayed in their textual representation: either true or false,
	* instead of integral values.
	*/
   cout << boolalpha;
   cout << "Does Polygon3 have the point (5, 10)? "
		<< polygon3.contains(Point(5, 10)) << endl;
   cout << "Does Polygon3 have the point (2, 2)? "
		<< polygon3.contains(Point(2, 2)) << endl;
   cout << endl;

   return 0;
}
