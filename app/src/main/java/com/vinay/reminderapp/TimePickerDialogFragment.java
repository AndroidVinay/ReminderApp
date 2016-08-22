//package com.vinay.reminderapp;
//
//
//import android.app.Dialog;
//import android.app.TimePickerDialog;
//import android.content.Context;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v4.app.DialogFragment;
//import android.support.v4.app.Fragment;
//import android.widget.TimePicker;
//import android.widget.Toast;
//
//import java.util.Calendar;
//
//
///**
// * A simple {@link Fragment} subclass.
// */
//public class TimePickerDialogFragment extends DialogFragment {
//Context context;
//	private int mYear, mMonth, mDay, mHour, mMinute;
//	Calendar cal;
//	public TimePickerDialogFragment() {
//		// Required empty public constructor
//	}
//
//	@Override
//	public void onAttach(Context context) {
//		super.onAttach(context);
//		this.context = context;
//		cal = Calendar.getInstance();
//	}
//
//	@NonNull
//	@Override
//	public Dialog onCreateDialog(Bundle savedInstanceState) {
//		return super.onCreateDialog(savedInstanceState);
//		// Get Current Time
//
//		mHour = cal.get(Calendar.HOUR_OF_DAY);
//		mMinute = cal.get(Calendar.MINUTE);
//
//		// Launch Time Picker Dialog
//		TimePickerDialog timePickerDialog = new TimePickerDialog(context,
//				new TimePickerDialog.OnTimeSetListener() {
//
//					@Override
//					public void onTimeSet(TimePicker view, int hourOfDay,
//					                      int minute) {
//
//
//						Toast.makeText(context, hourOfDay + ":" + minute, Toast.LENGTH_SHORT)
//								.show();
//
//					}
//				}, mHour, mMinute, false);
//		timePickerDialog.show();
//	}
//}
