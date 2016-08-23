package com.vinay.reminderapp;


import android.app.Activity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentScheduleDosage extends Fragment {

	private static String TAG = FragmentScheduleDosage.class.getSimpleName();
	SimpleAdapter simpleAdapter;
	ListView timeList;
	ArrayList<HashMap<String, String>> arrayIntervalList = new ArrayList<>();
	int selectedPosition = 0;
	HashMap<String, String> map1, map2;
	private int mYear, mMonth, mDay, mHour, mMinute;

	AppCompatButton btn_add_time;

	public FragmentScheduleDosage() {
		// Required empty public constructor
	}


	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		for (int i = 0; i < 24; i++) {
			String k;
			map1 = new HashMap<>();

			if (i < 10) {
				k = "0" + i;
			} else {
				k = "" + i;
			}

			map1.put("hr", k);
			map1.put("min", "00");
			map1.put("count", "");
			arrayIntervalList.add(map1);
			map2 = new HashMap<>();
			map2.put("hr", k);
			map2.put("min", "30");
			map2.put("count", "");
			arrayIntervalList.add(map2);
		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_schedule_dosage, container, false);

		btn_add_time = (AppCompatButton) view.findViewById(R.id.add_time);
		timeList = (ListView) view.findViewById(R.id.timelist);
		simpleAdapter = new SimpleAdapter(getActivity(), arrayIntervalList, R.layout
				.row_dosage_list, new String[]{"hr", "min", "count"}, new int[]{R.id
				.txt_hour, R.id.txt_minute, R.id.txt_count}) {

			ImageButton add, minus;
			AppCompatTextView txt_count;

			@Override
			public View getView(final int position, View convertView, final ViewGroup parent) {

//				if (convertView == null) {
				convertView = super.getView(position, convertView, parent);

				add = (ImageButton) convertView.findViewById(R.id.add_button);
				minus = (ImageButton) convertView.findViewById(R.id.minus_button);
//				}
				final double[] count = {0.5};

				add.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						count[0] = count[0] + 0.5;
						View v = (View) view.getParent();
						txt_count = (AppCompatTextView) v.findViewById(R.id.txt_count);
						txt_count.setText("" + count[0]);
						arrayIntervalList.get(position).put("count", count[0] + "");

					}
				});
				minus.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						count[0] = count[0] - 0.5;
						View v = (View) view.getParent();
						txt_count = (AppCompatTextView) v.findViewById(R.id.txt_count);
						txt_count.setText("" + count[0]);
						arrayIntervalList.get(position).put("count", count[0] + "");
					}
				});

				notifyDataSetChanged();

				return convertView;

			}


			@Override
			public Object getItem(int position) {
				return position;
			}


			@Override
			public long getItemId(int position) {
				return position;
			}

			@Override
			public int getCount() {
				return arrayIntervalList.size();
			}
		};
		timeList.setAdapter(simpleAdapter);


		btn_add_time.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				timeDialog();
			}
		});
		return view;
	}

	public void timeDialog() {
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);

		final TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
				new TimePickerDialog.OnTimeSetListener() {

					@Override
					public void onTimeSet(TimePicker view, int hourOfDay,
					                      int minute) {
						HashMap<String, String> map = new HashMap<>();

						for (int i = 0; i < arrayIntervalList.size(); i++) {


							if (hourOfDay < Integer.parseInt(arrayIntervalList.get(i).get("hr"))) {

								if (minute < Integer.parseInt(arrayIntervalList.get(i).get("min")
								)) {

									if (hourOfDay < 10) {
										map.put("hr", "0" + hourOfDay);
									} else {
										map.put("hr", hourOfDay + "");
									}

									if (minute < 10) {
										map.put("min", "0" + minute);
									} else {
										map.put("min", "" + minute);
									}

									map.put("dose", "");
									arrayIntervalList.add(i - 2, map);
									simpleAdapter.notifyDataSetChanged();
								} else if (minute > Integer.parseInt(arrayIntervalList.get(i).get
										("min"))) {
									if (hourOfDay < 10) {
										map.put("hr", "0" + hourOfDay);
									} else {
										map.put("hr", hourOfDay + "");
									}

									if (minute < 10) {
										map.put("min", "0" + minute);
									} else {
										map.put("min", "" + minute);
									}
									map.put("dose", "");
									arrayIntervalList.add(i - 1, map);

								}
								break;
							}

						}
						Toast.makeText(getActivity(), hourOfDay + ":" + minute, Toast.LENGTH_SHORT)
								.show();

					}
				}, mHour, mMinute, false);
		timePickerDialog.show();

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

				ArrayList<HashMap<String, String>> tempData = new ArrayList<>();

				for (int i = 0; i < arrayIntervalList.size(); i++) {
					Log.d(TAG, " This is data: " + arrayIntervalList.get(i).get("count").toString
							());
					if (!(arrayIntervalList.get(i).get("count").toString().isEmpty()) || !
							(arrayIntervalList.get(i).get("count").equalsIgnoreCase(""))) {
						if (Double.parseDouble(arrayIntervalList.get(i).get("count").toString())
								>= 0.5) {
							tempData.add(arrayIntervalList.get(i));
						}
					}
				}

				getActivity().getIntent().putExtra("name", tempData);
				getTargetFragment().onActivityResult(getTargetRequestCode(), Activity
						.RESULT_OK, getActivity().getIntent());
				getActivity().onBackPressed();

		}

		return super.onOptionsItemSelected(item);
	}
}
