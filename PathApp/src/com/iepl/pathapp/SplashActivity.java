package com.iepl.pathapp;

import org.androidannotations.annotations.EActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * The Class SplashActivity.
 */
@EActivity(R.layout.splash_layout)
public class SplashActivity extends Activity {
	
	/** The Constant SPLASH_TIME_OUT. */
	private static final int SPLASH_TIME_OUT = 3000;
	
	/* 
	 * This method is the first in the android activity life cycle.
	 */
	@Override
	protected void onCreate(Bundle savedeState) {		
		super.onCreate(savedeState);
				
		new Handler().postDelayed(new Runnable() {
			
			/* 
			 * this method is used to start the next activity after a delay.
			 */
			@Override
			public void run() {
				final Intent intent = new Intent(SplashActivity.this, ActivityChooserActivity_.class);
				startActivity(intent);
				finish();
			}
		}, SPLASH_TIME_OUT);
	}
}
