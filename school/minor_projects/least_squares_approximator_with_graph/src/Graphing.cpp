
#include "stdafx.h"
#include <math.h>
//#include "opencv2/core/core.hpp"
//#include "opencv2/photo/photo.hpp"
#include "opencv2/highgui/highgui.hpp"

#include "Matrix.h"

using namespace std;
using namespace cv;

int width = 0;
int height = 0;
double dWidth = 0;
double dHeight = 0;


inline const int frameIndex(int yIndex, int xIndex, int bgr)// easy indexing
{
	int realIndex = (yIndex*width + xIndex) * 3 + bgr;
	if (realIndex <0 || realIndex >= width*height * 3)
	{
		return 0;
	}
	return realIndex;
}


int MIN_DEGREE = 0;
int MAX_DEGREE = 1;
double degreeStep = 1;
//double dataSet[][2] = { { -5, -5 }, { 5, 5 }, { 8, 8 }, { 12, 15 }, { 15, 20 }, { 30, 30 }, {40,40} };// {15,115}, {20,155}, {25,200}, {30,252}, {40,365}, {50, 490},{70,800}};
double** dataSet;
char functionSet[] = { 'c'};
// log, e does not work. |x| and x^-n does not work well with large degrees.
int functionSetSize = 1;
int dataSetSize = 0;

Matrix solutionVector;
Mat frame;
bool dataPointUpdated = 1;


double scaleInverse_x = 20;			// how many pixels are contained by 1 unit?
double scaleInverse_y = 20;
double gridInterval = 50;	// unit for each grid

double g(double x)	// subfunctions
{
	return cos(x);
}

double function(double x)		// function to plot
{
	return approximatedFunction(solutionVector, x, functionSet, functionSetSize, MIN_DEGREE, MAX_DEGREE, degreeStep);
}

double gridSize_x = 1 / scaleInverse_x;	// size per one pixel
double gridSize_y = 1 / scaleInverse_y;	// same

double gridActualDistance_x = gridInterval / gridSize_x;
double gridActualDistance_y = gridInterval / gridSize_y;


void CallBack(int event, int x, int y, int flags, void* userdata)
{
	if (event == EVENT_LBUTTONDOWN)
	{
		//int b = frame.data[frame.channels()*(frame.cols*y + x) + 0];
		//int g = frame.data[frame.channels()*(frame.cols*y + x) + 0];
		//int r = frame.data[frame.channels()*(frame.cols*y + x) + 0];
		//-function((x - dWidth / 2)*gridSize_x) / gridSize_y + dHeight / 2
		cout << "clicked coordinate (" << x << ", " << y << ")" << endl;
		double yPos = (-y+(double)height/2.0)*(double)gridSize_y;// + height / 2;
		double xPos = (x - (double)width / 2.0)*(double)gridSize_x;
		cout << "clicked x-y plane (" << xPos << ", " << yPos << ")" << endl;
		dataSet[dataSetSize][0] = xPos;
		dataSet[dataSetSize++][1] = yPos;
		dataPointUpdated = 1;
	}
	if (event == EVENT_RBUTTONDOWN && dataSetSize>=1)
	{
		cout << "undo" << endl;
		dataSetSize--;
		dataPointUpdated = 1;
	}
}


void drawGrid()
{


	for (int i = 0; i < dHeight; i++)			//clear screen
	{
		for (int j = 0; j < dWidth; j++)
		{
			frame.data[frameIndex(i, j, 0)] = 255;
			frame.data[frameIndex(i, j, 1)] = 255;
			frame.data[frameIndex(i, j, 2)] = 255;
		}
	}
	for (int i = 0; i < dWidth; i++)				// plot x-axis
	{
		frame.data[frameIndex(dHeight / 2, i, 2)] = 0;
		frame.data[frameIndex(dHeight / 2, i, 1)] = 0;
		frame.data[frameIndex(dHeight / 2, i, 0)] = 0;
	}
	for (int i = 0; i < dHeight; i++)				// plot y-axis
	{
		frame.data[frameIndex(i, dWidth / 2, 2)] = 0;
		frame.data[frameIndex(i, dWidth / 2, 1)] = 0;
		frame.data[frameIndex(i, dWidth / 2, 0)] = 0;
	}

	//int gridLength = dWidth;

	for (int i = dWidth / 2; i < dWidth; i += gridActualDistance_x)
		for (int j = -dWidth / 2; j < dWidth / 2; j++)	//draw grid for x-axis
			frame.data[frameIndex(j + dHeight / 2, i, 2)] = 0;
	for (int i = dWidth / 2; i >= 0; i -= gridActualDistance_x)
		for (int j = -dWidth / 2; j < dWidth / 2; j++)	//draw grid for x-axis
			frame.data[frameIndex(j + dHeight / 2, i, 2)] = 0;
	for (int i = dHeight / 2; i < dHeight; i += gridActualDistance_y)
		for (int j = -dWidth / 2; j < dWidth / 2; j++)		//draw grid for y-axis
			frame.data[frameIndex(i, j + dWidth / 2, 2)] = 0;
	for (int i = dHeight / 2; i >= 0; i -= gridActualDistance_y)
		for (int j = -dWidth / 2; j < dWidth / 2; j++)		//draw grid for y-axis
			frame.data[frameIndex(i, j + dWidth / 2, 2)] = 0;


}

void plotPoints()
{
	// plot points
	for (int i = 0; i < dataSetSize; i++)
	{
		int yPosOnGraph = (int)(-(dataSet[i][1]) / gridSize_y + dHeight / 2);
		//cout << dataSet[i][0] / (double)gridSize_x + (dWidth / 2.0) << ", " << yPosOnGraph << endl;
		//(dataSet[i][0] - dWidth / 2)*gridSize_x;
		// diagonal NE
		for (int j = -5; j < 5; j++)
		{
			frame.data[frameIndex(j + yPosOnGraph, j + dataSet[i][0] / (double)gridSize_x + (dWidth / 2.0), 0)] = 0;
			frame.data[frameIndex(j + yPosOnGraph, j + dataSet[i][0] / (double)gridSize_x + (dWidth / 2.0), 1)] = 0;
			frame.data[frameIndex(j + yPosOnGraph, j + dataSet[i][0] / (double)gridSize_x + (dWidth / 2.0), 2)] = 0;
		}
		// diagonal SW
		for (int j = -5; j < 5; j++)
		{
			frame.data[frameIndex(j + yPosOnGraph, -j + dataSet[i][0] / (double)gridSize_x + (dWidth / 2.0), 0)] = 0;
			frame.data[frameIndex(j + yPosOnGraph, -j + dataSet[i][0] / (double)gridSize_x + (dWidth / 2.0), 1)] = 0;
			frame.data[frameIndex(j + yPosOnGraph, -j + dataSet[i][0] / (double)gridSize_x + (dWidth / 2.0), 2)] = 0;
		}
		// diagonal E
		for (int j = -5; j < 5; j++)
		{
			frame.data[frameIndex(j + yPosOnGraph, dataSet[i][0] / (double)gridSize_x + (dWidth / 2.0), 0)] = 0;
			frame.data[frameIndex(j + yPosOnGraph, dataSet[i][0] / (double)gridSize_x + (dWidth / 2.0), 1)] = 0;
			frame.data[frameIndex(j + yPosOnGraph, dataSet[i][0] / (double)gridSize_x + (dWidth / 2.0), 2)] = 0;
		}
		// diagonal S
		for (int j = -5; j < 5; j++)
		{
			frame.data[frameIndex(yPosOnGraph, j + dataSet[i][0] / (double)gridSize_x + (dWidth / 2.0), 0)] = 0;
			frame.data[frameIndex(yPosOnGraph, j + dataSet[i][0] / (double)gridSize_x + (dWidth / 2.0), 1)] = 0;
			frame.data[frameIndex(yPosOnGraph, j + dataSet[i][0] / (double)gridSize_x + (dWidth / 2.0), 2)] = 0;
		}
	}
}


void drawGraph()
{
	int yPosOnGraph = 0;
	int prevYPosOnGraph = 0;

	for (int x = 0; x<dWidth; x++)			// plot a given function.
	{
		yPosOnGraph = (int)(-function(((double)x - (double)dWidth / 2.0)*(double)gridSize_x) / (double)gridSize_y + (double)dHeight / 2.0);
		//cout << yPosOnGraph << endl;
		try
		{
			frame.data[frameIndex(yPosOnGraph, x, 1)] = 0;	// plot one dot for y corresponding to x
			for (int i = prevYPosOnGraph; i < yPosOnGraph; i++)		// fill up the empty space to look graph better, instead of leaving graph as bunch of tiny dots
				frame.data[frameIndex(i, x, 1)] = 0;
			for (int i = prevYPosOnGraph; i > yPosOnGraph; i--)		// same, fill up space, in case of increaing value of y
				frame.data[frameIndex(i, x, 1)] = 0;
		}
		catch (Exception e){}

		prevYPosOnGraph = yPosOnGraph;
	}
}


int main(int argc, char* argv[])
{
	cout << sizeof(functionSet)/sizeof(char) << endl;
	double a[] = { 1, 2, 3, 4, 5, 6 };
	double c[] = { 1, 2, 3 };
	Matrix mat(2, 3, a);
	mat.print();
	cout << mat.get_col() << endl;
	cout << mat.get_row() << endl;
	Matrix matCopy=mat;
	matCopy.clear();
	matCopy.print();
	matCopy = mat;
	matCopy.print();
	matCopy.clear();
	cout << mat.get_col() << endl;
	cout << mat.get_row() << endl;
	mat.print();

	Matrix vector(3, 1, c);
	vector.print();
	Matrix vector2(1,3,c);
	vector2.print();

	multiplication(vector,vector2).print();
	
	Matrix C(multiplication(mat, vector));
	C.print();

	multiplication(mat.transpose(), mat).print();
	leastSquareSolution(mat,vector);
	cout << vectorDistance(vector, leastSquareSolution(mat, vector));//.print();
	
	cout << endl;

	double at[] = { 1, 2, 3, 1, 2, 3, 1, 2, 3 };
	Matrix nMat(2, 4, at);
	nMat.basis().print();
	
	int s;
	cout << "Matrix test done. Enter any key to continue" << endl;
	cin >> s;

	dataSet = new double*[1000];
	for (int i = 0; i < 1000; i++)
	{
		dataSet[i] = new double[2];
	}
	//dataSet[0][0] = -5; dataSet[0][1] = -5;
	//dataSet[1][0] = 8; dataSet[1][1] = 8;

	dataSetSize = 0;

	//solutionVector = leastSquareApproximation(functionSet, functionSetSize, dataSet, dataSetSize, MIN_DEGREE, MAX_DEGREE, degreeDifference);

	//cout << endl << approximatedFunction(solutionVector, -5, functionSet, functionSetSize, MIN_DEGREE, MAX_DEGREE, degreeDifference);
	
	

	
	VideoCapture cap(0); // open the video camera no. 0

	if (!cap.isOpened())  // if not success, exit program
	{
		cout << "Cannot open the video cam" << endl;
		return -1;
	}

	dWidth = cap.get(CV_CAP_PROP_FRAME_WIDTH); //get the width of frames of the video
	dHeight = cap.get(CV_CAP_PROP_FRAME_HEIGHT); //get the height of frames of the video

	cout << "Frame size : " << dWidth << " x " << dHeight << endl;
	width = dWidth;
	height = dHeight;
	
	namedWindow("Plot", CV_WINDOW_NORMAL); //create a window called "MyVideo"

	bool bSuccess = cap.read(frame); // read a new frame from video

	while (1)
	{
		//cout << "frame" << endl;
		
		if (dataPointUpdated)
		{
			solutionVector = leastSquareApproximation(functionSet, functionSetSize, dataSet, dataSetSize, MIN_DEGREE, MAX_DEGREE, degreeStep);
			drawGrid();
			drawGraph();
			plotPoints();
			dataPointUpdated = 0;
			try {
				imshow("Plot", frame); //show the frame in "Plot" window
			}
			catch (cv::Exception e) {
				cout << "Error!" << endl;
			}
		}


		setMouseCallback("Plot", CallBack, NULL);


		


		if (waitKey(50) == 27) //wait for 'esc' key press for 30ms. If 'esc' key is pressed, break loop
		{
			cout << "esc key is pressed by user" << endl;
			break;
		}
	}
	
	return 0;
	
}