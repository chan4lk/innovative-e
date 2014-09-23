package com.iepl.pathapp;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

@EActivity
public class ActivityChooserActivity extends Activity {
	protected String [] activities = {"MapActivity", "FragmentActivity", "LoginActivity"};
	
	@ViewById(R.id.activity_list)
	ListView list;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_chooser);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getBaseContext(), android.R.layout.simple_list_item_1, activities);
		list.setAdapter(adapter);
		
		list.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos,
					long id) {
				Intent intent;
				switch (pos) {
				case 0:
					intent = new Intent(getApplicationContext(), MapActivity_.class);
					break;
				case 1:
					intent = new Intent(getApplicationContext(), DualFragmentActivity_.class);
					break;
				case 2:
					intent = new Intent(getApplicationContext(), LoginActivity_.class);
					break;

				default:
					intent = new Intent(getApplicationContext(), MapActivity_.class);
					break;
				}
				startActivity(intent);
				
			}
			
		});
	}
}
