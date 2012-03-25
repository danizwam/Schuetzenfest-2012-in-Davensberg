package de.dazw.schuetzenfest.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import de.dazw.schuetzenfest.ShowVeranstaltungen;

public class SchuetzenFestWidgetProvider extends AppWidgetProvider {

	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
		//Toast.makeText(context, "onEnabled", 2000).show();
		//System.out.println("Enabled: " + Calendar.getInstance().getTime().toGMTString());
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		//Toast.makeText(context, "onReceive", 2000).show();
		
		//Toast.makeText(context, intent.toString(), 2000).show();
		
		onUpdate(context, appWidgetManager, appWidgetIds);
		
		
		ShowVeranstaltungen.getInstance(context).start();
		
		
//		long timeInMillis = GregorianCalendar.getInstance().getTimeInMillis();
//		
//		Calendar instance = GregorianCalendar.getInstance();
//		instance.set(2012, 00, 28, 15, 12);
//		long timeInMillis2 = instance.getTimeInMillis();
//		
//		if(timeInMillis > timeInMillis2){
//			showNotification(context, R.drawable.ic_launcher, "Als nächstes beginnt", "Zapfenstreich", "Am Zeltplatz");
//		}
		
//		HTTPDownloader down = new HTTPDownloader();
//		File download = down.download("http://dl.dropbox.com/u/12898258/Veranstaltungen.txt");
//		
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(download));
//			
//			String readLine = br.readLine();
//			List<Veranstaltung> verList = new ArrayList<Veranstaltung>();
//			
//			while(readLine != null){
//				StringTokenizer token = new StringTokenizer(readLine, ";");
//				int i = 1;
//				
//				
//				if(token.hasMoreTokens()){
//					String name = token.nextToken();
//					String ort = token.nextToken();
//					String wann = token.nextToken();
//
//					Veranstaltung v = new Veranstaltung(name, ort, wann, wann + 100000, i++);
//					verList.add(v);
//					//NotificationFactory.showNotification(context, R.drawable.ic_launcher, "Nächste Veranstaltung", name,	ort, v.getAnfang().getTimeInMillis(), i++);
//					
//					SimpleDateFormat sf = new SimpleDateFormat();
//					Calendar instance = GregorianCalendar.getInstance();
//					instance.setTimeInMillis(Long.parseLong(wann));
//					System.out.println(name + " " + ort + " " + sf.format(instance.getTime()));
//					
//				}
//				readLine = br.readLine();
//			}
//			
//			NotificationFactory.showNotification(context, R.drawable.ic_launcher, verList);
//			
//			download.delete();
//			
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
	}

	
//	public static void showNotification(Context paramContext, int paramInt, String paramString1, String paramString2, String paramString3) {
//		NotificationManager localNotificationManager = (NotificationManager) paramContext.getSystemService("notification");
//		PendingIntent localPendingIntent = PendingIntent.getActivity(paramContext, 0, new Intent(paramContext, NotificationTouchActivity.class), 0);
//		Notification localNotification = new Notification(paramInt, paramString1, System.currentTimeMillis());
//		localNotification.flags = 2;
//		localNotification.setLatestEventInfo(paramContext, paramString2, paramString3, localPendingIntent);
//		localNotificationManager.notify(1, localNotification);
//	}
	
	Context context; 
	AppWidgetManager appWidgetManager;
	int[] appWidgetIds;
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

		
//		final Intent intent = new Intent(context, UpdateService.class);
//		final PendingIntent pending = PendingIntent.getService(context, 0, intent, 0);
//		final AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//		alarm.cancel(pending);
//		long interval = 1000*60;
//		alarm.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), interval, pending);
		
		// Timer timer = new Timer();
		// timer.scheduleAtFixedRate(new MyTime(context, appWidgetManager), 1,
		// 1000);

		Intent intent = new Intent(context, UpdateService.class);
		context.startService(intent);

//		Toast.makeText(context, "onUpdate", 2000).show();
//		RemoteViews remoteViews;
//		ComponentName thisWidget;
//		remoteViews = new RemoteViews(context.getPackageName(), R.layout.schuetzenfestwidget);
//		thisWidget = new ComponentName(context, SchuetzenFestWidgetProvider.class);
//		remoteViews.setTextViewText(R.id.widget_textview,
//				"Noch " + CalcTageBisSchuetzenfest.berechneTage() + " Tage");
//		remoteViews.setTextViewText(R.id.widget_textview2, "verbleibend!");
//		appWidgetManager.updateAppWidget(thisWidget, remoteViews);
//
//		Intent intent = new Intent(context, SchuetzenFestWidgetProvider.class);
//
//		intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
//		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
		
	}

	// private class MyTime extends TimerTask {
	// RemoteViews remoteViews;
	// AppWidgetManager appWidgetManager;
	// ComponentName thisWidget;
	// Context context;
	//
	// public MyTime(Context context, AppWidgetManager appWidgetManager) {
	// this.appWidgetManager = appWidgetManager;
	// this.context = context;
	// remoteViews = new RemoteViews(context.getPackageName(),
	// R.layout.schuetzenfestwidget);
	// thisWidget = new ComponentName(context,
	// SchuetzenFestWidgetProvider.class);
	// }
	//
	// @Override
	// public void run() {
	// Toast.makeText(context, "Update Widget", 2000).show();
	// remoteViews.setTextViewText(R.id.widget_textview, "Noch " +
	// CalcTageBisSchuetzenfest.berechneTage() + " Tage");
	// remoteViews.setTextViewText(R.id.widget_textview2, "verbleibend!");
	// appWidgetManager.updateAppWidget(thisWidget, remoteViews);
	// }
	//
	// // public void updateTage(){
	// // Toast.makeText(context, "UpdateTage", 2000).show();
	// // onUpdate(context, appWidgetManager, appWidgetIds);
	// // }
	// //
	// // public void update(View v){
	// // Toast.makeText(context, "Update", 10000);
	// // }
	// }
}
