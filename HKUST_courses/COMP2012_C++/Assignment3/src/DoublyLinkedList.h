/*
 * DoublyLinkedList.h
 *
 *  Created on: Jan 14, 2015
 *      Author: cspeter
 */

#ifndef DOUBLYLINKEDLIST_H_
#define DOUBLYLINKEDLIST_H_


#include "Pair.h"
#include <cstddef> // a header file defining NULL
#include <cassert>
#include <iostream>
using std::cout;              // cout refers to std::cout
using std::endl;              // endl refers to std::endl


//
// Given:
//     DoublyLinkedList::Iterator and
//     DoublyLinkedList::ConstIterator
// are already implemented
//
// TODO: Implement DoublyLinkedList class in "DoublyLinkedList.impl"
//
// Restrictions: ElemType must define the operator< and operator=
//
// Notes:
//
// This doubly-linked list will have a dummy head node
// The element inside will be sorted according to operator<,
// which should define a "strict weak ordering" on the ElemType
// The ordering should not be changed once after inserted into the list,
// until it is removed
//

// ========================================================================
// Declaration of
//    template<class ElemType>
//    class DoublyLinkedList
// ========================================================================


template<class ElemType>
class DoublyLinkedList {

private:
	// a private nested struct, anybody outside cannot access it
	struct DoublyLinkedListNode {
	   DoublyLinkedListNode(const ElemType& element = ElemType())
		: m_element( element ), m_next(NULL), m_prev(NULL) { }
	   ElemType m_element;
	   DoublyLinkedListNode* m_next;
	   DoublyLinkedListNode* m_prev;
	};

private:
	DoublyLinkedListNode* m_head;
	int m_size;

public:

	// Note:
	//   Both classes are already implemented in DoublyLinkedListIterators.impl
	class Iterator;	     // a public nested class, pointing to an element in a linked list
	class ConstIterator; // a public nested class, pointing to a constant element in a linked list


public:

	// ====================================================================
	// TODO: Implement all the remaining functions in DoublyLinkedList.impl
	// ====================================================================

	// default constructor
	DoublyLinkedList();

	// destructor
	~DoublyLinkedList();

	// copy constructor
	DoublyLinkedList(const DoublyLinkedList& copy);

	// assignment operator
	DoublyLinkedList& operator=(const DoublyLinkedList& assign);

	// return an iterator pointing to the newly added element
	Iterator insert(const ElemType& element);

	// return true if the element exist (compared according to operator
	bool isExist(const ElemType& element) const;

	// return true only if at least one element is really deleted
	bool deleteElem(const ElemType& element);	// delete all the items, that are equivalent to element
	bool deleteElem(Iterator& it);              // the iterator will point to the next element

	// clear the whole list
	void clear();

	// return true if it is empty
	bool isEmpty() const;

	// return the number of element inside
	int size() const;

	// return the iterator, which pointed to the first equivalent element inside the list
	// return end() if not found
	Iterator search(const ElemType& target);
	ConstIterator search(const ElemType& target) const;

	// return a pair of iterators, which indicate the range;
	// first iterator  will equal to target,
	// last iterator is the first one (after first iterator) does not equal target or end()
	// return ( end(), end() ) if not found
	Pair<Iterator, Iterator> searchRange(const ElemType& target);
	Pair<ConstIterator, ConstIterator> searchRange(const ElemType& target) const;

	// return the begin iterator of the list
	Iterator begin();
	ConstIterator begin() const;

	// return the end iterator of the list
	Iterator end();
	ConstIterator end() const;


	// FOR Debug purpose only, ElemType must define the operator<<
	void printForward() const;
	void printBackward() const;


};


// The debug printing functions are implemented
template<class ElemType>
void DoublyLinkedList<ElemType>::printForward() const
{
	cout << "(size=" << m_size << ") ";
	const DoublyLinkedListNode* cur = m_head->m_next;
	while ( cur != m_head )
	{
		cout << cur->m_element << " ";
		cur = cur->m_next;
	}
	cout << endl;
}

template<class ElemType>
void DoublyLinkedList<ElemType>::printBackward() const
{
	cout << "(size=" << m_size << ") ";
	const DoublyLinkedListNode* cur = m_head->m_prev;
	while ( cur != m_head )
	{
		cout << cur->m_element << " ";
		cur = cur->m_prev;
	}
	cout << endl;
}


// ========================================================================
// Declaration of
//    template<class ElemType>
//    class DoublyLinkedList<ElemType>::Iterator
// Note: It is already implemented, and you only need to know how to use it
// =========================================================================

template<class ElemType>
class DoublyLinkedList<ElemType>::Iterator {

public:
	friend class DoublyLinkedList;
	friend class DoublyLinkedList::ConstIterator;

public:
	Iterator();                    // default constructor
	ElemType& operator*() const;   // dereference operator
	Iterator& operator++();        // Prefix increment operator
	Iterator operator++(int);      // Postfix increment operator

	friend bool operator==(const Iterator& it1, const Iterator& it2) {
		return ( it1.m_pLinkedList == it2.m_pLinkedList && it1.m_pNode == it2.m_pNode );
	}

	friend bool operator!=(const Iterator& it1, const Iterator& it2) {
		return ( it1.m_pLinkedList != it2.m_pLinkedList || it1.m_pNode != it2.m_pNode );
	}

private:
	// These members can only be used inside the friend classes
	Iterator(DoublyLinkedList* linkedList, DoublyLinkedListNode* element);
	void set(DoublyLinkedList* linkedList, DoublyLinkedListNode* element);

private:
	const DoublyLinkedList*	m_pLinkedList;
	DoublyLinkedListNode* m_pNode;
};

// ========================================================================
// Declaration of
//    template<class ElemType>
//    class DoublyLinkedList<ElemType>::ConstIterator
// Note: It is already implemented, and you only need to know how to use it
// ========================================================================

template<class ElemType>
class DoublyLinkedList<ElemType>::ConstIterator {

public:
	friend class DoublyLinkedList;
	friend class DoublyLinkedList::Iterator;

public:

	ConstIterator();
	ConstIterator(const Iterator& nonCostIterator);		// can cast Iterator as ConstIterator
	ConstIterator& operator=(const Iterator& assign);	// can cast Iterator as ConstIterator


	const ElemType& operator*() const;		// dereference operator
	ConstIterator& operator++();			// Prefix increment operator
	ConstIterator operator++(int);			// Postfix increment operator
	friend bool operator==(const ConstIterator& it1, const ConstIterator& it2) {
		return ( it1.m_pLinkedList == it2.m_pLinkedList && it1.m_pNode == it2.m_pNode );
	}

	friend bool operator!=(const ConstIterator& it1, const ConstIterator& it2) {
		return ( it1.m_pLinkedList != it2.m_pLinkedList || it1.m_pNode != it2.m_pNode );
	}

private:
	// These members can only be used inside the friend classes
	ConstIterator(const DoublyLinkedList* linkedList, const DoublyLinkedListNode* element);
	void set(const DoublyLinkedList* linkedList, const DoublyLinkedListNode* element);

private:
	const DoublyLinkedList*	m_pLinkedList;
	const DoublyLinkedListNode* m_pNode;
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

#include "DoublyLinkedListIterators.impl" // Given: No need to implement
#include "DoublyLinkedList.impl"          // TODO: Implement





#endif /* DOUBLYLINKEDLIST_H_ */
