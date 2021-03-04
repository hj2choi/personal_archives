class ExampleUsingHongJoonTemplate extends HongJoonLibraryExtended
{

	static int[] array = new int[10000];
	int length;


	public static int[] convertNumberSystemToBase(int n, int base) // convert decimal to other numbersystem, index 0 = > lsb
	{
		int[] newNumber = new int[1000];


		return newNumber;

	}
	public static void f(int a)
	{
		a=100;
	}

	public static void printArr(int[] list, int length)
	{
		for (int i=0; i<length; i++)
		{
			System.out.println(list[i]);
		}
	}
	public static void printArr(double[] list, int length)
	{
		for (int i=0; i<length; i++)
		{
			System.out.println(list[i]);
		}
	}

	public static void main (String args[])
	{
		
		System.out.println(convertNumberToWords(31266487));
		double[] huffArr = {0.2544, 0.4096, 0.336};
		quickSort(huffArr, 0, 2);
		System.out.println("add "+(huffArr[0]+huffArr[1]));
		printArr(huffArr, 3);
/*
		int firstN = 5000000;
		int secondN = 20;
		int thirdN = 100000;
		int[] arr1 = new int[firstN];

		System.out.println("Sorting with n = "+firstN);

		System.out.println("\nPoorlyImplementedBubbleSort: ");
		for (int i=0; i<firstN; i++)
		{
			arr1[i]=(int)(Math.random()*10000);
		}
		startTimer();
		//sort(arr1, firstN, 1);
		endTimer();
		//printArr(arr1, 10);

		System.out.println("\nSelectionSort: (swap)");
		for (int i=0; i<firstN; i++)
		{
			arr1[i]=(int)(Math.random()*10000);
		}
		startTimer();
		//selectionSort(arr1);
		endTimer();
		//printArr(arr1, 10);

		System.out.println("\nInsertionSort: (no swap)");
		for (int i=0; i<firstN; i++)
		{
			arr1[i]=(int)(Math.random()*10000);
		}
		startTimer();
		//insertionSort(arr1, 0, firstN-1);
		endTimer();
		//printArr(arr1, 10);


		System.out.println("\nHeapSort: ");
		for (int i=0; i<firstN; i++)
		{
			arr1[i]=(int)(Math.random()*10000);
		}
		startTimer();
		heapSort(arr1, firstN);
		endTimer();
		//printArr(arr1, 10);

		System.out.println("\nQuickSort: ");
		for (int i=0; i<firstN; i++)
		{
			arr1[i]=(int)(Math.random()*10000);
		}
		startTimer();
		quickSort(arr1, 0, firstN-1);
		endTimer();
		//printArr(arr1, 10);

		System.out.println("\nOptimizedQuickSort: (insertion sort for small portions)");
		for (int i=0; i<firstN; i++)
		{
			arr1[i]=(int)(Math.random()*10000);
		}
		startTimer();
		optimizedQuickSort(arr1, 0, firstN-1);
		endTimer();
		//printArr(arr1, 10);

		System.out.println("\nCounting sort: O(n+k)");
		for (int i=0; i<firstN; i++)
		{
			arr1[i]=(int)(Math.random()*10000);
		}
		startTimer();
		arr1 = countingSort(arr1, firstN);
		endTimer();
		//printArr(arr1, 10);
*/


	}


	public static boolean[] generatePrimes(int num)// for enhancedPrimeCheck
	{
		boolean[] prime = new boolean[num+1];
		for (int i=0; i<=num; i++)
			prime[i]=true;
		for (int i=4; i<=num; i+=2)
			prime[i]=false;
		for (int i=3; i<=num; i+=2)
			for (int j=i*2; j<=num; j+=i)
				prime[j]=false;
		return prime;
	}

	public static boolean primarility(boolean[] prime, int num) // pre-requisite: run generatePrimes()
	{
		if (prime[num])
			return true;
		return false;
	}

	public static int binSearch(int searchFor, int first, int last)
	{
		int mid = last/first;
		if (array[mid]==searchFor)
			return mid;
		else if (array[mid]<searchFor)
			return binSearch(searchFor, mid+1, last);
		else if (array[mid]>searchFor)
			return binSearch(searchFor, first, mid-1);
		return -1;
	}
}
