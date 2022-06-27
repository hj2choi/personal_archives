/*
 * BTreeExercises.h
 *
 *  Created on: Jan 12, 2015
 *      Author: cspeter
 */

#ifndef BTREE_EXERCISES_H
#define BTREE_EXERCISES_H

#include "Node.h"


// Basic:
void cleanUp(Node *root);
int treeHeight(Node *root);
int countNodes(Node *root);
void printInOrder(Node *root);
void printPostOrder(Node *root);
void printPreOrder(Node *root);
void print(Node *root, int depth=0);
void insert(Node *root, int data);

// Medium to Advance
bool isComplete(Node *root);
bool isBST(Node *root);
Node *mirror(Node *root);

int maxValue(Node* root);








#endif /* BTREE_EXERCISES_H */
