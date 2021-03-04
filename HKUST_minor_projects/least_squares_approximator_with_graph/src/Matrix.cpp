#include "stdafx.h"
#include "Matrix.h"


static double f(double x, char type)	//define your basic function here
{
	switch (type)
	{
	case 'x':
		return x;
	case 'c':
		return cos(x);
	case 's':
		return sin(x);
	case 'l':
		return log(x);
	case 'a':
		return abs(x);
	case 'e':
		return exp(x);
	default:
		return x;
		// exponential
	}
}

string f(char type)	//define how your basic function's called here
{
	switch (type)
	{
	case 'x':
		return "x";
	case 'c':
		return "cos(x)";
	case 's':
		return "sin(x)";
	case 'l':
		return "log(x)";
	case 'a':
		return "|x|";
	case 'e':
		return "e^x";
	default:
		return "x";
		// exponential
	}
}

double polynomial(double x, double degree, char functionType)	// power of polynomial
{/*
 double y=1;
 for (int i=0; i>degree; i--)
 y/=f(x, function);
 for (int i=0; i<degree; i++)
 y*=f(x, function);*/
	try
	{
		//cout << " " << degree << " = " << pow(f(x, functionType), degree)<<endl;
	}
	catch (exception e)
	{
		cout << ("exception in calculating polynomial") << endl;
	}
	if (isinf(pow(f(x, functionType), degree)))
		return 0;
	return pow(f(x, functionType), degree);
}


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
}

double round(double num, int digit)
{
	int power = 1;
	for (int i = 0; i<digit; i++)
		power *= 10;
	return (double)((int)(num*power)) / power;

}

string doubleToString(double a)
{
	string Result;
	ostringstream convert;
	convert << a;
	return convert.str();
}


Matrix::Matrix()
{
	dim_row = 0;
	dim_col = 0;
	matrix = NULL;
}

Matrix::Matrix(int dr, int dc, double** mat)
{
	dim_row = dr;
	dim_col = dc;
	matrix = new double*[dr];
	for (int i = 0; i < dim_row; i++)
	{
		matrix[i] = new double[dc];
	}

	for (int i = 0; i < dim_row; i++)
		for (int j = 0; j < dim_col; j++)
			matrix[i][j] = mat[i][j];

} 



Matrix::Matrix(int dr, int dc, const double mat[])
{
	dim_row = dr;
	dim_col = dc;
	matrix = new double*[dr];
	for (int i = 0; i < dim_row; i++)
	{
		matrix[i] = new double[dc];
	}
	//matrix[dim_row*dim_col];
	for (int i = 0; i < dim_row*dim_col; i++)
	{
		matrix[i/dim_col][i%dim_col] = mat[i];
	}

}

Matrix::Matrix(int dr, int dc)
{
	dim_row = dr;
	dim_col = dc;
	matrix = new double*[dim_row];
	for (int i = 0; i < dim_row; i++)
	{
		matrix[i] = new double[dim_col];
	}
	for (int i = 0; i < dim_row; i++)
		for (int j = 0; j < dim_col; j++)
			matrix[i][j] = 0;
}

Matrix::Matrix(const Matrix& mat)
{
	dim_row = mat.dim_row;
	dim_col = mat.dim_col;
	matrix = new double*[dim_row];
	for (int i = 0; i < dim_row; i++)
	{
		matrix[i] = new double[dim_col];
	}

	for (int i = 0; i < dim_row; i++)
		for (int j = 0; j < dim_col; j++)
			matrix[i][j] = mat.matrix[i][j];
}

const Matrix& Matrix::operator=(const Matrix& mat)
{
	clear();
	dim_row = mat.dim_row;
	dim_col = mat.dim_col;
	matrix = new double*[mat.dim_row];
	for (int i = 0; i < dim_row; i++)
	{
		matrix[i] = new double[dim_col];
	}

	for (int i = 0; i < dim_row; i++)
		for (int j = 0; j < dim_col; j++)
			matrix[i][j] = mat.matrix[i][j];

	return *this;
}

Matrix::Proxy Matrix::operator[](int i) const
{
	return Matrix::Proxy(matrix[i]);
}


Matrix::~Matrix()
{
	clear();
}

void Matrix::clear()
{
	if (matrix)
	{
		//cout << "clear()" << endl;
		for (int i = 0; i < dim_row; ++i)
		{
			delete[] matrix[i];
		}
		delete[] matrix;
		matrix = NULL;
	}
	dim_col = 0;
	dim_row = 0;
}


void Matrix::print() const
{
	for (int i = 0; i<dim_row; i++)
	{
		cout << "|";
		for (int j = 0; j<dim_col; j++)
			cout << ("" + allignMiddle((((*this)[i][j] >= 0) ? " " : "") + doubleToString(round((*this)[i][j], 2)), 6));
		cout << " |\n";
	}
	cout << endl;
}

Matrix Matrix::transpose() const
{
	Matrix C(dim_col, dim_row);
	for (int i = 0; i<dim_col; i++)
		for (int j = 0; j<dim_row; j++)
			C.matrix[i][j] = matrix[j][i];
	return C;
}

void Matrix::ERO_switch(int a, int b)//swap row a and b
{
	//cout << "ERO_switch" << endl;
	a--; b--;
	Matrix elementaryMatrix(dim_row,dim_row);
	for (int i = 0; i < elementaryMatrix.dim_row; i++)
		for (int j = 0; j < elementaryMatrix.dim_col; j++)
			elementaryMatrix.matrix[i][j] = 0;
	for (int i = 0; i<dim_row; i++)
	{
		if (i == a)
			elementaryMatrix.matrix[i][b] = 1;
		else if (i == b)
			elementaryMatrix.matrix[i][a] = 1;
		else
			elementaryMatrix.matrix[i][i] = 1;
	}
	*this=multiplication(elementaryMatrix, *this);
}

void Matrix::ERO_multiplication(int a, double k)//multiply (a)th row by factor (k)
{
	//cout << "ERO_multiplication" << endl;
	a--;
	Matrix elementaryMatrix(dim_row, dim_row);
	for (int i = 0; i < elementaryMatrix.dim_row; i++)
		for (int j = 0; j < elementaryMatrix.dim_col; j++)
			elementaryMatrix.matrix[i] [j] = 0;
	for (int i = 0; i<dim_row; i++)
	{
		if (i == a)
			elementaryMatrix.matrix[i][i] = k;
		else
			elementaryMatrix.matrix[i][i] = 1;
	}
	*this = multiplication(elementaryMatrix, *this);
}

void Matrix::ERO_addition(int a, int b, double k)//add to (a)th row with (b)th row, multiplied by factor (k).
{
	//cout << "ERO_addition" << endl;
	a--; b--;
	Matrix elementaryMatrix(dim_row, dim_row);
	for (int i = 0; i < elementaryMatrix.dim_row; i++)
		for (int j = 0; j < elementaryMatrix.dim_col; j++)
			elementaryMatrix.matrix[i][j] = 0;
	for (int i = 0; i<dim_row; i++)
	{
		if (i == a)
			elementaryMatrix.matrix[i][b] = k;
		elementaryMatrix.matrix[i][i] = 1;
	}
	*this = multiplication(elementaryMatrix, *this);
}

Matrix Matrix::rref(/*boolean ref*/) const//if ref==true, returns reduced echelon form
{
	// Based on Gauss-Jordan algorithm
	bool ref = false;
	Matrix rref(*this);// = new Matrix(this.dim_row, this.dim_col, this.matrix);
	
	int piv_y = 0;	// pivot
	int piv_x = 0;
	while (piv_y<dim_row && piv_x<dim_col)
	{
		//step 1
		if (rref.matrix[piv_y][piv_x] == 0)	// if pivot is zero, swich with other row. If whole row is zero, move onto next column.
		{
			bool zeroFlag = true;
			for (int i = piv_y; i<rref.get_row(); i++)
				if (rref.matrix[i][piv_x] != 0)
				{
					rref.ERO_switch(piv_y + 1, i + 1);
					zeroFlag = false;
					break;
				}
			if (zeroFlag)
				piv_x++;
			continue;
		}
		//step 2 to 4
		
		else
		{
			rref.ERO_multiplication(piv_y + 1, 1 / rref.matrix[piv_y][ piv_x]);	// multiply row to make matrix[piv_y][piv_x]=1;
			for (int i = piv_y + 1; i<dim_row; i++)// perform row operation to eliminate whole column (piv_x)
				rref.ERO_addition(i + 1, piv_y + 1, -rref.matrix[i][piv_x]);
		}
		piv_y++;
		piv_x++;
	}

	if (ref)
		return rref;
	//cout << "rref() -> ref form" << endl;
	//rref.print();
	piv_y = 0; piv_x = 0;
	while (piv_y<dim_row && piv_x<dim_col)
	{
		if (rref.matrix[piv_y][piv_x] == 0)
		{
			piv_x++;
			continue;
		}
		else
			for (int i = piv_y - 1; i >= 0; i--)
				rref.ERO_addition(i + 1, piv_y + 1, -rref.matrix[i][piv_x]);

		piv_y++; piv_x++;
	}

	
	return rref;
}


Matrix Matrix::basis() const
{
	Matrix C = this->rref();
	double** b = new double*[dim_row];
	for (int i = 0; i < dim_row; i++)
		b[i] = new double[dim_col];
	int* remainingColIndex = new int[dim_col]; //= new int[dim_col];
	int b_col = 0;
	int piv_y = 0; int piv_x = 0;
	while (piv_y<dim_row && piv_x<dim_col)
	{
		if (C.matrix[piv_y][piv_x] == 0)
		{
			piv_x++;
			continue;
		}
		else
		{
			remainingColIndex[b_col] = piv_x;
			for (int i = 0; i<dim_row; i++)
				b[i][b_col] = matrix[i][piv_x];
			b_col++;
		}
		piv_y++; piv_x++;
	}
	for (int i = 0; i<b_col; i++)
	{
		cout << (remainingColIndex[i] + " ");
	}
	cout << endl;

	// below is c++ version specific fix for 1d array problem
	/*double b_1d[MAX_DIM];
	for (int i = 0; i < dim_row; i++)
	{
		for (int j = 0; j < b_col; j++)
		{
			b_1d[i + j*dim_row] = b[_2dIndex(i, j)];
			//cout << b[_2dIndex(i, j)] << " ";
			cout << (i + j*dim_row) << ":" << b_1d[i + j*dim_row] << " ";
		}
			
	}
	cout << endl;*/
	Matrix retMat(dim_row, b_col, b);
	for (int i = 0; i < dim_row; ++i)
	{
		delete[] b[i];
	}
	delete[] b;
	return retMat;
}


Matrix augment(const Matrix& A, const Matrix& B)	// B matrix must be row vector
{
	Matrix C(A.get_row(), A.get_col() + B.get_col());
	for (int i = 0; i<A.get_row(); i++)
		for (int j = 0; j<A.get_col(); j++)
			C[i][j] = A[i][j];
	for (int i = 0; i<C.get_row(); i++)
		for (int j = A.get_col(); j<C.get_col(); j++)
			C[i][j] = B[i][j - A.get_col()];

	return C;
}

Matrix multiplication(const Matrix& A, const Matrix& B)		//AB=C
{
	Matrix C(A.get_row(), B.get_col());
	
	for (int i = 0; i<C.get_row(); i++)
	{
		for (int j = 0; j<C.get_col(); j++)
		{
			double indexValue = 0;
			for (int k = 0; k<A.get_col(); k++)
				indexValue += A[i][k] * B[k][j];
			C[i][j] = indexValue;
		}
	}
	return C;
}

Matrix addition(const Matrix& A, const Matrix& B, bool add)// add or subtract
{
	Matrix C(A.get_row(), A.get_col());
	for (int i = 0; i<A.get_row(); i++)
		for (int j = 0; j<A.get_col(); j++)
			C[i][j] = add ? (A[i][j] + B[i][j]) : (A[i][j] - B[i][j]);


	return C;
}


Matrix leastSquareSolution(const Matrix& A, const Matrix& B)//B must be a vector
{
	Matrix Augmented = augment(multiplication(A.transpose(), A), multiplication(A.transpose(), B));
	//Augmented.print();
	Matrix C = Augmented.rref();
	cout << "rref form of augmented matrix Ax* = b* (b0 = projection of target vector onto polynomial subspace)" << endl;
	C.print();
	Matrix solutionSet(C.get_row(), 1);
	for (int i = 0; i<C.get_row(); i++)
	{
		solutionSet[i][0] = C[i][C.get_col() - 1];
	}
	return solutionSet;
}

double vectorDistance(const Matrix& A, const Matrix& B)
{
	Matrix C = addition(A, B, false);
	double distance = 0;
	for (int i = 0; i<C.get_row(); i++)	//inner product
		distance += C[i][0] * C[i][0];
	return sqrt(distance);
}

double round_1(double val)
{
	return ((double)((int)(val * 10))) / 10;

}

//MAGIC, DO NOT TOUCH
//only god knows what is happening here
//I knew what I was doing before, something permutation or similar..
Matrix leastSquareApproximation(char functionSet[], int functionSetCount, double** dataSet, int dataSetCount, int MIN_DEGREE, int MAX_DEGREE, double degreeDifference)// functionSet: collection of functions // dataset: col1=x col2=y
{
	int dimensionMultiple=(int)(1/degreeDifference);
	int dimension=(MAX_DEGREE-MIN_DEGREE)*dimensionMultiple;
	int subspaceDimension=1;
	
	int functionSetSize = functionSetCount;//sizeof(functionSet) / sizeof(*functionSet) / 4;
	cout << functionSetSize << endl;
	int dataSetSize = dataSetCount;// / sizeof(*dataSet);
	cout << dataSetSize << endl;
	for (int i=0; i<functionSetSize; i++)
		subspaceDimension*=(MAX_DEGREE-MIN_DEGREE)*dimensionMultiple+1;
		
	cout << subspaceDimension << endl;
	
	double** functionSetDegree = new double*[subspaceDimension];//[subspaceDimension][functionSetSize];	// row = ith coeficient : and columns = degree of each functionSet
	for (int i = 0; i < subspaceDimension; i++)
	{
		functionSetDegree[i] = new double[functionSetSize];
	}
	
	double** a = new double*[dataSetSize];// [subspaceDimension];//[dataSet.length][subspaceDimension];
	for (int i = 0; i < dataSetSize; i++)
	{
		a[i] = new double[subspaceDimension];
	}
	for (int i = 0; i<dataSetSize; i++)
		for (int j=0; j<subspaceDimension; j++)
		{
			a[i][j]=1;
			//cout << j << " => ";
			for (int k=0; k<functionSetSize; k++)		// all possible combinations of polynomials in multiplication
			{
				double degree=(((j/((int)pow(dimension+1,k)))%(dimension+1))*degreeDifference+MIN_DEGREE);
				//cout << "*" << functionSet[k] << "^" << degree;
				a[i][j]*=polynomial(dataSet[i][0], degree, functionSet[k]);
				functionSetDegree[j][k] = degree;
			}
			//cout << endl;
		}
	double* y = new double[dataSetSize];
	for (int i = 0; i<dataSetSize; i++)
		y[i]=dataSet[i][1];
	Matrix A(dataSetSize,subspaceDimension,a);	//subspace
	cout << "vector subspace of dimension R" << subspaceDimension << endl;
	//A.print();
	cout << "rref form for subspace matrix\n";
	//A.rref().print();
	//A=A.basis();
	//System.out.println("basis for polynomial subspace\n"+A);
	Matrix Y(dataSetSize,1,y);	//target vector
	cout << "target vector\n" << endl;
	//Y.print();

	Matrix C = leastSquareSolution(A,Y);		//solution for projection of vector onto subspace
		
		
	cout << "Projection of target vector onto subspace, b* = " << endl;
	//multiplication(A,C).print();
	cout << "coordinate vector x* for projection b* onto subspace = " << endl;
	double distance=vectorDistance(multiplication(A,C), Y);
	C.print();
	cout << "distance from target vector to subspace = " << distance << "^2" << endl;		//distance from target solution vector to subspace
		
	cout << "best fit equation for data set : " << endl;
	for (int i=0; i<subspaceDimension; i++)
	{
		if (C[i][0]!=0)
		{
			cout << round(C[i][0],2);
			for (int j=0; j<functionSetSize; j++)
			{
				if (functionSetDegree[i][j] != 0)
				{
					cout << f(functionSet[j]);
					if (functionSetDegree[i][j] != 1)
					{
						cout << "^" << round_1(functionSetDegree[i][j]);
					}
				}
			}
			cout << " + ";
		}
	}
	cout << endl;
	//return distance;
	// delete all dynamic arrays
	for (int i = 0; i < subspaceDimension; ++i)
	{
		delete[] functionSetDegree[i];
	}
	delete[] functionSetDegree;
	for (int i = 0; i < dataSetSize; ++i)
	{
		delete[] a[i];
	}
	delete[] a;
	delete[] y;
	cout << "done" << endl;
	return C;
}

// Also magic, DO NOT TOUCH
// I forgot all of the linear algebra stuff
double approximatedFunction(const Matrix& ans, double x, char functionSet[], int functionSetSize, int MIN_DEGREE, int MAX_DEGREE, double degreeDifference)
{
	double y = 1;
	double dimensionMultiple = (1 / degreeDifference);
	double dimension = (MAX_DEGREE - MIN_DEGREE)*dimensionMultiple;
	//cout << dimension << endl;
	int subspaceDimension = 1;

	//int functionSetSize = functionSetCount;//sizeof(functionSet) / sizeof(*functionSet) / 4;
	//cout << functionSetSize << endl;
	
	for (int i = 0; i<functionSetSize; i++)
		subspaceDimension *= (MAX_DEGREE - MIN_DEGREE)*dimensionMultiple + 1;

	//cout << "subspaceDim = "<< subspaceDimension << endl;

	//double** functionSetDegree = new double*[subspaceDimension];//[subspaceDimension][functionSetSize];	// row = ith coeficient : and columns = degree of each functionSet
	//for (int i = 0; i < subspaceDimension; i++)
	//{
	//	functionSetDegree[i] = new double[functionSetSize];
	//}
	//cout << "ansMtrixDim = " << ans.dim_row << endl;
	for (int j = 0; j<subspaceDimension; j++)
	{
		double term = 1;
		//cout << j << " => ";
		for (int k = 0; k<functionSetSize; k++)		// all possible combinations of polynomials in multiplication
		{
			double degree = ((((int)j / ((int)pow((int)dimension + 1, k))) % ((int)dimension + 1))*degreeDifference + MIN_DEGREE);
			//cout << "*" << functionSet[k] << "^" << degree;
			term *= polynomial(x, degree, functionSet[k]);
			//functionSetDegree[j][k] = degree;
		}
		y += term*ans[j][0];
		//cout << "(" << term << "*" << ans.matrix[ans._2dIndex(j, 0)] << ")";
		//cout << " = "<< y;
		//cout << endl;
	}
	//for (int i = 0; i < subspaceDimension; ++i)
	//{
	//	delete[] functionSetDegree[i];
	//}
	//delete[] functionSetDegree;
	return y;
}
