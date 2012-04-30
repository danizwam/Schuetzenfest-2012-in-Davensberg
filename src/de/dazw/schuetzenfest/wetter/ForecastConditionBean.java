package de.dazw.schuetzenfest.wetter;

import android.util.Log;

public class ForecastConditionBean extends ConditionBean {

	public String day_of_week = null;
	public String low_data = null;
	public String high_data = null;
	public Integer value = 0;
	
	
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getDay_of_week() {
		return day_of_week;
	}
	public void setDay_of_week(String day_of_week) {
		this.day_of_week = day_of_week;
	}
	public String getLow_data() {
		return low_data;
	}
	public void setLow_data(String low_data) {
		this.low_data = low_data;
	}
	public String getHigh_data() {
		return high_data;
	}
	public void setHigh_data(String high_data) {
		this.high_data = high_data;
	}
	
	public boolean isErrorFree(){
		boolean errorFree = (super.isErrorFree() && day_of_week != null && low_data != null && high_data != null);

		if(!errorFree){
			Log.e("Wetterdaten", "Forecastcondition " + value + " is not errorFree");
		}
		
		return errorFree;
	}	
}
