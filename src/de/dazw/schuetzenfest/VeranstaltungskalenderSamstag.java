package de.dazw.schuetzenfest;

import android.content.Intent;
import android.os.Bundle;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import de.dazw.schuetzenfest.sonst.Constants;
import de.dazw.schuetzenfest.tools.InlineFormatter;

public class VeranstaltungskalenderSamstag extends ParentActivity {
	
	public String DANIEL;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.veranstaltungskalendersamstag);
        
        TextView samstag_3 = (TextView) findViewById(R.id.samstag_3);
        samstag_3.setText(InlineFormatter.setSpanBetweenTokens(samstag_3.getText(), "##", new ForegroundColorSpan(0xFFFF0000)));

        TextView samstag_4 = (TextView) findViewById(R.id.samstag_4);
        samstag_4.setText(InlineFormatter.setSpanBetweenTokens(samstag_4.getText(), "##", new ForegroundColorSpan(0xFFFF0000)));
        
        TextView samstag_5 = (TextView) findViewById(R.id.samstag_5);
        samstag_5.setText(InlineFormatter.setSpanBetweenTokens(samstag_5.getText(), "##", new ForegroundColorSpan(0xFFFF0000)));
    }
    
    public void mapButtonClicked(View v){
    	Toast.makeText(VeranstaltungskalenderSamstag.this, R.string.mittwochMapsLoadingText, 2000).show();

    	Intent i = new Intent(this, SFMapActivity.class);
    	i.putExtra(Constants.LATITUDE, Double.parseDouble("51.82073"));
    	i.putExtra(Constants.LONGITUDE, Double.parseDouble("7.59207"));
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
