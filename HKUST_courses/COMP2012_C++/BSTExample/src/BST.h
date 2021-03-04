/*
 * BST.h
 *
 *  Created on: May 25, 2015
 *      Author: HongJoon
 */

#ifndef BST_H_
#define BST_H_

template <typename T>
class BST
{
private:
	static const T DUMMY;	// returned value of an empty BST
	struct bst_node
	{
		T value;
		T* left;
		T* right;
		bst_node(const T& x):value(x),left(),right(){};

	};

	bst_node* root;

public:
	BST():root(0){}
	BST(const BST& bst){root=bst.root;}
	~BST(){delete root;}

	bool is_empty() const {return root == 0;}
	bool contains(const T& x) const;
	void print(int depth =0) const;

	const T& find_max() const;	// find maximum value
	const T& find_min() const;
	void insert(const T&);
	void remove(const T&);

	void print(int depth =0) const;
	void insert(const T&);
};
template <typename T>
void BST<T>::bst_node::print(int depth) const
{
	if (root==0)
		return;
	root->right.print(depth+1);

	for (int i=0; i<depth; i++)
	{
		std::cout <<"\t";
	}
	std::cout<<root->value<<std::endl;

	root->left.print(depth+1);
}
template <typename T>
void BST<T>::print(int depth) const
{
	root->print(depth);
}

template <typename T>
void BST<T>::insert(const T& data)
{
	if (is_empty())
	{
		root = new bst_node(data);
	}
	else if (data->value<root->value)
	{
		root->left.insert(data);
	}
	else if (data->value>root->value)
	{
		root->right.insert(data);
	}
}

#endif /* BST_H_ */
