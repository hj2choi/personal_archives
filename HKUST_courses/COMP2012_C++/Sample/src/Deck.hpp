/*
 * Deck.h
 *
 *  Created on: Feb 5, 2015
 *      Author: HongJoon
 */

#ifndef DECK_HPP_
#define DECK_HPP_
#include "Card.hpp"
#include <time.h>
#include <cstdlib>


const int MAX_DECK_COUNT = TOTAL_TYPE_COUNT*TOTAL_RANK_COUNT;

class Deck
{
private:
	Card cards[MAX_DECK_COUNT];
	int deck_size;

public:
	Deck();
	void print() const;
	void shuffle();
	//Card draw_random_card(void);
	Card draw_from_top();
	void add_to_top(Card);

};

Deck generate_full_card_deck();

#endif /* DECK_HPP_ */
