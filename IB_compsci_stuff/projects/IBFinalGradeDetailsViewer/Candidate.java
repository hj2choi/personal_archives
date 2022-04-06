class Candidate extends HongJoonUtilities
{
	public final static String CERTIFICATE="Certificate";
	public final static String DIPLOMA_NOT_AWARDED="DIPLOMA NOT AWARDED";
	public final static String DIPLOMA_AWARDED="DIPLOMA AWARDED";
	public final static String BILINGUAL_DIPLOMA_AWARDED="BILINGUAL DIPLOMA AWARDED";

	String candidateCode;
	String name;
	int birthYear;
	Subject[] subjectsTaken;
	int bonusPoints;
	int finalGrade;
	double preciseMark;
	int subjectCount;
	String diplomaCategory;
	int difficultyIndex;
	
	
	int ranking;
	int rankingExcludeBonus;
	int preciseMarkRanking;
	int rankingWithSubjectDifficulty;

	public Candidate(String candidateCode, String name, String[] subjects, char[] grades, int bonusPoints, int finalGrade, int subjectCount, String diplomaCategory, int birthYear)
	{
		this.candidateCode=candidateCode;
		this.name=name;
		this.bonusPoints=bonusPoints;
		this.finalGrade=finalGrade;
		this.subjectCount=subjectCount;
		this.diplomaCategory=diplomaCategory;
		this.birthYear=birthYear;
		subjectsTaken = new Subject[subjectCount];
		for (int i=0; i<subjectCount; i++)
			subjectsTaken[i] = new Subject(subjects[i], grades[i]);
		if (finalGrade==0)
			this.diplomaCategory=CERTIFICATE;
	}
	public void setPreciseMark(double preciseMark)
	{
		this.preciseMark = preciseMark;
	}
	public String toString()
	{
		return allignLeft(name,35)+candidateCode+"  ("+(diplomaCategory.equals(CERTIFICATE)?"Course)":"Diploma)")+"  "+(finalGrade>0?finalGrade:"");

	}
	public void printCandidateInformation()
	{
		int percentageScoreTotal = 0;
		System.out.println(allignMiddle("[Personal details]", 40));
		System.out.println("Candidate\t\t"+candidateCode+"\nName\t\t\t"+name);
		System.out.println("Category\t\t"+(diplomaCategory.equals(CERTIFICATE)?"Course":"Diploma"));
		System.out.println("Birth Year\t\t"+birthYear+"\n");

		System.out.println(allignMiddle("[Subject details]", 80));
		System.out.println("\t\t{Grade}\t\t\t{Subject}");
		for (int i=0; i<subjectCount; i++)
			if (subjectsTaken[i].isRealSubject){
				System.out.println(subjectsTaken[i]);
				percentageScoreTotal+=subjectsTaken[i].percentageScore;
			}
		System.out.println("");
		for (int i=0; i<subjectCount; i++)
			if (!subjectsTaken[i].isRealSubject)
				System.out.println(subjectsTaken[i]);
		if (finalGrade>0)
		{
			System.out.println("\n"+allignMiddle("======<"+diplomaCategory+">======",80));
			System.out.println(allignMiddle("======< Total Points = "+(finalGrade-bonusPoints)+" (+"+(bonusPoints)+") >======",80));
			System.out.println(allignMiddle("======< Precise Mark = "+preciseMark+" >======",80));
			System.out.println("raw score total = "+percentageScoreTotal+"/600");
			System.out.println(allignLeft("True rank",20)+preciseMarkRanking+"/"+187+" (top "+round((preciseMarkRanking/187.0)*100,1)+"%)");
			System.out.println(allignLeft("Total points rank",20)+ranking+"/"+187+" at most (top "+(int)((ranking/187.0)*100)+"%)");
			System.out.println(allignLeft("Estimated difficulty index",20)+difficultyIndex);//+"   difficulty index ranking = "+rankingWithSubjectDifficulty);
			System.out.println("\n");
		}
	}
	public void calculateRankingBySubject(Candidate[] candidates, int candidateCount)
	{
		for (int i=0; i<subjectCount; i++)
			subjectsTaken[i].calculateRanking(candidates, candidateCount);
	}

	public void calculateCandidateRanking(Candidate[] candidates, int candidateCount)
	{
		int[] scoreList = new int[candidateCount];
		for (int i=0; i<candidateCount; i++)
			scoreList[i] = candidates[i].finalGrade;
		scoreList = sort(scoreList,candidateCount,1);
		ranking=search(scoreList,this.finalGrade)+1;
	}
	public void calculateCandidateRankingExcludeBonusPoints(Candidate[] candidates, int candidateCount)
	{
		int[] scoreList = new int[candidateCount];
		for (int i=0; i<candidateCount; i++)
			scoreList[i] = candidates[i].finalGrade-candidates[i].bonusPoints;
		scoreList = sort(scoreList,candidateCount,1);
		rankingExcludeBonus=search(scoreList,this.finalGrade-this.bonusPoints)+1;
	}
	public void calculateTrueRankings(Candidate[] candidates, int candidateCount)
	{
		int[] scoreList = new int[candidateCount];
		for (int i=0; i<candidateCount; i++)
			scoreList[i] = (int)(candidates[i].preciseMark*1000);
		scoreList = sort(scoreList,candidateCount,1);
		preciseMarkRanking=search(scoreList,(int)(this.preciseMark*1000))+1;
	}
	public void computeAllSubjectDiffucultyIndex(Candidate[] candidates, int candidateCount)
	{
		int sum=0;
		for (int i=0; i<subjectCount; i++)
			if (!(subjectsTaken[i].name).contains("THEORY"))
				sum+=Subject.printAllCandidatesBySubject(candidates, candidateCount, subjectsTaken[i].name, false);
		difficultyIndex=sum;
	}
	public void computeRankingsWithSubjectDifficulty(Candidate[] candidates, int candidateCount)
	{
		int[] scoreList = new int[candidateCount];
		for (int i=0; i<candidateCount; i++)
			scoreList[i] = (int)(((double)candidates[i].difficultyIndex)*1000);
		scoreList = sort(scoreList,candidateCount,1);
		rankingWithSubjectDifficulty=search(scoreList,(int)(((double)this.difficultyIndex)*1000))+1;
	}
}
