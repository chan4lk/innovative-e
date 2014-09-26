package com.iepl.pathapp;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import com.iepl.pathapp.common.SessionManager;
import com.iepl.pathapp.event.BusProvider;
import com.iepl.pathapp.event.SignUpEvent;
import com.iepl.pathapp.fragment.SigninFragment;
import com.squareup.otto.Subscribe;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

@EActivity
public class LoginActivity extends FragmentActivity {
	
	@Bean
	BusProvider bus;
	
	@Bean
	SessionManager session;
			 
	@Override
	protected void onCreate(Bundle bundle) {		
		super.onCreate(bundle);
		setContentView(R.layout.login_layout);
		if(session.isLoggedIn())
		{
			Toast.makeText(getApplicationContext(), "User is logged in", Toast.LENGTH_LONG).show();
		}
		changePanel(new SigninFragment());
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
	
	@Override
	protected void onResume() {
		bus.register(this);
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		bus.unregister(this);
		super.onPause();
	}
	
	@Subscribe 
	public void onSignIn(SignUpEvent event){
		changePanel(new SigninFragment());
	}
	
	@Click(R.id.signout_btn)
	public void onSignOut()
	{
		session.logoutUser();
	}
	
	
}
