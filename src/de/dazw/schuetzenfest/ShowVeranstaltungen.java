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
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import de.dazw.schuetzenfest.beans.Veranstaltung;
import de.dazw.schuetzenfest.sonst.Constants;
import de.dazw.schuetzenfest.sonst.VeranstaltungFactory;
import de.dazw.schuetzenfest.standorte.Burgturm;
import de.dazw.schuetzenfest.standorte.Festzelt;
import de.dazw.schuetzenfest.standorte.Haverkamp;
import de.dazw.schuetzenfest.standorte.Kirche;
import de.dazw.schuetzenfest.standorte.Vogelstange;
import de.dazw.schuetzenfest.tools.HTTPDownloader;
import de.dazw.schuetzenfest.tools.NotificationFactory;

public class ShowVeranstaltungen {

	private Context context = null;
	private Long vorlaufZeit = 3600000L;

	private static ShowVeranstaltungen instance = null;
	List<Veranstaltung> verList = new ArrayList<Veranstaltung>();
	private static Boolean startet = Boolean.FALSE;
	private static Timer timer;

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

	public static void createEventMap(Context context){
		context.getSharedPreferences(Constants.PREFS_NAME, 0);
	}
	
	public boolean isRunning() {
		return startet;
	}

	public void stop() {
		timer = null;
		startet = Boolean.FALSE;
		instance = null;
		Log.i("ShowVeranstaltungen", "Gestoppt!");
	}

	public void start() {
		
		Calendar cal = new GregorianCalendar(2012, 5, 6);
		Calendar calzwei = new GregorianCalendar(2012, 5, 11);

		long sfestStart = cal.getTimeInMillis();
		long sfestEnde = calzwei.getTimeInMillis();
		long now = System.currentTimeMillis();
		
		if(now < sfestStart || now > sfestEnde){
			stop();
			Log.i("ShowVeranstaltungen", "Nicht gestartet, falsches Datum!");
			return;
		}
		
		if (!startet) {
			Log.i("ShowVeranstaltungen", "Gestartet!");
			timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask() {

				@Override
				public void run() {
					if (context.getSharedPreferences(Constants.PREFS_NAME, 0).getBoolean(
							Constants.NOTIFICATION_PREF, true)) {
						// if(MainActivity.notification){
						showNextVeranstaltungen(2);
					}
				}
			}, 1, 900000L);

			// laufThread.start();
			startet = Boolean.TRUE;
		}
	}

	public void forceUpdate() {
		showNextVeranstaltungen(1);
	}

	private ShowVeranstaltungen(Context context) {
		this.context = context;

		new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (verList) {
					// verList = ladeVeranstaltungenAusInternet();
					if (verList.size() == 0) {
						createBackup();
					}
				}
			}
		}).start();

	}

	private boolean fehler = false;

	// private void showToastText(String text){
	// Toast.makeText(context, text, 2000).show();
	// }

	public static ShowVeranstaltungen getInstance(Context context) {
		if (instance == null) {
			instance = new ShowVeranstaltungen(context);
		}
		return instance;
	}

	public void showNextVeranstaltungen(final int anzahl) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				long now = System.currentTimeMillis();
				int zaehler = 0;

				while (verList.size() == 0) {
					// Warten bis Backup geladen wurde
				}
				synchronized (verList) {
					
					for (Veranstaltung v : verList) {
						if ((now + vorlaufZeit) > v.getAnfang().getTimeInMillis()
								&& now < v.getAnfang().getTimeInMillis() + 1800000L) {
							
							String value = context.getSharedPreferences(Constants.PREFS_NAME, 0).getString(v.getAnfang().getTimeInMillis() + "", null);
							if(value == null){
								//NotificationFactory.showNotification(context, v.getStandort().get, v);
								NotificationFactory.showNotification(context, R.drawable.ic_launcher, v);
								SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.PREFS_NAME, 0);
								Editor edit = sharedPreferences.edit();
								edit.putString(v.getAnfang().getTimeInMillis() + "", v.getAnfang().getTimeInMillis() + "");
								edit.commit();
								zaehler++;
							}
//							if (zaehler == anzahl) {
//								break;
//							}
						}
//						if (zaehler == anzahl) {
//							break;
//						}
					}
				}

			}
		}).start();

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
			Log.e("LadeVeranstaltung", "NPE: " + e.getMessage());
		}

		// showToastText("Veranstaltungedaten erfolgreich geladen!");

		return verList;

	}

	private void createBackup() {
		// showToastText("Veranstaltungen konnten nicht geladen werden. Es werden die zum Release der App bekannten Zeiten benutzt!");

		VeranstaltungFactory f = VeranstaltungFactory.getInstance();

		verList.add(f.createVeranstaltung("Rockfestival", new Festzelt(), "06.06.2012:19:30", 400,
				1));

		verList.add(f.createVeranstaltung("Gemütliches Beisammensein", new Festzelt(),
				"07.06.2012:14:00", 400, 2));

		verList.add(f.createVeranstaltung("Antreten", new Burgturm(), "08.06.2012:15:30", 90, 3));
		verList.add(f.createVeranstaltung("Vogelschießen", new Vogelstange(), "08.06.2012:17:00",
				150, 4));
		verList.add(f.createVeranstaltung("Abmarsch zum Festzelt", new Festzelt(),
				"08.06.2012:19:30", 30, 5));

		verList.add(f.createVeranstaltung("Antreten am Burgturm", new Burgturm(),
				"09.06.2012:15:00", 60, 6));
		verList.add(f.createVeranstaltung("Gottesdienst", new Kirche(), "09.06.2012:16:00", 90, 7));
		verList.add(f.createVeranstaltung("Fahnenschlag", new Burgturm(), "09.06.2012:17:30", 105,
				8));
		verList.add(f.createVeranstaltung("Großer Zapfenstreich", new Haverkamp(),
				"09.06.2012:19:15", 45, 9));
		verList.add(f.createVeranstaltung("Einmarsch ins Zelt", new Festzelt(), "09.06.2012:20:00",
				45, 10));
		verList.add(f.createVeranstaltung("Public Viewing", new Festzelt(), "09.06.2012:20:45",
				105, 11));

		verList.add(f.createVeranstaltung("Einmarsch", new Festzelt(), "10.06.2012:11:30", 210, 12));
		verList.add(f.createVeranstaltung("lockerer Ausklang", new Festzelt(), "10.06.2012:15:00",
				105, 13));

		//
		// Calendar rockfestival = GregorianCalendar.getInstance();
		// rockfestival.set(2012, 5, 6, 21, 00);
		//
		// Calendar antreten = GregorianCalendar.getInstance();
		// antreten.set(2012, 5, 8, 15, 00);
		//
		// Calendar vogelschiessen = GregorianCalendar.getInstance();
		// vogelschiessen.set(2012, 5, 8, 17, 00);
		//
		// Calendar sonstEins = GregorianCalendar.getInstance();
		// sonstEins.set(2012, 5, 9, 10, 00);
		//
		// Calendar sonstZwei = GregorianCalendar.getInstance();
		// sonstZwei.set(2012, 5, 10, 10, 00);
		//
		// Calendar sonstDrei = GregorianCalendar.getInstance();
		// sonstDrei.set(2012, 5, 11, 10, 00);
		//
		// String[] namen = new String[] { "Rockfestival", "Antreten",
		// "Vogelschiessen", "Sonstwas" };
		// String[] orte = new String[] { "Festzelt", "Burgturm", "Vogelstange",
		// "Sonstwo" };
		// Calendar[] zeiten = new Calendar[] { rockfestival, antreten,
		// vogelschiessen, sonstEins };
		//
		// for (int i = 0; i < namen.length; i++) {
		// Veranstaltung v = new Veranstaltung(namen[i], orte[i],
		// zeiten[i].getTimeInMillis() + "",
		// (zeiten[i].getTimeInMillis() + 1800000L) + "", i + 1);
		// verList.add(v);
		// }

	}

}
