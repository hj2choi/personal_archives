/* COMP2012 2015S Lab02 */

// Polygon.h is the header file for the class Polygon

#include "Point.h"

class Polygon
{
   public:
      Polygon();  // Default constructor
      Polygon(const Point points[], int numPoints); // Constructor
      Polygon(const Polygon& p);  // Copy constructor: deep copy required
      ~Polygon();  // Destructor

      void addPoint(const Point& p); // add a point (x, y) to the vertices 
      bool contains(const Point& p) const; // returns true if the polygon has a point (x, y); returns false if otherwise

      void print() const; // to print the list of Point objects in vertices
 
   private:
      void set(const Point points[], int numPoints);  // copies numPoints of Point object from the array, points, to vertices; deep copy required

      int numVertices; // the current number of Point objects
      Point* vertices; // a dynamic array of Point objects
};


