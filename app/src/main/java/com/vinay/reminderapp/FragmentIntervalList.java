package com.vinay.reminderapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentIntervalList extends Fragment {

	ListView intervalList;
	SimpleAdapter simpleAdapter;
	ArrayList<HashMap<String, String>> arrayIntervalList = new ArrayList<>();
	int selectedPosition = 0;
	AppCompatCheckBox rdb_interval;

	public FragmentIntervalList() {


	}


	@Override
	public View onCreateView(final LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_interval_list, container, false);
		intervalList = (ListView) view.findViewById(R.id.list_interval);
		HashMap<String, String> map = new HashMap<>();
		map.put("data", "One-time event");
		map.put("count", "");
		arrayIntervalList.add(map);
		HashMap<String, String> map1 = new HashMap<>();
		map1.put("data", "Dialy");
		map1.put("count", "");
		arrayIntervalList.add(map1);
		HashMap<String, String> map2 = new HashMap<>();
		map2.put("data", "Every X Days");
		map2.put("count", " 0 days");
		arrayIntervalList.add(map2);
		HashMap<String, String> map3 = new HashMap<>();
		map3.put("data", "Weekly");
		map3.put("count", "Mon,Tue");
		arrayIntervalList.add(map3);
		HashMap<String, String> map4 = new HashMap<>();
		map4.put("data", "Monthly");
		map4.put("count", "1,2,3");
		arrayIntervalList.add(map4);
		HashMap<String, String> map5 = new HashMap<>();
		map5.put("data", "Every X Hours");
		map5.put("count", "0 hours");
		arrayIntervalList.add(map5);

		simpleAdapter = new SimpleAdapter(getActivity(), arrayIntervalList, R.layout
				.row_interval_list, new String[]{"data", "count"}, new int[]{R.id
				.txt_interval_type, R.id.txt_count}) {

			@Override
			public View getView(final int position, View convertView, ViewGroup parent) {

				View view = super.getView(position, convertView, parent);

				rdb_interval = (AppCompatCheckBox) view.findViewById(R.id.rdb_interval);

				rdb_interval.setTag(position);

				if (position == selectedPosition) {
					rdb_interval.setChecked(true);
				} else {
					rdb_interval.setChecked(false);
				}

				view.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						selectedPosition = position;

						switch (position) {

							case 0:
								break;
							case 1:
								break;
							case 2:


								break;
							case 3:
								DialogFragment dialogFrag = ListDialogFragment.newInstance
										("Select" +
										" days", R.array.weekDays);
								dialogFrag.setTargetFragment(FragmentIntervalList
										.this, 1);
								dialogFrag.show(getFragmentManager().beginTransaction(), "day");
								break;
							case 4:
								DialogFragment dialogFrag1 = ListDialogFragment.newInstance
										("Select month", R.array.months);
								dialogFrag1.setTargetFragment(FragmentIntervalList
										.this, 1);
								dialogFrag1.show(getFragmentManager().beginTransaction(),
										"month");
								break;
							case 5:
								break;
							default:
								break;


						}
						notifyDataSetChanged();
					}
				});

				return view;

			}
		};

		intervalList.setAdapter(simpleAdapter);

		return view;
	}


	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
//		super.onActivityResult(requestCode, resultCode, data);
//		switch (requestCode){

		if (resultCode == Activity.RESULT_OK) {
			// After Ok code.
			Bundle bundle = data.getExtras();
			String result = bundle.getString("name");
			Toast.makeText(getActivity(), "" + result, Toast.LENGTH_SHORT).show();
		} else if (resultCode == Activity.RESULT_CANCELED) {
			// After Cancel code.
		}
//		}
	}
}

//class intervalModel {
//
//	String data;
//	String count;
//
//	public intervalModel(String data, String count) {
//		this.data = data;
//		this.count = count;
//	}
//
//	public String getData() {
//		return data;
//	}
//
//	public void setData(String data) {
//		this.data = data;
//	}
//
//	public String getCount() {
//		return count;
//	}
//
//	public void setCount(String count) {
//		this.count = count;
//	}
//}

