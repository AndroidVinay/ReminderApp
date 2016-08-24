package com.vinay.reminderapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//this is to stop screenshot of application
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
				WindowManager.LayoutParams.FLAG_SECURE);
		Fragment fragment = new FragmentMedication();
		FragmentManager manager = getSupportFragmentManager();
		manager.beginTransaction().replace(R.id.container, fragment).addToBackStack(MainActivity
				.class.getSimpleName()).commit();
	}

}
