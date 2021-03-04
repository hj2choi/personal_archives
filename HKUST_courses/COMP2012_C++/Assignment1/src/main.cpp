/*
 * main.cpp
 *
 *      Author: Peter (cspeter@cse.ust.hk)
 *
 *      The sample test case of the StringSet class
 *
 */

#include "stringset.h"
#include <iostream>
#include <fstream>
using namespace std;


void printSetContent(const StringSet& s ) {
	cout << "Content = " ;
        int sz = s.getSize();
        cout << "{" ;
        if ( sz > 0 ) {
                for (int i=0; i<sz-1; i++ ) {
                        cout << s.getItem(i) << "," ;
                }
                cout << s.getItem(sz-1);
        }
        cout << "}" ;
        cout << endl ;
}
void printSetSizeAndCapacity(const StringSet& s ) {

	cout << "Size = " << s.getSize() << " ; " << " Capacity = " << s.getCapacity() << endl ;

}


int main() {
	//insert many items
	//insert, remove, assign, sort in random order
	// equals
	// union, interesct, and check capacity with size

	cout << "HELLO, Welcome to PA1 Gaurilla Test" << endl;
	const string groupCSE_EClub = "CSE Entrepreneurship Club";
	const string groupCSE_One = "CSE One" ;
	const string groupCSEAA = "CSE Alumni Association";

	const string actHackathon = "Hackathon@HKUST" ;
	const string actMentorship = "CSE Mentorship Program";
	const string actTechShare = "CSE TechShare" ;

	const string A = "A" ;
	const string B = "B" ;
	const string C = "C" ;
	const string D = "D" ;
	const string E = "E" ;
	const string F = "F" ;

	const string G = "G" ;
	const string H = "H" ;
	const string I = "I" ;
	const string J = "J" ;

	StringSet set1;
	set1.sort();
	set1.insert(A);
	set1.insert(A);
	set1.insert(B);
	set1.insert(D);
	set1.insert(C);
	set1.remove(B);
	set1.remove(E);
	set1.sort();
	set1.insert(F);
	set1.insert(B);
	set1.insert(E);
	set1.insert(E);
	set1.insert(A);
	set1.sort();
	if (!set1.isEmpty())
	{
		printSetContent(set1);
		printSetSizeAndCapacity(set1);
	}
	StringSet set2 = set1.assign(A);
	cout << "=====set1====" << endl;
	printSetContent(set1);
	printSetSizeAndCapacity(set1);
	cout << "=====set2====" << endl;
	printSetContent(set2);
	printSetSizeAndCapacity(set2);

	set1.insert(C);
	set1.remove(B);
	set1.remove(E);
	set1.sort();
	set1.insert(F);
	set1.insert(J);
	set1.insert(D);
	cout << "=====set1====" << endl;
	printSetContent(set1);
	printSetSizeAndCapacity(set1);
	cout << "=====set2====" << endl;
	printSetContent(set2);
	printSetSizeAndCapacity(set2);







	StringSet set3 = H;
	StringSet set4 = set3;

	set4.insert(B);
	set4.insert(A);
	set4.remove(A);
	set4.insert(A);
	set3.insert(F);
	cout << "=====set3====" << endl;
	printSetContent(set3);
	printSetSizeAndCapacity(set3);
	cout << "=====set4====" << endl;
	printSetContent(set4);
	printSetSizeAndCapacity(set4);

	string inputStrArr[] = {G, J, C, D, F, H, I, E, A, B};
	StringSet* setMax = new StringSet(inputStrArr, 10);
	cout << "=====setMax====" << endl;
	printSetContent(*setMax);
	printSetSizeAndCapacity(*setMax);


	cout << "=====setMax====" << endl;
	printSetContent(*setMax);
	printSetSizeAndCapacity(*setMax);
	cout << "=====set4====" << endl;
	printSetContent(set4);
	printSetSizeAndCapacity(set4);

	set1.remove(C);
	set1.remove(D);
	set2.remove(C);
	set2.remove(D);
	set2.insert(J);
	set2.insert(F);
	set3.insert(A);

	cout << endl << endl << endl;
	cout << "=====set1====" << endl;
	printSetContent(set1);
	printSetSizeAndCapacity(set1);
	cout << "=====set2====" << endl;
	printSetContent(set2);
	printSetSizeAndCapacity(set2);
	cout << "=====set3====" << endl;
	printSetContent(set3);
	printSetSizeAndCapacity(set3);
	cout << "=====set4====" << endl;
	printSetContent(set4);
	printSetSizeAndCapacity(set4);
	cout << "=====setMax====" << endl;
	printSetContent(*setMax);
	printSetSizeAndCapacity(*setMax);

	cout << endl << endl << endl;
	cout << "equality test (111110000010)" << endl;
	cout << set1.equals(set1)<< endl;
	cout << set1.equals(set2)<< endl;
	cout << set2.equals(set2)<< endl;
	cout << set3.equals(set3)<< endl;
	cout << set4.equals(set4)<< endl;
	cout << set1.equals(set3)<< endl;
	cout << set1.equals(set4)<< endl;
	cout << set2.equals(set3)<< endl;
	cout << set2.equals(set4)<< endl;
	cout << set3.equals(set4)<< endl;

	StringSet setMaxSort = *setMax;
	setMaxSort.sort();
	cout << setMax->equals(setMaxSort) << endl;
	setMaxSort.remove(A);
	cout << setMax->equals(setMaxSort) << endl;
	setMaxSort.insert(A);
	cout << "=====setMax====" << endl;
	printSetContent(*setMax);
	printSetSizeAndCapacity(*setMax);
	cout << "=====setMaxSort====" << endl;
	printSetContent(setMaxSort);
	printSetSizeAndCapacity(setMaxSort);


	cout << endl << endl << endl;

	cout << "check if union is same as intersection (should be all 1)" << endl;
	cout << set1.setUnion(set1).equals(set1.setIntersect(set1)) << endl;
	cout << set2.setUnion(set2).equals(set2.setIntersect(set2)) << endl;
	cout << set3.setUnion(set3).equals(set3.setIntersect(set3)) << endl;
	cout << set4.setUnion(set4).equals(set4.setIntersect(set4)) << endl;
	cout << setMaxSort.setUnion(*setMax).equals(setMaxSort.setIntersect(*setMax)) << endl;



	cout << "set operation, set 1 and set 3 (A,F is common factor)" << endl;
	StringSet setUni, setInt, setDiff;
	setUni.assign(setUnion(set1, set3));
	setInt.assign(setIntersect(set1, set3));
	setDiff.assign(setDifference(set1, set3));

	cout << "=====setUni====" << endl;
	printSetContent(setUni);
	printSetSizeAndCapacity(setUni);
	cout << "=====setInt====" << endl;
	printSetContent(setInt);
	printSetSizeAndCapacity(setInt);
	cout << "=====setDiff====" << endl;
	printSetContent(setDiff);
	printSetSizeAndCapacity(setDiff);
	cout << "correctness (should be 1)" << endl;
	StringSet set1Tmp = set1;
	cout << set1Tmp.setUnion(set3).equals(setUni) << endl;
	set1Tmp.assign(set1);
	cout << set1Tmp.setIntersect(set3).equals(setInt) << endl;
	set1Tmp.assign(set1);
	cout << set1Tmp.setDifference(set3).equals(setDiff) << endl;

	cout << "set operation, set 2 and set Max Sort" << endl;
	//StringSet setUni, setInt, setDiff;
	setUni.assign(setUnion(set2, setMaxSort));
	setInt.assign(setIntersect(set2, setMaxSort));
	setDiff.assign(setDifference(set2, setMaxSort));

	cout << "=====setUni====" << endl;
	printSetContent(setUni);
	printSetSizeAndCapacity(setUni);
	cout << "=====setInt====" << endl;
	printSetContent(setInt);
	printSetSizeAndCapacity(setInt);
	cout << "=====setDiff====" << endl;
	printSetContent(setDiff);
	printSetSizeAndCapacity(setDiff);
	cout << "correctness (should be 1)" << endl;
	StringSet set2Tmp = set2;
	cout << set2Tmp.setUnion(setMaxSort).equals(setUni) << endl;
	set2Tmp.assign(set2);
	cout << set2Tmp.setIntersect(setMaxSort).equals(setInt) << endl;
	set2Tmp.assign(set2);
	cout << set2Tmp.setDifference(setMaxSort).equals(setDiff) << endl;


	StringSet setEmpty;
	cout << "check if difference is same as empty set (should be all 1)" << endl;
	cout << setEmpty.equals(set1.setDifference(set1)) << endl;
	cout << setEmpty.equals(set2.setDifference(set2)) << endl;
	cout << setEmpty.equals(set3.setDifference(set3)) << endl;
	cout << setEmpty.equals(set4.setDifference(set4)) << endl;
	//cout << setEmpty.equals(setMaxSort.setDifference(*setMax)) << endl;



	cout << "delete all from setMax" << endl;

	cout << "Remove " << A << " ? " << setMaxSort.remove(A)  << endl;
	printSetSizeAndCapacity(setMaxSort);
	printSetContent(setMaxSort);
	cout << "Remove " << B << " ? " << setMaxSort.remove(B)  << endl;
	printSetSizeAndCapacity(setMaxSort);
	printSetContent(setMaxSort);
	cout << "Remove " << D << " ? " << setMaxSort.remove(D)  << endl;
	printSetSizeAndCapacity(setMaxSort);
	printSetContent(setMaxSort);
	cout << "Remove " << C << " ? " << setMaxSort.remove(C)  << endl;
	printSetSizeAndCapacity(setMaxSort);
	printSetContent(setMaxSort);
	cout << "Remove " << C << " ? " << setMaxSort.remove(C)  << endl;
	printSetSizeAndCapacity(setMaxSort);
	printSetContent(setMaxSort);
	cout << "Insert " << C << " ? " << setMaxSort.insert(C)  << endl;
	printSetSizeAndCapacity(setMaxSort);
	printSetContent(setMaxSort);
	/*cout << "Insert " << A << " ? " << setMaxSort.insert(A)  << endl;
	printSetSizeAndCapacity(setMaxSort);
	printSetContent(setMaxSort);*/
	cout << "Remove " << F << " ? " << setMaxSort.remove(F)  << endl;
	printSetSizeAndCapacity(setMaxSort);
	printSetContent(setMaxSort);
	cout << "Remove " << G << " ? " << setMaxSort.remove(G)  << endl;
	printSetSizeAndCapacity(setMaxSort);
	printSetContent(setMaxSort);
	cout << "Remove " << A << " ? " << setMaxSort.remove(A)  << endl;
	printSetSizeAndCapacity(setMaxSort);
	printSetContent(setMaxSort);
	cout << "Remove " << C << " ? " << setMaxSort.remove(C)  << endl;
	printSetSizeAndCapacity(setMaxSort);
	printSetContent(setMaxSort);
	cout << "Remove " << E << " ? " << setMaxSort.remove(E)  << endl;
	printSetSizeAndCapacity(setMaxSort);
	printSetContent(setMaxSort);
	cout << "Remove " << H << " ? " << setMaxSort.remove(H)  << endl;
	printSetSizeAndCapacity(setMaxSort);
	printSetContent(setMaxSort);
	cout << "Remove " << I << " ? " << setMaxSort.remove(I)  << endl;
	printSetSizeAndCapacity(setMaxSort);
	printSetContent(setMaxSort);
	cout << "Remove " << J << " ? " << setMaxSort.remove(J)  << endl;
	printSetSizeAndCapacity(setMaxSort);
	printSetContent(setMaxSort);


	//setUni.assign(setUnion(set1,set3));
	//setUni = setIntersect(set1,set3);
	//setUni = setDifference(set1,set3);
	//cout << setUni.equals(set1.setUnion(set3)) << endl;
	//cout << setInt.equals(set1.setIntersect(set3)) << endl;
	//cout << setDiff.equals(set1.setDifference(set3)) << endl;
/*
	cout << "=== Case 1: Empty Set ===" << endl ;
	StringSet set1;
	set1.sort();
	if (set1.isEmpty() ) {
		printSetContent(set1);
		printSetSizeAndCapacity(set1);
	}

	cout << "=== Case 2: A set with a single item ===" << endl ;
	StringSet set2(groupCSE_EClub);
	printSetContent(set2);
	printSetSizeAndCapacity(set2);
	
	cout << "=== Case 2.1: A set with a array of items ===" << endl ;
	string inputStrArr[] = {groupCSE_EClub, groupCSE_One, groupCSEAA};
	StringSet set2_1(inputStrArr, 3);
	printSetContent(set2_1);
	printSetSizeAndCapacity(set2_1);

	cout << "=== Case 3: Copy constructor, insert and sort operations ===" << endl ;
	StringSet set3(set2);
	printSetContent(set3);
	printSetSizeAndCapacity(set3);
	cout << "Insert " << groupCSE_One  << " ? " << set3.insert(groupCSE_One) << endl ;
	printSetSizeAndCapacity(set3);
	cout << "Insert " << groupCSEAA    << " ? " << set3.insert(groupCSEAA) << endl ;
	printSetSizeAndCapacity(set3);
	cout << "Insert " << actHackathon  << " ? " << set3.insert(actHackathon) << endl ;
	printSetSizeAndCapacity(set3);
	cout << "Insert " << actMentorship << " ? " << set3.insert(actMentorship) << endl ;
	printSetSizeAndCapacity(set3);
	cout << "Insert " << actTechShare  << " ? " << set3.insert(actTechShare) << endl ;
	printSetSizeAndCapacity(set3);
	cout << "Insert " << groupCSE_EClub  << " ? " << set3.insert(groupCSE_EClub) << endl ;
	printSetSizeAndCapacity(set3);

	cout << set3.exists(actTechShare) << endl;
	printSetSizeAndCapacity(set3);
	set3.sort();
	printSetContent(set3);


	cout << "=== Case 4: Assignment and remove operations ===" << endl ;
	StringSet set4;    // empty set
	set4.assign(set3); // assign set3 to set4
	printSetSizeAndCapacity(set4);
	printSetContent(set4);


	cout << "Remove " << groupCSEAA      << " ? "   << set4.remove(groupCSEAA)  << endl;
	printSetSizeAndCapacity(set4);
	cout << "Remove " << groupCSE_One    << " ? "   << set4.remove(groupCSE_One)  << endl;
	printSetSizeAndCapacity(set4);
	cout << "Remove " << actHackathon    << " ? "   << set4.remove(actHackathon)  << endl;
	set4.sort();
	printSetSizeAndCapacity(set4);
	printSetContent(set4);


	cout << "Remove " << actTechShare    << " ? "   << set4.remove(actTechShare)  << endl;
	printSetSizeAndCapacity(set4);
	cout << "Remove " << actHackathon    << " ? "   << set4.remove(actHackathon)  << endl;
	printSetSizeAndCapacity(set4);
	cout << "Remove " << groupCSE_EClub  << " ? "   << set4.remove(groupCSE_EClub)  << endl;
	set4.sort();
	printSetSizeAndCapacity(set4);
	printSetContent(set4);
	cout << "Remove " << groupCSE_EClub  << " ? "   << set4.remove(actMentorship)  << endl;
	set4.sort();
	printSetSizeAndCapacity(set4);
	printSetContent(set4);

	cout << "=== Case 5: Set comparison ===" << endl ;
	StringSet set5_empty1 ; // empty set 1
	StringSet set5_empty2 ; // empty set 2
	cout << "Two empty sets are equal? " <<  (set5_empty1.equals(set5_empty2)) << endl ;
	cout << "Two empty sets are different? " << (set5_empty1.notEquals(set5_empty2)) << endl ;

	StringSet set5_order1; // set1 = {CSE One,CSE Entrepreneurship Club}

	set5_order1.insert(groupCSE_One);
	set5_order1.insert(groupCSE_EClub);

	StringSet set5_order2; // set2 = {CSE Entrepreneurship Club,CSE One}
	set5_order2.insert(groupCSE_EClub);
	set5_order2.insert(groupCSE_One);

	cout << "Order 1 ";
	printSetSizeAndCapacity(set5_order1);
	printSetContent(set5_order1);
	cout << "Order 2 ";
	printSetSizeAndCapacity(set5_order2);
	printSetContent(set5_order2);

	cout << "Two sets are equal? " << (set5_order1.equals(set5_order2)) << endl ;
	cout << "Two sets are different? " << (set5_order2.notEquals(set5_order1)) << endl ;

	cout << "Insert " << groupCSEAA << " ? " << ( set5_order1.insert(groupCSEAA) ) << endl ;
	cout << "Two sets are equal? " << (set5_order1.equals(set5_order2)) << endl ;
	cout << "Two sets are different? " << (set5_order2.notEquals(set5_order1)) << endl ;

	cout << "Insert " << actHackathon << " ? " << ( set5_order2.insert(actHackathon) ) << endl ;
	cout << "Two sets are equal? " << (set5_order1.equals(set5_order2)) << endl ;
	cout << "Two sets are different? " << (set5_order2.notEquals(set5_order1)) << endl ;


	cout << "=== Case 6: Set union, intersect and difference ===" << endl ;

	string arrayA[3] = {groupCSEAA, groupCSE_EClub, groupCSE_One};
	string arrayB[4] = {groupCSE_EClub, actHackathon, actMentorship, actTechShare};


	StringSet set6a(arrayA, 3);
	StringSet set6b(arrayB, 4);
	

	set6a.sort();
	set6b.sort();

	cout << "Set A ";
	printSetSizeAndCapacity(set6a);
	printSetContent(set6a);
	cout << "Set B ";
	printSetSizeAndCapacity(set6b);
	printSetContent(set6b);

	StringSet setUni, setInter, setDiff;

	setUni.assign(set6a);
	setUni.setUnion(set6b);

	setInter.assign(set6a);
	setInter.setIntersect(set6b);

	setDiff.assign(set6a);
	setDiff.setDifference(set6b);

	setUni.sort();
	setInter.sort();
	setDiff.sort();

	cout << "A union B ";
	printSetSizeAndCapacity(setUni);
	printSetContent(setUni);
	cout << "A intersect B ";
	printSetSizeAndCapacity(setInter);
	printSetContent(setInter);
	cout << "A difference B ";
	printSetSizeAndCapacity(setDiff);
	printSetContent(setDiff);
	

	cout << "=== Case 7: Binary operators for union, intersect and difference ===" << endl ;
	StringSet set7a(set6a);
	StringSet set7b(set6b);
	cout << "Set A ";
	printSetContent(set7a);
	cout << "Set B ";
	printSetContent(set7b);

	setUni.assign(  setUnion(set7b,set7a) ) ;
	setInter.assign(  setIntersect(set7b,set7a)) ;
	setDiff.assign(   setDifference(set7b,set7a)) ;

	setUni.sort();
	setInter.sort();
	setDiff.sort();

	cout << "B union A ";
	printSetContent(setUni);
	cout << "B intersect A ";
	printSetContent(setInter);
	cout << "B difference A ";
	printSetContent(setDiff);

	cout << "=== Case 8: Size and capacity ===" << endl ;
	StringSet set8;
	printSetSizeAndCapacity(set8);
	cout << "Insert " << actHackathon << " ? " << set8.insert(actHackathon)  << endl;
	printSetSizeAndCapacity(set8);
	cout << "Insert " << actTechShare << " ? " << set8.insert(actTechShare)  << endl;
	printSetSizeAndCapacity(set8);
	cout << "Insert " << actMentorship << " ? " << set8.insert(actMentorship)  << endl;
	printSetSizeAndCapacity(set8);
	cout << "Insert " << groupCSE_EClub << " ? " << set8.insert(groupCSE_EClub)  << endl;
	printSetSizeAndCapacity(set8);

	cout << "Remove " << groupCSE_EClub << " ? " << set8.remove(groupCSE_EClub)  << endl;
	printSetSizeAndCapacity(set8);
	cout << "Remove " << actHackathon << " ? " << set8.remove(actHackathon)  << endl;
	printSetSizeAndCapacity(set8);
	printSetContent(set8);
	cout << "Remove " << actTechShare << " ? " << set8.remove(actTechShare)  << endl;
	printSetSizeAndCapacity(set8);
	printSetContent(set8);

	cout << "Remove " << actMentorship << " ? " << set8.remove(actMentorship)  << endl;
	printSetSizeAndCapacity(set8);
	printSetContent(set8);
	cout << "Insert " << actHackathon << " ? " << set8.insert(actHackathon)  << endl;
	printSetSizeAndCapacity(set8);
	printSetContent(set8);
*/

	/*

	*/
	delete setMax;
	return 0;
}

