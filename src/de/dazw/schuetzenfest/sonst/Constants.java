package de.dazw.schuetzenfest.sonst;

import java.util.HashMap;
import java.util.Map;

import de.dazw.schuetzenfest.standorte.Burgturm;
import de.dazw.schuetzenfest.standorte.Festzelt;
import de.dazw.schuetzenfest.standorte.Haverkamp;
import de.dazw.schuetzenfest.standorte.Kirche;
import de.dazw.schuetzenfest.standorte.Standort;
import de.dazw.schuetzenfest.standorte.Vogelstange;

public class Constants {

	public static final String PREFS_NAME = "SchuetzenFestPrefsFile";
	public static final String NOTIFICATION_PREF = "NOTIFICATION";
	public static final String SHOWN_EVENTS = "SHOWN_EVENTS";
	public static final String LATITUDE = "LATITUDE";
	public static final String LONGITUDE = "LONGITUDE";
	public static final String GESTURE_RIGHT = "RIGHT";
	public static final String GESTURE_LEFT = "LEFT";
	
	public static final String BURGTURM = "Burgturm";
	public static final String VOGELSTANGE = "Vogelstange";
	public static final String FESTZELT = "Festzelt";
	public static final String HAVERKAMP = "Haverkamp";
	public static final String KIRCHE = "Kirche";
	
	public static Map<String, Standort> myStandorte = new HashMap<String, Standort>();
	
	static{
		Burgturm burgturm = new Burgturm();
		Festzelt festzelt = new Festzelt();
		Haverkamp haverkamp = new Haverkamp();
		Kirche kirche = new Kirche();
		Vogelstange vogelstange = new Vogelstange();
		
		myStandorte.put(burgturm.getOrt(), burgturm);
		myStandorte.put(festzelt.getOrt(), festzelt);
		myStandorte.put(haverkamp.getOrt(), haverkamp);
		myStandorte.put(kirche.getOrt(), kirche);
		myStandorte.put(vogelstange.getOrt(), vogelstange);
	}
	
}
