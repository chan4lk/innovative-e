package com.iepl.pathapp;

import org.androidannotations.annotations.EActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

@EActivity(R.layout.splash_layout)
public class SplashActivity extends Activity {
	private static int SPLASH_TIME_OUT = 3000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
				
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Intent intent = new Intent(SplashActivity.this, MapActivity_.class);
				startActivity(intent);
				finish();
			}
		}, SPLASH_TIME_OUT);
	}
}
