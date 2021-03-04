#include "Card.hpp"


Card::Card(void)
{
	rank =0;
	type=0;



}


Card::Card(int type, int rank)
{
	this->rank = rank;
	this->type = type;
}


void Card::print() const
{
	cout << "Card: " << map_rank2char() << "(" << map_type2char() << ")"<< endl;

}











inline char Card::map_type2char() const
{
	switch(type)
	{
	case 1:
		return 'S';	//spade
	case 2:
		return 'H';	//heart
	case 3:
		return 'D';	//diamond
	case 4:
		return 'C';	//club
	default:
		return '?';	//unresolved type

	}



}

inline char Card::map_rank2char() const
{
	switch(rank)
	{
	case 1:
		return 'A';
	case 2:
	case 3:
	case 4:
	case 5:
	case 6:
	case 7:
	case 8:
	case 9:
		return rank+48;
	case 10:
		return '0';
	case 11:
		return 'J';
	case 12:
		return 'Q';
	case 13:
		return 'K';
	default:
		return '?';	//unresolved type

	}



}

