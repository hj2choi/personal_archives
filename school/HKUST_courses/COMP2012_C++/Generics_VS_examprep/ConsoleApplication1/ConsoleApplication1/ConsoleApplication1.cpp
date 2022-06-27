// ConsoleApplication1.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>
#include "matrix.h"

using namespace std;

template < typename Type >
class Basic{
public:
	Basic() :value(0){};
	Basic(Type x) :value(x){};
	~Basic(){};

	//template <typename Type>
	void print(){ cout << value << " "; }
	Type value;

};


template <class Object, int max_size>
class Stack
{
public:
	Stack()
	{
		items = new Object*[max_size];
		top_index = -1;
	}
	~Stack()
	{
		for (int i = 0; i < top_index; i++)
			delete items[i];
		delete[] items;
	}

	void reset()
	{
		for (int i = 0; i < top_index; i++)
		{
			delete items[i];
			items[i] = NULL;
		}
		top_index = -1;
	}

	void push(Object* item)
	{
		if (!full())
			items[++top_index] = item;
		else
		{
			delete items[0];
			for (int i = 0; i < top_index; i++)
				items[i] = items[i + 1];
			items[top_index] = item;
		}
	}

	Object* pop()
	{
		if (!empty())
			return items[top_index--];
		else
			return NULL;
	}
	
	bool empty() const
	{
		return top_index == -1;
	}

	bool full() const
	{
		return top_index == max_size - 1;
	}

	void print() const
	{
		for (int i = 0; i <= top_index; i++)
			items[i]->print();
		cout << endl;
	}

private:
	Object** items;
	int top_index;
};





template <typename Type, typename Doub>
Type my_max(const Type& a, const Doub& b)
{
	return (a > b) ? a : b;

}



template<typename Type>
class Vector2
{
public:
	Vector2(Type a = 0, Type b = 0) : x(a), y(b){};
	Type getX() const {return x;}
	Type getY() const { return y; }
private:
	Type x;
	Type y;
};
/*
template<typename Type>
Vector2<Type> operator+(const Vector2<Type>& a, const Vector2<Type>& b)
{
	return Vector2<Type>(a.getX()+b.getX(), a.getY()+b.getY());

}
*/
Vector2<int> operator+(const Vector2<int>& a, const Vector2<int>& b)
{
	return Vector2<int>(a.getX() + b.getX(), a.getY() + b.getY());

}
/*
int main()
{
	Vector2<int> a(1, 3), b(-5, 7), c(22), d;
	d = a+b+c; // d = a + b + c
	cout << d.getX() << ", " << d.getY() << endl;
	d = (8 +a);
	cout << d.getX() << ", " << d.getY() << endl;
	d = a + 5;
	cout << d.getX() << ", " << d.getY() << endl;

	int z;
	cin >> z;
}
*/
#include <iostream>
#include <cstring>
class Word
{
private:
	int frequency; string str;
	void set(int f, string s)
	{
		frequency = f; str = s;
	}
public:
	Word(string s, int k = 1)
	{
		set(k, s); std::cout << "conversion\n";
	}
	Word(const Word& w)
	{
		set(w.frequency, w.str); std::cout << "copy\n";
	}
};



template <typename Type>
struct Node
{
	Type value;
	Node *next;
};

template <typename Type>
class LinkedQueue
{
public:
	LinkedQueue(){ head = NULL; }
	~LinkedQueue()
	{
		Node<Type>* p;
		while(head!=NULL)
		{
			p = head;
			head = head->next;
			cout << p->value << "deleted!" << endl;
			delete p;

		}
	}
	void print() const
	{
		cout << "{";
		for (Node<Type>* p = head; p != NULL; p = p->next)
		{
			cout << p->value << ", ";
		}
		cout << "}" << endl;
	}

	void deleteItem(const Type& item)
	{
		Node<Type>* prev = 0;
		Node<Type>* p = head;
		while (p != NULL && p->value != item)
		{
			prev = p;
			p = p->next;
		}
		if (p != NULL)
		{
			if (p == head)
				head = head->next;
			else
				prev->next = p->next;
			delete p;
		}
	}

	void enque(const Type& value)
	{
		Node<Type>* newNode = new Node<Type>();
		newNode->value = value;
		Node<Type>* p=head;
		if (head == NULL)
			head = newNode;
		else
		{
			while (p->next != NULL)
				p = p->next;
			p->next = newNode;
		}
		
	}

	Type* deque()
	{
		if (head != NULL)
		{
			Type* retVal = new Type(head->value);
			Node<Type>* tempHead = head;
			head = head->next;
			delete tempHead;
			return retVal;
		}
		return NULL;
	}

	int size() const
	{
		int size = 0;
		for (Node<Type>* p = head; p != NULL; p = p->next)
		{
			size++;
		}
		return size;
	}

private:
	Node<Type>* head;

};



int main()
{
	const double dat[][100] = { {1,2,3}, {4,5,6} };
	const double dat2[][100] = { { 1, 2 }, { 3, 4 }, { 5, 6 } };

	Matrix a(2,3,dat);
	a.print();
	Matrix b(3, 2, dat2);
	b.print();

	Matrix c(3,2,dat2);
	c.print();
	c = a+a;
	c.print();
	c = a*b;
	c.print();

	Vector s(3, dat2);
	s.print();
	s = s + s;
	s.print();

	Matrix d(1,3,dat);
	d.print();
	d = d*s;
	d.print();

	Vector e(c);
	e.print();

	//multiplication(b,b).print();
	/*
	LinkedQueue<int>* list;
	list = new LinkedQueue<int>();
	list->enque(3);
	list->enque(4);
	list->enque(5);
	list->print();
	list->enque(4);
	list->print();
	list->enque(5);
	list->print();
	list->enque(3);
	list->print();
	cout << list->size() << endl;
	int *a = list->deque();
	cout << *a << endl;
	list->deque();
	list->deque();
	list->deque();
	list->deque();
	cout << list->size() << endl;
	list->print();
	list->deque();
	list->print();
	list->deque();
	list->print();
	cout << list->size() << endl;*/
	int z;
	cin >> z;
}