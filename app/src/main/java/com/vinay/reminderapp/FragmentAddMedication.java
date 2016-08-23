package com.vinay.reminderapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
	AppCompatButton btn_startDate, btn_endDate;
	private int mYear, mMonth, mDay, mHour, mMinute;

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


		edt_interval.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Fragment fragment = new FragmentIntervalList();
				FragmentManager manager = getFragmentManager();
				fragment.setTargetFragment(FragmentAddMedication.this, 3);
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
				fragment.setTargetFragment(FragmentAddMedication.this, 4);
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
						.this, 1);
				dialogFrag.show(getFragmentManager().beginTransaction(), "day");
			}
		});

		btn_endDate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				DialogFragment dialogFrag = new DatePickerDialogFragment();
				dialogFrag.setTargetFragment(FragmentAddMedication
						.this, 2);
				dialogFrag.show(getFragmentManager().beginTransaction(), "day");
			}
		});

		return view;
	}


	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		switch (requestCode) {

			case 1:
				if (resultCode == Activity.RESULT_OK) {
					Bundle bundle = data.getExtras();
					String result = bundle.getString("name");
					Toast.makeText(getActivity(), "Start Date " + result, Toast.LENGTH_SHORT)
							.show();
					btn_startDate.setText(result);
				}
				break;

			case 2:
				if (resultCode == Activity.RESULT_OK) {
					Bundle bundle = data.getExtras();
					String result = bundle.getString("name");
					Toast.makeText(getActivity(), "End Date " + result, Toast.LENGTH_SHORT).show();
					btn_endDate.setText(result);
				}
				break;

			case 3:
				if (resultCode == Activity.RESULT_OK) {
					Bundle bundle = data.getExtras();
					String result = bundle.getString("name");
					Toast.makeText(getActivity(), "End Date " + result, Toast.LENGTH_SHORT).show();
//					btn_endDate.setText(result);
				}
				break;

			case 4:
				if (resultCode == Activity.RESULT_OK) {
					Bundle bundle = data.getExtras();
					ArrayList<HashMap<String,String>>  tempData= (ArrayList<HashMap<String,
							String>>) bundle.getSerializable("name");
					Toast.makeText(getActivity(), "End Date " + tempData.toString(), Toast.LENGTH_SHORT).show();
				}
				break;
		}
	}
}
