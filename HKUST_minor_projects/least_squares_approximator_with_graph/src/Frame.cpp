#include "stdafx.h"
#include "Frame.h"
#include <iostream>
using namespace std;
using namespace cv;


IMG::IMG() : width(0), height(0), frame(), name("") {};

IMG::IMG(string name) : width(0), height(0), frame(), name(name) { cout << endl; }





bool IMG::camera_read()
{
	VideoCapture cap(0);
	width = cap.get(CV_CAP_PROP_FRAME_WIDTH); //get the width of frames of the video
	height = cap.get(CV_CAP_PROP_FRAME_HEIGHT); //get the height of frames of the video
	return cap.read(frame);
}

void IMG::create_window() const
{
	namedWindow(name, CV_WINDOW_AUTOSIZE); //create a window called "MyVideo"
}

void IMG::display() const
{
	imshow(name, frame);
}

inline int IMG::data(int y, int x, int bgr) const
{/*
 int realIndex = (y*width + x) * 3 + bgr;
 if (realIndex <0 || realIndex >= width*height * 3)
 {
 return 0;
 }*/
	return (y*width + x) * 3 + bgr;
}