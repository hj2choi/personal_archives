/*
 * BTreeExercises.cpp
 *
 *  Created on: May 7, 2015
 *      Author: HongJoon
 */
#include "BTreeExercises.h"

#include <iostream>

using namespace std;



void cleanUp(Node* root)
{
	if (root!=0)
	{
		cleanUp(root->left);
		cleanUp(root->right);
		delete root;
	}

}

int treeHeight(Node* root)
{

	if (!root) return 0;
	int leftHeight = treeHeight(root->left);
	int rightHeight = treeHeight(root->right);
	return (leftHeight > rightHeight) ? leftHeight + 1: rightHeight + 1;

}

int countNodes(Node* root)
{
	int count=0;
	if (root!=0)
	{
		count += countNodes(root->left);
		count += countNodes(root->right);
		count++;
	}
	return count;
}

void printInOrder(Node* root)
{
	if (root!=0)
	{
		printInOrder(root->left);
		cout << root->data << " ";
		printInOrder(root->right);
	}

}

void printPostOrder(Node* root)
{
	if (root!=0)
	{
		printInOrder(root->left);
		printInOrder(root->right);
		cout << root->data << " ";
	}

}

void printPreOrder(Node* root)
{
	if (root!=0)
	{
		cout << root->data << " ";
		printInOrder(root->left);
		printInOrder(root->right);
	}

}

bool isComplete(Node* root)
{
	bool complete=false;
	if (root!=0)
	{
		if (isComplete(root->left))
		complete = isComplete(root->left) && isComplete(root->right);
		/*if (root->left!=0)
		{
			complete=true;
		}//BFS
		else
		{
			complete=false;
		}
		complete = isComplete(root->left);*/
	}
	else
		return true;
	return complete;
}

int maxValue(Node* root)
{
	if (root!=NULL)
	{
		if (root->data > maxValue(root->left) && root->data > maxValue(root->right))
		{
			return root->data;
		}
		else
		{
			return (maxValue(root->left)>maxValue(root->right))?maxValue(root->left):maxValue(root->right);
		}
	}
	return 0;
}

bool isBST(Node* root)
{
	if (root!=NULL)
	{
		//if (root->data>maxValue(root->left)) && (root->data<maxValue(root->right))
		//		return true;
		if (maxValue(root->left)>root->data)
			return false;
		if (maxValue(root->right)<root->data)
			return false;

		return isBST(root->left) && isBST(root->right);
	}
	return true;
}

Node *mirror(Node* root)
{
	/*
	if (root->left!=0)
	{
		root = mirror(root->left);

	}
	if (root->right!=0)
	{
		root = mirror(root->right);
	}*/
	Node* temp=new Node;
	if (root!=0)
	{
		temp->data = root->data;
		temp->left = mirror(root->right);
		temp->right = mirror(root->left);
		return temp;
	}
	else
		return root;
}
