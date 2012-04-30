package de.dazw.schuetzenfest;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

import de.dazw.schuetzenfest.sonst.Constants;
import de.dazw.schuetzenfest.sonst.MyItemizedOverlayFactory;
import de.dazw.schuetzenfest.standorte.Burgturm;
import de.dazw.schuetzenfest.standorte.Festzelt;
import de.dazw.schuetzenfest.standorte.Haverkamp;
import de.dazw.schuetzenfest.standorte.Kirche;
import de.dazw.schuetzenfest.standorte.Vogelstange;

public class SFMapActivity extends MapActivity {

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	private MapView map;
	private Double longitude = null;
	private Double latitude = null;
	private boolean showSpecific = false;

//	@Override
//	protected void onResume() {
//		super.onResume();
//		// in onResume
//		myLocationOverlay.enableCompass(); 
//		myLocationOverlay.enableMyLocation();
//	}
//	
//	@Override
//	protected void onPause() {
//		super.onPause();
//		// in onPause
//		myLocationOverlay.disableCompass(); 
//		myLocationOverlay.disableMyLocation();
//	}
	
	@Override
	protected void onCreate(Bundle icicle) {
		// TODO Auto-generated method stub
		super.onCreate(icicle);
		
		showSpecific = false;
		
		setContentView(R.layout.sfestmapview);
		map = (MapView) findViewById(R.id.mapview);
		map.setBuiltInZoomControls(true);
		map.getController().setZoom(18);
		map.setSatellite(false);
		
        Bundle extras = getIntent().getExtras();
        
        if(extras != null){
        	longitude = extras.getDouble(Constants.LONGITUDE);
        	latitude = extras.getDouble(Constants.LATITUDE);
        	showSpecific = true;
        	//Toast.makeText(Veranstaltungskalender.this, "Zweite Seite: " + DANIEL, 10000).show();
        }
		

		final MyLocationOverlay  myLocationOverlay = new MyLocationOverlay(this, map);
		myLocationOverlay.enableMyLocation();
		myLocationOverlay.enableCompass();
		map.getOverlays().add(myLocationOverlay);

		Toast.makeText(getApplicationContext(), "Standort wird abgerufen!", 1000).show();
		
		if(!showSpecific){
			myLocationOverlay.runOnFirstFix(new Runnable() {
				public void run() {
					map.getController().animateTo(
							myLocationOverlay.getMyLocation());
				}
			});
		}
		
		//myLocation(map);
		
		List<Overlay> mapOverlays = map.getOverlays();	
		
		MyItemizedOverlayFactory factory = MyItemizedOverlayFactory.getInstance();
		
		
//		Drawable logoHaverkamp = this.getResources().getDrawable(R.drawable.logo_clear_klein);
//		Drawable logoVogelstange = this.getResources().getDrawable(R.drawable.logo_schuetzenverein_clean);
//		Drawable logoBurgturm = this.getResources().getDrawable(R.drawable.burgturm_clean);
//		
//		MyItemizedOverlay itemizedoverlayHaverkamp = new MyItemizedOverlay(logoHaverkamp, this);
//		MyItemizedOverlay itemizedoverlayVogelstange = new MyItemizedOverlay(logoVogelstange, this);
//		MyItemizedOverlay itemizedoverlayBurgturm = new MyItemizedOverlay(logoBurgturm, this);
//		MyItemizedOverlay itemizedoverlayFestzelt = new MyItemizedOverlay(logoHaverkamp, this);
//		
//		GeoPoint burgturm = new GeoPoint ((int)(51.82073 * 1E6), (int)(7.59207 * 1E6));
//		OverlayItem burgturmoverlayitem = new OverlayItem(burgturm, "Burgturm", "in Davensberg");
//		
//		GeoPoint vogelstange = new GeoPoint ((int)(51.820242 * 1E6), (int)(7.599717 * 1E6));
//		OverlayItem vogelstangeoverlayitem = new OverlayItem(vogelstange, "Vogelstange", "in Davensberg");
//		
//		GeoPoint festzelt = new GeoPoint ((int)(51.820003 * 1E6), (int)(7.596059 * 1E6));
//		OverlayItem festzeltoverlayitem = new OverlayItem(festzelt, "Festzelt", "");
//		
//		GeoPoint haverkamp = new GeoPoint ((int)(51.820149 * 1E6), (int)(7.596627 * 1E6));
//		OverlayItem haverkampoverlayitem = new OverlayItem(haverkamp, "Gasthaus \"Zur Davert\"", "Haverkamp");

		
		
//		
////	    <string name="haverkampGeo">geo:0,0?q=51.820099,7.596008 (Gasthaus \"Zur Davert\" Haverkamp)&amp;z=8</string>
////	    	    <string name="vogelstangeGeo">geo:0,0?q=51.820242,7.599717 (Vogelstange am Telgenpatt)&amp;z=8</string>
////	    	    <string name="alteKoenigGeo">geo:0,0?q=51.820242,7.599717 (Alter Koenig)&amp;z=8</string>
////	    	    <string name="neuerKoenigGeo">geo:0,0?q=51.820242,7.599717 (Neuer Koenig)&amp;z=8</string>
////
//		itemizedoverlayHaverkamp.addOverlay(haverkampoverlayitem);
//		itemizedoverlayFestzelt.addOverlay(festzeltoverlayitem);
//		itemizedoverlayVogelstange.addOverlay(vogelstangeoverlayitem);
//		itemizedoverlayBurgturm.addOverlay(burgturmoverlayitem);
		
		mapOverlays.add(factory.generateOverlay(this, new Haverkamp()));
		mapOverlays.add(factory.generateOverlay(this, new Burgturm()));
		mapOverlays.add(factory.generateOverlay(this, new Vogelstange()));
		mapOverlays.add(factory.generateOverlay(this, new Kirche()));
		mapOverlays.add(factory.generateOverlay(this, new Festzelt()));
		
		
//		mapOverlays.add(itemizedoverlayHaverkamp);
//		mapOverlays.add(itemizedoverlayFestzelt);
//		mapOverlays.add(itemizedoverlayBurgturm);
//		mapOverlays.add(itemizedoverlayVogelstange);
		
//		MyLocationOverlay myLocation = new MyLocationOverlay(this, map);
//		myLocation.enableMyLocation();
////		GeoPoint myLocation2 = myLocation.getMyLocation();
////		Log.i("Huhu", (myLocation2 == null) + "");
//		map.getOverlays().add(myLocation);
		
		//map.getController().animateTo(myLocation.getMyLocation());

//		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//		
//		List<String> providers = locationManager.getProviders(true);
//		
//		for(String provider : providers){
//			Log.i("demo", provider);
//		}
//		
//		Criteria crit = new Criteria();
//		crit.setAccuracy(Criteria.ACCURACY_COARSE);
//		crit.setPowerRequirement(Criteria.POWER_LOW);
//		crit.setAltitudeRequired(false);
//		crit.setBearingRequired(false);
//		crit.setSpeedRequired(false);
//		crit.setCostAllowed(false);
//		
//		String bestProvider = locationManager.getBestProvider(crit, true);
//		
//		LocationProvider provider = locationManager.getProvider(bestProvider);
//		Location loc = locationManager.getLastKnownLocation(bestProvider);
//		
//		if(loc != null){
//			Drawable a = this.getResources().getDrawable(R.drawable.burgturm_clean);
//			
//			HelloItemizedOverlay h = new HelloItemizedOverlay(a, this);
//			
//			GeoPoint point = new GeoPoint ((int)(51.82073 * 1E6), (int)(7.59207 * 1E6));
//			OverlayItem oi = new OverlayItem(point, "Burgturm", "in Davensberg");
//			
//			h.addOverlay(oi);
//			map.getOverlays().add(h);
//		}
		
		if(showSpecific){
			GeoPoint specific = new GeoPoint ((int)(latitude * 1E6), (int)(longitude * 1E6));
			map.getController().animateTo(specific);
			map.getController().setZoom(19);
		}
		
	}
	
	public void toggleSat(View v){
		map.setSatellite(!map.isSatellite());
	}
	
	public void myLocation(View v){
		Toast.makeText(getApplicationContext(), "Standort wird abgerufen!", 1000).show();
		boolean found  = false;
		for(Overlay o : map.getOverlays()){
			if(o instanceof MyLocationOverlay){
				if(((MyLocationOverlay) o).getMyLocation() != null){
					found = true;
					map.getController().animateTo(((MyLocationOverlay) o).getMyLocation());
					map.getController().setZoom(19);
				}
			}
		}
		
		if(!found){
			Toast.makeText(getApplicationContext(), "Ihr Standort konnte noch nicht ermittelt werden. Bitte haben Sie etwas geduld!", 2000).show();
		}
	}
	
}
