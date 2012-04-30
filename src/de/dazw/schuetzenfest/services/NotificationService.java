package de.dazw.schuetzenfest.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.os.Vibrator;

public class NotificationService extends Service {

	private final IBinder mBinder = new ServiceBinder();
	private IntentFilter filter = new IntentFilter(Intent.ACTION_TIME_TICK);
	ZeitgeberReceiver zeitgeberReceiver = new ZeitgeberReceiver();
//	ScreenOnReceiver screenOnReceiver = new ScreenOnReceiver();
//	IntentFilter filterScreenOn = new IntentFilter(Intent.ACTION_SCREEN_ON);
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return this.mBinder;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		Vibrator v = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
		// Vibrate for 300 milliseconds
		v.vibrate(2000);
		//ShowVeranstaltungen.getInstance(getApplicationContext()).showNextVeranstaltungen(2);

		
		//getApplicationContext().registerReceiver(screenOnReceiver, filterScreenOn);
		getApplicationContext().registerReceiver(zeitgeberReceiver, filter);
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		getApplicationContext().unregisterReceiver(zeitgeberReceiver);
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		handleStartCommand(intent);
	}
	
	private void handleStartCommand(Intent paramIntent){
		//ShowVeranstaltungen.getInstance(getApplicationContext()).showNextVeranstaltungen(4);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		int onStartCommand = super.onStartCommand(intent, flags, startId);
		handleStartCommand(intent);
		return START_STICKY;
	}
	
	public class ServiceBinder extends Binder
	{
		public ServiceBinder()
		{
		}
		
		public NotificationService getService()
		{
			return NotificationService.this;
		}
	}
}

