package com.solutionsfit.calculator;

import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.res.StringRes;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

@EActivity(R.layout.activity_calculator)
public class Calculator extends Activity {
	@ViewById EditText firstNumber;
	@ViewById EditText secondNumber;
	@ViewById TextView total;
	@ViewById Button addButton;
    
	@StringRes String totalLabel;
	@StringRes String undefinedLabel;
	
	@Bean CalculatorBean calculatorService;
	
    @Click
    public void addButton() {
    	Integer totalResult = calculatorService.add(getFirstNumberResult(),  getSecondNumberResult());
        total.setText(totalLabel + " = " + Integer.toString(totalResult));
    }

    @Click
    public void divideButton() {
    	handleDivide();
    }
    
    @Background
    protected void handleDivide() {
    	showDivideResult(calculatorService.divide(getFirstNumberResult(),  getSecondNumberResult()));
    }
    
    @UiThread
    protected void showDivideResult(Integer divideResult) {
    	if(divideResult == null) {
    		total.setText(totalLabel + " = " + undefinedLabel);
    	} else {
    		total.setText(totalLabel + " = " + Integer.toString(divideResult));
    	}
    }

	private Integer getFirstNumberResult() {
		return Integer.parseInt(firstNumber.getText().toString());
	}

	private Integer getSecondNumberResult() {
		return Integer.parseInt(secondNumber.getText().toString());
	}
}
