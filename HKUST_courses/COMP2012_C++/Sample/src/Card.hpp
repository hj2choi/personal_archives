/*
 * Card.h
 *
 *  Created on: Feb 5, 2015
 *      Author: HongJoon
 */

#ifndef CARD_HPP_
#define CARD_HPP_
#include <iostream>
using namespace std;

const int TOTAL_TYPE_COUNT=4;
const int TOTAL_RANK_COUNT=13;


class Card
{
private :
	int type;
	int rank;	//1 to 13; 11=J, 12=Q, 13=K, 1=A
	char map_type2char() const;
	char map_rank2char() const;


public :
	Card();
	Card(int, int);
	void print(void) const;

	char get_rank() const;
	char get_type() const;
};








#endif /* CARD_HPP_ */
