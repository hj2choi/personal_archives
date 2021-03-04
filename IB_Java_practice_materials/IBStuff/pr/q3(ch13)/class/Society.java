/*************************************************							Class World							*****************************************************/
class World
{
	static java.util.Scanner s = new java.util.Scanner(System.in);
	static int worldDimension = 9;//below 10
	static int worldSize = worldDimension*worldDimension;		
//============Methods=============
	public static int toArrayLocation(int displayedLocation)
	{
		int location=0;
		location+=worldDimension*(displayedLocation/10-1);
		location+=displayedLocation%10;
		return location;
	}
	public static byte leftOrRight(int arrayLocation)	 //0 if nothing, 1 if leftmost, 2 if rightmost
	{
		if (arrayLocation%worldDimension==0)
		{
			return 2;
		}
		else if (arrayLocation%worldDimension==1)
		{
			return 1;
		}
		return 0;
	}
	public static void p(String info)
	{System.out.print(info);}
	public static void pp(String info)
	{System.out.println(info);}
	public static void pp(int info)
	{System.out.println(info);}
	public static void printLine(int lines)
	{for (int i=0;i<lines ;i++ )
			pp("");
	}
	public static void clearscreen()
	{for (int i=0;i<40 ;i++ )
			pp("");
	}
	public static int safeInput()		//safe from exception
    {String input = s.next();
        try
        {
			return Integer.parseInt(input);
		}
        catch (Exception e)
        {
			System.out.println("Invalid input! Caused by : "+e);
           return 0;
        }
    }
}




/*************************************************							Class Society							*****************************************************/
class Society extends World
{
	public static void main(String args[])
	{
		String[] person = new String[worldSize+1];
		String input="";
		Person p1 = new Person();
		do
		{
			p("Enter dimension of the world(Below 10) : ");
			worldDimension = safeInput();
			worldSize =worldDimension*worldDimension;
		}
		while (worldDimension<2 || worldDimension>=10);
		
		while (true)
		{
			clearscreen();
//===========InitializeSociety==========
			for (int i=0;i<worldSize+1 ;i++ )
				person[i] = ".";
//===========getPerson'sInitial===========
			person[p1.getLocation()] = ""+p1.getName().charAt(0);
//===========PrintSociety===========
			pp(p1.getName()+"'s profile");
			pp("age : "+p1.getAge());
			pp("Energy : "+p1.getEnergy());
			pp("Cash : ");
			printLine(5);
			for (int i=1;i<=worldDimension ;i++ )
			{
				p(i+"  ");
				for (int j=1;j<=worldDimension ;j++ )
					p(person[(i-1)*worldDimension+j]+"  ");
				pp("\n");
			}
			p("   ");
			for (int i=1;i<=worldDimension ;i++ )
				p(i+"  ");
			pp("");
			printLine(3);
//===========ask for userinput===========
			
			pp("use [wasd] to move your person around. To rest, enter [r]");
			input=s.next();
			if (input.equals("w") && p1.getLocation()-worldDimension>0)
				p1.walkTo(p1.getLocation()-worldDimension);
			if (input.equals("s") && p1.getLocation()+worldDimension<=worldSize)
				p1.walkTo(p1.getLocation()+worldDimension);
			if (input.equals("a") && leftOrRight(p1.getLocation())!=1)
				p1.walkTo(p1.getLocation()-1);
			if (input.equals("d") && leftOrRight(p1.getLocation())!=2)
				p1.walkTo(p1.getLocation()+1);
			if (input.equals("r"))
				p1.rest();

//===========win===========
			if (p1.getLocation()==worldSize)
			{
				clearscreen();
				pp("Success!!!\nEnter any key to exit");
				s.next();
				return;
			}
		}
	}
}





/*************************************************							Class Person							*****************************************************/
class Person extends World
{
//==============property of human(Information hiding)==========
	private int age;
	private String name;
	private int location;
	private int energy;
//==============Constructor(Initialize data)======
	Person()
	{
		this.age=1;			// property of human
		this.name="HongJoon";
		this.location=1;
		this.energy=100;
	} 
	public Person(String name)
	{
		this.name=name;
	}
	public Person(String name, int age, int location)//overloading
	{
		this.name=name;
		this.age=age;
		this.location = location;
	}
//==============Function of Human============
	public void walkTo(int locationRequest)	 
	{
		if (energy>=10)
		{
			location=locationRequest;
			energy-=10;
		}
		else
			pp("Not enough energy to move!!");
	}
	public void rest()
	{
		energy+=30;
	}
//==============Accessor============
	public int getAge()
	{
		return age;
	}
	public String getName()
	{
		return name;
	}
	public int getLocation()
	{
		return location;
	}
	public int getEnergy()
	{
		return energy;
	}
}