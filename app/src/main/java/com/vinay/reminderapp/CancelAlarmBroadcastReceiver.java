package com.vinay.reminderapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CancelAlarmBroadcastReceiver extends BroadcastReceiver {
	public CancelAlarmBroadcastReceiver() {
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		PendingIntent pendingIntent = intent.getParcelableExtra("key");
		AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		am.cancel(pendingIntent);
	}
}
