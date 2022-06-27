package mars;
 
   import java.awt.*;
   import java.awt.event.*;
   import java.util.*;
   import mars.Globals;
   import mars.mips.hardware.*;
   import mars.simulator.Exceptions;
   import mars.util.*;
   
   

    public class MMIOInput implements KeyListener, Observer{

	public class MMIOThread extends Thread {
		MMIOInput mmioInput = null;
		Vector events = new Vector();
		boolean isFinish = false;
		

		public MMIOThread(MMIOInput mmioInput){
			super();
			this.mmioInput = mmioInput;
			
		}


		public synchronized void stopProcess() {
			isFinish = true;
			
		}


		public synchronized Object[] getEvent(){
			Object[] event = null;
			if (isFinish || events.size() <= 0)
				return null;
			event = new Object[2]; 
			event[0] = events.remove(0);
			if ( ((Integer) event[0]).intValue() == 1)
				event[1] = events.remove(0);
						
			return event;
		}


		public synchronized void add(int action, KeyEvent e){
			try {
				events.addElement(new Integer(action));
				if (e != null)
					events.addElement(e);
				notify();
			
			} catch (Exception ex) {
				SystemIO.printString("\nMMIO error: problems in processing input keys!\n");
			}
			
		}


		public void run() {
			try {
			Object[] event = null;
			while (!isFinish) { 
				synchronized (this) {
					event = getEvent();
					if (event == null) {
						wait(100);  // even if no events, wake up periodically to check whether isFinish is true
						event = getEvent(); // get the event immediately while holding the lock
					}					
				}

				if (event != null)
					switch ( ((Integer) event[0]).intValue() ){
						case 1: mmioInput.writeInput((KeyEvent) event[1]);
							break;
						case 2: mmioInput.clearInput();
							break;
					}
			}	
			

			} catch (Exception e){
				SystemIO.printString("\nMMIO error: problems in processing input keys!\n");
            
			}

		}
	}
 

      public int RECEIVER_CONTROL;    // keyboard Ready in low-order bit
      public int RECEIVER_DATA;       // keyboard character in low-order byte
      MMIOThread mmioThread = null;
      boolean noRepeatedKeyEvents = true;  // no repeated keyPress events of key-heldDown are processed after the second (repeated) one, then keyRelease event is processed
      int keyPressNum = 0;
      boolean acceptClearInput = true;
	
   	// Set the MMIO addresses.  Prior to MARS 3.7 these were final because
   	// MIPS address space was final as well.  Now we will get MMIO base address
   	// each time to reflect possible change in memory configuration. DPS 6-Aug-09
       public MMIOInput(boolean noRepeatedKeyEvents) {
	
         RECEIVER_CONTROL    = Memory.memoryMapBaseAddress; //0xffff0000; // keyboard Ready in low-order bit
         RECEIVER_DATA       = Memory.memoryMapBaseAddress + 4; //0xffff0004; // keyboard character in low-order byte
	 this.noRepeatedKeyEvents = noRepeatedKeyEvents; 
	 mmioThread = new MMIOThread(this);  // create memory updating thread which must be different from memory observer thread to avoid deadlock on MIPS Memory lock 
	 mmioThread.start();
	 addAsObserver();

	}

	/**
   	 *  destroy any used resources.
   	 * 
   	 */
       public void destroy() {
	 Globals.memory.deleteObserver(this);
	 mmioThread.stopProcess();

      }

      /**
   	 *  registers us as an Observer over the static data segment
   	 *  (starting address 0x10010000) only.
   	 *
   	 *  When user enters keystroke, set RECEIVER_CONTROL and RECEIVER_DATA using the action listener.
   	 *  When user loads word (lw) from RECEIVER_DATA (we are notified of the read), then clear RECEIVER_CONTROL.
   	 *  When user stores word (sw) to TRANSMITTER_DATA (we are notified of the write), then clear TRANSMITTER_CONTROL, read TRANSMITTER_DATA,
   	 *  echo the character to display, wait for delay period, then set TRANSMITTER_CONTROL.
   	 *
   	 *  If you use the inherited GUI buttons, this method is invoked when you click "Connect" button on MarsTool or the
   	 *  "Assemble and Run" button on a Mars-based app.
   	 */
       void addAsObserver() {
    	try {
         // We want to be an observer only of MIPS reads from RECEIVER_DATA and writes to TRANSMITTER_DATA.
         Globals.memory.addObserver(this, RECEIVER_DATA,RECEIVER_DATA);
	}
        catch (Exception aee) {
            SystemIO.printString("\nMMIO error: adding observer of incorrect MMIO address!\n");
            
        }
       }


      /**
   	 * Update display when connected MIPS program accesses (data) memory.
   	 * @param memory the attached memory
   	 * @param arg information provided by memory in MemoryAccessNotice object
   	 */
       public void update(Observable memory, Object arg) {

	 AccessNotice accessNotice = (AccessNotice) arg; 
         MemoryAccessNotice notice = (MemoryAccessNotice) accessNotice;
      	// If MIPS program has just read (loaded) the receiver (keyboard) data register,
      	// then clear the Ready bit to indicate there is no longer a keystroke available.
      	// If Ready bit was initially clear, they'll get the old keystroke -- serves 'em right
      	// for not checking!
         if (notice.accessIsFromMIPS() && notice.getAddress()==RECEIVER_DATA && notice.getAccessType()==AccessNotice.READ) {
		synchronized (this) {
 			if (!noRepeatedKeyEvents || acceptClearInput) // keyPressNum can be 0 depending on key event order, which is ok
	    			clearInput();		   
	    	
	    	}
         }
      }


   	


      //////////////////////////////////////////////////////////////////////////////////////
      //  Private methods defined to support the above.
      //////////////////////////////////////////////////////////////////////////////////////

   

   	 ////////////////////////////////////////////////////////////////////
       // update the MMIO Control register memory cell. We will delegate.
       private void updateMMIOControl(int addr, int intValue) {
         updateMMIOControlAndData(addr, intValue, 0, 0, true);
      }


   	 /////////////////////////////////////////////////////////////////////
       // update the MMIO Control and Data register pair -- 2 memory cells. We will delegate.
       private void updateMMIOControlAndData(int controlAddr, int controlValue, int dataAddr, int dataValue) {
         updateMMIOControlAndData(controlAddr, controlValue, dataAddr, dataValue, false);
      }


   	 /////////////////////////////////////////////////////////////////////////////////////////////////////
       // This one does the work: update the MMIO Control and optionally the Data register as well
   	 // NOTE: last argument TRUE means update only the MMIO Control register; FALSE means update both Control and Data.
       private void updateMMIOControlAndData(int controlAddr, int controlValue, int dataAddr, int dataValue, boolean controlOnly) {
         synchronized (Globals.memoryAndRegistersLock) {
               try {
                  Globals.memory.setRawWord(controlAddr, controlValue);
		  if (noRepeatedKeyEvents && (controlValue & 1) == 1) // set input
			synchronized (this) {
				if (keyPressNum >= 2)
					acceptClearInput = false;
			}
                  if (!controlOnly) Globals.memory.setRawWord(dataAddr, dataValue);
               }
                   catch (Exception aee) {
                     SystemIO.printString("\nMMIO error: updating data of incorrect MMIO address!\n");
                     
                  }
            }
         	// HERE'S A HACK!!  Want to immediately display the updated memory value in MARS
         	// but that code was not written for event-driven update (e.g. Observer) --
         	// it was written to poll the memory cells for their values.  So we force it to do so.

            if (Globals.getGui() != null && Globals.getGui().getMainPane().getExecutePane().getTextSegmentWindow().getCodeHighlighting() ) {
               Globals.getGui().getMainPane().getExecutePane().getDataSegmentWindow().updateValues();
            }
      }



     /////////////////////////////////////////////////////////////////////
     // Return value of the given MMIO control register after ready (low order) bit set (to 1).
     // Have to preserve the value of Interrupt Enable bit (bit 1)
       private boolean isReadyBitSet(int mmioControlRegister) {
         try {
            return (Globals.memory.get(mmioControlRegister, Memory.WORD_LENGTH_BYTES) & 1) == 1;
         }
             catch (Exception aee) {
               SystemIO.printString("\nMMIO error: accessing incorrect MMIO address!\n");
               
            }
         return false; // to satisfy the compiler -- this will never happen.
      }


     /////////////////////////////////////////////////////////////////////
     // Return value of the given MMIO control register after ready (low order) bit set (to 1).
     // Have to preserve the value of Interrupt Enable bit (bit 1)
       private int readyBitSet(int mmioControlRegister) {
         try {
            return Globals.memory.get(mmioControlRegister, Memory.WORD_LENGTH_BYTES) | 1;
         }
             catch (Exception aee) {
               SystemIO.printString("\nMMIO error: accessing incorrect MMIO address!\n");
               
            }
         return 1; // to satisfy the compiler -- this will never happen.
      }

     /////////////////////////////////////////////////////////////////////
     //  Return value of the given MMIO control register after ready (low order) bit cleared (to 0).
     // Have to preserve the value of Interrupt Enable bit (bit 1). Bits 2 and higher don't matter.
       private int readyBitCleared(int mmioControlRegister) {
         try {
            return Globals.memory.get(mmioControlRegister, Memory.WORD_LENGTH_BYTES) & 2;
         }
             catch (Exception aee) {
               SystemIO.printString("\nMMIO error: accessing incorrect MMIO address!\n");
               
            }
         return 0; // to satisfy the compiler -- this will never happen.
       }


   	///////////////////////////////////////////////////////////////////////////////////
   	//
   	//  grab keystrokes going to keyboard echo area and send them to MMIO area
   	//
        public void writeInput(KeyEvent e) { 
            int updatedReceiverControl = readyBitSet(RECEIVER_CONTROL);
            updateMMIOControlAndData(RECEIVER_CONTROL, updatedReceiverControl, RECEIVER_DATA,  e.getKeyChar() & 0x00000ff);
            if (updatedReceiverControl != 1
                && (Coprocessor0.getValue(Coprocessor0.STATUS) & 2)==0   // Added by Carl Hauser Nov 2008
            	 && (Coprocessor0.getValue(Coprocessor0.STATUS) & 1)==1) {
               // interrupt-enabled bit is set in both Receiver Control and in
            	// Coprocessor0 Status register, and Interrupt Level Bit is 0, so trigger external interrupt.
               mars.simulator.Simulator.externalInterruptingDevice = Exceptions.EXTERNAL_INTERRUPT_KEYBOARD;
            }
        }


	public void clearInput() {
	    	updateMMIOControl(RECEIVER_CONTROL, readyBitCleared(RECEIVER_CONTROL));
		
        }
       

         public void keyTyped(KeyEvent e) {
	   
//SystemIO.printString("type '" + (char) e.getKeyChar() +e.getWhen()+"'\n" );
		//mmioThread.add(1, e);
        }

        public synchronized void keyPressed(KeyEvent e) {
	    
//SystemIO.printString("press '" + (char) e.getKeyChar() +e.getWhen()+"'\n" );
	    if (noRepeatedKeyEvents){
	    
		if (keyPressNum >= 2)
			return;
		else keyPressNum++;
	    }
 	    
	     mmioThread.add(1, e);
        }

       
        public synchronized void keyReleased(KeyEvent e) {
//SystemIO.printString("release '" + (char) e.getKeyChar() +e.getWhen()+"'\n" );
	    if (noRepeatedKeyEvents) {
		acceptClearInput = true;
		keyPressNum = 0;
	    }
        }
        

}