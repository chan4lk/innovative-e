package com.iepl.pathapp.fragment;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

import com.iepl.pathapp.R;
import com.iepl.pathapp.event.BusProvider;
import com.iepl.pathapp.event.LocationChangeEvent;
import com.iepl.pathapp.model.Header;
import com.squareup.otto.Subscribe;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

@EFragment
public class SlideHeaderFragment extends Fragment{
	
	@Bean
	BusProvider bus;
	
	protected TextView nameText; 
	protected TextView descriptionText; 
	protected RatingBar ratingBar;

	protected static final String TAG = "Slide Header Fragment";
	
	public static SlideHeaderFragment newInstance(Header header) {		
		SlideHeaderFragment fragment = new SlideHeaderFragment_();
		
		Bundle args = new Bundle();	
		
		args.putString("name",  header.getName());
		args.putString("description", header.getDescription());
		args.putFloat("rating", header.getRating());		
		
		fragment.setArguments(args);
		
		return fragment;
	}
	@Override
	public void onResume() {	
		super.onResume();	
		bus.register(this);
	}
	
	@Override
	public void onPause() {		
		super.onPause();
		bus.unregister(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.slide_panel_header, container, false);
		
		nameText = (TextView)view.findViewById(R.id.location_name);
		descriptionText = (TextView)view.findViewById(R.id.location_description);
		ratingBar = (RatingBar)view.findViewById(R.id.location_raiting);
		
		
		nameText.setText(this.getArguments().getString("name"));
		descriptionText.setText(this.getArguments().getString("description"));
		ratingBar.setRating(this.getArguments().getFloat("rating"));
		
		return view;
	}	
	
	@Subscribe public void onLocationChanged(LocationChangeEvent event)
	{
		Log.i(TAG, "header changed");
		Header header = event.getHeader();
		nameText.setText(header.getName());
		descriptionText.setText(header.getDescription());
		ratingBar.setRating(header.getRating());
	}
}
