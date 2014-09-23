package com.iepl.pathapp.fragment;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import com.iepl.pathapp.R;

import android.app.Fragment;

/**
 * The Class SigninFragment.
 */
@EFragment(R.layout.signin_fragment)
public class SigninFragment extends Fragment{

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
}
