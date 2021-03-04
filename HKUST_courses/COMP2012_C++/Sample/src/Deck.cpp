/*
 * Deck.cpp
 *
 *  Created on: Feb 5, 2015
 *      Author: HongJoon
 */
#include "Deck.hpp"

Deck::Deck()
{
	deck_size=0;
}

void Deck::print() const
{
	cout << "| Deck info |" << endl;
	for (int i=0; i<deck_size; i++)
		cards[i].print();
	cout << "| End of Deck |" << endl;
}

void Deck::shuffle()
{
	srand(time(NULL));
	for (int i=0; i<deck_size; i++)
	{
		int swap_target = rand()%deck_size;
		Card temp = cards[i];
		cards[i] = cards[swap_target];
		cards[swap_target] = temp;
	}
}

Card Deck::draw_from_top()
{
	return cards[--deck_size];// = new_one;
}

void Deck::add_to_top(Card new_one)
{
	cards[deck_size++] = new_one;
}



Deck generate_full_card_deck(void)
{
	Deck full_deck;
	for (int i=1; i<=TOTAL_TYPE_COUNT; ++i)
		for (int j=1; j<=TOTAL_RANK_COUNT; ++j)
			full_deck.add_to_top(Card(i,j));
	return full_deck;


}


