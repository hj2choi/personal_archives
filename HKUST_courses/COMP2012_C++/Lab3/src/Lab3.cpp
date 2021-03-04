//============================================================================
// Name        : Lab3.cpp
// Author      : CHOI, Hong Joon
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include "Prism.h"
using namespace std;

int main() {

   cout << "Prism:" << endl;
   Prism prism;
   prism.print();
   cout << endl;

   cout << "A point (10, 20) is added" << endl;
   prism.addPoint(Point(10, 20));
   prism.print();
   cout << endl;

   Point pts[3] = {Point(0, 0), Point(2, 2), Point(4, 4) };

   cout << "Prism2:" << endl;
   Prism prism2(pts, 3, 10);
   prism2.print();
   cout << endl;

   /* Setting the boolalpha format flag so that bool values will be
    * displayed in their textual representation: either true or false,
    * instead of integral values.
    */
   cout << boolalpha;
   cout << "Does Prism2 have the point (5, 10)? "
        << prism2.contains(Point(5, 10)) << endl;
   cout << "Does Prism2 have the point (2, 2)? "
        << prism2.contains(Point(2, 2)) << endl;
   cout << endl;


   return 0;
}
