package com.vinay.reminderapp;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.util.Log;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.TaskParams;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyTaskService extends GcmTaskService {
	private static final String TAG = "MyTaskService";
	public static final String ACTION_DONE = "GcmTaskService#ACTION_DONE";
	public static final String TASK_TAG_PERIODIC = "periodic_task";
	public static final String EXTRA_TAG = "extra_tag";
	public static final String EXTRA_RESULT = "extra_result";
	private GcmNetworkManager mGcmNetworkManager;
	private BroadcastReceiver mReceiver;

	@Override
	public int onRunTask(TaskParams taskParams) {

		Log.d(TAG, "onRunTask: " + taskParams.getTag());

		String tag = taskParams.getTag();

		// Default result is success.
		int result = GcmNetworkManager.RESULT_SUCCESS;



		return result;
	}




}
