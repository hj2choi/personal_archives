package base;

import java.util.ArrayList;
import java.util.Collections;
import java.io.*;

public class Folder implements Comparable<Folder>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Note> notes;
	private String name;

	public Folder(String name) {
		this.name = name;
		notes = new ArrayList<Note>();
	}

	public void addNote(Note note) {
		notes.add(note);
	}

	public String getName() {
		return this.name;
	}

	public ArrayList<Note> getNotes() {
		return notes;
	}

	public boolean equals(Folder other) {
		return this.name.equals(other.getName());
	}

	public String toString() {
		int nText = 0;
		int nImage = 0;
		
		for (int i = 0; i < notes.size(); i++){
			if (notes.get(i) instanceof TextNote){
				nText += 1;
			} else if (notes.get(i) instanceof ImageNote){
				nImage += 1;
			}
		}
		
		return name + ":" + nText + ":" + nImage;
	}

	public void sortNotes() {
		Collections.sort(notes);
	}

	public int compareTo(Folder o) {
		if (this.name.length() < o.getName().length()) {
			return -1;
		}
		return 1;
	}

	private boolean noteContainsKeyword(Note n, String keyword) {
		// imageNote
		String noteTitle = n.getTitle().toLowerCase();
		if (n instanceof ImageNote) {
			if (!noteTitle.contains(keyword)) {
				return false;
			}
		}
		// textNote
		else {
			String noteContent = ((TextNote)n).getContent().toLowerCase();
			if (!(noteTitle.contains(keyword)|| noteContent.contains(keyword))) {
				return false;
			}
		}
		return true;
	}

	public ArrayList<Note> searchNotes(String keywords) {
		ArrayList<Note> result = new ArrayList<Note>();

		String[] keywordList = keywords.split(" ");
		for (int j=0; j<notes.size(); ++j) {

			boolean searchFlag = true;
			boolean orClauseFlag=false;
			for (int i=0; i<keywordList.length; ++i) {
				String term = keywordList[i].toLowerCase();
				//System.out.print(term+" ");
				// detect OR case
				
				if (i<keywordList.length-1 && keywordList[i+1].toLowerCase().equals("or")) {
					//System.out.println("OR CASE");
					// is already true in the sequence of or clause
					if (orClauseFlag && searchFlag) {
						//System.out.println("OR true carried");
						searchFlag=true;
					}
					else {
						orClauseFlag=true;
						searchFlag = true;
						String term2 = keywordList[i+2].toLowerCase();
						if (!(noteContainsKeyword(notes.get(j), term) || noteContainsKeyword(notes.get(j), term2))) {
							searchFlag = false;
						}
					}
					i=i+1;
					if (i+1==keywordList.length-1) {
						break;
					}
				}
				// AND case
				else {
					orClauseFlag=false;
					//System.out.println("AND CASE");
					searchFlag = noteContainsKeyword(notes.get(j), term);
					if (!searchFlag) {
						break;
					}
				}
			}
			if (searchFlag) {
				//System.out.println("====SEARCH TERM FOUND====");
				result.add(notes.get(j));
			}
		}

		return result;
	}

}
