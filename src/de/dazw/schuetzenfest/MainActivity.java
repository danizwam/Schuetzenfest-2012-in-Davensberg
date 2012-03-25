package de.dazw.schuetzenfest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ParentActivity {
	
	public static final String PREFS_NAME = "SchuetzenFestPrefsFile";
	public static final String NOTIFICATION_PREF = "NOTIFICATION";
	public static boolean notification = true;
	
	//public static boolean fromNotification = false;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.main);
        
        
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean notifi = settings.getBoolean(NOTIFICATION_PREF, true);
        
        CheckBox c = (CheckBox) findViewById(R.id.showNotifications);
        
        c.setChecked(notifi);
        		
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(NOTIFICATION_PREF, c.isChecked());
        editor.commit();
        
        notification = notifi;
        
        ShowVeranstaltungen.getInstance(getApplicationContext()).start();
        
	        
//        getApplicationContext().registerReceiver(new BroadcastReceiver() {
//			
//			@Override
//			public void onReceive(Context context, Intent intent) {
//				System.out.println(intent.toString());
//				
//			}
//		}, new IntentFilter());
        
//        Intent i = new Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel:+492593316"));
//        startActivity(i);
    }
    
    @Override
    protected void onNewIntent(Intent intent) {
    	// TODO Auto-generated method stub
    	super.onNewIntent(intent);
    }
    
    @Override
    public void onBackPressed() {
    	// TODO Auto-generated method stub
    	super.onBackPressed();
    	this.finish();
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	
    	//ShowVeranstaltungen.getInstance(getApplicationContext()).start();
    }
    
    public void notifChange(View v){
    	 SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
         CheckBox c = (CheckBox) findViewById(R.id.showNotifications);
         SharedPreferences.Editor editor = settings.edit();
         editor.putBoolean(NOTIFICATION_PREF, c.isChecked());
         editor.commit();
         notification = c.isChecked();
         
         if(notification){
        	 ShowVeranstaltungen.getInstance(getApplicationContext()).forceUpdate();
         }
    }
    
    public void buttonClicked(View v){
    	Toast.makeText(MainActivity.this, R.string.toasttext, 2000).show();
    	TextView t = new TextView(MainActivity.this);
    	t.setText(R.string.burgturmGeo);
    	Toast.makeText(MainActivity.this, t.getText(), 2000).show();
    	
    	Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(t.getText() + ""));
    	//geo:0,0?q=37.423156,-122.084917 (" + name + ")"
    	startActivity(intent);
    	
    }
    

    
    public void zweiteSeite(View v){
    	Intent i = new Intent(this, ZweiteSeite.class);
    	i.putExtra("DANIEL", "Dieses ist ein Übergabeparameter");
    	startActivity(i);
//    	Intent i = new Intent(this, ZweiteSeite.class);
//    	i.putExtra("DANIEL", "Dieses ist ein Übergabeparameter");
//    	startActivity(i);
    }
    
}