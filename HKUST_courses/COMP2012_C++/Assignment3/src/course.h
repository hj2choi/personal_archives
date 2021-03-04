/*
 * Course.h
 *
 *  Created on: Jan 16, 2015
 *      Author: cspeter
 */

#ifndef COURSE_H_
#define COURSE_H_


#include <string>
using std::string;   // string refers to std::string


// ========================================================================
// Declaration of
//    class Course
// ========================================================================
// This will maintain a record for a course
// m_courseCode must consist of upper letters or digits only, its length is at most 8 characters
// m_courseName can be a string with 1 <= length <= 50
// m_credit must be one of {0,1,2,3,4,5}
class Course {
public:
	static const int COURSE_CODE_MIN_LEN;
	static const int COURSE_CODE_MAX_LEN;
	static const int COURSE_CODE_ALPHABET_LEN;
	static const int COURSE_NAME_MIN_LEN;
	static const int COURSE_NAME_MAX_LEN;
	static const int COURSE_CREDIT_LOWER_BOUND;
	static const int COURSE_CREDIT_UPPER_BOUND;

public:

	Course(const string& code = "COMP2012",
		const string& name = "Object Oriented Programming and Data Structures",
		int credit = 4);

	// return the attributes
	const string& getCourseCode() const;
	const string&getCourseName() const;
	int getCourseCredit() const;

	// set the attributes
	bool setCourse(const string& name, int credit);	// return true if successful to set
	bool setCourseName(const string& name);
	bool setCourseCredit(int credit);		// return true if successful to set

	// hash function
	static unsigned int hash(const Course& course, unsigned int num_bucket); // return 0 to (num_bucket)

	// return true if it is a valid credit
	static bool isValidCourseCode(const string& code); // return true if it is valid
	static bool isValidCourseName(const string& name);
	static bool isValidCourseCredit(int credit);


	// define a "strict weak ordering" over the course code
	friend bool operator<(const Course& c1, const Course& c2);

private:
	string	m_courseCode;
	string	m_courseName;
	int	m_credit;
};



#endif /* COURSE_H_ */
