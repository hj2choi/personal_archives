/*
 * Student.h
 *
 *  Created on: Jan 16, 2015
 *      Author: cspeter
 */

#ifndef STUDENT_H_
#define STUDENT_H_

#include <string>
using std::string;   // string refers to std::string

// ========================================================================
// Declaration of
//    class Student
// ========================================================================
// This will maintain a record for a student:
// m_studentID must be 8 digits character
// m_studentName can be a string of arbitary length
// m_year must be one of {0,1,2,3,4,5}
// m_gender must be one of {MALE, FEMALE}

class Student {
public:
	static const int STUDENT_ID_LEN;
	static const int STUDENT_NAME_MAX_LEN;
	static const int STUDENT_NAME_MIN_LEN;
	static const int STUDENT_YEAR_LOWER_BOUND;
	static const int STUDENT_YEAR_UPPER_BOUND;

	enum GENDER {MALE = 0,FEMALE = 1};

private:
	string m_studentID;
	string m_studentName;
	int m_year;
	GENDER m_gender;
public:

	Student(const string& id = "00000000",
		const string& name = "Noname",
		int year = 3,
		GENDER gender = MALE);

	// return the attributes
	const string& getStudentID() const;
	const string& getStudentName() const;
	int getYear() const;
	GENDER getGender() const;

	// set the attributes
	bool setStudent(const string& name, int year, GENDER gender);	// return true if successful to set
	bool setStudentName(const string& name);
	bool setStudentYear(int year);	// return true if successful to set
	void setStudentGender(GENDER gender);

	// hash function
	static unsigned int hash(const Student& stu, unsigned int num_bucket); // return 0 to (num_bucket)

	// return true if it is a valid year
	static bool isValidStudentID(const string& id);	// return true if it is valid
	static bool isValidStudentName(const string& name);
	static bool isValidStudentYear(int year);

	// define a "strict weak ordering" over student id
	friend bool operator<(const Student& s1, const Student& s2);

};



#endif /* STUDENT_H_ */
