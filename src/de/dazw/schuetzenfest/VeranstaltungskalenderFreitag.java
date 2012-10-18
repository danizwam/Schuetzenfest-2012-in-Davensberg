package de.dazw.schuetzenfest;

import android.content.Intent;
import android.os.Bundle;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import de.dazw.schuetzenfest.sonst.Constants;
import de.dazw.schuetzenfest.standorte.Burgturm;
import de.dazw.schuetzenfest.standorte.Festzelt;
import de.dazw.schuetzenfest.standorte.Standort;
import de.dazw.schuetzenfest.standorte.Vogelstange;
import de.dazw.schuetzenfest.tools.InlineFormatter;

public class VeranstaltungskalenderFreitag extends ParentActivity {
	
	public String DANIEL;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.veranstaltungskalenderfreitag);
     
        TextView freitag_3 = (TextView) findViewById(R.id.freitag_3);
        freitag_3.setText(InlineFormatter.setSpanBetweenTokens(freitag_3.getText(), "##", new ForegroundColorSpan(0xFFFF0000)));
        
        TextView freitag_4 = (TextView) findViewById(R.id.freitag_4);
        freitag_4.setText(InlineFormatter.setSpanBetweenTokens(freitag_4.getText(), "##", new ForegroundColorSpan(0xFFFF0000)));
    }
    
    public void mapButtonClickedBurgturm(View v){
    	Toast.makeText(VeranstaltungskalenderFreitag.this, R.string.mittwochMapsLoadingText, 2000).show();

    	Standort s = new Burgturm();
    	
    	Intent i = new Intent(this, SFMapActivity.class);
    	i.putExtra(Constants.LATITUDE, s.getLatitude());
    	i.putExtra(Constants.LONGITUDE, s.getLongitude());
    	startActivity(i);
    	
//    	TextView t = new TextView(Veranstaltungskalender.this);
//    	t.setText(R.string.haverkampGeo);
//    	Toast.makeText(Veranstaltungskalender.this, t.getText(), 2000).show();
//    	Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(t.getText() + ""));
//    	startActivity(intent);
    }

    public void mapButtonClickedFestzelt(View v){
    	Toast.makeText(VeranstaltungskalenderFreitag.this, R.string.mittwochMapsLoadingText, 2000).show();

    	Standort s = new Festzelt();
    	
    	Intent i = new Intent(this, SFMapActivity.class);
    	i.putExtra(Constants.LATITUDE, s.getLatitude());
    	i.putExtra(Constants.LONGITUDE, s.getLongitude());
    	startActivity(i);
    	
//    	TextView t = new TextView(Veranstaltungskalender.this);
//    	t.setText(R.string.haverkampGeo);
//    	Toast.makeText(Veranstaltungskalender.this, t.getText(), 2000).show();
//    	Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(t.getText() + ""));
//    	startActivity(intent);
    }
 
    public void mapButtonClickedVogelstange(View v){
    	Toast.makeText(VeranstaltungskalenderFreitag.this, R.string.mittwochMapsLoadingText, 2000).show();

    	Standort s = new Vogelstange();
    	
    	Intent i = new Intent(this, SFMapActivity.class);
    	i.putExtra(Constants.LATITUDE, s.getLatitude());
    	i.putExtra(Constants.LONGITUDE, s.getLongitude());
    	startActivity(i);
    	
//    	TextView t = new TextView(Veranstaltungskalender.this);
//    	t.setText(R.string.haverkampGeo);
//    	Toast.makeText(Veranstaltungskalender.this, t.getText(), 2000).show();
//    	Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(t.getText() + ""));
//    	startActivity(intent);
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
