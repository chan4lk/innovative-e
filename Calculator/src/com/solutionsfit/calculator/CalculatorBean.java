package com.solutionsfit.calculator;

import android.content.Context;
import android.widget.Toast;

import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.RootContext;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.res.StringRes;

@EBean
public class CalculatorBean {
	@RootContext Context context;
	
	@StringRes String divideByZeroMessage;
	
	public Integer add(Integer num1, Integer num2) {
		return num1 + num2;
	}
	
	public Integer divide(Integer num1, Integer num2) {
		if(num2 == 0) {
			showMessage(divideByZeroMessage);
			return null;
		}
		
		return num1 / num2;
	}
	
	@UiThread
	public void showMessage(String message) {
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}
}
