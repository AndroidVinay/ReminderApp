package com.vinay.reminderapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
	HashMap<String, String> map, map1, map2, map3, map4, map5;

	public FragmentIntervalList() {


	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		map = new HashMap<>();
		map.put("data", "One-time event");
		map.put("count", "");
		arrayIntervalList.add(map);
		map1 = new HashMap<>();
		map1.put("data", "Dialy");
		map1.put("count", "");
		arrayIntervalList.add(map1);
		map2 = new HashMap<>();
		map2.put("data", "Every X Days");
		map2.put("count", " 0 days");
		arrayIntervalList.add(map2);
		map3 = new HashMap<>();
		map3.put("data", "Weekly");
		map3.put("count", "Mon,Tue");
		arrayIntervalList.add(map3);
		map4 = new HashMap<>();
		map4.put("data", "Monthly");
		map4.put("count", "1,2,3");
		arrayIntervalList.add(map4);
		map5 = new HashMap<>();
		map5.put("data", "Every X Hours");
		map5.put("count", "0 hours");
		arrayIntervalList.add(map5);
	}

	@Override
	public View onCreateView(final LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_interval_list, container, false);
		intervalList = (ListView) view.findViewById(R.id.list_interval);


		simpleAdapter = new SimpleAdapter(getActivity(), arrayIntervalList, R.layout
				.row_interval_list, new String[]{"data", "count"}, new int[]{R.id
				.txt_interval_type, R.id.txt_count}) {

			@Override
			public View getView(final int position, View convertView, ViewGroup parent) {

				View view = super.getView(position, convertView, parent);
//				if (view == null){
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
								XInputFragment xInputFragment = new XInputFragment();
								xInputFragment.setTargetFragment(FragmentIntervalList.this, 1);
								getFragmentManager()
										.beginTransaction()
										.replace(R.id.container, xInputFragment, "null")
										.addToBackStack(null)
										.commit();

								break;
							case 3:
								DialogFragment dialogFrag = ListDialogFragment.newInstance
										("Select" +
												" days", R.array.weekDays);
								dialogFrag.setTargetFragment(FragmentIntervalList
										.this, 2);
								dialogFrag.show(getFragmentManager().beginTransaction(), "day");
								break;
							case 4:
								DialogFragment dialogFrag1 = ListDialogFragment.newInstance
										("Select month", R.array.months);
								dialogFrag1.setTargetFragment(FragmentIntervalList
										.this, 3);
								dialogFrag1.show(getFragmentManager().beginTransaction(),
										"month");
								break;
							case 5:
								XInputFragment xInputFragment1 = new XInputFragment();
								xInputFragment1.setTargetFragment(FragmentIntervalList.this, 4);
								getFragmentManager()
										.beginTransaction()
										.replace(R.id.container, xInputFragment1, "null")
										.addToBackStack(null)
										.commit();

								break;
							default:
								break;


						}
						notifyDataSetChanged();
					}
				});
//			}
				return view;

			}
		};

		intervalList.setAdapter(simpleAdapter);

		return view;
	}





	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {


		switch (requestCode) {

			case 1:
				if (resultCode == Activity.RESULT_OK) {

					Bundle bundle = data.getExtras();
					String result = bundle.getString("name");
					Toast.makeText(getActivity(), "1 : " + result, Toast.LENGTH_SHORT).show();
					map2.put("count", result + " days");
				} else if (resultCode == Activity.RESULT_CANCELED) {

				}
				break;
			case 2:
				if (resultCode == Activity.RESULT_OK) {

					Bundle bundle = data.getExtras();
					String result = bundle.getString("name");
					Toast.makeText(getActivity(), "2 : " + result, Toast.LENGTH_SHORT).show();
					result = result.replaceAll("\\[", "").replaceAll("\\]","");
					map3.put("count", result + " ");
					simpleAdapter.notifyDataSetChanged();
				} else if (resultCode == Activity.RESULT_CANCELED) {

				}
				break;
			case 3:
				if (resultCode == Activity.RESULT_OK) {

					Bundle bundle = data.getExtras();
					String result = bundle.getString("name");
					Toast.makeText(getActivity(), "3 : " + result, Toast.LENGTH_SHORT).show();
					result = result.replaceAll("\\[", "").replaceAll("\\]","");
					map4.put("count", result + " ");
					simpleAdapter.notifyDataSetChanged();
				} else if (resultCode == Activity.RESULT_CANCELED) {

				}
				break;
			case 4:
				if (resultCode == Activity.RESULT_OK) {

					Bundle bundle = data.getExtras();
					String result = bundle.getString("name");
					Toast.makeText(getActivity(), "4 : " + result, Toast.LENGTH_SHORT).show();
					map5.put("count", result + " hours");
				} else if (resultCode == Activity.RESULT_CANCELED) {

				}
				break;

		}

	}



}


