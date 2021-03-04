/*
 * PrettyTree.h
 *
 *  Created on: Jan 12, 2015
 *      Author: cspeter
 */

#ifndef PRETTYTREE_H_
#define PRETTYTREE_H_

#include "Node.h"
#include <iostream>

void printPretty(Node *root,
		int level,
		int indentSpace, std::ostream& out);



#endif /* PRETTYTREE_H_ */
