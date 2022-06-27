/*
 * HashTable.h
 *
 *  Created on: Jan 16, 2015
 *      Author: cspeter
 */

#ifndef HASHTABLE_H_
#define HASHTABLE_H_

#include "Pair.h"
#include "DoublyLinkedList.h" // depends on DoublyLinkedList
#include <cstddef>            // a header file defining NULL
#include <cassert>
#include <climits>            // a header file defining UINT_MAX
#include <iostream>
using std::cout;              // cout refers to std::cout
using std::endl;              // endl refers to std::endl

// An upper bound of the hash table, can be changed to a smaller value (e.g. 1000)
const unsigned int HASH_END_ITERATOR_BUCKET_INDEX = UINT_MAX;

//
// TODO:
//
// Restrictions:
//   ElemType must define the operator<
//   If operator< on ElemType indicated that two keys are equal, then the hash function must return the same value
//   Also, the ordering (defined by operator<) and the index (returned by the function pointer) should not be changed once
//   after inserted into the hash table (until removed from the hash table)
//
// Notes:
//   The following is the implementation of hash table using separate chaining
//   The hash function is inputted as a function pointer in the constructor, it will return the index of bucket
//

// ========================================================================
// Declaration of
//    template<class ElemType>
//    class HashTable
// ========================================================================

template<class ElemType>
class HashTable {
public:
	typedef unsigned int (*HashFunc) (const ElemType&, unsigned int NUM_OF_BUCKET);
	class Iterator;
	class ConstIterator;

private:
	DoublyLinkedList<ElemType>* m_buckets;
	unsigned int m_numBuckets;
	int m_size;
	HashFunc m_hashFunction;

public:
	// hashFunc must return an integer in-between 0 and (numBuckets-1)
	HashTable(int numBuckets, HashFunc hashFunc);
	HashTable(const HashTable& copy);
	HashTable& operator=(const HashTable& assign);
	~HashTable();

	// return an iterator pointing to the newly added element
	Iterator insert(const ElemType& element);

	// return true only if there exist an element (equivalent to the target)
	bool isExist(const ElemType& target) const;

	// return true only if at least one element is really deleted
	bool deleteElem(const ElemType& target);	// delete all the items, that are equivalent to target
	bool deleteElem(Iterator& it);              // the iterator will point to the next element

	// clear the whole hash table
	void clear();

	// return true if the hash table is empty
	inline bool isEmpty() const;

	// return the number of elements inside the hash table
	inline int size() const;

	// return the begin and end iterator
	Iterator begin();
	ConstIterator begin() const;
	Iterator end();
	ConstIterator end() const;

	// return the first element, which is equivalent to the target
	// return end() if not found
	Iterator search(const ElemType& target);
	ConstIterator search(const ElemType& target) const;

	// FOR Debug purpose only, KeyType and ContentType must define the operator<<
	// It is given and implemented
	void print() const;

};

// Implementation of print() for DEBUG purpose
template<class ElemType>
void HashTable<ElemType>::print() const {
	for (unsigned int i=0; i<m_numBuckets; i++) {
		cout << "Hash Buckets = " << i << ":" << endl;

		if ( m_buckets[i].isEmpty() )
			cout << "Empty" << endl;
		else {
			cout << ' ';	// since it will print every element with a preceeding space
			m_buckets[i].printForward();
		}
		cout << endl;
	}
}


// ========================================================================
// Declaration of
//    template<class ElemType>
//    class HashTable<ElemType>::Iterator
// Note: It is already implemented, and you only need to know how to use it
// =========================================================================
template<class ElemType>
class HashTable<ElemType>::Iterator {
public:
	friend class HashTable;
	friend class ConstIterator;
	Iterator();

	ElemType& operator*() const;	// dereference operator
	Iterator& operator++();			// Prefix increment operator
	Iterator operator++(int);		// Postfix increment operator
	friend bool operator==(const Iterator& it1, const Iterator& it2)
	{
		if ( it1.m_pHashTable == it2.m_pHashTable && it1.m_bucketIndex == it2.m_bucketIndex)
		{
			if ( it1.m_bucketIndex == HASH_END_ITERATOR_BUCKET_INDEX )
				return true;
			else return ( it1.m_iterator == it2.m_iterator );
		}
		else return false;
	}

	friend bool operator!=(const Iterator& it1, const Iterator& it2)
	{
		if ( it1.m_pHashTable != it2.m_pHashTable || it1.m_bucketIndex != it2.m_bucketIndex )
			return true;
		else if ( it1.m_bucketIndex == HASH_END_ITERATOR_BUCKET_INDEX )
			return false;
		else return ( it1.m_iterator != it2.m_iterator );
	}

private:
	// These members can only be used inside HashTable
	Iterator(HashTable* hashTable, unsigned int bucketIndex, const typename DoublyLinkedList<ElemType>::Iterator& it);
	void set(HashTable* hashTable, unsigned int bucketIndex, const typename DoublyLinkedList<ElemType>::Iterator& it);

private:
	const HashTable* m_pHashTable;
	unsigned int m_bucketIndex;
	typename DoublyLinkedList<ElemType>::Iterator m_iterator;

};



// ========================================================================
// Declaration of
//    template<class ElemType>
//    class HashTable<ElemType>::ConstIterator
// Note: It is already implemented, and you only need to know how to use it
// =========================================================================
template<class ElemType>
class HashTable<ElemType>::ConstIterator {
public:
	friend class HashTable;
	friend class Iterator;
	ConstIterator();
	ConstIterator(const Iterator& nonConstIterator);    // can cast Iterator as ConstIterator
	ConstIterator& operator=(const Iterator& assign);   // can cast Iterator as ConstIterator

	const ElemType& operator*() const;                  // dereference operator
	ConstIterator& operator++();                        // Prefix increment operator
	ConstIterator operator++(int);                      // Postfix increment operator

	friend bool operator==(const ConstIterator& it1, const ConstIterator& it2)
	{
		if ( it1.m_pHashTable == it2.m_pHashTable && it1.m_bucketIndex == it2.m_bucketIndex)
		{
			if ( it1.m_bucketIndex == HASH_END_ITERATOR_BUCKET_INDEX )
				return true;
			else return ( it1.m_iterator == it2.m_iterator );
		}
		else return false;
	}

	friend bool operator!=(const ConstIterator& it1, const ConstIterator& it2)
	{
		if ( it1.m_pHashTable != it2.m_pHashTable || it1.m_bucketIndex != it2.m_bucketIndex )
			return true;
		else if ( it1.m_bucketIndex == HASH_END_ITERATOR_BUCKET_INDEX )
			return false;
		else return ( it1.m_iterator != it2.m_iterator );
	}

private:
	// These members can only be used inside HashTable
	ConstIterator(const HashTable* hashTable, unsigned int bucketIndex,
		const typename DoublyLinkedList<ElemType>::ConstIterator& it);
	void set(const HashTable* hashTable, unsigned int bucketIndex,
		const typename DoublyLinkedList<ElemType>::ConstIterator& it);

	const HashTable* m_pHashTable;
	unsigned int m_bucketIndex;
	typename DoublyLinkedList<ElemType>::ConstIterator m_iterator;


};


//
// ========================================================================
// Implementation of
//    template<class ElemType>
//    class DoublyLinkedList
// ========================================================================
//
// Note: Inclusive compilation is required for C++ template class
// Thus, the implementation must be appended at the end of the header file
//

#include "HashTableIterators.impl" // Given: No need to implement
#include "HashTable.impl"          // TODO: Implement




#endif /* HASHTABLE_H_ */
