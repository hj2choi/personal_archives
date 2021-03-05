class Subject extends HongJoonUtilities
{
	static int subjectCount=0;
	static String[] subjectList = new String[100];

	String name;
	char grade;
	int percentageScore;
	boolean isRealSubject;
	int ranking;
	int subjectsTakenCount = 0;

	public Subject(String name, char grade)
	{
		this.name=name;
		this.grade=grade;
		try
		{
			int validation = Integer.parseInt(""+grade);
			isRealSubject=true;
		}
		catch (Exception e)
		{
			isRealSubject=false;
		}
		if (name.startsWith("CHINESE B"))
			this.name+=" SL";
	}
	public void setPercentageScore(int percentageScore)
	{
		this.percentageScore=percentageScore;
	}

	public String toString()
	{
		String stringRepresentation=percentageScore+"%)  ";
		if (percentageScore==0)
			stringRepresentation="N/A)   ";
		if (name.contains("THEORY"))
			stringRepresentation=percentageScore+"/60)";
		if (name.contains(" EE"))
			stringRepresentation=percentageScore+"/36)";
		return "\t\t|"+allignLeft(grade+"| ("+stringRepresentation, 23)+allignLeft(name,45)+allignLeft((percentageScore!=0?("("+ranking+"/"+subjectsTakenCount+")"):""),12)+("(top "+round((double)ranking/subjectsTakenCount*100,1)+"%)");
	}

	public void calculateRanking(Candidate[] candidates, int candidateCount)
	{
		int[] subjectScoreList = new int[1000];
		for (int i=0; i<candidateCount; i++)		// generate list of scores in this subject
			for (int j=0; j<candidates[i].subjectCount; j++)
				if ((candidates[i].subjectsTaken[j].name).equals(this.name))
					subjectScoreList[subjectsTakenCount++] = candidates[i].subjectsTaken[j].percentageScore;
		subjectScoreList = sort(subjectScoreList, subjectsTakenCount, 1);
		ranking = search(subjectScoreList, percentageScore)+1;
	}

	public static int printAllCandidatesBySubject(Candidate[] candidates, int candidateCount, String subjectName, boolean showResult)
	{
		double[] grades=new double[300];
		double[] percentages=new double[300];
		double[] difficultyIndex=new double[300];
		int studentsCount=0;
		int numberOfPer=0;
		int count=0;
		int count2=0;
		Candidate[] candidatesTakingSubject = new Candidate[300];
		for (int i=0; i<candidateCount; i++)
			for (int j=0; j<candidates[i].subjectCount; j++)
				if ((candidates[i].subjectsTaken[j].name).equals(subjectName)){
					candidatesTakingSubject[count++]=candidates[i];
				}
		if (showResult)
		{
			System.out.print(allignMiddle("Rank",10)+"|"+allignMiddle("Grade",8)+"|"+allignMiddle("Raw score",12)+"|"+allignMiddle("Candidate name",50)+"|"+allignMiddle("total Score",12)+"\n");
			System.out.println("=================================================================================================");
		}
		int rankCount=1;
		for (int grade=7; grade>=0; grade--)		// print out everything in order of ranks
			for (int score=100; score>=0; score--)
				for (int i=0; i<count; i++)
					for (int j=0; j<candidatesTakingSubject[i].subjectCount; j++)
						if ((candidatesTakingSubject[i].subjectsTaken[j].name).equals(subjectName))
							if ((gradeToInteger(candidatesTakingSubject[i].subjectsTaken[j].grade)==grade) && (candidatesTakingSubject[i].subjectsTaken[j].percentageScore==score))
							{
								Subject cur=candidatesTakingSubject[i].subjectsTaken[j];
								if (showResult)
									System.out.println(allignMiddle(rankCount+++"/"+cur.subjectsTakenCount,10)+"|"+allignMiddle(cur.grade+"",8)+"|"+allignMiddle((cur.percentageScore>0?cur.percentageScore+"%":"N/A"),12)+"|"+allignMiddle(candidatesTakingSubject[i].name,50)+"|"+allignMiddle(candidatesTakingSubject[i].finalGrade+"",8));
								grades[count2++]=gradeToInteger(cur.grade);
								if (cur.percentageScore>0){
									percentages[numberOfPer++]=cur.percentageScore;
									if (candidatesTakingSubject[i].finalGrade>0)
										difficultyIndex[studentsCount++]=(candidatesTakingSubject[i].finalGrade)/(gradeToInteger(cur.grade));
								}
							}
		// calculate statistics
		double grades_mean=0;
		double grades_st_dev=0;
		double percentages_mean=0;
		double percentages_st_dev=0;
		double difficulty=0;

		double summision=0;
		for (int i=0; i<count2; i++)
			summision+=grades[i];
		grades_mean=summision/count2;
		summision=0;
		for (int i=0; i<count2; i++)
			summision+=(grades_mean-grades[i])*(grades_mean-grades[i]);
		grades_st_dev=Math.sqrt(summision/count2);
		summision=0;
		for (int i=0; i<numberOfPer; i++)
			summision+=percentages[i];
		percentages_mean=summision/numberOfPer;
		summision=0;
		for (int i=0; i<numberOfPer; i++)
			summision+=(percentages_mean-percentages[i])*(percentages_mean-percentages[i]);
		percentages_st_dev=Math.sqrt(summision/numberOfPer);
		summision=0;
		for (int i=0; i<studentsCount; i++)
			summision+=difficultyIndex[i];
		difficulty=summision/studentsCount;
		if (showResult)
		{
			System.out.println("\n[grades]         "+allignLeft("mean = "+round(grades_mean,1),15)+allignLeft("st_dev = "+round(grades_st_dev,1),17)+"median = "+grades[count2/2]);
			System.out.println("[percentages]    "+allignLeft("mean = "+round(percentages_mean,0)+"%",15)+allignLeft("st_dev = "+round(percentages_st_dev,0)+"%",17)+"median = "+percentages[numberOfPer/2]+"%");
			System.out.println(allignLeft("Difficulty Index = "+(int)((difficulty/6-1)*100),60));//difficulty = index = average of ((final grade)/(grade))/6
		}
		return (int)((difficulty/6-1)*100);
	}

	public static int gradeToInteger(char grade)
	{
		switch (grade)
		{
		case '7':
			return 7;
		case '6':
			return 6;
		case '5':
			return 5;
		case '4':
			return 4;
		case '3':
			return 3;
		case '2':
			return 2;
		case '1':
			return 1;

		}
		switch (grade)
		{
		case 'A':
			return 6;
		case 'B':
			return 5;
		case 'C':
			return 4;
		case 'D':
			return 3;
		case 'E':
			return 2;

		}
		return 0;

	}
}