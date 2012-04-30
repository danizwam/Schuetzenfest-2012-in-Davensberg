package de.dazw.schuetzenfest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import de.dazw.schuetzenfest.sonst.Constants;
import de.dazw.schuetzenfest.standorte.Standort;

public class VeranstaltungskalenderSonntag extends ParentActivity {
	
	public String DANIEL;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.veranstaltungskalendersonntag);
        
    }
    
    public void mapButtonClicked(View v){
    	Toast.makeText(VeranstaltungskalenderSonntag.this, R.string.mittwochMapsLoadingText, 2000).show();

    	Standort standort = Constants.myStandorte.get(Constants.FESTZELT);
    	
    	Intent i = new Intent(this, SFMapActivity.class);
    	i.putExtra(Constants.LATITUDE, standort.getLatitude());
    	i.putExtra(Constants.LONGITUDE, standort.getLongitude());
    	startActivity(i);
    	
//    	TextView t = new TextView(Veranstaltungskalender.this);
//    	t.setText(R.string.haverkampGeo);
//    	Toast.makeText(Veranstaltungskalender.this, t.getText(), 2000).show();
//    	Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(t.getText() + ""));
//    	startActivity(intent);
    }
    

    
}
