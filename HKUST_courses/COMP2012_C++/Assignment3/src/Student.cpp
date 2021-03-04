/*
 * Student.cpp
 *
 *  Created on: Jan 16, 2015
 *      Author: cspeter
 */

#include "Student.h"
using namespace std;


// TODO:
// ========================================================================
// Implementation of class Student
// ========================================================================

const int Student::STUDENT_ID_LEN=8;
const int Student::STUDENT_NAME_MAX_LEN=32;
const int Student::STUDENT_NAME_MIN_LEN=1;
const int Student::STUDENT_YEAR_LOWER_BOUND=1;
const int Student::STUDENT_YEAR_UPPER_BOUND=5;

Student::Student(const string& id,const string& name,int year,GENDER gender)
{
	m_studentID = id;
	m_studentName = name;
	m_year = year;
	m_gender = gender;

}

const string& Student::getStudentID() const
{
	return m_studentID;
}

const string& Student::getStudentName() const
{
	return m_studentName;
}

int Student::getYear() const
{
	return m_year;
}

Student::GENDER Student::getGender() const
{
	return m_gender;
}

bool Student::setStudent(const string& name, int year, GENDER gender)
{
	if (setStudentYear(year) && setStudentName(name))
	{
		setStudentGender(gender);
		return true;
	}
	return false;

}
bool Student::setStudentName(const string& name)
{
	if (isValidStudentName(name))
	{
		m_studentName = name;
		return true;
	}
	return false;


}
bool Student::setStudentYear(int year)	// return true if successful to set
{
	if (isValidStudentYear(year))
	{
		m_year = year;
		return true;
	}
	return false;
}
void Student::setStudentGender(GENDER gender)
{
	m_gender = gender;
}

bool Student::isValidStudentID(const string& id)
{

	for (string::const_iterator stritr = id.begin();stritr!=id.end(); stritr++)
	{
		if (!(47< *stritr && *stritr<58))
			return false;
	}
	return id.length() == STUDENT_ID_LEN;
}
bool Student::isValidStudentName(const string& name)
{
	return STUDENT_NAME_MIN_LEN <= name.length() && name.length() <=STUDENT_NAME_MAX_LEN;

}
bool Student::isValidStudentYear(int year)
{
	return STUDENT_YEAR_LOWER_BOUND <= year && year <= STUDENT_YEAR_UPPER_BOUND;

}

int powerMOD(int value, int pow, int mod)
{
	int retVal=1;
	for (int i=0; i<pow; i++)
		retVal*=value%mod;
	return retVal;
}

unsigned int Student::hash(const Student& stu, unsigned int num_bucket)
{
	unsigned int value=0;
	int i=0;
	for (string::const_iterator itr = stu.getStudentID().begin(); itr!=stu.getStudentID().end(); itr++)
	{
		value+=(((*itr)-48)%num_bucket)*powerMOD(10,i, num_bucket);
		i++;
	}
	return value%num_bucket;
}

bool operator<(const Student& s1, const Student& s2)
{
	return s1.m_studentID < s2.m_studentID;
}
