/*
 * utility.h
 *
 *  Created on: Jan 16, 2015
 *      Author: cspeter
 */

#ifndef UTILITY_H_
#define UTILITY_H_

#include <cstddef> // a header file defining NULL

// Always set back the pointer to NULL after the delete operation
//
// Usage:
//      string *sp = new string("Hello World");
//      SAFE_DEL(sp);
//      assert(sp == NULL);
//
//      int *ip = new int[5];
//      SAFE_DEL_ARRAY (ip);
//      assert(ip == NULL);
//
//

template <class T>
inline void SAFE_DEL(T* & x) { if (x) { delete (x); x = NULL; } } ;

template <class T>
inline void  SAFE_DEL_ARRAY(T* &x)	{ if (x) {delete [] (x); x = NULL;} }



#endif /* UTILITY_H_ */
