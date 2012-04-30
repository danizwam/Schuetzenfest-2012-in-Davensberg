package de.dazw.schuetzenfest.wetter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.util.Log;


public class ForecastInformationBean {

	private String city = null;
	private String postalCode = null;
	private String latitude_e6 = null;
	private String longitude_e6 = null;
	private Calendar forecast_Date = null;
	private Calendar current_date_time = null;
	private String unit_system = null;
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getLatitude_e6() {
		return latitude_e6;
	}
	public void setLatitude_e6(String latitude_e6) {
		this.latitude_e6 = latitude_e6;
	}
	public String getLongitude_e6() {
		return longitude_e6;
	}
	public void setLongitude_e6(String longitude_e6) {
		this.longitude_e6 = longitude_e6;
	}
	public Calendar getForecast_Date() {
		return forecast_Date;
	}
	public void setForecast_Date(String forecast_Date) {
		Calendar cal = GregorianCalendar.getInstance();
		//(())<forecast_date data="2012-04-12"/>
		
		cal.set(Calendar.YEAR, Integer.parseInt(forecast_Date.substring(0, 4)));
		cal.set(Calendar.MONTH, Integer.parseInt(forecast_Date.substring(5, 7)) - 1);
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(forecast_Date.substring(8, 10)));
		
		
		this.forecast_Date = cal;
	}
	public Calendar getCurrent_date_time() {
		return current_date_time;
	}
	public void setCurrent_date_time(String current_date_time) {
		Calendar cal = GregorianCalendar.getInstance();
		
		//<current_date_time data="1970-01-01 00:00:00 +0000"/>
		
		cal.set(Calendar.YEAR, Integer.parseInt(current_date_time.substring(0, 4)));
		cal.set(Calendar.MONTH, Integer.parseInt(current_date_time.substring(5, 7)) - 1);
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(current_date_time.substring(8, 10)));
		
		this.current_date_time = cal;
	}
	public String getUnit_system() {
		return unit_system;
	}
	public void setUnit_system(String unit_system) {
		this.unit_system = unit_system;
	}
	
	public boolean isErrorFree(){
		boolean errorFree = true;
		errorFree = city != null && postalCode != null && latitude_e6 != null && longitude_e6 != null && forecast_Date != null && current_date_time != null && unit_system != null;

		if(!errorFree){
			Log.e("Wetterdaten", "ForecastInformation is not errorFree");
		}
		return errorFree;
	}
}
