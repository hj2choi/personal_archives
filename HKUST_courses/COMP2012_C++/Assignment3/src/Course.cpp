/*
 * Course.cpp
 *
 *  Created on: Jan 16, 2015
 *      Author: cspeter
 */

#include "Course.h"
using namespace std;
#include <iostream>

// TODO:
// ========================================================================
// Implementation of class Course
// ========================================================================

const int Course::COURSE_CODE_MIN_LEN=7;
const int Course::COURSE_CODE_MAX_LEN=9;
const int Course::COURSE_CODE_ALPHABET_LEN=26;
const int Course::COURSE_NAME_MIN_LEN=1;
const int Course::COURSE_NAME_MAX_LEN=50;
const int Course::COURSE_CREDIT_LOWER_BOUND=0;
const int Course::COURSE_CREDIT_UPPER_BOUND=5;


Course::Course(const string& code, const string& name, int credit)
{
	m_courseCode = code;
	m_courseName = name;
	m_credit = credit;
}

const string& Course::getCourseCode() const
{
	return m_courseCode;

}

const string& Course::getCourseName() const
{

	return m_courseName;
}

int Course::getCourseCredit() const
{
	return m_credit;

}

bool Course::setCourse(const string& name, int credit)
{
	return setCourseName(name) && setCourseCredit(credit);
}

bool Course::setCourseName(const string& name)
{
	if (isValidCourseName(name))
	{
		m_courseName = name;
		return true;
	}
	return false;
}
bool Course::setCourseCredit(int credit)
{
	if (isValidCourseCredit(credit))
	{
		m_credit = credit;
		return true;
	}
	return false;
}

int powerMod(int value, int pow, int mod)
{
	int retVal=1;
	for (int i=0; i<pow; i++)
		retVal*=value%mod;
	return retVal;
}

unsigned int Course::hash(const Course& course, unsigned int num_bucket)
{
	unsigned int value=0;
	int i=0;
	for (string::const_iterator itr = course.getCourseCode().begin(); itr!=course.getCourseCode().end(); itr++)
	{
		int c = (47<(*itr) && (*itr)<58)?(*itr-48):(*itr-55);

		value+=(c%num_bucket)*powerMod(36,i, num_bucket);
		i++;
	}
	return value%num_bucket;

}

bool Course::isValidCourseCode(const string& code)
{
	int i=0;
	for (string::const_iterator itr=code.begin();itr!=code.end();itr++)
	{
		if (!((65<= *itr && *itr<=90) || (i>=4 && 47<(*itr) && (*itr)<58)))
			return false;
		i++;
	}
	return COURSE_CODE_MIN_LEN <= code.length() && code.length() <=COURSE_CODE_MAX_LEN;
}

bool Course::isValidCourseName(const string& name)
{
	return COURSE_NAME_MIN_LEN <= name.length() && name.length() <=COURSE_NAME_MAX_LEN;

}


bool Course::isValidCourseCredit(int credit)
{
	return COURSE_CREDIT_LOWER_BOUND <=credit && credit <= COURSE_CREDIT_UPPER_BOUND;

}

bool operator<(const Course& s1, const Course& s2)
{
	return s1.m_courseCode < s2.m_courseCode;
}
