#include "stdafx.h"
#include <iostream>
#include <string>
#include <cmath>
#include <sstream>

#ifndef _MATRIX_H
#define _MATRIX_H


class Matrix
{
	friend Matrix multiplication(const Matrix& A, const Matrix& B);
	friend Matrix addition(const Matrix& A, const Matrix& B, bool add=true);
	friend Matrix operator+(const Matrix& A, const Matrix& B);
	friend Matrix operator-(const Matrix& A, const Matrix& B);
	friend Matrix operator*(const Matrix& A, const Matrix& B);
	friend ostream& operator<<(ostream& o, const Matrix& a);
public :
	//Matrix(int, int, const Type[]);
	Matrix(int, int);
	Matrix(int, int, const double[][100]);
	Matrix(const Matrix& A);
	Matrix();
	~Matrix();

	Matrix operator=(const Matrix& other);
	const Matrix& operator+=(const Matrix& A);
	const Matrix& operator-=(const Matrix& A);
	const Matrix& operator*=(const Matrix& A);
	//double operator[](int i, int j) const;

	void print() const;
	//void ERO_switch(int, int);
	//void ERO_multiplication(int, double);
	//void ERO_addition(int, int, double);
	Matrix transpose() const;
	//Matrix rref();
	//Matrix basis();
private :
	int dim_row;
	int dim_col;
	double** data;

	void clear();

	
};

//Matrix multiplication(const Matrix& A, const Matrix& B);
//Matrix addition(const Matrix& A, const Matrix& B, bool add = true);


class Vector : public Matrix
{
public:
	Vector() :Matrix(){};
	Vector(int dim) :Matrix(dim,1){};
	Vector(int dim, const double a[][100]) :Matrix(dim, 1, a){};
	Vector(const Matrix& a) :Matrix(a){};
	~Vector(){};
};





#endif