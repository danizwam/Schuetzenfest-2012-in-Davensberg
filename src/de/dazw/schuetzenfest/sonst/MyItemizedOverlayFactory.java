package de.dazw.schuetzenfest.sonst;

import android.app.Activity;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

import de.dazw.schuetzenfest.MyItemizedOverlay;
import de.dazw.schuetzenfest.standorte.Standort;

public class MyItemizedOverlayFactory {

	private static MyItemizedOverlayFactory instance;
	
	
	private MyItemizedOverlayFactory(){
		
	}
	
	public static MyItemizedOverlayFactory getInstance(){
		if(instance == null){
			instance = new MyItemizedOverlayFactory();
		}
		
		return instance;
	}
	
	
	public MyItemizedOverlay generateOverlay(Activity activity, Standort ort){
		
		MyItemizedOverlay ovl = new MyItemizedOverlay(activity.getApplicationContext().getResources().getDrawable(ort.getDrawableMarker()), activity);
		
		GeoPoint geopoint = new GeoPoint ((int)(ort.getLatitude() * 1E6), (int)(ort.getLongitude() * 1E6));
		OverlayItem overlayItem = new OverlayItem(geopoint, ort.getText1(), ort.getText2());
		ovl.addOverlay(overlayItem);
		
		return ovl;
	}
	
}
