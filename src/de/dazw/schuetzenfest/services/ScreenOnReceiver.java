package de.dazw.schuetzenfest.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class ScreenOnReceiver extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    //paramContext.startService(new Intent(paramContext, de.dazw.schuetzenfest.services.NotificationService.class));
    Log.i("ScreenOnReceiver", "Screen on!");
	  Toast.makeText(paramContext, "ScreenOnReceiver", 10000).show();
  }
}