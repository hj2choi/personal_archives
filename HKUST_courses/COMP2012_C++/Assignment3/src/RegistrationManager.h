/*
 * RegistrationManager.h
 *
 *  Created on: Jan 16, 2015
 *      Author: cspeter
 */

#ifndef REGISTERATIONMANAGER_H_
#define REGISTERATIONMANAGER_H_

#include "Student.h"
#include "Course.h"



#include "DoublyLinkedList.h"
#include "HashTable.h"

class RegistrationManager {
private:

	int m_numOfStudentBuckets;   // number of student bucket
	int m_numOfCourseBuckets;    // number of course bucket
	HashTable<Student>  *m_studentTable; // hash table of Student
	HashTable<Course>   *m_courseTable;  // hash table of Course

public:

	RegistrationManager(int numOfStudentBuckets, int numOfCourseBuckets);
	~RegistrationManager();

	// Student Management
	bool newStudent(const Student& stu);	// return true if student not exist before
	bool modifyStudent(const Student& modifyStu);	// return true if student exist before
	bool deleteStudent(const string& studentID);	// return true if studentID exist before
	bool isStudentExist(const string& studentID) const;	// return true if studentID exist before
	bool retrieveStudent(const string& studentID, Student& student) const;	// return true if studentID exist before

	// Course Management
	bool newCourse(const Course& course);	// return true if course not exist before
	bool modifyCourse(const Course& modifyCourse);	// return true if course exist before
	bool deleteCourse(const string& courseCode); // return true if courseCode exist before
	bool isCourseExist(const string& courseCode) const; // return true if courseCode exist before
	bool retrieveCourse(const string& courseCode, Course& course) const;	// return true if courseCode exist before

	// Query Management
	void getAllStudents(DoublyLinkedList<Student>& studentList) const; // output to studentList (sorted)
	void getAllCourses(DoublyLinkedList<Course>& courseList) const;	// output to courseList (sorted)

};


#endif /* REGISTERATIONMANAGER_H_ */
