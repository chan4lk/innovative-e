package com.chanideals.budgetapp;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.widget.TextView;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {  
	
	@ViewById
    TextView myText;	
	
	@Click
	void myButton()
	{
		myText.setText("Chan ideals");
	}
}
