package com.iepl.pathapp.common;

import java.util.HashMap;

import org.androidannotations.annotations.EBean;
import com.iepl.pathapp.LoginActivity_;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * The Class SessionManager.
 */
@EBean
public class SessionManager {
	
	/** The preferences. */
	protected SharedPreferences  prefs;
	
	/** The editor. */
	protected Editor editor;
	
	/** The _context. */
	protected Context _context;
	
	/** The private mode. */
	protected int PRIVATE_MODE = 0; 
	
	/** The Constant PREF_NAME. */
	protected static final String PREF_NAME = "IEPL_Pref";
	
	/** The Constant IS_LOGIN. */
	public static final String IS_LOGIN = "IsLoggedIn";
	
	/** The Constant KEY_NAME. */
	public static final String KEY_NAME = "username";
	
	/** The Constant KEY_EMAIL. */
	public static final String KEY_EMAIL = "email";
	
	public static final String KEY_PASSWORD = "password";
	
	/**
	 * Instantiates a new session manager.
	 *
	 * @param the context
	 */
	@SuppressLint("CommitPrefEdits") public SessionManager(Context context)
	{
		this._context = context;
		prefs = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = prefs.edit();
	}
	
	/**
	 * Creates the login session.
	 *
	 * @param name the name
	 * @param email the email
	 */
	public void createLoginSession(String name,
			String email, String password)
	{
		editor.putBoolean(IS_LOGIN, true);
		editor.putString(KEY_NAME, name);
		editor.putString(KEY_EMAIL, email);
		editor.putString(KEY_PASSWORD, password);
		editor.commit();
	}
	
	/**
	 * Check login.
	 */
	public void checkLogin()
	{
		if(!this.isLoggedIn())
		{
			Intent intent = new Intent(_context, LoginActivity_.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			_context.startActivity(intent);
		}
	}
	
	/**
	 * Gets the user details.
	 *
	 * @return the user details
	 */
	public HashMap<String, String> getUserDetails()
	{
		HashMap<String, String> user = new HashMap<String, String>();
		
		user.put(KEY_NAME, prefs.getString(KEY_NAME, null));
		user.put(KEY_EMAIL, prefs.getString(KEY_EMAIL, null));
		user.put(KEY_PASSWORD, prefs.getString(KEY_PASSWORD, null));
		return user;
	}
	
	/**
	 * Logout user.
	 */
	public void logoutUser()
	{
		//editor.clear();
		editor.putBoolean(IS_LOGIN, false);
		editor.commit();
		
		Intent intent = new Intent(_context, LoginActivity_.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		_context.startActivity(intent);
	}

	/**
	 * Checks if is logged in.
	 *
	 * @return true, if is logged in
	 */
	public boolean isLoggedIn() {
		boolean result = prefs.getBoolean(IS_LOGIN, false);
		return result;
	}
	
	
}
