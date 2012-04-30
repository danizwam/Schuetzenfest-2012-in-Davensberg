package de.dazw.schuetzenfest.wetter;

import android.util.Log;

public class CurrentConditionBean extends ConditionBean {

	public String temp_f = null;
	public String temp_c = null;
	public String humidity = null;
	public String wind_condition = null;
	
	
	public String getTemp_f() {
		return temp_f;
	}
	public void setTemp_f(String temp_f) {
		this.temp_f = temp_f;
	}
	public String getTemp_c() {
		return temp_c;
	}
	public void setTemp_c(String temp_c) {
		this.temp_c = temp_c;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getWind_condition() {
		return wind_condition;
	}
	public void setWind_condition(String wind_condition) {
		this.wind_condition = wind_condition;
	}
	
	public boolean isErrorFree(){
		boolean errorFree = (super.isErrorFree() && temp_c != null && temp_f != null && humidity != null && wind_condition != null);
		
		if(!errorFree){
			Log.e("Wetterdaten", "Currentcondition is not errorFree");
		}

		return errorFree;
	}
	
}
