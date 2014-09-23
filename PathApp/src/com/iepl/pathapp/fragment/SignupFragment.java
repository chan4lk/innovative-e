package com.iepl.pathapp.fragment;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import com.iepl.pathapp.R;

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
	
	/**
	 * On sign up button pressed.
	 */
	@Click(R.id.signup_btn)
	protected void onSignUpButtonPressed(){
		if (!usernameText.getText().equals("") || passwordText.getText().equals("") ) {
			Toast
			.makeText(getActivity(), usernameText.getText(), Toast.LENGTH_SHORT)
			.show();
		}
		else {
			Toast
			.makeText(getActivity(), "User name or password is blank", Toast.LENGTH_SHORT)
			.show();
		}
		
	}

}
