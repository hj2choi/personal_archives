package hkust.engg2990D.airshipController;


import hkust.engg2990D.airshipcontrol.R;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/* Two new technical stuff:
 * System.getCurrentTimeMillis() : returns current time in milliseconds, counting since year 1970
 * implements SensorEventListener
 * 
 * 
 */


public class MainActivity extends Activity implements OnClickListener, SensorEventListener, OnTouchListener
{
	
	int transmissionDelay=300;		//transmission period when tiltmode is enabled.
	int liftPropellerPower = 200;		// propeller power for lifting. range from 0 to 255
	int HorizontalPropellerPower = 200;	// maximum propeller power for horizontal movement. range from 0 to 255
	int yawPower = 60;	//yaw coefficient. suggested max = 100
	
	Button btnHalt, btnTiltMode, btnConnection;
	ImageButton btnUp, btnDown, btnYawLeft, btnYawRight;
	TextView motor1, motor2, motor3, motor4;
	SensorManager sm;
	Sensor sensor;
	Transmitter transmitter; 

    /****Bluetooth protection timer and timer task begins here****/
    //To monitor the bluetooth connection
    /*Timer timer = new Timer(true);

    //bluetooth connection protection
    public class timerTask extends TimerTask
    {
        public void run()
        {
            if (!transmitter.isBTSignal)
                transmitter.MotorStart(7,0);
        }
    }*/
	int motorS1=0;
	int motorS2=0;
	int motorS3=0;
	int motorS4=0;
	
	float scaledSensorX=0; //accelerometer sensor value, x axis
	float scaledSensorY=0; //accelerometer sensor value, y axis
	long lastTransmissionTime=0;	// time in milliseconds when last transmission was made
	boolean tiltMode=false;
	
	@Override 
	protected void onCreate(Bundle savedInstanceState)
	{ 
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // fix screen orientation - landscape
		
		super.onCreate(savedInstanceState); 

        //timer.schedule(new timerTask(), 1000, 100);

        setContentView(R.layout.activity_main);
		transmitter = new Transmitter(this);
        if(!transmitter.AutoStartBT()){
            return;
        }
		
		//******************* all the initialization and torturing stuff ****************
		
		btnHalt = (Button) findViewById(R.id.btnHalt);		
		btnConnection = (Button) findViewById(R.id.btnConnection);
		btnUp = (ImageButton) findViewById(R.id.btnUp);
		btnDown = (ImageButton) findViewById(R.id.btnDown);
		btnTiltMode = (Button) findViewById(R.id.btnTiltMode);
		btnYawLeft = (ImageButton) findViewById(R.id.btnYawLeft);
		btnYawRight = (ImageButton) findViewById(R.id.btnYawRight);
		
		motor1 = (TextView) findViewById(R.id.motor1);
		motor2 = (TextView) findViewById(R.id.motor2);
		motor3 = (TextView) findViewById(R.id.motor3);
		motor4 = (TextView) findViewById(R.id.motor4);
		
		btnHalt.setOnClickListener(this);
		btnConnection.setOnClickListener(this);
		btnUp.setOnTouchListener(this);
		btnDown.setOnTouchListener(this);
		btnTiltMode.setOnTouchListener(this);
		btnYawLeft.setOnTouchListener(this);
		btnYawRight.setOnTouchListener(this);
		
		sm = (SensorManager) getSystemService(SENSOR_SERVICE);	// initialize sensor
		sensor=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);	// type accelerometer
		//******************* end of the torture ****************///
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (v.getId()) 
		{
		case R.id.btnTiltMode:
			if(event.getAction()==MotionEvent.ACTION_DOWN)	// when you touch the button
			{
				btnTiltMode.setText("TiltMode On");
				tiltMode=true;
				transmitSensorValues();
				lastTransmissionTime=System.currentTimeMillis();
			}
			if(event.getAction()==MotionEvent.ACTION_UP)	// at release
			{
				btnTiltMode.setText("TiltMode Off");
				tiltMode=false;
				transmitter.MotorStart(3,0);	// stop all horizontal motors when tiltButton is released
				lastTransmissionTime=System.currentTimeMillis();
				//motor1.setText(""+0); motor2.setText(""+0); motor3.setText(""+0); motor4.setText(""+0);
			}
			break;
		
		case R.id.btnUp:
			if(event.getAction()==MotionEvent.ACTION_DOWN)
				transmitter.MotorStart(0, liftPropellerPower);	// start lift propellers at fixed power
			if(event.getAction()==MotionEvent.ACTION_UP)
				transmitter.MotorStart(0, 0);		// turn them off at button release
			break;
			
		case R.id.btnDown:
			if(event.getAction()==MotionEvent.ACTION_DOWN)
				transmitter.MotorStart(0, -liftPropellerPower);	// start lift propellers at fixed power
			if(event.getAction()==MotionEvent.ACTION_UP)
				transmitter.MotorStart(0, 0);		// turn them off at button release
			break;
			
			
		case R.id.btnYawLeft:
			if(event.getAction()==MotionEvent.ACTION_DOWN)
				transmitter.MotorStart(4, yawPower);	// yaw
			if(event.getAction()==MotionEvent.ACTION_UP)
				transmitter.MotorStart(4, 0);		// turn them off at button release
			break;
		case R.id.btnYawRight:
			if(event.getAction()==MotionEvent.ACTION_DOWN)
				transmitter.MotorStart(4, -yawPower);	// yaw
			if(event.getAction()==MotionEvent.ACTION_UP)
				transmitter.MotorStart(4, 0);		// turn them off at button release
			break;
		default:
			break;
		
		}
		
		return false;
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		sm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
		// Rates: SENSOR_DELAY_FASTEST, SENSOR_DELAY_GAME,
		// SENSOR_DELAY_NORMAL, SENSOR_DELAY_UI
	}
	
	/*************************** This sensor function is invoked every certain period with new set of sensor values *************************************/
	@Override
	public void onSensorChanged(SensorEvent event)
    {
		scaledSensorX=event.values[1];
		scaledSensorY=event.values[0];
		
		if (tiltMode)	// if tiltMode is enabled, send sensor values
			if (lastTransmissionTime<System.currentTimeMillis()-transmissionDelay)
			{
				scaledSensorX*=(HorizontalPropellerPower/10);	// scale up sensor values for transmission
				scaledSensorY*=(HorizontalPropellerPower/10);
				transmitSensorValues();
				lastTransmissionTime=System.currentTimeMillis();
			}
		
		
    }
	
	public void transmitSensorValues()
	{
		transmitter.MotorStart(1, (int)scaledSensorX);	// send two scaled sensor values. Arduino will figure out what to do with them.
		transmitter.MotorStart(2, (int)scaledSensorY);
		/*motor1.setText(""+(int)(scaledSensorX-scaledSensorY));    // this part is for reference. Transformation matrix for receiver side
		motor2.setText(""+(int)(-scaledSensorX-scaledSensorY));
		motor3.setText(""+(int)(-scaledSensorX-scaledSensorY));
		motor4.setText(""+(int)(scaledSensorX-scaledSensorY));*/
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) 
		{
		case R.id.btnHalt:
			transmitter.MotorStart(9, 0);//stop all motors
        	break;
			
		case R.id.btnConnection:
			bluetoothConnection();
			break;
		default:
			break;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/******************************* Nothing that we really have to worry about *****************************************/
	
	
	public void bluetoothConnection()
	{
		if (!transmitter.IsConnected()) {
			class EnableUI extends AsyncTask<Void, Void, Void>{

				protected void onPreExecute(){
					transmitter.Connect();	
				}
				
				protected void onPostExecute(Void result){
					
				}
				@Override
				protected Void doInBackground(Void... params) {
					while(!transmitter.IsConnected());
					return null;
				}				
			}
			
			EnableUI enableUI = new  EnableUI();
			enableUI.execute();
		}
		else if (transmitter.IsConnected()) {
			class DisableUI extends AsyncTask<Void, Void, Void>{


				protected void onPreExecute(){
					transmitter.Disconnect();	
				}
				
				protected void onPostExecute(Void result){
					
				}
				@Override
				protected Void doInBackground(Void... params) {
					while(transmitter.IsConnected());
					return null;
				}				
			}
			
			DisableUI disableUI = new  DisableUI();
			disableUI.execute();
		}
	}
	


	@Override
	protected void onPause()
	{
		super.onPause();
	    sm.unregisterListener(this);
	}
	
	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {}
	
}