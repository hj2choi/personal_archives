public class pr015decisionpr26
{
	public static void main (String[]args)
	{
		for (int i=1;i<=100 ;i++ )
		{
			if (i%2==0)							//reject multiple of 2
				continue;  
			if (i%3==0)							//reject multiple of 3
				continue;
			System.out.println(i);
		}
	}
}
/*
1
5
7
11
13
17
19
23
25
29
31
35
37
41
43
47
49
53
55
59
61
65
67
71
73
77
79
83
85
89
91
95
97
*/