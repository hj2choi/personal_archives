package hkust.engg2990D.airshipController;


   /*
    *       NOTE: A WORD OF CAUTION. DO NOT MODIFY THE CODE IN THIS FILE IN ANY WAY.
    *             DO NOT TOUCH THE CODE IN THIS FILE, FAILING WHICH THE CODE WILL STOP WORKING
    * 
    * 
    */

//import java.io.BufferedReader;
//import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.widget.Toast;

public class Transmitter{
	
	// Motors indicated from 0 .. 5
	// Two switches at 6 and 7
	public final int MOTOR1 = 0;
	public final int MOTOR2 = 1;
	public final int MOTOR3 = 2;
	public final int MOTOR4 = 3;
	public final int MOTOR5 = 4;
	public final int MOTOR6 = 5;
	public final int SWITCH1 = 6;
	public final int SWITCH2 = 7;
	public final int STOP = 8;
	public final int ON = 255;
	public final int OFF = 0;
	
	private UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
	private BluetoothAdapter btAdapter;
	private BluetoothDevice btDev;
	private BluetoothSocket btSocket;
	private String address;
	private int btIndex;
	private List<String> ListStr;
	private Set<BluetoothDevice> btDevs;	

	//private BufferedReader reader; 
	private BufferedWriter writer;
	
	private Activity activity;
	private boolean connected = false;

    // whether motor signal is being sent
    public boolean isBTSignal = false;


	public Transmitter(MainActivity mainActivity){
		activity = mainActivity;
		btAdapter = BluetoothAdapter.getDefaultAdapter();
	}
	
	public void Connect(){
				
		btDev = null;
		ListStr = new ArrayList<String>();
		btDevs = btAdapter.getBondedDevices();
		
		// make a list of all the Bluetooth devices that you can connect to
		for(Iterator<BluetoothDevice> it = btDevs.iterator(); it.hasNext();){
			BluetoothDevice b = it.next();
			ListStr.add(b.getName());
		}
		String [] str = new String[ListStr.size()];
		for(int i = 0; i < ListStr.size(); i++){
			str[i] = ListStr.get(i);			
		}
		
		// Displays the alert dialog and allows user to select one of the Bluetooth device to associate with
		Builder AlertDialogConn = new AlertDialog.Builder(activity);
		DialogInterface.OnClickListener ListClick = new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which) {
				btIndex = which;
				for(Iterator<BluetoothDevice> it = btDevs.iterator(); it.hasNext();){
					BluetoothDevice b = it.next();
					if(b.getName().equals(ListStr.get(btIndex))){
						address = b.getAddress();
						btDev = btAdapter.getRemoteDevice(address);
						try {
							btSocket = btDev.createRfcommSocketToServiceRecord(uuid);	
							btSocket.connect();		
							//reader = new BufferedReader(new InputStreamReader(btSocket.getInputStream(), "ISO-8859-1"));
							writer = new BufferedWriter(new OutputStreamWriter(btSocket.getOutputStream(), "ISO-8859-1"));							
					        
							connected = true;
							Toast.makeText(activity, "Connected", Toast.LENGTH_SHORT).show();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							connected = false;
							Toast.makeText(activity, "Failed", Toast.LENGTH_SHORT).show();			
						}
					}
				}
			}
		};
		AlertDialogConn.setItems(str, ListClick);
		AlertDialogConn.show();		
	}
	
	public void Disconnect(){
		
		// when Bluetooth is disconnected, all the motors are stopped before the connection
		// is turned off. This ensures that we do not lose control of the Airship by mistake.
		// The airship will be forced to land on the ground
		try {
			AllStop();
			Thread.sleep(500);
			btSocket.close();
			connected = false;	
			Toast.makeText(activity, "Disconnected", Toast.LENGTH_SHORT).show();									
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block	
			Toast.makeText(activity, "Failed", Toast.LENGTH_SHORT).show();									
		}
	}
		
	public boolean IsConnected(){
		return connected;		
	}
	
	public boolean AutoStartBT(){
		if(!btAdapter.isEnabled()){
			try{
				btAdapter.enable();
				while(!btAdapter.isEnabled());
				Toast.makeText(activity, "Bluetooth Enabled", Toast.LENGTH_SHORT).show();
			}
			catch(Exception e){
				return false;
			}
			
		}
		return true;
	}

	// if we send a command with only one argument, by default the second argument is set to 0
	private void SendCmd(int cmd){
		SendCmd(cmd,0);
	}
	
	// send the command with the associated data
	// For example cmd indicates the motor number, and data indicates the velocity
	// For pin 7 and pin 8, cmd is either 6 or 7 and data is either 0 or 255.
	private void SendCmd(int cmd, int data){
		if(cmd >= 0 && cmd <= 127 && data <= 255 && data >= -255){
			isBTSignal=true;
            data += 256;
			int raw_data = ((cmd << 9) | data);
			final char[] ch = {(char) ((raw_data & 0x0000ff00) >> 8), (char) (raw_data & 0x000000ff)};
				
			Thread thr = new Thread(new Runnable(){
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						writer.write('$');
						writer.write(ch[0]);
						writer.write(ch[1]);
						writer.write('\n');	
						writer.flush();	
					}
					catch (Exception e) {
					// TODO Auto-generated catch block
					}
				}
			});
			thr.start();
            isBTSignal = false;
		}
	}
	
	// if we send command with value 8, then all motors are turned off
	public void AllStop(){
		SendCmd(STOP);
	}
	
	// index is from 0 .. 5, velocity is from -255 .. 255
	public void MotorStart(int index, int vel){
		SendCmd(index, vel);
	}
	
	
	// either turn on/off switch 1 or 2
	public void SwitchChange(int index, int state){
		if(index == SWITCH1 || index == SWITCH2){
			if(state == ON || state == OFF)
			SendCmd(index, state);
		}
	}
}