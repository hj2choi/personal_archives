#include "stdafx.h"
#include "matrix.h"

using namespace std;


string allignMiddle(string a, int distance)	// " aaa ccc" if distance = 5
{
	string ss = "";
	int space = distance - a.length();
	if (space % 2 != 0)
		ss += " ";
	for (int i = 0; i<space / 2; i++)
		ss += " ";
	ss += a;
	for (int i = 0; i<space / 2; i++)
		ss += " ";
	return ss;
};

double round(double num, int digit)
{
	int power = 1;
	for (int i = 0; i<digit; i++)
		power *= 10;
	return (double)((int)(num*power)) / power;

};

string doubleToString(double a)
{
	string Result;
	ostringstream convert;
	convert << a;
	return convert.str();
};


Matrix::Matrix()
{
	dim_row = 0;
	dim_col = 0;
	data = new double*[dim_row];
	for (int i = 0; i < dim_row; i++)
		data[i] = new double[dim_col];
}

Matrix::~Matrix()
{
	clear();
}

Matrix::Matrix(int row, int col)
{
	dim_row = row;
	dim_col = col;
	data = new double*[dim_row];
	for (int i = 0; i < dim_row; i++)
	{
		data[i] = new double[dim_col];
		for (int j = 0; j < dim_col; j++)
			data[i][j] = 0;
	}
}

Matrix::Matrix(const Matrix& A)
{
	clear();
	dim_row = A.dim_row;
	dim_col = A.dim_col;
	data = new double*[dim_row];
	for (int i = 0; i < dim_row; i++)
	{
		data[i] = new double[dim_col];
		for (int j = 0; j < dim_col; j++)
		{
			data[i][j] = A.data[i][j];
		}
	}
}

Matrix::Matrix(int row, int col, const double mat[][100])
{
	dim_row = row;
	dim_col = col;
	data = new double*[dim_row];
	for (int i = 0; i < dim_row; i++)
	{
		data[i] = new double[dim_col];
		for (int j = 0; j < dim_col; j++)
		{
			data[i][j] = mat[i][j];
		}
	}
	//matrix[dim_row*dim_col];

}


void Matrix::clear()
{
	for (int i = 0; i < dim_row; i++)
	{
		delete[] data[i];
		data[i] = NULL;
	}
//	delete[] data;
	data = NULL;
}

void Matrix::print() const
{
	for (int i = 0; i<dim_row; i++)
	{
		cout << "|";
		for (int j = 0; j<dim_col; j++)
			cout << ("" + allignMiddle(((data[i][j] >= 0) ? " " : "") + doubleToString(round(data[i][j], 2)), 6));
		cout << " |\n";
	}
	cout << endl;
}

Matrix Matrix::operator=(const Matrix& other)
{
	clear();
	dim_row = other.dim_row;
	dim_col = other.dim_col;
	data = new double*[dim_row];
	for (int i = 0; i < dim_row; i++)
	{
		data[i] = new double[dim_col];
		for (int j = 0; j < dim_col; j++)
		{
			data[i][j] = other.data[i][j];
		}
	}
	return *this;
}


Matrix multiplication(const Matrix& A, const Matrix& B)		//AB=C
{
	Matrix C(A.dim_row, B.dim_col);
	for (int i = 0; i<C.dim_row; i++)
	{
		for (int j = 0; j<C.dim_col; j++)
		{
			double indexValue = 0;
			for (int k = 0; k<A.dim_col; k++)
				indexValue += A.data[i][k] * B.data[k][j];
			C.data[i][j] = indexValue;
		}
	}

	return C;
}

Matrix addition(const Matrix& A, const Matrix& B, bool add)// add or subtract
{
	Matrix C(A.dim_row, A.dim_col);
	for (int i = 0; i<A.dim_row; i++)
		for (int j = 0; j<A.dim_col; j++)
			C.data[i][j] = add ? (A.data[i][j] + B.data[i][j]) : (A.data[i][j] - B.data[i][j]);


	return C;
}

Matrix operator+(const Matrix& A, const Matrix& B)
{
	return addition(A,B,true);
}
Matrix operator-(const Matrix& A, const Matrix& B)
{
	return addition(A,B,false);
}
Matrix operator*(const Matrix& A, const Matrix& B)
{
	return multiplication(A,B);
}
const Matrix& Matrix::operator*=(const Matrix& A)
{
	*this = multiplication(*this,A);
	return *this;
}
const Matrix& Matrix::operator+=(const Matrix& A)
{
	*this = addition(*this, A, true);
	return *this;
}
const Matrix& Matrix::operator-=(const Matrix& A)
{
	*this = addition(*this, A, false);
	return *this;
}
ostream& operator<<(ostream& o, const Matrix& A)
{
	for (int i = 0; i<A.dim_row; i++)
	{
		o << "|";
		for (int j = 0; j<A.dim_col; j++)
			o << ("" + allignMiddle(((A.data[i][j] >= 0) ? " " : "") + doubleToString(round(A.data[i][j], 2)), 6));
		o << " |\n";
	}
	o << endl;
}
/*
double Matrix::operator[][](int i, int j)
{
	return
}*/