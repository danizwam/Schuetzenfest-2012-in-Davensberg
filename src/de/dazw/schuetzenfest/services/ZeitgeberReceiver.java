package de.dazw.schuetzenfest.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import de.dazw.schuetzenfest.ShowVeranstaltungen;
import de.dazw.schuetzenfest.sonst.Constants;

public class ZeitgeberReceiver extends BroadcastReceiver {

	public void onReceive(Context paramContext, Intent paramIntent) {
		// ShowVeranstaltungen.getInstance(paramContext).showNextVeranstaltungen(2);

		Log.w("test", paramIntent.getAction());

		SharedPreferences sharedPreferences = paramContext.getSharedPreferences("NOTIRECEIVER", 0);
		int minuten = sharedPreferences.getInt("MINUTEN", 0);

		// Toast.makeText(paramContext, "Minuten: " + minuten, 5000).show();

		minuten++;

		Editor edit = sharedPreferences.edit();
		if (minuten >= 15) {
			if (paramContext.getSharedPreferences(Constants.PREFS_NAME, 0).getBoolean(
					Constants.NOTIFICATION_PREF, true)) {
				// if(MainActivity.notification){
				ShowVeranstaltungen.getInstance(paramContext).showNextVeranstaltungen(4);
				// showNextVeranstaltungen(2);
				Log.i("Zeitgeber", "15 Minuten sind um!");
			}

			// ShowVeranstaltungen.getInstance(paramContext).start();
			minuten = 0;
			// edit.putInt("MINUTEN", 0);

		}

		Log.i("Zeitgeber", "1 Minute ist um!");

		edit.putInt("MINUTEN", minuten);
		edit.commit();

	}

}