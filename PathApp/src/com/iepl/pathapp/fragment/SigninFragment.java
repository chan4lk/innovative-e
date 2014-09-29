package com.iepl.pathapp.fragment;

import com.iepl.pathapp.R;
import com.iepl.pathapp.common.AlertDialogManager;
import com.iepl.pathapp.common.SessionManager;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * The Class SigninFragment.
 */
public class SigninFragment extends Fragment{

	
	/** The session. */
	SessionManager session;	
	
	/** The username. */
	EditText username;	
	
	/** The password. */
	EditText password;
	
	/* (non-Javadoc)
	 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {		
		
		session = new SessionManager(getActivity());
		View view = inflater.inflate(R.layout.signin_fragment, container, false);
		username = (EditText)view.findViewById(R.id.username);
		password = (EditText)view.findViewById(R.id.password);
		
		Button signupButton = (Button)view.findViewById(R.id.signup_now_btn);
		signupButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				signupNow();
				
			}
		});
		
		Button signinButton = (Button)view.findViewById(R.id.signin_btn);
		signinButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onSignIn();
				
			}
		});
		
		return view;
	}	
	/**
	 * Sign up now.
	 */

	protected void signupNow()
	{
		getFragmentManager().beginTransaction()
							.replace(R.id.login_container, new SignupFragment_())
							.addToBackStack(null)
							.commit();
	}	
	
	/**
	 * On sign in.
	 */
	protected void onSignIn()
	{
		AlertDialogManager alert = new AlertDialogManager();	
		
		if(!session.isSignedUp())
		{
			alert.showMessageDialog(getActivity(), "Error", "Please Signup", Boolean.FALSE);
		}else if((username.getText().toString().trim().length()>0 && password.getText().toString().trim().length()>0)){
			
			String prefUserName =  session.getUserDetails().get(SessionManager.KEY_NAME);
			String prefEmail =  session.getUserDetails().get(SessionManager.KEY_EMAIL);
			String prefPassword =  session.getUserDetails().get(SessionManager.KEY_PASSWORD);
			
			if (
					(prefUserName.equalsIgnoreCase(username.getText().toString()) && prefPassword.equals(password.getText().toString()))
					||
					(prefEmail.equalsIgnoreCase(username.getText().toString()) && prefPassword.equals(password.getText().toString()))
				) {
				session.createLoginSession(prefUserName, prefEmail, prefPassword);
				Toast.makeText(getActivity(), username.getText().toString()+ " logged in", Toast.LENGTH_LONG).show();
				
				username.setText("");
				password.setText("");
				
				getActivity().finish();
			}
			else
			{				
				alert.showMessageDialog(getActivity(), "Error", "Username or Password is invalid", Boolean.FALSE);
			}			
		}
		else
		{
			alert.showMessageDialog(getActivity(), "Error", "Username or Password is empty", Boolean.FALSE);
		}
		
	}
}
