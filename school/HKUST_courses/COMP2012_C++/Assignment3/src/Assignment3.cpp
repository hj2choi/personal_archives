#include "Course.h"
#include <iostream>
using namespace std;

int main() {

	Course testCourse("COMP2012", "Object Oriented Programming and Data Structures", 4);

	// validity of getter functions
	cout <<  testCourse.getCourseCode() << endl ;
	cout <<  testCourse.getCourseName() << endl ;
	cout << testCourse.getCourseCredit() << endl ;

	const unsigned int numBucket = 17;
	cout << Course::hash(testCourse, numBucket) << endl;
	return 0;
}
