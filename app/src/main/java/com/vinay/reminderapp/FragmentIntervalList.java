package com.vinay.reminderapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentIntervalList extends Fragment {

	ListView intervalList;
	SimpleAdapter simpleAdapter;
	ArrayList<HashMap<String, String>> arrayIntervalList = new ArrayList<>();

	public FragmentIntervalList() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_interval_list, container, false);
		intervalList = (ListView) view.findViewById(R.id.list_interval);
		HashMap<String, String> map = new HashMap<>();
		map.put("data", "One-time event");
		arrayIntervalList.add(map);
		HashMap<String, String> map1 = new HashMap<>();
		map1.put("data", "Dialy");
		arrayIntervalList.add(map1);
		HashMap<String, String> map2 = new HashMap<>();
		map2.put("data", "Every X Days");
		arrayIntervalList.add(map2);
		HashMap<String, String> map3 = new HashMap<>();
		map3.put("data", "Weekly");
		arrayIntervalList.add(map3);
		HashMap<String, String> map4 = new HashMap<>();
		map4.put("data", "Monthly");
		arrayIntervalList.add(map4);
		HashMap<String, String> map5 = new HashMap<>();
		map5.put("data", "Every X Hours");
		arrayIntervalList.add(map5);

		simpleAdapter = new SimpleAdapter(getActivity(), arrayIntervalList, R.layout
				.row_interval_list, new String[]{"data"}, new int[]{R.id.txt_interval_type});

		intervalList.setAdapter(simpleAdapter);


		return view;
	}

}
