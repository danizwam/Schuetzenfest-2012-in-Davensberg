package de.dazw.schuetzenfest.widget;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;
import de.dazw.schuetzenfest.MainActivity;
import de.dazw.schuetzenfest.R;
import de.dazw.schuetzenfest.logic.CalcTageBisSchuetzenfest;

public class UpdateService extends Service {
	
	private static int zaehler = 0;
	private int meinZaehler = 0;
	private static final String LOG = "de.dazw.schuetzenfest.widget.UpdateService";
	
	@Override
	public void onCreate() {
		super.onCreate();
		meinZaehler = ++zaehler;
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		
		Toast.makeText(getApplicationContext(), meinZaehler + "", 20000);
		Log.w(LOG, "onStart called " + meinZaehler);
		
		RemoteViews updateViews = new RemoteViews(this.getPackageName(), R.layout.schuetzenfestwidget);

		//RemoteViews remoteViews = new RemoteViews(this.getApplicationContext().getPackageName(), R.layout.schuetzenfestwidget);
		String[] ermittelWidgetText = ermittelWidgetText(CalcTageBisSchuetzenfest.berechneTage()); 
		updateViews.setTextViewText(R.id.widget_textview, ermittelWidgetText[0]);
		updateViews.setTextViewText(R.id.widget_textview2, ermittelWidgetText[1]);
		
//		updateViews.setTextViewText(R.id.widget_textview, "Noch " + CalcTageBisSchuetzenfest.berechneTage() + " Tage");
//		updateViews.setTextViewText(R.id.widget_textview2, "verbleibend!");
//		updateViews.setTextViewText(R.id.widget_textview, "Last Update:");
//		updateViews.setTextViewText(R.id.widget_textview2, formatTime(GregorianCalendar.getInstance().getTime()));

		// Register an onClickListener
		Intent clickIntent = new Intent(this.getApplicationContext(), SchuetzenFestWidgetProvider.class);
		
		clickIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
		clickIntent.setClass(getApplicationContext(), MainActivity.class);
		clickIntent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
		
		PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		updateViews.setOnClickPendingIntent(R.id.widget_textview, pendingIntent);
		updateViews.setOnClickPendingIntent(R.id.widget_textview2, pendingIntent);

		ComponentName thisWidget = new ComponentName(this, SchuetzenFestWidgetProvider.class);
		AppWidgetManager manager = AppWidgetManager.getInstance(this);
		manager.updateAppWidget(thisWidget, updateViews);

		super.onStart(intent, startId);

	}

	
	private String[] ermittelWidgetText(int anzahlTage){
		String[] retArr = new String[2];
		
		if(anzahlTage == 1){
			retArr[0] = "Morgen ist";
			retArr[1] = "Schützenfest!";
			return retArr;
		} else if(anzahlTage > 0){
			retArr[0] = "Noch " + anzahlTage + " Tage";
			retArr[1] = "verbleibend!";
			return retArr;
		}else if(anzahlTage <= 0 && anzahlTage > - 5){
			retArr[0] = "Es ist gerade";
			retArr[1] = "Schützenfest!";
			return retArr;
		} else {
			retArr[0] = "Schützenfest";
			retArr[1] = "2012 ist vorbei!";
			return retArr;
		}
	}
	
//	private static String formatTime(Date date){
//		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
//		return formatter.format(date);
//	}
	
//	@Override
//	public void onStart(Intent intent, int startId) {
//		Log.i(LOG, "Called");
//		// Create some random data
//
//		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this
//				.getApplicationContext());
//		
//		RemoteViews updateViews = new RemoteViews(this.getPackageName(), R.layout.schuetzenfestwidget);
//
//		ComponentName thisWidget = new ComponentName(getApplicationContext(), SchuetzenFestWidgetProvider.class);
////		int[] allWidgetIds2 = appWidgetManager.getAppWidgetIds(thisWidget);
////		Log.w(LOG, "From Intent" + String.valueOf(allWidgetIds.length));
////		Log.w(LOG, "Direct" + String.valueOf(allWidgetIds2.length));
//
//
//			// Create some random data
////			int number = (new Random().nextInt(100));
//
//			RemoteViews remoteViews = new RemoteViews(this.getApplicationContext().getPackageName(), R.layout.schuetzenfestwidget);
//			updateViews.setTextViewText(R.id.widget_textview, "Noch " + CalcTageBisSchuetzenfest.berechneTage() + " Tage");
//			updateViews.setTextViewText(R.id.widget_textview2, "verbleibend!");
//			// Register an onClickListener
//			Intent clickIntent = new Intent(this.getApplicationContext(),SchuetzenFestWidgetProvider.class);
//
//			clickIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
//
//			PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//			updateViews.setOnClickPendingIntent(R.id.widget_textview, pendingIntent);
//			appWidgetManager.updateAppWidget(R.id.widget_textview2, updateViews);
//		
//		stopSelf();
//
//		super.onStart(intent, startId);
//	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}