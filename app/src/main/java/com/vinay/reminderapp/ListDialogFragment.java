package com.vinay.reminderapp;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListDialogFragment extends DialogFragment {

	ArrayList<Integer> mSelectedItems;
	ArrayList<String> mSelectedData;
	String[] array;
	String title;
	@ArrayRes
	int arrayData;
	Context context;

	public ListDialogFragment() {
	}

	public static ListDialogFragment newInstance(String title, @ArrayRes int data) {
		// Required empty public constructor
		ListDialogFragment dialogFragment = new ListDialogFragment();
		dialogFragment.title = title;
		dialogFragment.arrayData = data;

		return dialogFragment;

	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		this.context = context;

	}


	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		mSelectedItems = new ArrayList();  // Where we track the selected items
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		// Set the dialog title
		if(title.contains("day")) {
			array = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
		}else if(title.contains("month")) {
			array = getActivity().getResources().getStringArray(R.array.months);
		}
		mSelectedData = new ArrayList<>();
		builder.setTitle(title)
				// Specify the list array, the items to be selected by default (null for none),
				// and the listener through which to receive callbacks when items are selected
				.setMultiChoiceItems(arrayData, null,
						new DialogInterface.OnMultiChoiceClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which,
							                    boolean isChecked) {
								if (isChecked) {
									// If the user checked the item, add it to the selected items
									mSelectedItems.add(which);
									mSelectedData.add(array[which]);
								} else if (mSelectedItems.contains(which)) {
									// Else, if the item is already in the array, remove it
									mSelectedItems.remove(Integer.valueOf(which));
								}
							}
						})
				// Set the action buttons
				.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {

						getActivity().getIntent().putExtra("name", mSelectedData.toString());
						getTargetFragment().onActivityResult(getTargetRequestCode(), Activity
								.RESULT_OK, getActivity().getIntent());
					}
				})
				.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						getTargetFragment().onActivityResult(getTargetRequestCode(), Activity
								.RESULT_CANCELED, getActivity().getIntent());
					}
				});

		return builder.create();
	}


}
