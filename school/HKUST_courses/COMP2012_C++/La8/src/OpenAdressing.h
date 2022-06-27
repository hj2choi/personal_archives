#ifndef OPEN_ADRESSING_H
#define OPEN_ADRESSING_H

/**

Assumptions:

    * Values are non-negative integers
	* -1 is reserved as EMPTY
	* No need to implement search and deletion

*/
class OpenAddressing {
public:
	static const int EMPTY = -1;   // -1 is reserved as EMPTY

	OpenAddressing(int size);      // constructor
	virtual ~OpenAddressing();	   // destructor
	bool insert(int value);        // insert a given value to the hash table using open addressing
	int reset(int newSize);        // reset the hash table with new size and initialize every cells as EMPTY
	void print() ;                 // print the current hash table

	// Pure virtual functions: to be implemented in the sub-classes
	virtual int hash(int value) = 0;                         // the primary hash function
	virtual int nextEmpty(int nonEmptyIndex, int value) = 0; // probe to the next empty location

protected:
	int* data;     // a dynamic array of integers
	int currUsed;  // number of array entry currently occupied
	int currSize;  // size of data
};






#endif //  OPEN_ADDRESSING_H
