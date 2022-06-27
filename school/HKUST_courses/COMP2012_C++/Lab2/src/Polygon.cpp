/* COMP2012 2015S Lab02 */

#include "Polygon.h"
#include <iostream>
using namespace std;


Polygon::Polygon() 
{
   cout << "Initialized by Polygon's default constructor" << endl;
   // This cout statement is for learning purpose only, which shows when the constructor will be invoked

   vertices = NULL;
   numVertices = 0;
}

Polygon::Polygon(const Point points[], int numPoints) 
{
   cout << "Initialized by Polygon's parameterized constructor" << endl;
   // This cout statement is for learning purpose only, which shows when the constructor will be invoked
   numVertices = numPoints;
   vertices = new Point[numPoints];
   //vertices[numPoints];	// vertices  = new Point[numPoints];
   for (int i=0; i<numPoints; ++i)
   {
	   vertices[i]=points[i];
   }
   // TODO: add your code here

}
// how does this cause problem?
Polygon::Polygon(const Polygon& p) 
{
   cout << "Initialized by Polygon's copy constructor" << endl;
   // This cout statement is for learning purpose only, which shows when the constructor will be invoked
   numVertices = p.numVertices;
   vertices = new Point[numVertices];
   //cout << "add" << endl;
   for (int i=0; i<numVertices; ++i)
   {
	   vertices[i] = p.vertices[i];
   }
   // TODO: add your code here

}

Polygon::~Polygon() 
{
   cout << "Polygon's destructor" << endl;
   // This cout statement is for learning purpose only, which shows when the desstructor will be invoked
   delete vertices;
   vertices = NULL;
   // TODO: add your code here

}

void Polygon::addPoint(const Point& p)
{
   // TODO: add your code here
	//vertices[numVertices++] = p;
	//delete vertices;
	numVertices++;
	Point *temp = new Point[numVertices];
	temp[numVertices-1] = p;
	p.print();
	for (int i=0; i<numVertices-1; i++)
	{
		temp[i] = vertices[i];
		vertices[i].print();
	}

	delete [] vertices;
	vertices = temp;
	vertices[numVertices-1].print();
/*
	//vertices = 0;
	vertices = new Point[numVertices];
	for (int i = 0; i < numVertices; i++)
		   vertices[i] = temp[i];
	//vertices[numVertices++] = p;
	delete temp;*/
}

bool Polygon::contains(const Point& p) const
{
   // TODO: add your code here
	for (int i=0; i<numVertices; i++)
	{
		if (vertices[i].equal(p))
			return true;
	}
	return false;
}

// This set function should only be used by Polygon constructors
// If the Polygon already exists, it requires a reset function
void Polygon::set(const Point points[], int numPoints)
{
   numVertices = numPoints;
   vertices = new Point[numVertices];

   for (int i = 0; i < numPoints; i++)
      vertices[i] = points[i];
}

void Polygon::print() const 
{
   if ((numVertices == 0) || (vertices == NULL)) {
      cout << "The Polygon is empty!" << endl;
      return;
   }
   cout << "Num of vertices: " << numVertices << endl;;
   cout << "Vertices: " << endl;
   for (int i = 0; i < numVertices; i++)
   {
      vertices[i].print();
      cout << endl;
   }
}

