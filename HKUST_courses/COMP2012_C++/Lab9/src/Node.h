/*
 * Node.h
 *
 *  Created on: Jan 12, 2015
 *      Author: cspeter
 */

#ifndef NODE_H_
#define NODE_H_


struct Node {
  Node *left, *right;
  int data;
  Node(int val = 0) : left(0), right(0), data(val) { }
};


#endif /* NODE_H_ */
