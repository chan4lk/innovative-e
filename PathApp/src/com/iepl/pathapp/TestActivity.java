package com.iepl.pathapp;

import com.iepl.pathapp.common.SlideUpDrawer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

public class TestActivity extends Activity{
	
	private static final String TAG = "TestActivity";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_up_frame_layout);
        
        Log.i(TAG, "starting activity");
        
        final LinearLayout layout =  (LinearLayout) findViewById(R.id.slideup_drawer);
        SlideUpDrawer drawer = new SlideUpDrawer(this, layout);
        drawer.setAnchorPoint(0.7f);
        
	}
}


