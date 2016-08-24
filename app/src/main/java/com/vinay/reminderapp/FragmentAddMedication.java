package com.vinay.reminderapp;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAddMedication extends Fragment {

	AppCompatEditText edt_interval, edt_schedule;
	AppCompatButton btn_startDate, btn_endDate, btn_save;
	private int mYear, mMonth, mDay, mHour, mMinute;
	public static final int INTERVAL_REQUEST = 3;
	public static final int SCHEDULE_DOSAGE_REQUEST = 4;
	public static final int STARTDATE_REQUEST = 1;
	public static final int ENDDATE_REQUEST = 2;
	String intervalResult = "";
	String DosageResult = "";

	public FragmentAddMedication() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_add_medication, container, false);

		edt_interval = (AppCompatEditText) view.findViewById(R.id.edt_interval);
		edt_schedule = (AppCompatEditText) view.findViewById(R.id.edt_schedule);
		btn_startDate = (AppCompatButton) view.findViewById(R.id.btn_startdate);
		btn_endDate = (AppCompatButton) view.findViewById(R.id.btn_end_date);
		btn_save = (AppCompatButton) view.findViewById(R.id.btn_save);


		edt_interval.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Fragment fragment = new FragmentIntervalList();
				FragmentManager manager = getFragmentManager();
				fragment.setTargetFragment(FragmentAddMedication.this, INTERVAL_REQUEST);
				FragmentTransaction fragmentTransaction = manager.beginTransaction();
				fragmentTransaction.replace(R.id.container, fragment).addToBackStack
						(FragmentMedication.class.getSimpleName()).commit();
			}
		});

		edt_schedule.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Fragment fragment = new FragmentScheduleDosage();
				FragmentManager manager = getFragmentManager();
				fragment.setTargetFragment(FragmentAddMedication.this, SCHEDULE_DOSAGE_REQUEST);
				FragmentTransaction fragmentTransaction = manager.beginTransaction();
				fragmentTransaction.replace(R.id.container, fragment).addToBackStack
						(FragmentMedication.class.getSimpleName()).commit();
			}
		});

		btn_startDate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				DialogFragment dialogFrag = new DatePickerDialogFragment();
				dialogFrag.setTargetFragment(FragmentAddMedication
						.this, STARTDATE_REQUEST);
				dialogFrag.show(getFragmentManager().beginTransaction(), "day");
			}
		});

		btn_endDate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				DialogFragment dialogFrag = new DatePickerDialogFragment();
				dialogFrag.setTargetFragment(FragmentAddMedication
						.this, ENDDATE_REQUEST);
				dialogFrag.show(getFragmentManager().beginTransaction(), "day");
			}
		});


		btn_save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startAlert();
			}
		});

		return view;
	}

	@Override
	public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
		super.onViewStateRestored(savedInstanceState);

		edt_interval.setText(intervalResult);
		edt_schedule.setText(DosageResult);
	}


	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		switch (requestCode) {

			case STARTDATE_REQUEST:
				if (resultCode == Activity.RESULT_OK) {
					Bundle bundle = data.getExtras();
					String result = bundle.getString("name");
					Toast.makeText(getActivity(), "Start Date " + result, Toast.LENGTH_SHORT)
							.show();
					btn_startDate.setText(result);
				}
				break;

			case ENDDATE_REQUEST:
				if (resultCode == Activity.RESULT_OK) {
					Bundle bundle = data.getExtras();
					String result = bundle.getString("name");
					Toast.makeText(getActivity(), "End Date " + result, Toast.LENGTH_SHORT).show();
					btn_endDate.setText(result);
				}
				break;

			case INTERVAL_REQUEST:
				if (resultCode == Activity.RESULT_OK) {
					Bundle bundle = data.getExtras();
					String result = bundle.getString("name");
					Toast.makeText(getActivity(), "Interval " + result, Toast.LENGTH_SHORT).show();
					intervalResult = result;

				}
				break;

			case SCHEDULE_DOSAGE_REQUEST:
				if (resultCode == Activity.RESULT_OK) {
					Bundle bundle = data.getExtras();
					ArrayList<HashMap<String, String>> tempData = (ArrayList<HashMap<String,
							String>>) bundle.getSerializable("name");

					String result = "";
					for (int i = 0; i < tempData.size(); i++) {
						String hr = tempData.get(i).get("hr");
						String min = tempData.get(i).get("min");
						String count = tempData.get(i).get("count");
						if (i == (tempData.size() - 1)) {
							result = result + hr + ":" + min + "(" + count + ")";
						} else {
							result = result + hr + ":" + min + "(" + count + ") ; ";
						}
					}

					Toast.makeText(getActivity(), "Schedule " + result, Toast.LENGTH_SHORT).show();
					DosageResult = result;

				}
				break;
		}
	}

	public void startAlert() {
		int i = Integer.parseInt("8");
		Intent intent = new Intent(getActivity(), MyBroadCastReceiver.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(
				getActivity().getApplicationContext(), 234324243, intent, 0);
		AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(getActivity()
				.ALARM_SERVICE);
		alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
				+ (i * 1000), pendingIntent);
		Toast.makeText(getActivity(), "Alarm set in " + i + " seconds", Toast.LENGTH_LONG).show();
	}
}
