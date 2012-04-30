package de.dazw.schuetzenfest.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootCompleteReceiver extends BroadcastReceiver {
	
	public void onReceive(Context paramContext, Intent paramIntent) {
		
		paramContext.startService(new Intent(paramContext,
				de.dazw.schuetzenfest.services.NotificationService.class));
	}
}