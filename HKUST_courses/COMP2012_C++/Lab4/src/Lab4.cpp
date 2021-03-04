//============================================================================
// Name        : Lab4.cpp
// Author      : CHOI, Hong Joon
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================
#include "Organism.h"
#include "HealthyPlant.h"
#include "PoisonPlant.h"
#include "Animal.h"

#include <iostream>
using namespace std;


/*
class Personal_Asset
{
public:
	Personal_Asset(const string& date): purchase_date(date) { }
	virtual ~Personal_Asset() { }
	void set_purchase_date(const string& d);
	virtual double compute_net_worth() const = 0;	//current net worth
	virtual bool is_insurable() const;		// can this asset be insured?

private:
	string purchase_date;

};

class Bank_Acc_Asset : public Personal_Asset
{
public :
	Bank_Acc_Asset(const string& d, double m, double r = 0.0):Personal_Asset(d), balance(m), interest_rate(r){}

	virtual double compute_net_worth() const {return balance;}


private :
	double balance;
	double interest_rate;
};

double compute_total_worth(const Personal_Asset* assets[], int num_assets)
{
	double total_worth=0;
	for (int i=0; i<num_assets; i++)
	{
		total_worth+=assets[i]->compute_net_worth();
	}
	return total_worth;
}
*/

int main() {
	const int numOfVictim = 3;

	Organism **orgVec = new Organism*[numOfVictim];	//dynamically allocate array of Organism*	// what does this mean?????
	// do I need public getLifePoint in Plant class??
	Animal *crazyMonkey = new Animal(110.0);

	HealthyPlant *banana = new HealthyPlant(5, 10.0);
	PoisonPlant *opium = new PoisonPlant(2.5, 10, 6.0);

	Animal *babyMonkey = new Animal(10.0);

	opium->photosynthesis(10.0);

	orgVec[0] = banana;
	orgVec[1] = opium;
	orgVec[2] = babyMonkey;

	cout << "CrazyMonkey's initial life point : " << crazyMonkey->getLifePoint() << endl;
	for (int i=0; i<numOfVictim; i++) {
		crazyMonkey->eat(orgVec[i]);
		cout << "CrazyMoney gains "
				<< orgVec[i]->nutritionValue()
				<< " life point " << endl;
		delete orgVec[i];
	}
	cout << "CrazyMonkey's final life point: " << crazyMonkey->getLifePoint() << endl ;

	delete crazyMonkey;
	delete [] orgVec;
	return 0;
}
