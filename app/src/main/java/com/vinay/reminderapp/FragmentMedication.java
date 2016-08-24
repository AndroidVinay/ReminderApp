package com.vinay.reminderapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A placeholder fragment containing a simple view.
 */
public class FragmentMedication extends Fragment {

	View view;
	Button btnAddMedication, btnMap;
	AppCompatEditText edt_interval;
	Fragment fragment;
	FragmentTransaction fragmentTransaction;
	FragmentManager manager;
	public FragmentMedication() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {


		view = inflater.inflate(R.layout.fragment_medication, container, false);

		btnAddMedication = (Button) view.findViewById(R.id.btn_add_medication);
		btnMap = (Button) view.findViewById(R.id.btn_map);
		manager = getFragmentManager();
		fragmentTransaction = manager.beginTransaction();
		btnAddMedication.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				fragment = new FragmentAddMedication();
				fragmentTransaction.replace(R.id.container, fragment).addToBackStack
						(FragmentMedication.class.getSimpleName()).commit();

			}
		});

		btnMap.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				fragment = new MapAddressFragment();
				fragmentTransaction.replace(R.id.container, fragment).addToBackStack
						(FragmentMedication.class.getSimpleName()).commit();
			}
		});

		return view;
	}
}
