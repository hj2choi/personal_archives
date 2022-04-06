class ppl//TestPerson
{
	public static void main(String[] args) 
	{
		System.out.println("Testing Date class\n");
		Date[] d = new Date[10];
		Date dAns = new Date(16, 2);
		for (int i=0;i<10 ;i++ )
		{
			d[i] = new Date(i,3);
			dAns = dAns.average(dAns, d[i], i);
			System.out.println(dAns);
		}
		
		Date d1 = new Date(16, 2);
		Date d2 = new Date(30, 12);
		Date d3 = new Date();
		System.out.println(d1+"\n"+d2);
		System.out.println(d3.addDate(d1,d2));
	}
}


class Date
{
	int month;
	int day;
	public Date()
	{}
	
	public Date(int d, int m)
	{
		day = d;
		month = m;
	}
	public String toString()
	{
		String output = day+"/"+month;
		return output;
	}
	public Date addDate(Date a, Date b)
	{
		int sumDays = a.day+b.day;
		int sumMonths = a.month+b.month;
		if (sumDays>30)
		{
			sumDays=sumDays%30;
			sumMonths=sumMonths/30+1;
		}
		if (sumMonths>12)
		{
			sumMonths%=12;
		}
		Date dateSum = new Date(sumDays, sumMonths);
		return dateSum;
	}
	public Date average(Date a, Date b, int num)
	{
		Date ave = new Date();
		ave = addDate(a, b);
		return ave;
	}
}



class Person
{
	String name;
	String surname;
	int age;
	
	public Person()
	{
	}		//empty constructor

	public Person(String n, String sn, int a)
	{
		name=n;
		surname=sn;
		age=a;
	}

	//class methods
	void talk()
	{
		System.out.println("Hello my name is "+name);
		System.out.println("Won't u tell me your name?");
	}

	void eat()
	{
		System.out.println("Nam Nam");
	}
}