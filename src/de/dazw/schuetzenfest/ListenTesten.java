package de.dazw.schuetzenfest;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import android.widget.Toast;

public class ListenTesten extends Activity implements OnTouchListener {
	
	protected SensorManager sManager;
	
	protected TextView textView1;
	protected TextView textView2;
	protected TextView textView3;
	protected TextView textView4;
	protected TextView textView01;
	protected TextView textView02;
	protected TextView textView03;
	protected TextView textView04;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.listentesten);

		textView1 = (TextView) findViewById(R.id.textView1);
		textView1.setOnTouchListener(this);
		
		textView2 = (TextView) findViewById(R.id.textView2);
		textView2.setOnTouchListener(this);
		
		textView3 = (TextView) findViewById(R.id.textView3);
		textView3.setOnTouchListener(this);
		
		textView4 = (TextView) findViewById(R.id.textView4);
		textView4.setOnTouchListener(this);
		
		textView01 = (TextView) findViewById(R.id.TextView01);
		textView01.setOnTouchListener(this);
		
		textView02 = (TextView) findViewById(R.id.TextView02);
		textView02.setOnTouchListener(this);
		
		textView03 = (TextView) findViewById(R.id.TextView03);
		textView03.setOnTouchListener(this);
		
		textView04 = (TextView) findViewById(R.id.TextView04);
		textView04.setOnTouchListener(this);
		
//		findViewById(R.id.textView2).setOnTouchListener(this);
//		findViewById(R.id.textView3).setOnTouchListener(this);
//		findViewById(R.id.textView4).setOnTouchListener(this);
//		findViewById(R.id.TextView01).setOnTouchListener(this);
//		findViewById(R.id.TextView02).setOnTouchListener(this);
//		findViewById(R.id.TextView03).setOnTouchListener(this);
//		findViewById(R.id.TextView04).setOnTouchListener(this);
		
        //Definieren des SensorManagers
//        sManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        //Definition des Orientatiossensor
//        OrientationListener sListener = new OrientationListener();
//        sManager.registerListener(new SensorEventListener() {
//			
//			@Override
//			public void onSensorChanged(SensorEvent event) {
//				Toast.makeText(getApplicationContext(), event.values[0] + " " + event.values[1] + " " + event.values[2] , 10).show();
//				
//			}
//			
//			@Override
//			public void onAccuracyChanged(Sensor sensor, int accuracy) {
//				//Toast.makeText(getApplicationContext(), event.values.toString(), 100);	
//				
//			}
//		}, sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_FASTEST);

        
        //        sManager.registerListener(new SensorEventListener() {
//			
//			@Override
//			public void onSensorChanged(SensorEvent event) {
//				
//				//Toast.makeText(getApplicationContext(), "SensorChanged " + sensor + " --- values", 10).show();
//				
//			}
//			
//			@Override
//			public void onAccuracyChanged(Sensor sensor, int accuracy) {
//				//Toast.makeText(getApplicationContext(), "SensorChanged " + sensor + " --- values", 10).show();
//				
//			}
//		}, Sensor.TYPE_MAGNETIC_FIELD, 100);
//        
//        //Definition des Orientatiossensor
//        OrientationListener sListener = new OrientationListener();
//        sManager.registerListener(sListener, sManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_FASTEST);
	}

	private void makeToast(String text) {
		Toast.makeText(getApplicationContext(), text, 1000).show();
	}

	private Map<TextView, Drawable> sicherung = new HashMap<TextView, Drawable>();
	
	private void flashAnimationDown(final TextView view){
		
		sicherung.put(view, view.getBackground());
		ColorStateList textColors = view.getTextColors();
		Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		 
		// Vibrate for 300 milliseconds
		v.vibrate(300);
		
		AlarmManager a = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		TextView t = new TextView(getApplicationContext());
		t.setText(R.string.haverkampGeo);
		Intent i = new Intent(Intent.ACTION_CALL, Uri.parse(t.getText() + ""));
		
		a.set(AlarmManager.ELAPSED_REALTIME, 0, PendingIntent.getBroadcast(getApplicationContext(), 0, i, 0));
		
		
		view.setTextColor(textColors);
		//view.setBackgroundColor(Color.LTGRAY);
	}
	
	private void flashAnimationUp(TextView view){
		view.setBackgroundDrawable(sicherung.get(view));
		view.setBackgroundColor(Color.RED);
	}
	

	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		if (event.getAction() == MotionEvent.ACTION_DOWN) // Wenn eine TextView
															// berührt wurde...
		{

			switch (v.getId()) // Bekomme heraus welches
			{
			case R.id.textView1:
				flashAnimationDown(textView1);
				makeToast("textview1Down");
				break;

			case R.id.textView2:
				makeToast("textview2Down");
				break;
			case R.id.textView3:
				makeToast("textview3Down");
				break;
			case R.id.textView4:
				makeToast("textview4Down");
				break;
			case R.id.TextView01:
				makeToast("TextView01Down");
				break;
			case R.id.TextView02:
				makeToast("TextView02Down");
				break;
			case R.id.TextView03:
				makeToast("TextView04Down");
				break;
			case R.id.TextView04:
				makeToast("TextView04Down");
				break;
			default:
				makeToast("default");

				return true;
			}

		}else if (event.getAction() == MotionEvent.ACTION_UP){ // Wars vlt. doch  das loslassen  eines TextViews?
	
			switch (v.getId()) // Bekomme heraus welches
			{
			case R.id.textView1:
				flashAnimationUp(textView1);
				makeToast("textview1UP");
				break;

			case R.id.textView2:
				makeToast("textview2UP");
				break;
			case R.id.textView3:
				makeToast("textview3UP");
				break;
			case R.id.textView4:
				makeToast("textview4UP");
				break;
			case R.id.TextView01:
				makeToast("TextView01UP");
				break;
			case R.id.TextView02:
				makeToast("TextView02UP");
				break;
			case R.id.TextView03:
				makeToast("TextView04Down");
				break;
			case R.id.TextView04:
				makeToast("TextView04Down");
				break;
			default:
				makeToast("default");
				return true;
			}
		}
		return false;
	}
	
    class OrientationListener implements SensorEventListener
    {

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {/*unused*/}

		@Override
		public void onSensorChanged(SensorEvent event) //Wenn der "Lenksensor" sich geÃ¤ndert hat
		{
			
			int wert = (int) event.values[1]; //Abrufen des Wertes der Y-Achse
			if (wert < -70); //Wenn nach rechts gekippt
			
		}
    	
    }
	
}
