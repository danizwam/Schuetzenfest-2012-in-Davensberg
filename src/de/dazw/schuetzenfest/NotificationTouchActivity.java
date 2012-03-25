package de.dazw.schuetzenfest;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;

public class NotificationTouchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		int id = -1;

		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			id = (Integer) extras.get("ID");
		}

		NotificationManager localNotificationManager = (NotificationManager) getApplicationContext()
				.getSystemService(Service.NOTIFICATION_SERVICE);
		localNotificationManager.cancel(id);
		
//		try {
//
//			MainActivity.fromNotification = true;
//
//			Intent i = new Intent(this, MainActivity.class);
//			startActivity(i);
//
//		} finally {
			this.finish();
		//}

	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);

		int id = -1;

		Bundle extras = intent.getExtras();

		if (extras != null) {
			id = (Integer) extras.get("ID");
		}

		NotificationManager localNotificationManager = (NotificationManager) getApplicationContext()
				.getSystemService(Service.NOTIFICATION_SERVICE);
		localNotificationManager.cancel(id);
		
//		try {
//			MainActivity.fromNotification = true;
//
//			Intent i = new Intent(this, MainActivity.class);
//			startActivity(i);
//		} finally {
			this.finish();
		//}

	}

}
