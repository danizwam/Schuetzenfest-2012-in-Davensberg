package de.dazw.schuetzenfest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import de.dazw.schuetzenfest.sonst.Constants;

public class Veranstaltungskalender extends ParentActivity {
	
	public String DANIEL;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.veranstaltungskalender);
        
//        Bundle extras = getIntent().getExtras();
//        
//        if(extras != null){
//        	DANIEL = extras.getString("DANIEL");
//        	Toast.makeText(Veranstaltungskalender.this, "Zweite Seite: " + DANIEL, 10000).show();
//        }
//        
//        
//        m_listview = (ListView) findViewById(R.id.id_list_view);
//
//        String[] items = new String[] {"Item 1", "Item 2", "Item 3"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, items);
//
//        m_listview.setAdapter(adapter);
//        
//        m_listview.setOnItemClickListener(new OnItemClickListener() {
//  	    public void onItemClick(AdapterView<?> parent, View view,
//  	        int position, long id) {
//  	    	
//  	    	((CheckedTextView) view).setChecked(!((CheckedTextView) view).isChecked());
//  	    	
//  	      // When clicked, show a toast with the TextView text
//  	      Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
//  	          Toast.LENGTH_SHORT).show();
//  	    }
//  	  });
        
    }
    
    public void mittwochButtonMaps(View v){
    	Toast.makeText(Veranstaltungskalender.this, R.string.mittwochMapsLoadingText, 2000).show();

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
    
}
