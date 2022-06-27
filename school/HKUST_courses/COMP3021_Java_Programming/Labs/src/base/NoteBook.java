package base;

import java.util.ArrayList;
import java.util.Collections;
import java.io.*;

public class NoteBook implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Folder> folders;
	public NoteBook() {
		folders = new ArrayList<Folder>();
	}

	public NoteBook(String file) {
		//TODO
		// How to load object in memory from file
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try{
			fis = new FileInputStream(file);
			in = new ObjectInputStream(fis);
			NoteBook n = (NoteBook) in.readObject();
			this.folders = n.getFolders();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//TODO
	}

	public boolean createTextNote(String folderName, String title) {
		TextNote note = new TextNote(title);
		return insertNote(folderName, note);
	}

	public boolean createTextNote(String folderName, String title, String content) {
		TextNote note = new TextNote(title, content);
		return insertNote(folderName, note);
	}

	public boolean createImageNote(String folderName, String title) {
		ImageNote note = new ImageNote(title);
		return insertNote(folderName, note);
	}


	public ArrayList<Folder> getFolders() {
		return folders;
	}

	public boolean insertNote(String folderName, Note note) {
		Folder targetFolder = new Folder(folderName);

		//STEP 1
		boolean folderExists=false;
		for (int i=0; i<folders.size(); ++i) {
			if (folders.get(i).equals(targetFolder)) {
				targetFolder = folders.get(i);
				folderExists=true;
				break;
			}
		}
		if (!folderExists) {
			targetFolder = new Folder(folderName);
			folders.add(targetFolder);
		}

		// STEP 2
		for (int i=0; i<targetFolder.getNotes().size(); ++i) {
			if (targetFolder.getNotes().get(i).equals(note)) {
				System.out.println("Creating note "+note.getTitle() + " under folder "+ folderName + " failed");
				return false;
			}
		}

		// STEP 3
		targetFolder.addNote(note);
		return true;
	}

	public void sortFolders() {
		for (int i=0; i<folders.size(); ++i) {
			folders.get(i).sortNotes();
		}
		Collections.sort(folders);
	}

	public ArrayList<Note> searchNotes(String keywords) {
		ArrayList<Note> result = new ArrayList<Note>();
		for (int i=0; i<folders.size(); ++i) {
			result.addAll(folders.get(i).searchNotes(keywords));
		}
		return result;
	}

	public boolean save(String file) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try{
			fos = new FileOutputStream(file);
			out = new ObjectOutputStream(fos);
			out.writeObject(this);
			out.close();
		} catch (Exception e){
			return false;
		}
		return true;
	}

}
