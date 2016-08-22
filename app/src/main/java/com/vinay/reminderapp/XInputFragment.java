package com.vinay.reminderapp;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class XInputFragment extends Fragment {

	AppCompatEditText edtData;

	public XInputFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_xinput, container, false);

		edtData = (AppCompatEditText) view.findViewById(R.id.edt_count);

		return view;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.main_xinput, menu);
		super.onCreateOptionsMenu(menu, inflater);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		switch (id) {

			case R.id.done:

				if (edtData.getText().toString() != null)
					getActivity().getIntent().putExtra("name", edtData.getText().toString());
				getTargetFragment().onActivityResult(getTargetRequestCode(), Activity
						.RESULT_OK, getActivity().getIntent());
				getActivity().onBackPressed();

		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onStop() {
		super.onStop();
	}
}
