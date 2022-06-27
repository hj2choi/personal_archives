/*
 * simpleTree.cpp
 *
 *  Created on: Jan 12, 2015
 *      Author: cspeter
 */

#include "Node.h"
#include "PrettyTree.h" // Given, no need to implement
#include "BTreeExercises.h" // TODO: To be implemented
#include <iostream>
#include <vector>
#include <string>
#include <cstdlib>
#include <cmath>
#include <fstream>
using namespace std;
Node* bTree;
// Load a tree from a text file, implemented below
Node* loadTree(const char* filename);
void print(Node* root, int depth)
{
	if (root==NULL)
		return;
	print(root->right,depth+1);
	for (int i=0; i<depth; i++)
		cout<<"\t";
	cout << root->data<<endl;
	print(root->left,depth+1);
}

void insert(Node* root, int data)
{
	if (root==NULL)
	{
		cout<<"inserted"<<endl;
		root = new Node(data);
		cout<<"new"<<root->data<<endl;
		print(root);
	}
	else if (root->data<data)
	{
		cout<<"right"<<root->data<<endl;
		insert(root->right,data);
	}
	else if (root->data>data)
	{
		cout<<"left"<<root->data<<endl;
		insert(root->left,data);
	}
	else
	{
		cout<<"notInserted"<<endl;
	}
}
int main() {


	cout << "It does something" << endl;
  vector<Node *> trees;
  vector<string> testCaseNames;
  Node *tree;

  // Suggested instructions:
  // Please comment out the case if you haven't finish it

  // Tree 1
  tree = new Node(2);
  tree->left = new Node(1);
  trees.push_back(tree);
  testCaseNames.push_back("Tree 1");

  // Tree 2
  tree = new Node(2);
  tree->left = new Node(1);
  tree->right = new Node(3);
  trees.push_back(tree);
  testCaseNames.push_back("Tree 2");

  // Tree 3
  tree = new Node(2);
  tree->left = new Node(3);
  tree->right = new Node(1);
  trees.push_back(tree);
  testCaseNames.push_back("Tree 3");

  //  Not a binary search tree
  tree = loadTree("tree_not_bst.txt");
  trees.push_back(tree);
  testCaseNames.push_back("Not a BST");

  //  Mirror
  Node *mirrorTree = mirror(tree);
  trees.push_back(mirrorTree);
  testCaseNames.push_back("Mirror");
  //

  print(tree);
  for (int i=0; i<trees.size(); i++) {
	  cout << "== " << testCaseNames[i] << " ==" << endl;
	  printPretty(trees[i], 1, 0, cout);
	  cout << endl ;
	  cout << "Nodes: " << countNodes(trees[i]) << endl ;
	  cout << "Height: " << treeHeight(trees[i]) << endl;
	  cout << "Pre-order: " ; printPreOrder(trees[i]); cout << endl;
	  cout << "In-order: " ; printInOrder(trees[i]); cout << endl;
	  cout << "Post-order: " ; printPostOrder(trees[i]); cout << endl;
	  cout << "Is Complete: " << (isComplete(trees[i]) ? "Yes" : "No") << endl;
	  cout << "max value: " << maxValue(trees[i]) << endl;
	  cout << "Is BST: " << (isBST(trees[i]) ? "Yes" : "No") << endl;
	  cout << endl;

	  cleanUp(trees[i]);
  }

  trees.clear();


  bTree = new Node(2);
  cout<<bTree->data<<endl;
  print(bTree);
  insert(bTree,3);
  insert(bTree,5);
  insert(bTree,1);
  insert(bTree,4);
  insert(bTree,2);
  cout<<endl;
  //, 1, 0, cout);
  return 0;
}


Node* loadTree(const char* filename) {
        ifstream fin(filename);
        int depth = 0;
        fin >> depth; // read the depth

        if ( depth <= 0 )
                return NULL;

        // Create a 2D dynamic array
        vector< vector<int>  > data(depth);
        vector< vector<Node* > > pointers(depth);

        int value, numInLevel;
        for (int i=0; i<depth; i++) {
                numInLevel = 1 << i;
                for (int j=0; j<numInLevel; j++) {
                        fin >> value;
                        data[i].push_back(value);

                        if ( value == 0 ) {
                                pointers[i].push_back(NULL);
                        } else {
                                Node *newNode = new Node;
                                newNode->data = value;
                                newNode->left = NULL;
                                newNode->right = NULL;
                                pointers[i].push_back(newNode);
                        }

                }
        }
        // Build up linkage
        for (int i=0; i<depth-1; i++) {
                numInLevel = 1 << i;
                for (int j=0; j<numInLevel; j++) {
                        if ( pointers[i][j]!=NULL ) {
                                pointers[i][j]->left = pointers[i+1][j*2];
                                pointers[i][j]->right = pointers[i+1][j*2+1];
                        }
                }
        }
        // Return the root node
        return pointers[0][0];
        fin.close();
}
