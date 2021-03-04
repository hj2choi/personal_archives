//============================================================================
// Name        : Test.cpp
// Author      : CHOI, Hong Joon
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================


#include <iostream>

using namespace std;


void print_xy(int x, int y){cout << x << ", " << y << endl;}

class B
{
private:
	int x, y;
public :
	B(int p=1, int q=2): x(p), y(q)
	{
		cout << "B's constructor"; ::print_xy(x, y);

	}
	void f() const
	{
		cout << "Base class : "; ::print_xy(x,y);
	}

};

class D : public B
{
private:
	float x, y;
public:
	D() : B(), x(10.0), y(20.0) {cout << "D's constructor\n";}
	void f() const
	{
		cout << "Derived class: "; print_xy(x,y); B::f();
	}
};

void smart(const B* z)
{
	cout << "Inside smart(): ";
	z->f();
}

int main()
{
	B base(5,6); cout << endl;
	D derive; cout << "done" << endl;

	B* b = &base;
	D* d = &derive;

	b->f();
	d->f();

	b = &derive;
	b->f(); cout << endl;

	smart(b);cout << endl;
	smart(d);cout << endl;
	return 0;
}
