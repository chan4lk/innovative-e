package com.iepl.pathapp.fragment;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import com.iepl.pathapp.R;
import com.iepl.pathapp.common.SessionManager;
import com.iepl.pathapp.event.BusProvider;
import com.iepl.pathapp.event.SignUpEvent;

import android.app.Fragment;
import android.widget.EditText;
import android.widget.Toast;

/**
 * The Class SignupFragment.
 */
@EFragment(R.layout.signup_layout)
public class SignupFragment extends Fragment {
	
	/** The password text. */
	@ViewById(R.id.password_signup)
	EditText passwordText;
	
	/** The user name text. */
	@ViewById(R.id.username_signup)
	EditText usernameText;
	
	/** The email text. */
	@ViewById(R.id.email_signup)
	EditText emailText;
	
	/** The session manager. */
	@Bean
	SessionManager session;
	
	/** The event bus. */
	@Bean
	BusProvider bus;
		
	/**
	 * On sign up button pressed.
	 */
	@Click(R.id.signup_btn)
	protected void onSignUpButtonPressed(){
		
		String username = usernameText.getText().toString();
		String email = emailText.getText().toString();
		String password = passwordText.getText().toString();
		
		if ((username.trim().length()<=0) 
				|| (email.trim().length()<=0)
				|| (password.trim().length() <=0) ) {
			Toast
			.makeText(getActivity(), "please enter valid details", Toast.LENGTH_SHORT)
			.show();
		}
		else {
			session.createLoginSession(username, email, password);
			bus.post(new SignUpEvent());
			Toast
			.makeText(getActivity(), "registering", Toast.LENGTH_SHORT)
			.show();
		}
		
	}
	
	@Override
	public void onResume() {		
		super.onResume();
		bus.register(this);
	}
	
	@Override
	public void onPause() {		
		super.onPause();
		bus.unregister(this);
	}

}
