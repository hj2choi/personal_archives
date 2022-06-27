package hkust.project.acceleratingBalls;

import java.util.Vector;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener
{
	final static int centerX=800;
	final static int centerY=500;

	static TextView txtTest;

	SensorManager sm;
	Sensor sensor;

	Vector<Ball> balls;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txtTest = (TextView) findViewById(R.id.txtTest);

		balls = new Vector<Ball>();

		balls.add(new Ball(centerX, centerY, 3, 0.02, (ImageView)findViewById(R.id.imgBall)));
		balls.add(new Ball(300, 500, 3, 0.02, (ImageView)findViewById(R.id.imgBall2)));
		balls.add(new Ball(200, 700, 3, 0.02, (ImageView)findViewById(R.id.imgBall3)));

		sm = (SensorManager) getSystemService(SENSOR_SERVICE);
		sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		if(event.getAction()==MotionEvent.ACTION_DOWN)	// when you touch the button
		{
			int x = (int) event.getX();
			int y = (int) event.getY();
			txtTest.setText("PID target = ("+x+", "+y+")");
			for (int i=0; i<balls.size(); i++)
			{
				balls.get(i).PID=true;
				balls.get(i).PIDToLocation(x,y);
				balls.get(i).display();
			}
		}
		if(event.getAction()==MotionEvent.ACTION_UP)	// when you touch the button
		{
			//txtTest.setText("");
			for (int i=0; i<balls.size(); i++)
			{
				balls.get(i).PID=false;
				balls.get(i).display();
			}
		}
		return false;
	}

	@Override
	public void onSensorChanged(SensorEvent event)
    {
		Field.tiltField(event.values[1], event.values[0]);

		//collide();

		boolean collide = false;
		for (int i=0; i<balls.size(); i++)
		{
			for (int j=0; j<balls.size(); j++)
				//if (i!=j && balls.get(i).collision(balls.get(j)))
				//	collide=true;
			balls.get(i).displacement();
			balls.get(i).display();
		}
		//txtTest.setText(""+balls.get(0).dX);
//		txtTest.setText(""+Math.sqrt((balls[0].x-balls[1].x)*(balls[0].x-balls[1].x)+(balls[0].y-balls[1].y)*(balls[0].y-balls[1].y))
//				+"\n"+(balls[0].radius+balls[1].radius)
//				+"\n"+collide);
		//txtTest.setText("ball = ("+(int)balls.get(1).x+", "+(int)balls.get(1).y+")" + "\n" + "("+(int)balls.get(1).velX+", "+(int)balls.get(1).velY+")" + "\n" + collide);

    }

	@Override
	protected void onResume()
	{
		super.onResume();
		sm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
		// Rates: SENSOR_DELAY_FASTEST, SENSOR_DELAY_GAME,
		// SENSOR_DELAY_NORMAL, SENSOR_DELAY_UI
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
