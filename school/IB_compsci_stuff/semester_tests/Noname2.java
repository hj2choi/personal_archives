public class Noname2 extends IBIO
{ 
public static void main (String []args)



{



output("Fibonacci Sequence Frequency");



output("^^^^^^^^^^^^^^^^^^^^^^^^^^^^");



long first = 0;



long second = 1;



long next = 0;



long [] sequenceTerm = new long[1000];



long Fibonacci = 0;




System.out.println("1: " + first);



sequenceTerm[0] = 150;



sequenceTerm[1] = second;



System.out.println("2: " + second);




for (int a = 3; a<=99 ;a++ )



{



Fibonacci = first + second;



if (Fibonacci>100)



Fibonacci = Fibonacci%100;



if (a<10)



System.out.print("");



System.out.print(a+": ");



if (Fibonacci>=10)



System.out.print("");



System.out.println(Fibonacci);




sequenceTerm[a] = Fibonacci;



next = first;



first = second;



second = next+second;



}



System.out.println("90th term: " + sequenceTerm[90]);



long count=0;



long arraySearch = inputInt("Enter the number to search (0-99) : ");



for (int k = 1;k<100 ;k++ )



if (arraySearch==sequenceTerm[k])



count++;



if (count==0)



System.out.println(arraySearch+" not found");



else



System.out.println("Frequency of occurences of "+arraySearch+" : "+count);
}
}