#ifndef FRAME_H_
#define FRAME_H_




//#include "stdafx.h"
#include <string>
#include "opencv2/highgui/highgui.hpp"



class IMG
{
public:

	IMG();	// empty
	IMG(std::string);	//only name
	//Image(int, int);	// only width and height
	//Image(const cv::Mat&, const std::string);	// full constructor
	//Image(const Image&);	// copy constructor
	~IMG();

	bool camera_read();
	void create_window() const;
	void display() const;
	int data(int, int, int) const;		//get value of image at specified position and color value

protected:
	int height;		// height of the image in pixels
	int width;		// width of the image in pixels

private:
	cv::Mat frame;		//Matrix of frame, stored in 1 dimentional array
	std::string name;			// name of image in character array;
};



#endif // !1