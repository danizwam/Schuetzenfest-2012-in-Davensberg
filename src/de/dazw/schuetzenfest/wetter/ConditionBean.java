package de.dazw.schuetzenfest.wetter;

import android.util.Log;

public class ConditionBean {
	
	public String condition = null;
	public String icon = null;
	
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = "http://www.google.com" + icon;
	}
	
	public boolean isErrorFree(){
		boolean errorFree = (condition != null && icon != null);
		
		if(!errorFree){
			Log.e("Wetterdaten", "ForecastInformation is not errorFree");
		}

		return errorFree;
	}
	
}
