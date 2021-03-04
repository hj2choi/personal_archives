package base;

import java.util.Date;
import java.io.*;

public class Note implements Comparable<Note>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date date;
	private String title;

	public Note(String title) {
		this.title = title;
		date = new Date();
	}

	public String toString() {
		return date.toString() + "\t" + title;
	}

	public String getTitle() {
		return title;
	}

	public Date getDate() {
		return date;
	}

	public boolean equals(Note other) {
		return this.title.equals(other.title);
	}

	public int compareTo(Note o) {
		if (this.date.after(o.getDate())) {
			return -1;
		}
		return 1;
	}

	public String getContent() {
		// TODO Auto-generated method stub
		return null;
	}

}
