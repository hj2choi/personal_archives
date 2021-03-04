/*
 * Player.cpp
 *
 *  Created on: Feb 6, 2015
 *      Author: HongJoon
 */
#include "Player.hpp"

Player::Player()
{
	cash=100;
}

Deck Player::get_deck()
{
	return deck;
}

int Player::get_cash()
{
	return cash;
}

void Player::add_to_deck(Card new_one)
{
	deck.add_to_top(new_one);
}

void Player::print()
{
	cout << "| ======= player info ======== |" << endl;
	cout << "current cash = " << cash << endl;
	deck.print();
	cout << "| ==== end of player info ==== |" << endl;

}
