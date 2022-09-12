#include "stdafx.h"
#include "Graph.h"


using namespace std;
using namespace cv;


/*
inline const int frameIndex(int yIndex, int xIndex, int bgr)// easy indexing
{
	int realIndex = (yIndex*width + xIndex) * 3 + bgr;
	if (realIndex <0 || realIndex >= width*height * 3)
	{
		return 0;
	}
	return realIndex;
}

void plot(double(*functionToPlot)(double), int w, int h)
{
	
	Mat frame, tempFrame;
	bool bSuccess = cap.read(frame); // read a new frame from video
	tempFrame = frame.clone();

	if (!bSuccess) {

		continue;
	}

	

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

	int gridLength = dWidth;

	for (int i = 0; i < dWidth; i += gridActualDistance_x)
		for (int j = -gridLength / 2; j < gridLength / 2; j++)	//draw grid for x-axis
			frame.data[frameIndex(j + dHeight / 2, i, 2)] = 0;
	for (int i = 0; i < dHeight; i += gridActualDistance_y)
		for (int j = -gridLength / 2; j < gridLength / 2; j++)		//draw grid for y-axis
			frame.data[frameIndex(i, j + dWidth / 2, 2)] = 0;

	int yPosOnGraph = 0;
	int prevYPosOnGraph = 0;




	for (int x = 0; x<dWidth; x++)			// plot function for graphing
	{
		yPosOnGraph = (int)(-function((x - dWidth / 2)*gridSize_x) / gridSize_y + dHeight / 2);
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

	for (int i = 0; i < dataSetSize; i++)
	{
		yPosOnGraph = (int)(-function(dataSet[i][0]) / gridSize_y + dHeight / 2);
		//(dataSet[i][0] - dWidth / 2)*gridSize_x;
		frame.data[frameIndex(yPosOnGraph, dataSet[i][0], 0)] = 0;
		frame.data[frameIndex(yPosOnGraph, dataSet[i][0], 0)] = 0;
	}



	setMouseCallback("Plot", CallBack, NULL);


	try {
		imshow("Plot", frame); //show the frame in "Plot" window
	}
	catch (cv::Exception e) {
		cout << "Error!" << endl;
	}
}

*/