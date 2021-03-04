/*
 * RegistrationManager.cpp
 *
 *  Created on: Jan 16, 2015
 *      Author: cspeter
 */


#include "RegistrationManager.h"

#include <string>
using namespace std;


// TODO:
// ========================================================================
// Implementation of RegistrationManager
// ========================================================================

RegistrationManager::RegistrationManager(int numOfStudentBuckets, int numOfCourseBuckets)
{
	m_numOfStudentBuckets=numOfStudentBuckets;
	m_numOfCourseBuckets=numOfCourseBuckets;
	m_studentTable = new HashTable<Student>(numOfStudentBuckets, Student::hash);
	m_courseTable = new HashTable<Course>(numOfCourseBuckets, Course::hash);
}

RegistrationManager::~RegistrationManager()
{
	delete m_studentTable;
	delete m_courseTable;

}

bool RegistrationManager::newStudent(const Student& stu)
{
	if (!m_studentTable->isExist(stu))
	{
		m_studentTable->insert(stu);
		return true;
	}
	return false;
}

bool RegistrationManager::modifyStudent(const Student& modifyStu)
{
	//return m_studentTablesetStudent.modify
	if (m_studentTable->isExist(modifyStu))
	{
		(*(m_studentTable->search(modifyStu))).setStudent(modifyStu.getStudentName(), modifyStu.getYear(), modifyStu.getGender());
		return true;
	}
	return false;
}

bool RegistrationManager::deleteStudent(const string& studentID)
{
	return m_studentTable->deleteElem(Student(studentID));

}

bool RegistrationManager::isStudentExist(const string& studentID) const
{
	return m_studentTable->isExist(Student(studentID));
}

bool RegistrationManager::retrieveStudent(const string& studentID, Student& student) const
{
	if (m_studentTable->isExist(Student(studentID)))
	{
		student=(*(m_studentTable->search(Student(studentID))));
		return true;
	}
	return false;
}

void RegistrationManager::getAllStudents(DoublyLinkedList<Student>& studentList) const
{
	studentList.clear();
	for (HashTable<Student>::Iterator itr=m_studentTable->begin(); itr!=m_studentTable->end(); itr++)
	{
		studentList.insert(*itr);
	}

}

bool RegistrationManager::newCourse(const Course& course)
{
	if (!m_courseTable->isExist(course))
	{
		m_courseTable->insert(course);
		return true;
	}
	return false;
}

bool RegistrationManager::modifyCourse(const Course& modifyCourse)
{
	//return m_studentTablesetStudent.modify
	if (m_courseTable->isExist(modifyCourse))
	{
		(*(m_courseTable->search(modifyCourse))).setCourse(modifyCourse.getCourseName(), modifyCourse.getCourseCredit());
		return true;
	}
	return false;
}

bool RegistrationManager::deleteCourse(const string& courseCode)
{
	return m_courseTable->deleteElem(Course(courseCode));

}

bool RegistrationManager::isCourseExist(const string& courseCode) const
{
	return m_courseTable->isExist(Course(courseCode));
}

bool RegistrationManager::retrieveCourse(const string& courseCode, Course& course) const
{
	if (m_courseTable->isExist(Course(courseCode)))
	{
		course=(*(m_courseTable->search(Course(courseCode))));
		return true;
	}
	return false;
}

void RegistrationManager::getAllCourses(DoublyLinkedList<Course>& courseList) const
{
	courseList.clear();
	for (HashTable<Course>::Iterator itr=m_courseTable->begin(); itr!=m_courseTable->end(); itr++)
	{
		courseList.insert(*itr);
	}

}
