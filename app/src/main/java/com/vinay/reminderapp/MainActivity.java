package com.vinay.reminderapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	Fragment fragment = new FragmentMedication();
		FragmentManager manager = getSupportFragmentManager();
		manager.beginTransaction().replace(R.id.container,fragment).addToBackStack(MainActivity.class.getSimpleName()).commit();
	}

}
