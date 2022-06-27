package base;

import java.io.File;
import java.io.*;

public class ImageNote extends Note implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File image;

	public ImageNote(String title) {
		super(title);
	}



}
