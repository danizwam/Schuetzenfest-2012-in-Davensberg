package de.dazw.schuetzenfest.tools;

import java.util.List;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import de.dazw.schuetzenfest.SFMapActivity;
import de.dazw.schuetzenfest.beans.Veranstaltung;
import de.dazw.schuetzenfest.sonst.Constants;
import de.dazw.schuetzenfest.standorte.Standort;

public class NotificationFactory {

	private NotificationFactory(){
		
	}
	
	public static void showNotification(Context paramContext, int iconID, List<Veranstaltung> verList){
		for(Veranstaltung v : verList){
			showNotification(paramContext, iconID, v);
		}
	}
	
	public static void showNotification(Context paramContext, int iconID, Veranstaltung v){
			showNotification(paramContext, iconID, v.getName(), v.getOrt(), "Nächste Veranstaltung", v.getAnfang().getTimeInMillis(), v.getId());
	}
	
	public static void showNotification(Context paramContext, int iconID, String titelString, String beschreibungString, String paramString3, Long startZeit, int id) {
		
		NotificationManager localNotificationManager = (NotificationManager) paramContext.getSystemService(Service.NOTIFICATION_SERVICE);
		//Intent i = new Intent(paramContext, NotificationTouchActivity.class);
		Standort standort = Constants.myStandorte.get(beschreibungString);

		Intent i = new Intent(paramContext, SFMapActivity.class);
		i.putExtra("ID", new Integer(id));
    	i.putExtra(Constants.LATITUDE, standort.getLatitude());
    	i.putExtra(Constants.LONGITUDE, standort.getLongitude());
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
		//PendingIntent localPendingIntent = PendingIntent.getActivity(paramContext, 0, new Intent(paramContext, NotificationTouchActivity.class), 0);
		PendingIntent localPendingIntent = PendingIntent.getActivity(paramContext, id, i, PendingIntent.FLAG_UPDATE_CURRENT);
		
		//Notification localNotification = new Notification(iconID, titelString, System.currentTimeMillis());
		Notification localNotification = new Notification(iconID, paramString3 , startZeit);
		localNotification.flags = Notification.FLAG_AUTO_CANCEL;
		//localNotification.setLatestEventInfo(paramContext, titelString, beschreibungString , localPendingIntent);
		localNotification.setLatestEventInfo(paramContext, titelString, beschreibungString , localPendingIntent);
		localNotificationManager.notify(id, localNotification);

		
		Vibrator v = (Vibrator) paramContext.getSystemService(Context.VIBRATOR_SERVICE);
		// Vibrate for 500 milliseconds
		v.vibrate(500);
		
	}
	
	
}
