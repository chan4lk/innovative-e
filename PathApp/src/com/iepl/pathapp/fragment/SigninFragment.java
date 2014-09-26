package com.iepl.pathapp.fragment;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import com.iepl.pathapp.R;
import com.iepl.pathapp.common.AlertDialogManager;
import com.iepl.pathapp.common.SessionManager;

import android.app.Fragment;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

/**
 * The Class SigninFragment.
 */
@EFragment(R.layout.signin_fragment)
public class SigninFragment extends Fragment{

	@Bean
	SessionManager session;
	
	@ViewById(R.id.username)
	EditText username;
	
	@ViewById(R.id.password)
	EditText password;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		session = new SessionManager(getActivity());
	}
	
	/**
	 * Sign up now.
	 */
	@Click(R.id.signup_now_btn)
	protected void signupNow()
	{
		getFragmentManager().beginTransaction()
							.replace(R.id.login_container, new SignupFragment_())
							.addToBackStack(null)
							.commit();
	}
	
	@Click(R.id.signin_btn)
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
			
			if ((prefUserName.equalsIgnoreCase(username.getText().toString()) 
					&& (prefPassword.equals(password.getText().toString()))
					||
			   (((prefEmail.equalsIgnoreCase(username.getText().toString())) 
					 && (prefPassword.equals(password.getText().toString())
				))))) {
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
