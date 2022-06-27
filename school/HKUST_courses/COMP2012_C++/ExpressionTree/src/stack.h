/****************************************/
//  QT Paint
//  File: stack.h
//  Author: Hong Joon Choi
//  Date: 25.Mar.2015
//  Modified Data: 25.May.2015
//	Modification: Now items are not dynamic anymore.
//					top() and pop() is unsafe
//					Now handles pointers
//  Description: the header file
//  -- Stack class declaration + definition. Stack implemented by array.
//     template implementation of stack data structure for multiple-level undo and redo
//
//   NOTE: classes should define operator=
//
//
/****************************************/

#include <iostream>

#ifndef _STACK_H
#define _STACK_H



template <class Type>
class Stack
{
public:
   /** constructor and destructor */
   Stack()  // 1000 level for default
   {
      max_size=1000;
      items = new Type[max_size];
      top_index = -1;
   }
   Stack(int max)
   {
      max_size=max;
      items = new Type[max_size];
      top_index = -1;

   }
   ~Stack()
   {
	  for (int i=0; i<= top_index; i++)
	       delete items[i];
      delete[] items;
   }

   /** reset all data members */
   void reset()
   {
	  for (int i=0; i<= top_index; i++)
	        delete items[i];
	  delete[] items;
	  items = new Type[max_size];
	  top_index = -1;
   }


   /** push, pop, isEmpty, isFull */
   void push(const Type& item)    // add one object item to stack. IF STACK IS FULL, ITEM AT THE BOTTOM IS DELETED BEFORE NEW ONE GETS ADDED.
   {

      if (!isFull())
         items[++top_index] = item;
      else
      {
         for (int i=0; i < top_index; i++)
            items[i] = items[i+1];
         items[top_index] = item;
      }
      //std::cout << " one item added to stack!" << std::endl;
   }

   Type pop()    // returns pointer of top object from the stack.
   {
      //std::cout << " stack.pop() called. Returned item as pointer" << std::endl;
	   return items[top_index--];
   }

   Type& top()
   {
	   return items[top_index];
   }

   bool isEmpty() const{return top_index == -1;}

   bool isFull() const{return top_index == max_size - 1;}

   int size() const{ return top_index+1;}

private:
   Type* items;
   int top_index;
   int max_size;

};


#endif
