package com.vinay.reminderapp;


import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
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

	SimpleAdapter simpleAdapter;
	ListView timeList;
	ArrayList<HashMap<String, String>> arrayIntervalList = new ArrayList<>();
	int selectedPosition = 0;
	HashMap<String, String> map1, map2, map3, map4, map5, map6, map7, map8, map9, map10, map11,
			map12, map13, map14, map15, map16, map17, map18, map19, map20, map21, map22, map23,
			map24;
	private int mYear, mMonth, mDay, mHour, mMinute;

	AppCompatButton btn_add_time;

	public FragmentScheduleDosage() {
		// Required empty public constructor
	}


	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

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
			map1.put("dose", "");
			arrayIntervalList.add(map1);
			map2 = new HashMap<>();
			map2.put("hr", k);
			map2.put("min", "30");
			map2.put("dose", "");
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
			public View getView(int position, View convertView, ViewGroup parent) {
				View view = super.getView(position, convertView, parent);

				add = (ImageButton) view.findViewById(R.id.add_button);
				minus = (ImageButton) view.findViewById(R.id.minus_button);
				txt_count = (AppCompatTextView) view.findViewById(R.id.txt_count);

				final double[] count = {0.5};
				add.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						count[0] = count[0] + 0.5;
						txt_count.setText(count[0] + "");
					}
				});
				minus.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						count[0] = count[0] - 0.5;
						txt_count.setText(count[0] + "");
					}
				});


				return view;
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

}
