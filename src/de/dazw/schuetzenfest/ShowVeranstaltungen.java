package de.dazw.schuetzenfest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.util.Log;
import de.dazw.schuetzenfest.beans.Veranstaltung;
import de.dazw.schuetzenfest.tools.HTTPDownloader;
import de.dazw.schuetzenfest.tools.NotificationFactory;

public class ShowVeranstaltungen {

	private Context context = null;
	private Long vorlaufZeit = 7200000L;

	private static ShowVeranstaltungen instance = null;
	List<Veranstaltung> verList = new ArrayList<Veranstaltung>();
	private Boolean startet = Boolean.FALSE;

	private Thread laufThread = new Thread(new Runnable() {
		@Override
		public void run() {
			while (true) {
				showNextVeranstaltungen(2);
				try {
					synchronized (this) {
						wait(1000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	});

	public boolean isRunning() {
		return startet;
	}

	public void start() {
		if (!startet) {
			Timer timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask() {

				@Override
				public void run() {
					if(context.getSharedPreferences(MainActivity.PREFS_NAME, 0).getBoolean(MainActivity.NOTIFICATION_PREF, true)){
					//if(MainActivity.notification){
						showNextVeranstaltungen(2);
					}
				}
			}, 1, 15000);

			// laufThread.start();
			startet = Boolean.TRUE;
		}
	}

	public void forceUpdate(){
		showNextVeranstaltungen(2);
	}
	
	private ShowVeranstaltungen(Context context) {
		this.context = context;
		verList = ladeVeranstaltungenAusInternet();
		if (verList.size() == 0) {
			createBackup();
		}
	}

	public static ShowVeranstaltungen getInstance(Context context) {
		if (instance == null) {
			instance = new ShowVeranstaltungen(context);
		}
		return instance;
	}

	public void showNextVeranstaltungen(int anzahl) {
		long now = System.currentTimeMillis();
		int zaehler = 0;

		for (Veranstaltung v : verList) {
			// if ((now + vorlaufZeit) > v.getAnfang().getTimeInMillis() && now
			// < v.getEnde().getTimeInMillis()) {
			NotificationFactory.showNotification(context, R.drawable.ic_launcher, v);
			zaehler++;
			if (zaehler == anzahl) {
				break;
			}
			// }
			if (zaehler == anzahl) {
				break;
			}
		}
	}

	private List<Veranstaltung> ladeVeranstaltungenAusInternet() {

		List<Veranstaltung> verList = new ArrayList<Veranstaltung>();

		HTTPDownloader down = new HTTPDownloader();
		File download = down.download("http://dl.dropbox.com/u/12898258/Veranstaltungen.txt");

		try {
			BufferedReader br = new BufferedReader(new FileReader(download));

			String readLine = br.readLine();

			while (readLine != null) {
				StringTokenizer token = new StringTokenizer(readLine, ";");
				int i = 1;

				if (token.hasMoreTokens()) {
					String name = token.nextToken();
					String ort = token.nextToken();
					String wann = token.nextToken();
					String ende = token.nextToken();
					String id = token.nextToken();

					Veranstaltung v = new Veranstaltung(name, ort, wann, ende, Integer.parseInt(id));
					verList.add(v);
					// NotificationFactory.showNotification(context,
					// R.drawable.ic_launcher, "Nächste Veranstaltung", name,
					// ort, v.getAnfang().getTimeInMillis(), i++);
					//
					// SimpleDateFormat sf = new SimpleDateFormat();
					// Calendar instance = GregorianCalendar.getInstance();
					// instance.setTimeInMillis(Long.parseLong(wann));
					// System.out.println(name + " " + ort + " " +
					// sf.format(instance.getTime()));

				}
				readLine = br.readLine();
			}

			// NotificationFactory.showNotification(context,
			// R.drawable.ic_launcher, verList);

			download.delete();

		} catch (FileNotFoundException e) {
			Log.e("LadeVeranstaltung", "Die Datei wurde nicht gefunden!");
		} catch (IOException e) {
			Log.e("LadeVeranstaltung", "Die Datei kann nicht gelesen werden!");
		} catch (NullPointerException e) {

		}

		return verList;

	}

	private void createBackup() {
		Calendar rockfestival = GregorianCalendar.getInstance();
		rockfestival.set(2012, 5, 6, 21, 00);

		Calendar antreten = GregorianCalendar.getInstance();
		antreten.set(2012, 5, 8, 15, 00);

		Calendar vogelschiessen = GregorianCalendar.getInstance();
		vogelschiessen.set(2012, 5, 8, 17, 00);

		Calendar sonstEins = GregorianCalendar.getInstance();
		sonstEins.set(2012, 5, 9, 10, 00);

		Calendar sonstZwei = GregorianCalendar.getInstance();
		sonstZwei.set(2012, 5, 10, 10, 00);

		Calendar sonstDrei = GregorianCalendar.getInstance();
		sonstDrei.set(2012, 5, 11, 10, 00);

		String[] namen = new String[] { "Rockfestival", "Antreten", "Vogelschiessen", "Sonstwas" };
		String[] orte = new String[] { "Festzelt", "Burgturm", "Vogelstange", "Sonstwo" };
		Calendar[] zeiten = new Calendar[] { rockfestival, antreten, vogelschiessen, sonstEins };

		for (int i = 0; i < namen.length; i++) {
			Veranstaltung v = new Veranstaltung(namen[i], orte[i],
					zeiten[i].getTimeInMillis() + "",
					(zeiten[i].getTimeInMillis() + 1800000L) + "", i + 1);
			verList.add(v);
		}

	}

}
