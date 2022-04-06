import java.math.BigInteger;
/*
Notice
This program is not only uesd for problem solving purpose.
Methods are designed to have further applications in other places
Thus, don't tell me I am stupid of converting everything into String!!
*/
class Euler017
{
	public static void main(String[] args) 
	{
		long ans=0;
		for (int i=1;i<1001 ;i++ )
		{
			System.out.println(integerToWord(i));
			ans+=integerToWord(i).length();
		}
		System.out.println(ans);
	}
	public static String integerToWord(int num)
	{
		if (num==0)
			return "Zero";
		String word="";
		if (num>=1000000000)		//Billions
		{
			word+=digitsBelowHundreds(num/1000000000);
			word+="Billion";
			if (num%1000000000!=0)
				word+="And";
		}
		if (num>=1000000)		//Millions
		{
			word+=digitsBelowHundreds(num/1000000);
			word+="Million";
			if (num%1000000!=0)
				word+="And";
		}
		if (num>=1000)			//thousands
		{
			word+=digitsBelowHundreds(num/1000);
			word+="Thousand";
			if (num%1000!=0)
				word+="And";
		}
		num=num%1000;
		word+=digitsBelowHundreds(num);
		return word;
	}
	public static String digitsBelowHundreds(int num)
	{
		String word ="";
		if (num>=100)		//hundreds
		{
			word+=numToString(num/100);
			word+="Hundred";
			if (num%100!=0)
				word+="And";
		}
		num=num%100;//tens
		if (num>=10 && num<20)
				return word+tensToStringException(num%100);
		else if (num>=20)
			word+=tensToString(num/10);
		num=num%10;//ones
		word+=numToString(num);
		return word;
	}
	public static String numToString(int num)
	{
		String word="";
		if (num==1)
		word+="One";
		if (num==2)
			word+="Two";
		if (num==3)
			word+="Three";
		if (num==4)
			word+="Four";
		if (num==5)
			word+="Five";
		if (num==6)
			word+="Six";
		if (num==7)
			word+="Seven";
		if (num==8)
			word+="Eight";
		if (num==9)
			word+="Nine";
		return word;
	}
	public static String tensToString(int num)
	{
		String word="";
		if (num==2)
			word+="Twenty";
		if (num==3)
			word+="Thirty";
		if (num==4)
			word+="Forty";
		if (num==5)
			word+="Fifty";
		if (num==6)
			word+="Sixty";
		if (num==7)
			word+="Seventy";
		if (num==8)
			word+="Eighty";
		if (num==9)
			word+="Ninety";
		return word;
	}
	public static String tensToStringException(int num)
	{
		String word="";
		if (num==10)
			word+="Ten";
		if (num==11)
			word+="Eleven";
		if (num==12)
			word+="Twelve";
		if (num==13)
			word+="Thirteen";
		if (num==14)
			word+="Fourteen";
		if (num==15)
			word+="Fifteen";
		if (num==16)
			word+="Sixteen";
		if (num==17)
			word+="Seventeen";
		if (num==18)
			word+="Eighteen";
		if (num==19)
			word+="Nineteen";
		return word;
	}

}
/*3354435543



*/