package de.dazw.schuetzenfest.beans;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Veranstaltung {

	private String name = null;
	private String ort = null;
	private Calendar anfang = null;
	private Calendar ende = null;
	private Integer id = null;
	
	public Veranstaltung(String name, String ort, String anfang, String ende, Integer id){
		this.name = name;
		this.ort = ort;
		
		this.anfang = GregorianCalendar.getInstance();
		this.anfang.setTimeInMillis(Long.parseLong(anfang));
		
		this.ende = GregorianCalendar.getInstance();
		this.ende.setTimeInMillis(Long.parseLong(ende));
		
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getOrt() {
		return ort;
	}

	public Calendar getAnfang() {
		return anfang;
	}

	public Calendar getEnde() {
		return ende;
	}

	public Integer getId() {
		return id;
	}
	
	
}
