/*
 * Prism.h
 *
 *  Created on: Mar 3, 2015
 *      Author: HongJoon
 */

#ifndef PRISM_H_
#define PRISM_H_

#include "Polygon.h"

class Prism : public Polygon
{
public:
	Prism();
	Prism(const Point points[], int numPoints, int height);// : Polygon(points, numPoints), height(height) {};
	~Prism();

	void print() const;

private:
	int height;
};





























/*







#include "Polygon.h"

class Prism: public Polygon
{
   public:
      Prism();  // Default constructor
      Prism(const Point points[], int numPoints, int height); // Constructor
      ~Prism();  // Destructor

     void print() const; // to print the Polygon base and the height of the Prism object

   private:
     int height; // the height of the Prism object

};

*/


#endif /* PRISM_H_ */
