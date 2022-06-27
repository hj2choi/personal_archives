#ifndef MATRIX_H_
#define MATRIX_H_

#include <iostream>
#include <cmath>
#include <sstream>

using namespace std;

//const static int MAX_DIM = 1000;


/**
	Matrix.h
	Author:	Hong Joon CHOI
	Last modified: 16/06/2015
	Matrix class, with



	Operator =, +, -, *, /, [], << are overloaded
	Constructor accepts regular expression in string
	Not comparable
*/

class Matrix
{

public :
	class Proxy{	// for overloading the operator [][].
	public:
		Proxy(double* arr){ proxy = arr; }
		double operator[](int i)const{ return proxy[i]; }	//const
		double& operator[](int i){ return proxy[i]; }	// non-const
	private:
		double* proxy;
	};

	//Constructors, Destructors
	Matrix(int, int);
	Matrix(int, int, double**);
	Matrix(const Matrix& mat);
	Matrix(int, int, const double[]);
	Matrix();
	~Matrix();
	void clear();

	// Accesors
	int get_row()const{ return dim_row; }
	int get_col()const{ return dim_col; }

	Proxy operator[](int i) const;


	// Overloaded operators
	const Matrix& operator=(const Matrix&);


	// Operations

	void print() const;
	void ERO_switch(int,int);
	void ERO_multiplication(int, double);
	void ERO_addition(int, int, double);
	Matrix transpose() const;
	Matrix rref() const;
	Matrix basis() const;
private :
	int dim_row;
	int dim_col;
	double** matrix;
};

Matrix multiplication(const Matrix&, const Matrix&);
Matrix addition(const Matrix&, const Matrix&, bool);
Matrix augment(const Matrix&, const Matrix&);
//static Matrix identity(int);
double vectorDistance(const Matrix&, const Matrix&);




#endif
