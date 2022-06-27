package test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;

import org.junit.Test;

import base.Folder;
import base.Note;
import base.NoteBook;
import base.TextNote;

public class JUnitTest {

	@Test
	public void testSearchNote() {
		NoteBook nb = new NoteBook();
		nb.createTextNote("Note1", "Java", "comp3021");
		nb.createTextNote("Note2", "Assignment", "due on 2016-10-16");
		nb.createTextNote("Note3", "lab","need to attend weekly");
		nb.createTextNote("Note4", "lab4","testing");
		List<Note> notes = nb.searchNotes("java or DUE or testing");
		System.out.println(notes.size());
		assertEquals("The size of the search results is not match", 3, notes.size(), 0.0);
		HashSet<String> titles = new HashSet<String>();
		for (Note note : notes) {
			titles.add(note.getTitle());
		}
		HashSet<String> expectedOutputs = new HashSet<String>();
		expectedOutputs.add("Java");
		expectedOutputs.add("Assignment");
		expectedOutputs.add("lab4");
		assertEquals("The search results is not match", expectedOutputs, titles);
	}
	
	// To do
	// Design the second test case which reveals the bug in function unknownFunction()
	
	@Test
	public void testCountLetter() {
		NoteBook nb = new NoteBook();

		nb.createTextNote("Note1", "a", "b");

		List<Note> notes = nb.getFolders().get(0).getNotes();
		
		char c = ((TextNote) notes.get(0)).countLetters();
		System.out.println(c);
		assertEquals("Either 'a' or 'b' should come up... ", 'a', c);
		
	}


}