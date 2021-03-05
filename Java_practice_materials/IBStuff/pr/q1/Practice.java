import java.util.*;
import java.io.File;
import java.io.PrintWriter;

public class Practice {

	public static void main(String[] args) throws Exception {

		ArrayDeque<Integer> intQueue = new ArrayDeque();
		ArrayDeque<Double> doubleQueue = new ArrayDeque();
		System.out.println("aa");

		intQueue.addLast(50);
		intQueue.addLast(10);

		
		boolean isEmpty=intQueue.isEmpty();
		
		int n = intQueue.size();
		int num=1;
		while (n>0)
		{
			int retValue = intQueue.removeFirst();
			System.out.println("Transaction "+num+": "+retValue);
			intQueue.addLast(retValue);
			n--;
			num++;
		}
	}
}
