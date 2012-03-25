package de.dazw.schuetzenfest.logic;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalcTageBisSchuetzenfest {
	
	public static void main(String args[]){
		System.out.println(berechneTage());
	}
	
	
	public static int berechneTage(){
		GregorianCalendar schuetzenfest = new GregorianCalendar();
		schuetzenfest.set(Calendar.YEAR, 2012);
		schuetzenfest.set(Calendar.MONTH, Calendar.JUNE);
		schuetzenfest.set(Calendar.DAY_OF_MONTH, 6);
		schuetzenfest.set(Calendar.SECOND, 0);
		
		GregorianCalendar now = (GregorianCalendar) GregorianCalendar.getInstance();
		now.set(Calendar.SECOND, 0);
		//now.add(Calendar.DAY_OF_YEAR, 110);
		
		
		Calendar frueher = now;
		Calendar spaeter = schuetzenfest;
		
		boolean minus = false;

		if(frueher.getTimeInMillis() > spaeter.getTimeInMillis()){
			frueher = schuetzenfest;
			spaeter = now;
			minus = true;
		}
		
		
		int anzahl = 0;
		while(!checkEqual(frueher, spaeter)){
			anzahl++;
			frueher.add(Calendar.DAY_OF_YEAR, 1);
		}
		
		return (minus ? anzahl * - 1 : anzahl);
	}
	
	private static boolean checkEqual(Calendar cal1, Calendar cal2){
		if(cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)){
			if(cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)){
				return true;
			}
		}
		
		return false;
	}
}
