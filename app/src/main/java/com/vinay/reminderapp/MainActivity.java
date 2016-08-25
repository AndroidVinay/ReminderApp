package com.vinay.reminderapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.PeriodicTask;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = MainActivity.class.getSimpleName();
	private static final int RC_PLAY_SERVICES = 123;

	public static final String TASK_TAG_PERIODIC = "periodic_task";

	private GcmNetworkManager mGcmNetworkManager;
	private BroadcastReceiver mReceiver;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		mGcmNetworkManager = GcmNetworkManager.getInstance(this);
		// [END get_gcm_network_manager]

		startPeriodicTask();

		// BroadcastReceiver to get information from MyTaskService about task completion.
		mReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				if (intent.getAction().equals(MyTaskService.ACTION_DONE)) {
					String tag = intent.getStringExtra(MyTaskService.EXTRA_TAG);
					int result = intent.getIntExtra(MyTaskService.EXTRA_RESULT, -1);

					String msg = String.format("DONE: %s (%d)", tag, result);
					Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
				}
			}
		};
		//this is to stop screenshot of application
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
				WindowManager.LayoutParams.FLAG_SECURE);
		Fragment fragment = new FragmentMedication();
		FragmentManager manager = getSupportFragmentManager();
		manager.beginTransaction().replace(R.id.container, fragment).addToBackStack(MainActivity
				.class.getSimpleName()).commit();

	}

	public void startPeriodicTask() {
		Log.d(TAG, "startPeriodicTask");

		// [START start_periodic_task]
		PeriodicTask task = new PeriodicTask.Builder()
				.setService(MyTaskService.class)
				.setTag(TASK_TAG_PERIODIC)
				.setPeriod(30L)
				.build();

		mGcmNetworkManager.schedule(task);
		// [END start_periodic_task]
	}


}
