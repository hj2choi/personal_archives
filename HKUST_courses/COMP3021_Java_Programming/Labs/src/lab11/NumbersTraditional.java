package lab11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumbersTraditional {

	public static List<Integer> isOdd(List<Integer> numbers) {
		List<Integer> results = new ArrayList<Integer>();
		for (int n : numbers) {
			if (n % 2 != 0)
				results.add(n);
		}
		return results;
	}

	// checks whether an integer is prime or not.
	public static boolean isPrimeInt(int n) {
		// check if n is a multiple of 2
		if (n % 2 == 0)
			return false;
		// if not, then just check the odds
		for (int i = 3; i * i <= n; i += 2) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	public static List<Integer> isPrime(List<Integer> numbers) {
		List<Integer> results = new ArrayList<Integer>();
		// TODO
		// Find out all the prime numbers

		for (int n : numbers) {
			if (isPrimeInt(n))
				results.add(n);
		}
		return results;
	}

	// checks whether an Integer is palindrome.
	public static boolean isPalindromeInt(int n) {
		int origin = n;
		int reverse = 0;

		while (n > 0) {
			int r = n % 10;
			reverse = reverse * 10 + r;
			n = n / 10;
		}
		
		if (origin == reverse)
			return true;
		else
			return false;

	}

	public static List<Integer> isPalindrome(List<Integer> numbers) {
		List<Integer> results = new ArrayList<Integer>();
		// TODO
		// Find out all the palindrome numbers, such as 484 and 121.
		for (int n : numbers) {
			if (isPalindromeInt(n))
				results.add(n);
		}
		return results;
	}

	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(480, 514, 484, 389, 709, 935, 328, 169, 649, 300, 685, 429, 243, 532, 308,
				87, 25, 282, 91, 415);

		System.out.println("The odd numbers are : " + isOdd(numbers));
		System.out.println("The prime numbers are : " + isPrime(numbers));
		System.out.println("The palindrome numbers are : " + isPalindrome(numbers));

	}
}