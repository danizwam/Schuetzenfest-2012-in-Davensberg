package de.dazw.schuetzenfest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import de.dazw.schuetzenfest.sonst.Constants;
import de.dazw.schuetzenfest.sonst.Gestures;

public class VeranstaltungTabActivity extends TabActivity implements OnGesturePerformedListener {

	private GestureLibrary gestureLib;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		GestureOverlayView gestureOverlayView = new GestureOverlayView(this);
		View inflate = getLayoutInflater().inflate(R.layout.veranstaltungentabhost, null);
		gestureOverlayView.addView(inflate);
		gestureOverlayView.addOnGesturePerformedListener(this);
		gestureOverlayView.setGestureVisible(false);
		gestureLib = GestureLibraries.fromRawResource(this, R.raw.gestures);
		if (!gestureLib.load()) {
			finish();
		}
		setContentView(gestureOverlayView);

		// setContentView(R.layout.veranstaltungentabhost);

		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		Intent intent; // Reusable Intent for each tab

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, VeranstaltungskalenderMittwoch.class);

		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost.newTabSpec("mittwoch").setIndicator("Mi", null).setContent(intent);
		tabHost.addTab(spec);

		// Do the same for the other tabs
		intent = new Intent().setClass(this, VeranstaltungskalenderDonnerstag.class);
		spec = tabHost.newTabSpec("donnerstag").setIndicator("Do.", null).setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, VeranstaltungskalenderFreitag.class);
		spec = tabHost.newTabSpec("freitag").setIndicator("Fr.", null).setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, VeranstaltungskalenderSamstag.class);
		spec = tabHost.newTabSpec("samstag").setIndicator("Sa", null).setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, VeranstaltungskalenderSonntag.class);
		spec = tabHost.newTabSpec("sonntag").setIndicator("So", null).setContent(intent);
		tabHost.addTab(spec);

		tabHost.setCurrentTab(checkTab());
	}

	private int checkTab() {

		int retVal = 0;

		Calendar now = GregorianCalendar.getInstance();

		if (now.get(Calendar.YEAR) == 2012 && now.get(Calendar.DAY_OF_YEAR) >= 158) {
			retVal = now.get(Calendar.DAY_OF_YEAR) - 158;

			if (retVal > 4) {
				retVal = 0;
			}

		}

		return retVal;
	}

	private void gestureTabs(Gestures gest) {
		switch (gest) {
		case LEFT:
			chooseNextTab();
			break;
		case RIGHT:
			choosePrevTab();
			break;
		default:
			break;
		}
	}

	private void chooseNextTab() {
		int currentTab = getTabHost().getCurrentTab();

		if (currentTab == 4) {
			getTabHost().setCurrentTab(0);
		} else{
			getTabHost().setCurrentTab(currentTab + 1);
		}
	}

	private void choosePrevTab() {
		int currentTab = getTabHost().getCurrentTab();

		if (currentTab == 0) {
			getTabHost().setCurrentTab(4);
		} else{
			getTabHost().setCurrentTab(currentTab - 1);
		}
	}

	@Override
	public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
		ArrayList<Prediction> predictions = gestureLib.recognize(gesture);
		for (Prediction prediction : predictions) {
			if (prediction.score > 1.0) {
				if(Constants.GESTURE_RIGHT.equals(prediction.name.toUpperCase())){
					gestureTabs(Gestures.RIGHT);
				} else{
					gestureTabs(Gestures.LEFT);
				}
				//Toast.makeText(this, prediction.name, Toast.LENGTH_SHORT).show();
			}
		}
	}

    @Override
    public void onBackPressed() {
    	// TODO Auto-generated method stub
    	super.onBackPressed();
    	this.finish();
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    }
	
}
