//============================================================================
// Name        : Sample.cpp
// Author      : CHOI, Hong Joon
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include <pthread.h>
#include <thread>
#include <string>
#include <windows.h>
#include "Card.hpp"
#include "Deck.hpp"
#include "Player.hpp"

using namespace std;

//thread t1;
Card auction_card;
Deck main_deck = generate_full_card_deck();
int cardCost;
bool auction_progress_flag;

void auctionCounter()
{
	auction_progress_flag=true;
	long auction_start_millis = GetTickCount();
	auction_card= main_deck.draw_from_top();
	cardCost=100;
	while (auction_progress_flag && cardCost>0)
	{
		if (GetTickCount()-auction_start_millis > 1000)
		{
			cardCost-=5;
			cout << "Card cost = " << cardCost << endl;
			auction_start_millis=GetTickCount();
		}
		Sleep(50);
	}
}


int main() {

	Card a(1,1);
	a.print();
	/*main_deck.print();
	main_deck.shuffle();
	main_deck.print();
	Player player1;
	for (int i=0; i<5; i++)
		player1.add_to_deck(main_deck.draw_from_top());
	Player player2;
	for (int i=0; i<5; i++)
		player2.add_to_deck(main_deck.draw_from_top());
	player1.print();
	//player2.print();
	//auctionCounter();
	//cin.get();
	for (int i=0; i<10; i++)
	{

		//std::thread t1;
	}
	*/
	return 0;
}


