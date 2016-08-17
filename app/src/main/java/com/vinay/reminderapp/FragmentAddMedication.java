package com.vinay.reminderapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAddMedication extends Fragment {

	AppCompatEditText edt_interval;

	public FragmentAddMedication() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_add_medication, container, false);

		edt_interval = (AppCompatEditText) view.findViewById(R.id.edt_interval);

		edt_interval.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Fragment fragment = new FragmentIntervalList();
				FragmentManager manager = getFragmentManager();
				FragmentTransaction fragmentTransaction = manager.beginTransaction();
				fragmentTransaction.replace(R.id.container, fragment).addToBackStack
						(FragmentMedication.class.getSimpleName()).commit();
			}
		});

		return view;
	}

}
