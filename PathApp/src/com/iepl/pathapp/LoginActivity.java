package com.iepl.pathapp;

import org.androidannotations.annotations.EActivity;

import com.iepl.pathapp.fragment.SigninFragment_;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

@EActivity
public class LoginActivity extends FragmentActivity {
	
	 
	@Override
	protected void onCreate(Bundle bundle) {		
		super.onCreate(bundle);
		setContentView(R.layout.login_layout);
		changePanel(new SigninFragment_());
	}
	
	protected void changePanel(Fragment fragment) {
		getFragmentManager().beginTransaction()
							.replace(R.id.login_container, fragment)							
							.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
							.commit();
	}
	
	@Override
	public void onBackPressed() {
		if (getFragmentManager().getBackStackEntryCount()>0) {			
			getFragmentManager().popBackStack();
		}
		else
		{
			super.onBackPressed();
		}
	}
}
