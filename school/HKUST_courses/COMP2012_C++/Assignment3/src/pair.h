/*
 * Pair.h
 *
 *  Created on: Jan 14, 2015
 *      Author: cspeter
 */

#ifndef PAIR_H_
#define PAIR_H_

#include "utility.h"


//
// TODO: Nothing to do. It is given, no need to change and modify
//
// Restrictions: FirstType and SecondType MUST either be a primitive type (e.g. int) or
//               have a default constructor implemented
//               They must also implement the assignment operator (=)
//               and less than operator (<)
//

// ========================================================================
// Declaration of
//    template<class FirstType, class SecondType>
//    class Pair
// ========================================================================

template<class FirstType, class SecondType>
class Pair {

private:
	FirstType m_first;
	SecondType m_second;

public:
	Pair(); // default constructor
	Pair(const FirstType& first, const SecondType& second); // another constructor
	Pair(const Pair<FirstType,SecondType>& copy); // copy constructor
	Pair<FirstType,SecondType>& operator=( const Pair<FirstType,SecondType>& assign); // assignment operator


	// Setter functions
	void setFirst(const FirstType& first);
	void setSecond(const SecondType& second);
	void set(const FirstType& first, const SecondType& second);

	// Getter functions
	const FirstType& getFirst() const;
	const SecondType& getSecond() const;
	FirstType getFirst();
	SecondType getSecond();

	// Given: Friend functions, for comparison
	friend bool operator<(const Pair& p1, const Pair& p2) {
		if (p1.m_first < p2.m_first)
			return true;
		else if ( p2.m_first < p1.m_first )
			return false;
		else return ( p1.m_second < p2.m_second );
	}

	friend bool operator==(const Pair& p1, const Pair& p2) {
		return ( p1.m_first == p2.m_first && p1.m_second == p2.m_second );
	}

};




//
// ========================================================================
// Implementation of
//  template<class FirstType, class SecondType>
//  class Pair
// ========================================================================
//
// Note: Inclusive compilation is required for C++ template class
// Thus, the implementation must be appended at the end of the header file
//

#include "Pair.impl"



#endif /* PAIR_H_ */
