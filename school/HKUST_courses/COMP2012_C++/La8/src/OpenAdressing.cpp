#include "OpenAdressing.h"

#include <iostream>
using namespace std;

bool OpenAddressing::insert(int value) {
	if ( currUsed < currSize ) {
		int itemIndex = hash(value);
		if ( data[itemIndex] != EMPTY ) {
			itemIndex = nextEmpty(itemIndex, value);
		}
		data[itemIndex] = value;
		currUsed = currUsed+1;
		return true;
	}

	return false;
}



OpenAddressing::OpenAddressing(int size)
: data(0), currUsed(0) {
	reset(size);
}

OpenAddressing::~OpenAddressing() {
	delete [] data;
}

int OpenAddressing::reset(int newSize) {
	if ( data != 0 ) {
		delete [] data ;
	}
	currUsed = 0;
	currSize = newSize;
	data = new int[currSize];
	for (int i=0; i<currSize; i++)
		data[i] = EMPTY ;
}

void OpenAddressing::print()  {
	cout << "[" ;
	for (int i=0; i<currSize; i++) {
		if ( data[i] == EMPTY ) {
			cout << "_" ;
		}
		else {
			cout << data[i] ;
		}
		if ( i<currSize-1)
			cout << ", " ;
	}
	cout << "]" << endl ;
}
