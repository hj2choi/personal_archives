import java.io.*;
class FinalGradesView extends HongJoonUtilities
{
	static int fileLength=0;
	static Candidate[] candidates = new Candidate[1000];
	static int candidateCount=-1;

	public static void main(String[] args) throws IOException
	{
		System.out.println("IB diploma May session, final results. OFS graduating class of 2013.");
		extractInformation();
		extractPercentageScore();
		extractPreciseMark();
		processSubjectData();
		processCandidateData();
		System.out.println("Candidates count = "+candidateCount);

		while (true)
		{
			System.out.println();
			System.out.println("[1] Search by name              [2] Search by subject			   [3] Others");
			switch (inputInt(""))
			{
			case 1:
				searchByName();
				break;
			case 2:
				searchBySubject();
				break;
			case 3:
				otherStatistics();
				break;
			}
		}
		
	}

	public static void searchByName()
	{
		System.out.println("Enter name to search");
		int[] indexList = smartSearch(input(""), candidates, candidateCount);
		for (int i=0; i<indexList.length; i++)
			System.out.println(allignLeft("["+(i+1)+"]",6)+candidates[indexList[i]]);
		System.out.println("\n"+indexList.length+" names searched.");
		if (indexList.length>0)
		{
			System.out.println("Enter number respective to the name to view detailes.");
			try
			{
				candidates[indexList[inputInt("")-1]].printCandidateInformation();
			}
			catch (Exception e)
			{
			}
		}
	}
	
	public static void searchBySubject()
	{
		System.out.println("Enter subject name to search");
		int[] indexList = smartSearch(input(""), Subject.subjectList, Subject.subjectCount);
		for (int i=0; i<indexList.length; i++)
			System.out.println(allignLeft("["+(i+1)+"]",6)+Subject.subjectList[indexList[i]]);
		System.out.println("\n"+indexList.length+" subjects searched.");
		if (indexList.length>0)
		{
			System.out.println("Enter number respective to the subject to view.");
			try
			{
				Subject.printAllCandidatesBySubject(candidates, candidateCount, Subject.subjectList[indexList[inputInt("")-1]], true);
			}
			catch (Exception e)
			{
			}
		}
	}

	public static void otherStatistics()
	{
		System.out.println("[1] year born");
		System.out.println("[2] number of ppl getting diploma");
		System.out.println("[3] general IB statistics");
		System.out.println("[4] IB final grade distribution");
		System.out.println("[5] IB bonus points distribution");
		System.out.println("[6] preciseMark distribution");
		System.out.println("[7] sort candidates by rankings");
		System.out.println("Enter number to begin");
		int input=inputInt("");


		if (input==1)
		{
			int[] year = new int[2000];
			for (int i=0; i<candidateCount; i++)
				year[candidates[i].birthYear]++;
			for (int i=1993; i<1997; i++)
				System.out.println("no. of ppl with Birth year ("+i+") = "+allignLeft(year[i]+"",4)+barGraph(year[i]/2));
		}
		if (input==2)
		{
			int certificateCount=0;
			int notAwardedCount=0;
			int diplomaCount=0;
			int bilingualCount=0;
			for (int i=0; i<candidateCount; i++)
			{
				if ((candidates[i].diplomaCategory).equals(Candidate.CERTIFICATE))
					certificateCount++;
				if ((candidates[i].diplomaCategory).equals(Candidate.DIPLOMA_NOT_AWARDED))
					notAwardedCount++;
				if ((candidates[i].diplomaCategory).equals(Candidate.DIPLOMA_AWARDED))
					diplomaCount++;
				if ((candidates[i].diplomaCategory).equals(Candidate.BILINGUAL_DIPLOMA_AWARDED))
					bilingualCount++;
			}
			System.out.println("certificate count = "+certificateCount);
			System.out.println("Diploma not awarded count = "+notAwardedCount);
			System.out.println("diploma count = "+diplomaCount);
			System.out.println("bilingual diploma count = "+bilingualCount);
		}
		if (input==3)
		{
			double final_mean;
			double final_stDev;
			double preciseMark_mean;
			double preciseMark_stDev;
			double bonus_mean;
			double bonus_stDev;
			double summision=0;

			for (int i=0; i<candidateCount; i++)
				summision+=candidates[i].finalGrade;
			final_mean=summision/185;
			summision=0;
			for (int i=0; i<candidateCount; i++)
				if (candidates[i].finalGrade!=0)
					summision+=(final_mean-candidates[i].finalGrade)*(final_mean-candidates[i].finalGrade);
			final_stDev=Math.sqrt(summision/185);
			summision=0;
			for (int i=0; i<candidateCount; i++)
				summision+=candidates[i].preciseMark;
			preciseMark_mean=summision/185;
			summision=0;
			for (int i=0; i<candidateCount; i++)
				if (candidates[i].preciseMark!=0)
					summision+=(preciseMark_mean-candidates[i].preciseMark)*(preciseMark_mean-candidates[i].preciseMark);
			preciseMark_stDev=Math.sqrt(summision/185);
			summision=0;
			for (int i=0; i<candidateCount; i++)
				summision+=candidates[i].bonusPoints;
			bonus_mean=summision/185;
			summision=0;
			for (int i=0; i<candidateCount; i++)
				if (!(candidates[i].diplomaCategory).equals(Candidate.CERTIFICATE))
					summision+=(bonus_mean-candidates[i].bonusPoints)*(bonus_mean-candidates[i].bonusPoints);
			bonus_stDev=Math.sqrt(summision/185);

			System.out.println("[final grade]       mean="+allignLeft(round(final_mean,2)+"",8)+"stDev="+round(final_stDev,2));
			System.out.println("[preciseMark]       mean="+allignLeft(round(preciseMark_mean,2)+"",8)+"stDev="+round(preciseMark_stDev,2));
			System.out.println("[bonus point]       mean="+allignLeft(round(bonus_mean,2)+"",8)+"stDev="+round(bonus_stDev,2));
		}
		if (input==4)
		{
			int[] gradeCount = new int[46];
			for (int i=0; i<candidateCount; i++)
				gradeCount[candidates[i].finalGrade]++;
			for (int i=45; i>12; i--)
				System.out.println("total points ("+i+") = "+allignLeft(gradeCount[i]+"",4)+barGraph(gradeCount[i]));
		}
		if (input==5)
		{
			int[] gradeCount = new int[4];
			for (int i=0; i<candidateCount; i++)
					gradeCount[candidates[i].bonusPoints]++;
			for (int i=3; i>=0; i--)
				System.out.println("bonus points ("+i+") = "+allignLeft(gradeCount[i]+"",4)+barGraph(gradeCount[i]));
		}
		if (input==6)
		{
			int[] gradeCount = new int[50];
			for (int i=0; i<candidateCount; i++)
					gradeCount[(int)candidates[i].preciseMark]++;
			for (int i=47; i>=14; i--)
				System.out.println("precise mark ("+i+") = "+allignLeft(gradeCount[i]+"",4)+barGraph(gradeCount[i]));
		}
		if (input==7)
		{
			Candidate[] gradeCount = new Candidate[250];
			for (int i=0; i<candidateCount; i++)
					gradeCount[(int)candidates[i].preciseMarkRanking]=candidates[i];
			for (int i=1; i<188; i++)
				System.out.println(allignLeft("rank ("+i+") = ",15)+allignLeft(gradeCount[i]+"",80));
			System.out.println("Enter number respective to the name to view.");
			try
			{
				gradeCount[inputInt("")].printCandidateInformation();
			}
			catch (Exception e)
			{
			}
		}

	}

	public static void extractInformation() throws IOException
	{
		fileLength=0;
		String[] file = fileReader("database.txt");
		System.out.println("rcv length = "+fileLength);
		
		for (int i=0; i<fileLength; i++)
		{
			if (file[i].equals("International Baccalaureate Organisation")) // extract information from file into Candidate class
			{	
				candidateCount++;

				String candidateCode="";
				String name="";
				String[] subjects= new String[20];
				int bonusPoints = 0;
				int finalGrade=0;
				int subjectCount=0;
				String diplomaCategory="";
				int birthYear=0;
				
				i+=10;
				String[] extractedInfo = file[++i].split("- ");
				candidateCode = extractedInfo[1];
				name = file[++i];
				extractedInfo = file[i+2].split(" ");
				birthYear=Integer.parseInt(extractedInfo[2]);
				i+=5;
				char[] grades=file[i].toCharArray();
				subjectCount=grades.length;
				for (int j=0; j<subjectCount; j++)
				{
					extractedInfo = file[++i].split("- ");
					subjects[j] = extractedInfo[1];
				}
				if (file[i+1].startsWith("EE/TOK"))	//for DIPLOMA course students
				{
					diplomaCategory=Candidate.DIPLOMA_AWARDED;
					i+=3;
					bonusPoints=Integer.parseInt(file[++i]);
					finalGrade=Integer.parseInt(file[++i]);
					if (file[++i].equals("Bilingual Diploma awarded"))
						diplomaCategory=Candidate.BILINGUAL_DIPLOMA_AWARDED;
					if (file[++i].equals("Diploma requirements code:"))
						diplomaCategory=Candidate.DIPLOMA_NOT_AWARDED;
				}
				candidates[candidateCount] = new Candidate(candidateCode, name, subjects, grades, bonusPoints, finalGrade, subjectCount, diplomaCategory, birthYear);
			}
		}


	}
	public static void extractPreciseMark() throws IOException
	{
		fileLength=0;
		String[] file = fileReader("preciseMark.txt");
		System.out.println("rcv length = "+fileLength);
		for (int i=0; i<fileLength; i++)
		{
			for (int j=0; j<candidateCount; j++)
			{
				String[] fileBreakDown = file[i].split(" ");
				String name=fileBreakDown[2];
				for (int k=3; k<fileBreakDown.length; k++)
					name+=" "+fileBreakDown[k];
				if (candidates[j].name.equals(name))
					candidates[j].setPreciseMark(Double.parseDouble(fileBreakDown[1]));
			}
			
		}

	}
	public static void extractPercentageScore() throws IOException
	{
		int subjectIndex=3;
		fileLength=0;
		String[] file = fileReader("detailedInformation.txt");
		System.out.println("rcv length= "+fileLength);
		
		String currentSubject="";
		
		for (int i=0; i<fileLength; i++)
		{
			if (currentSubject.startsWith("extended"))
			{
				extractPercentageScoreExceptionEE(i, file[i], currentSubject);
				continue;
			}

			int percentageGrade=0;
			try		//update currentSubject
			{
				int a=Integer.parseInt(file[i].split(" ")[1]);
			}
			catch (Exception e)
			{
				String[] ss = file[i].split((subjectIndex)+" ");
				if (ss.length<2)
					continue;
				currentSubject=ss[1];
				i++;
				subjectIndex++;
				continue;
			}
			try		//append percentageGrade
			{
				int[] currentSubjectIndex = searchForSubjectIndex(currentSubject, searchForCandidateIndex(file[i].substring(5)));
				percentageGrade=Integer.parseInt(file[i].substring(2,4));
				candidates[currentSubjectIndex[0]].subjectsTaken[currentSubjectIndex[1]].setPercentageScore(percentageGrade);
			}
			catch (Exception e)
			{
			}
			
		}
		
		
	}

	public static void extractPercentageScoreExceptionEE(int i, String currentFile, String currentSubject)
	{
		int nameIndex=0;
		int percentageGrade=0;
		String[] fileBreakDown = currentFile.split(" ");
		for (int a=0; a<fileBreakDown.length; a++)
		{
			try{ percentageGrade=Integer.parseInt(fileBreakDown[a]);nameIndex=a+1;}
			catch (Exception e){}
		}
		String name="";
		try{name=fileBreakDown[nameIndex];}
		catch (Exception e){}
		for (int k=nameIndex+1; k<fileBreakDown.length; k++)
			name+=" "+fileBreakDown[k];
		try		//append percentageGrade
		{
			int[] currentSubjectIndex = searchForSubjectIndex(currentSubject, searchForCandidateIndex(name));
			candidates[currentSubjectIndex[0]].subjectsTaken[currentSubjectIndex[1]].setPercentageScore(percentageGrade);
		}
		catch (Exception e)
		{
		}
		
	}

	public static void processSubjectData()
	{
		boolean flag=false;
		for (int i=0; i<candidateCount; i++)
			for (int j=0; j<candidates[i].subjectCount; j++)
			{
				flag=true;
				for (int k=0; k<Subject.subjectCount; k++)
					if ((candidates[i].subjectsTaken[j].name).equals(Subject.subjectList[k]))
						flag=false;
				if (flag)
					Subject.subjectList[Subject.subjectCount++]=candidates[i].subjectsTaken[j].name;
			}
		System.out.println("Subjects count = "+Subject.subjectCount);
	}
	
	public static void processCandidateData()
	{
		for (int i=0; i<candidateCount; i++)
		{
			candidates[i].calculateRankingBySubject(candidates, candidateCount);
			candidates[i].calculateCandidateRanking(candidates, candidateCount);
			candidates[i].calculateCandidateRankingExcludeBonusPoints(candidates, candidateCount);
			candidates[i].calculateTrueRankings(candidates, candidateCount);
			candidates[i].computeAllSubjectDiffucultyIndex(candidates, candidateCount);
			candidates[i].computeRankingsWithSubjectDifficulty(candidates, candidateCount);
		}
	}
	public static String[] fileReader(String fileName) throws IOException		// returns array with loaded data of file (String filename)
	{
		String[] file = new String[30000000];
		File f = new File(fileName);
		FileReader fr = new FileReader(f);
		BufferedReader in  = new BufferedReader(fr);
		while (in.ready())
			file[fileLength++] = in.readLine();
		return file;
	}

	public static int[] searchForSubjectIndex(String searchFor, int candidateIndex)// return[0]:index of candidate, return[1]:index of subject in candidate
	{
		searchFor=searchFor.split(" in ")[0];
		int[] index=new int[2];
		for (int j=0; j<candidates[candidateIndex].subjectCount; j++)
		{
			String subject = (candidates[candidateIndex].subjectsTaken[j].name);
			if (subject.contains(searchFor.substring(0,4)))
				if ((subject.contains("HL") && searchFor.contains("HL")) || (subject.contains("SL") && searchFor.contains("SL")))
				{
					if (subject.contains(" A") || subject.contains(" B"))
					{
						if ((subject.contains(" A") && searchFor.contains(" A")) || (subject.contains(" B") && searchFor.contains(" B")))
							index[0]=candidateIndex; index[1]=j; return index;
					}
					else
					{
						index[0]=candidateIndex; index[1]=j; return index;
					}
				}
			if (subject.contains(" EE") && searchFor.contains("extended"))
			{
				index[0]=candidateIndex; index[1]=j; return index;
			}
			if (subject.contains("THEORY") && searchFor.contains("ToK"))
			{
				index[0]=candidateIndex; index[1]=j; return index;
			}
		}
		return index;

	}

	public static int searchForCandidateIndex(String candidateName)
	{
		for (int i=0; i<candidateCount; i++)
			if ((candidates[i].name).equals(candidateName))
				return i;
		return -1;
	}
}
