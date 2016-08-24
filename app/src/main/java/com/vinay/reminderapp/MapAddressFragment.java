package com.vinay.reminderapp;


import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapAddressFragment extends Fragment implements OnMapReadyCallback {

	private static String TAG = MapAddressFragment.class.getSimpleName();
	private View rootView;
	MapView mMapView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
			savedInstanceState) {
		try {
			rootView = inflater.inflate(R.layout.fragment_address_data, container, false);
			MapsInitializer.initialize(this.getActivity());
			mMapView = (MapView) rootView.findViewById(R.id.map);
			mMapView.onCreate(savedInstanceState);
			mMapView.getMapAsync(this);
		} catch (InflateException e) {
			Log.e(TAG, "Inflate exception");
		}
		return rootView;
	}

	@Override
	public void onPause() {
		super.onPause();
		mMapView.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mMapView.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mMapView.onSaveInstanceState(outState);
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		mMapView.onLowMemory();
	}

	@Override
	public void onResume() {
		super.onResume();
		mMapView.onResume();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	@Override
	public void onMapReady(GoogleMap googleMap) {
		{
			double latitude = 0, longitude = 0;
			Geocoder geocoder = new Geocoder(getActivity());
			List<Address> addresses;
			try {
				addresses = geocoder.getFromLocationName("Appasaheb Marathe Marg, Prabhadevi " +
						"Mumbai – 400 025, INDIA", 1);
				if (addresses.size() > 0) {
					latitude = addresses.get(0).getLatitude();
					longitude = addresses.get(0).getLongitude();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}


			googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude))
					.title("Marker"));

			googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,
					longitude), 12.0f));
		}

	}

}

