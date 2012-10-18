package de.dazw.schuetzenfest;

import java.util.Calendar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import de.dazw.schuetzenfest.sonst.Constants;

public class MainActivity extends ParentActivity {
	
	public static boolean notification = true;
	
	//public static boolean fromNotification = false;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.main);
        
//		IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
//		//filter.addAction(Intent.ACTION_SCREEN_OFF);
//		BroadcastReceiver mReceiver = new ScreenOnReceiver();
//		getApplicationContext().registerReceiver(mReceiver, filter);
        
//        try {
//			URL url = new URL("http://www.google.com/ig/images/weather/sunny.gif");
//			InputSource inputSource = new InputSource(url.openStream());
//			ImageView iv = (ImageView) findViewById(R.id.wetterdatenimageView1);
//			Bitmap decodeStream = BitmapFactory.decodeStream(inputSource.getByteStream());
//			//iv.setImageURI(Uri.parse("http://www.google.com/ig/images/weather/sunny.gif"));
//			iv.setImageBitmap(decodeStream);
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        
        SharedPreferences settings = getSharedPreferences(Constants.PREFS_NAME, 0);
        boolean notifi = settings.getBoolean(Constants.NOTIFICATION_PREF, true);
        
        CheckBox c = (CheckBox) findViewById(R.id.showNotifications);
        
        c.setChecked(notifi);
        CharSequence text = c.getText();
        		
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(Constants.NOTIFICATION_PREF, c.isChecked());
        editor.commit();
        
        notification = notifi;
        
        if(notification){
        	ShowVeranstaltungen.getInstance(getApplicationContext()).start();
        } else{
        	ShowVeranstaltungen.getInstance(getApplicationContext()).stop();
        }
        
        
        //Veranstaltung v = ShowVeranstaltungen.getInstance(getApplicationContext()).verList.get(0);
        
        //calenderevent(v.getAnfang(), v.getEnde(), v.getName());
        
        //getApplicationContext().registerReceiver(new ScreenOnReceiver(), new IntentFilter(Intent.ACTION_TIME_TICK));
        
	        
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
    
	public void calenderevent(Calendar begintime, Calendar endtime, String str){

	    Intent intent = new Intent(Intent.ACTION_EDIT);
	    intent.setType("vnd.android.cursor.item/event");
	    intent.putExtra("beginTime", begintime.getTimeInMillis());
	    intent.putExtra("allDay", true);
	    intent.putExtra("rrule", "FREQ=YEARLY");
	    intent.putExtra("endTime", endtime.getTimeInMillis());
	    intent.putExtra("title", str);
	    startActivity(intent);
	}
    
    @Override
    protected void onNewIntent(Intent intent) {
    	super.onNewIntent(intent);
    }
    
    @Override
    public void onBackPressed() {
    	super.onBackPressed();
    	this.finish();
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	
        if(notification){
        	ShowVeranstaltungen.getInstance(getApplicationContext()).start();
        } else{
        	ShowVeranstaltungen.getInstance(getApplicationContext()).stop();
        }
    }
    
    public void notifChange(View v){
    	 SharedPreferences settings = getSharedPreferences(Constants.PREFS_NAME, 0);
         CheckBox c = (CheckBox) findViewById(R.id.showNotifications);
         SharedPreferences.Editor editor = settings.edit();
         editor.putBoolean(Constants.NOTIFICATION_PREF, c.isChecked());
         editor.commit();
         notification = c.isChecked();
         
         if(notification){
        	 Toast.makeText(getApplicationContext(), "Sie werden jetzt 1 Stunde vor jedem Event benachrichtigt!", 2500).show();
        	 ShowVeranstaltungen.getInstance(getApplicationContext()).start();
        	 ShowVeranstaltungen.getInstance(getApplicationContext()).forceUpdate();
         }else{
        	 ShowVeranstaltungen.getInstance(getApplicationContext()).stop();
        	 Toast.makeText(getApplicationContext(), "Benachrichtigung deaktiviert!", 2500).show();
         }
         
         
//         String longMessage ="longMessage";
//         LayoutInflater inflater = getLayoutInflater();
//         final View checkboxLayout = inflater.inflate(R.layout.wetterdaten, null);
////         CheckBox cb = (CheckBox)checkboxLayout.findViewById(R.id.checkBox);
////         TextView tv = (TextView)checkboxLayout.findViewById(R.id.message);
//        // tv.setText(longMessage);
//         AlertDialog.Builder builder = new AlertDialog.Builder(this)
//         .setTitle("Some message")
//         .setView(checkboxLayout)
//         .setNegativeButton("NÖ",  new DialogInterface.OnClickListener() {
//             public void onClick(DialogInterface dialog, int which) {
//             }
//         })
//         .setPositiveButton("Ok",
//             new DialogInterface.OnClickListener() {
//             public void onClick(DialogInterface dialog, int which) {
//             }
//         });
//         AlertDialog dialog = builder.create();
//         dialog.show();
         
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
    
    public void mapsClicked(View v){
    	Intent i = new Intent(this, SFMapActivity.class);
//    	i.putExtra(Constants.LATITUDE, Double.parseDouble("51.82073"));
//    	i.putExtra(Constants.LONGITUDE, Double.parseDouble("7.59207"));
    	startActivity(i);
    }
    
    public void veranstaltungskalenderClicked(View v){
    	Intent i = new Intent(this, VeranstaltungTabActivity.class);
    	startActivity(i);
    }
    
    public void wetterClicked(View v){
    	Intent i = new Intent(this, WetterActivity.class);
    	startActivity(i);
    }
    
    public void davertliedClicked(View v){
    	Intent i = new Intent(this, DavertliedActivity.class);
    	startActivity(i);
    }
    
    public void zweiteSeite(View v){
    	Intent i = new Intent(this, WetterActivity.class);
    	i.putExtra("DANIEL", "Dieses ist ein Übergabeparameter");
    	startActivity(i);
//    	Intent i = new Intent(this, ZweiteSeite.class);
//    	i.putExtra("DANIEL", "Dieses ist ein Übergabeparameter");
//    	startActivity(i);
    }
    
}