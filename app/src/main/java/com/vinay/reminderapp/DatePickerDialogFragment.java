package com.vinay.reminderapp;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickerDialogFragment extends DialogFragment
		implements DatePickerDialog.OnDateSetListener {


	public DatePickerDialogFragment() {
		// Required empty public constructor
	}

	@NonNull
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);

		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}

	@Override
	public void onDateSet(DatePicker datePicker, int year, int month, int day) {
		month = month + 1;

		getActivity().getIntent().putExtra("name", day + "/" + month + "/" + year);
		getTargetFragment().onActivityResult(getTargetRequestCode(), Activity
				.RESULT_OK, getActivity().getIntent());
	}
}
