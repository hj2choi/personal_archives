//============================================================================
// Name        : Lab7.cpp
// Author      : CHOI, Hong Joon
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <set>
#include <algorithm>
#include <string>
#include <iostream>
#include <vector>
using namespace std;

void printSetUsingIterator(set<string>& s) {
   // Print a set of string using iterator
	set<string>::iterator p;		// const?????
	//cout << "a"<<endl;
	cout << "{";
	for (p = s.begin(); p!=s.end(); ++p)
	{
		cout << *p;

	}
	cout << "}" << endl;
}

void printVectorUsingIterator(vector<string>& s) {
   // Print a vector of string using iterator
	vector<string>::iterator p;		// const?????
	//cout << "a"<<endl;
	cout << "{";
	for (p = s.begin(); p!=s.end(); ++p)
	{
		cout << *p;

	}
	cout << "}" << endl;
}

int main() {

    set<string> aSet, bSet;

	aSet.insert("CSE Alumni Association");
	aSet.insert("CSE Entrepreneurship Club");
	aSet.insert("CSE One");

	bSet.insert("CSE Entrepreneurship Club");
	bSet.insert("CSE Mentorship Program");
	bSet.insert("CSE TechShare");
	bSet.insert("Hackathon@HKUST");

	cout << "Set A Content =" ;
	printSetUsingIterator(aSet);
	cout << "Set B Content =" ;
	printSetUsingIterator(bSet);

	// Part 1: Complete the set operations here..
	set<string> setResult(aSet);
	set<string>::iterator st = setResult.end();
	set<string>::iterator f1 = aSet.begin();
	set<string>::iterator f2 = bSet.begin();
	set_union(f1, aSet.end(), f2, bSet.end(), st);
	cout << "A union B Content = ";
	printSetUsingIterator(setResult);

	vector<string> vecR ;
	// Part 2: Merge setA and setB to vectorR

	cout << "Vector R Content = ";
	printVectorUsingIterator(vecR);



	// Part 3: Complete the vector operations here..

	return 0;
}
