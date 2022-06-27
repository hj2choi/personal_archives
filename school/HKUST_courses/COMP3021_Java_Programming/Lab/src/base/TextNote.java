package base;

import java.io.*;
import java.util.HashMap;

public class TextNote extends Note implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String content;

	public TextNote(String title) {
		super(title);
	}

	public String getContent() {
		return content;
	}

	public TextNote(String title, String content) {
		super(title);
		this.content = content;
	}

	public TextNote(File f) {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}

	private String getTextFromFile(String absolutePath) {
		String result="";
		File file = new File(absolutePath);
		FileInputStream fis = null;
		BufferedReader reader = null;

		//TODO
		try {
			fis = new FileInputStream(file);
			reader = new BufferedReader(new InputStreamReader(fis));
			while ((result = reader.readLine()) != null) {
				System.out.println(result);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		} catch (IOException e) {
			e.printStackTrace();
		}


		return result;
	}
	
	public Character countLetters(){
		HashMap<Character,Integer> count = new HashMap<Character,Integer>();
		String a = this.getTitle() + this.getContent();
		int b = 0;
		Character r = ' ';
		for (int i = 0; i < a.length(); i++) {
			Character c = a.charAt(i);
			if (c <= 'Z' && c >= 'A' || c <= 'z' && c >= 'a') {
				if (!count.containsKey(c)) {
					count.put(c, 1);
				} else {
					count.put(c, count.get(c) + 1);
					if (count.get(c) > b) {
						b = count.get(c);
						r = c;
					}
				}
			}
		}
		return r;
	}

	public void exportTextToFile(String pathFolder) {
		//TODO
		//File file = new File(pathFolder + File.separator + ""/*TODO*/	+ ".txt");
		//TODO
		BufferedWriter bw = null;
		try{
		File file = new File(pathFolder + this.getTitle().replaceAll("\\s+", "_") + ".txt");
			if(!file.exists()){
				file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);

			bw.write(this.content);
			bw.close();
			
			
		} catch (IOException ioE){
			ioE.printStackTrace();
		}
	}



}
