class sortingTest extends HongJoonLibrary
{
	public static void main (String args[]) // throws IOException
	{
		String[] stringList = new String[10000];
		try
		{
			stringList = readFile("Num1000");
		}
		catch (Exception e){return;}
		
		int[] list = new int[10000];

		for (int i=0; i<10000; i++)
			list[i] = Integer.parseInt(stringList[i]);
		
		
		System.out.println("\n\n\n\n\n");
		list = selectionSort(list);
		for (int i=0;i<10000 ;i++ )
		{
			System.out.println(list[i]);
		}
	}
}
