/*
 * Player.hpp
 *
 *  Created on: Feb 5, 2015
 *      Author: HongJoon
 */

#ifndef PLAYER_HPP_
#define PLAYER_HPP_
#include "Deck.hpp"

class Player
{
private:
	Deck deck;
	int cash;

public:
	Player();
	Deck get_deck();
	int get_cash();

	void add_to_deck(Card);
	void print();

};



#endif /* PLAYER_HPP_ */
