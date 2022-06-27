   package mars.mips.instructions.syscalls; 
   import mars.util.*;
   import mars.mips.hardware.*;
	import mars.simulator.*;
   import mars.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.*;
import java.util.*;
import javax.imageio.*;



/**
 * Service to create a game
 */

    public class SyscallGame extends AbstractSyscall {
	public class GameObject {
		public int id = -1;  // unique non-negative ID of a game object 
		public int x = -1, y = -1;  // x- and y-cooridnates
		public int imgIndex = -1; // index to game image array for object image, -1 for no image
		public String str = null;  // (x,y) is for string's baseline
		public Integer intNum = null;
		public Color color = Color.black;
		public Font font = new Font(null, Font.PLAIN, 16); // font for text drawing

		public GameObject(int id) {
			this.id = id;
		}


		void reset() {
			imgIndex = -1;
			str = null;
			intNum = null;
		}

		public void setString(String str){
			reset();
			this.str = str;
			
		}

		public void setIntNum(int num){
			reset();
			intNum = new Integer(num);
		}

		public void setColor(int color) throws Exception {
			try {
				this.color = new Color(color);
			} catch (Exception e) {
				throw new Exception("\nGame call error: invalid color 0x" + Integer.toHexString(color) + "!\n");
                   	
			}
		}

		public void setFont(String name, int size, boolean useBold, boolean useItalic) throws Exception {
			if (size <= 0)
				throw new Exception("\nGame call error: invalid font size " + size + "!\n");
 
			int style = Font.PLAIN;
			if (useBold && useItalic)
				style = Font.BOLD | Font.ITALIC;
			else if (useBold)
				style = Font.BOLD;
			else if (useItalic)
				style = Font.ITALIC;

                  	
			try {
				
				font = new Font(name, style, size);
			} catch (Exception e) {
				throw new Exception("\nGame call error: invalid font size " + size + "!\n");
                   	
			}
		}

		public void setImageIndex(int index) {
			reset();
			imgIndex = index;
		}

		public void setLocation(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public void paint(Graphics g, BufferedImage[] images) throws Exception {
			try {
				if (imgIndex >= 0 && images[imgIndex] != null)
					g.drawImage(images[imgIndex], x, y, null);
			} catch (Exception e) {
				
				throw new Exception("\nGame call error: invalid image index (" + imgIndex + ") of game object ID " + id + "!\n");
                     		                  		
			}

			if (str != null && str.length() > 0){
				g.setColor(color);
				g.setFont(font);
				g.drawString(str, x, y);
			}
		        if (intNum != null){
				g.setColor(color);
				g.setFont(font);
				g.drawString("" + intNum.intValue(), x, y);
			}
   		 }

	}


	public class GameScreen extends JPanel {
		BufferedImage offscreenImg = null; 
		public Vector objects = new Vector();
		BufferedImage backgroundImg = null;
		int width, height;
		String title = "";
		JFrame frame = null;
		int imageNum = 0;
		BufferedImage[] images = null;
   		public boolean inError = false; 
		MMIOInput keyListener = null;
		public boolean isClosed = false;
 
		public GameScreen(int width, int height, String title, String backgroundImageName, MMIOInput keyListener) throws Exception {
   			this.width = width;
			this.height = height;
			this.title = title;
			Dimension dim = new Dimension(width, height);
			setPreferredSize(dim);
			//setMinimumSize(dim); // not needed these but only preferred size
			//setMaximumSize(dim);
			setFocusable(true); // so that it can get key events
			this.keyListener = keyListener;
			addKeyListener(keyListener);
			
			try {
				offscreenImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
				
			} catch (Exception e) {
				setErrorState();
				throw new Exception("\nGame call error: invalid game screen size (width=" + width + ", height=" + height + ") or cannot create game for other reasons!\n");
			}

			try {
				backgroundImg = ImageIO.read(new File(backgroundImageName));
			} catch (Exception e) {
				setErrorState();
				throw new Exception("\nGame call error: invalid background image \"" + backgroundImageName + "\"!\n");
                     		
			}
		}

		public void createScreen() throws Exception {
		        frame = new JFrame(title);
		        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        frame.setContentPane(this);
		        frame.setLocationRelativeTo(null);
			frame.setResizable(false);
			requestFocus();
			frame.pack();
			redrawOffscreen();
			frame.addWindowListener(new WindowAdapter(){public void windowClosed(WindowEvent e){isClosed = true; removeKeyListener(keyListener); keyListener.destroy();}} );
        		frame.setVisible(true);	
		}

		
		public void addObject(Object object){
			objects.addElement(object);
		}
 

		public Object getObject(int index) throws Exception {
			try {
				return objects.elementAt(index);
			} catch (Exception e) {
				setErrorState();
				throw new Exception("\nGame call error: invalid game object ID " + index + "!\n");
                     		
			}
		}


		public void createObjects(int numObj) throws Exception {
			if (numObj <= 0) {
				setErrorState();
				throw new Exception("\nGame call error: invalid number (" + numObj + ") of game objects to create!\n");           
			}
			
			objects.clear();
			for (int i = 0; i < numObj; i++){
				addObject(new GameObject(i));
			}

		}


		public void setImages(String[] imageNames) throws Exception {
			if (imageNames == null)
				return;

			imageNum = imageNames.length;
			images = new BufferedImage[imageNum];
			for (int i = 0; i < imageNum; i++)
			try {
				images[i] = ImageIO.read(new File(imageNames[i]));
			} catch (Exception e) {
				setErrorState();
				throw new Exception("\nGame call error: invalid game object image \"" + imageNames[i] + "\"!\n");
                     		                  		
			}
		}


    		public synchronized void paintComponent(Graphics g) {

		        g.drawImage(offscreenImg, 0, 0, null);
   		 }


		public synchronized void redrawOffscreen() throws Exception {
			Graphics2D g2d = offscreenImg.createGraphics();
		        super.paintComponent(g2d); 
			if (backgroundImg != null)
				g2d.drawImage(backgroundImg, 0, 0, null);
			else {
				g2d.setColor(Color.BLACK);
				g2d.fillRect(0, 0, width, height);
			}

			int size = objects.size(); 
			for (int i = 0; i < size; i++) {
				GameObject obj = (GameObject) objects.elementAt(i);
				obj.paint(g2d, images);
			}

		}


		public void destroy(){
			
			frame.dispose();
		}

		public synchronized void setErrorState() {
			if (inError)
				return;
 
			inError = true;
			removeKeyListener(keyListener);
			keyListener.destroy();
		}
	}



   GameScreen gameScreen = null;
   
   MMIOInput mmioInput = null;

   /**
    * Build an instance of the syscall with its default service number and name.
    */
       public SyscallGame() {
         super(100, "Game");

      }


	public String getStrFromMIPS(int baseAddress) throws Exception {
		if (baseAddress == 0) {
			throw new Exception("\nGame call error: invalid base address (0x" + Integer.toHexString(baseAddress) + ") of the string!\n");
                   	
		}


		try {

			Memory mem = Globals.memory;
			char ch = '\0';
			String str = "";
			ch = (char) (mem.getByte(baseAddress) & 0xFF);
			baseAddress++;
			while (ch != '\0') {
				str += ch;
				ch = (char) (mem.getByte(baseAddress) & 0xFF);
				baseAddress++;
			}

			return str;

		} catch (Exception e) {
			throw new Exception("\nGame call error: invalid byte address (0x" + Integer.toHexString(baseAddress) + ") occupied by a character of the string!\n");
                   	
		}

	}


	public String[] getStrArrayFromMIPS(int size, int baseAddress) throws Exception {
		if (baseAddress == 0) {
			throw new Exception("\nGame call error: invalid base address (0x" + Integer.toHexString(baseAddress) + ") of the array of strings!\n");
                   	
		}

		if (size <= 0) {
			throw new Exception("\nGame call error: invalid size (" + size + ") of the array of strings!\n");
                   	
		}

		try {
			
			Memory mem = Globals.memory;
			String[] strs = new String[size];
			for (int i = 0; i < size; i++){
				strs[i] = getStrFromMIPS(mem.get(baseAddress, Memory.WORD_LENGTH_BYTES));
				baseAddress += 4;
			}

			return strs;

		} catch (Exception e) {
			throw new Exception("\nGame call error: invalid word address (0x" + Integer.toHexString(baseAddress) + ") occupied by an element of the string array!\n");
                   	
		}


	}			
	 

       public void simulate(ProgramStatement statement) throws ProcessingException {
          // Input arguments: $a0 is game action code.
		int gameAction = 0;

               try
               {
		  gameAction = RegisterFile.getValue(4);

		  if (gameScreen == null && gameAction != 1)
		  	throw new Exception("\nGame call error: no call has been made to create a game yet!\n");	

		  if (gameScreen != null && gameScreen.inError &&  gameAction != 1)
			return;
		  
		  if (gameScreen != null && gameScreen.isClosed && gameAction != 1)
		  	throw new Exception("\nGame call error: game screen is closed during the game!\n");	


                  switch (gameAction){
			case 1: // create game 
			if (gameScreen != null)
				gameScreen.destroy();
			mmioInput = new MMIOInput(true);  
			gameScreen = new GameScreen(RegisterFile.getValue(5),
                    	RegisterFile.getValue(6), getStrFromMIPS(RegisterFile.getValue(7)),
	                getStrFromMIPS(RegisterFile.getValue(8)), mmioInput);
			break;

                  	case 2: // create game objects
			gameScreen.createObjects(RegisterFile.getValue(5));
			break;

			case 3: // set images of a game
			gameScreen.setImages(getStrArrayFromMIPS(RegisterFile.getValue(5), RegisterFile.getValue(6)));
			break;
                    
			case 4: // create and show the game screen
			gameScreen.createScreen();
			break;

			case 5: // redraw the game screen
			gameScreen.redrawOffscreen();
			gameScreen.repaint();
			break;

			case 6: // destroy game
			gameScreen.destroy();
			break;

			case 11: // set image index of a game object
			((GameObject) gameScreen.getObject(RegisterFile.getValue(5))).setImageIndex(RegisterFile.getValue(6));
			break;

			case 12: // set location of a game object
			((GameObject) gameScreen.getObject(RegisterFile.getValue(5))).setLocation(RegisterFile.getValue(6), RegisterFile.getValue(7));
			break;

			case 13: // set string of a game object
			((GameObject) gameScreen.getObject(RegisterFile.getValue(5))).setString(getStrFromMIPS(RegisterFile.getValue(6)));
			break;

			case 14: // set integer of a game object
			((GameObject) gameScreen.getObject(RegisterFile.getValue(5))).setIntNum(RegisterFile.getValue(6));
			break;

			case 15: // set color of a game object
			((GameObject) gameScreen.getObject(RegisterFile.getValue(5))).setColor(RegisterFile.getValue(6));
			break;

			case 16: // set font of a game object
			((GameObject) gameScreen.getObject(RegisterFile.getValue(5))).setFont(null, RegisterFile.getValue(6), RegisterFile.getValue(7) != 0, RegisterFile.getValue(8) != 0);
			break;
                  }

               }
               catch (Exception e)
               {
                   SystemIO.printString(e.getMessage());
		   if (gameAction == 1)
			gameScreen = null;
                   else if (gameScreen != null)
			gameScreen.setErrorState();
		  
		   throw new ProcessingException();
               }
       }

   }
