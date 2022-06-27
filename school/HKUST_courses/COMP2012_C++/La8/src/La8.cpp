#include "OpenAdressing.h"

#include "LinearProbing.h"    // TODO: Implement the class LinearProbing
#include "QuadraticProbing.h" // TODO: Implement the class QuadraticProbing
#include "DoubleHashing.h"    // TODO: Implement the class DoubleHashing

#include <string>
#include <iostream>
using namespace std;

int main() {

	const int inputSize = 5;
	const int bucketSize = 11;

	// Generate some input values
	int input[inputSize];
	for (int i=0; i<inputSize; i++) {
		input[i] = 1 + bucketSize*i;
	}

	string algoNames[3] = {"LinearProbing", "QuadraticProbing", "DoubleHashing"};
	OpenAddressing *algo[3];

	// Create 3 different algorithms:
	//   0: Linear Probing
	//   1: Quadratic Probing
	//   2: Double Hashing

	algo[0] = new LinearProbing(bucketSize);
	algo[1] = new QuadraticProbing(bucketSize);
	algo[2] = new DoubleHashing(bucketSize);

	// Run 3 algorithms on the same data set
	//  Print out all intermediate steps
	for (int i=0; i<3; i++) {
		cout << "==" << algoNames[i]<< "==" << endl;
		cout << "Step " << 0 << " : " ;
		algo[i]->print();
		for (int j=0; j<inputSize; j++) {
			algo[i]->insert( input[j] );
			cout << "Step " << j+1 << " : " ;
			algo[i]->print();
		}

		delete algo[i];  // clean up the ith algorithm
	}
	return 0;
}
