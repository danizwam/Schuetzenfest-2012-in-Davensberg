package de.dazw.schuetzenfest.sonst;

import java.util.Calendar;
import java.util.GregorianCalendar;

import de.dazw.schuetzenfest.beans.Veranstaltung;
import de.dazw.schuetzenfest.standorte.Standort;

public class VeranstaltungFactory {

	private static VeranstaltungFactory instance = null;
	
	private VeranstaltungFactory(){
		
	}
	
	public static VeranstaltungFactory getInstance(){
		if(instance == null){
			instance = new VeranstaltungFactory();
		}
		
		return instance;
	}
	
	
	public Veranstaltung createVeranstaltung(String name, Standort ort, String anfang, int dauer, int id){
		//"06.06.2012:15:30";
		int tag = Integer.parseInt(anfang.substring(0, 2));
		int monat = Integer.parseInt(anfang.substring(3, 5));
		int jahr = Integer.parseInt(anfang.substring(6, 10));
		int stunde = Integer.parseInt(anfang.substring(11, 13));
		int minute = Integer.parseInt(anfang.substring(14, anfang.length()));
		
		
		Calendar anfangCal = new GregorianCalendar(jahr, monat - 1, tag, stunde, minute);
		Calendar endeCal = new GregorianCalendar();
		endeCal.setTimeInMillis(anfangCal.getTimeInMillis());
		endeCal.add(Calendar.MINUTE, dauer);
		
		Veranstaltung v = new Veranstaltung(name, ort, anfangCal, endeCal, id);
		
		return v;
	}
	
}
