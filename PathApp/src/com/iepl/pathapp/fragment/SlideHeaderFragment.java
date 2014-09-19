package com.iepl.pathapp.fragment;

import com.iepl.pathapp.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

public class SlideHeaderFragment extends Fragment{
	
	public static SlideHeaderFragment newInstance(Header header) {		
		SlideHeaderFragment fragment = new SlideHeaderFragment();
		
		Bundle args = new Bundle();	
		
		args.putString("name",  header.getName());
		args.putString("description", header.getDescription());
		args.putFloat("rating", header.getRating());		
		
		fragment.setArguments(args);
		
		return fragment;
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.slide_panel_header, container, false);
		
		TextView nameText = (TextView)view.findViewById(R.id.location_name);
		TextView descriptionText = (TextView)view.findViewById(R.id.location_description);
		RatingBar ratingBar = (RatingBar)view.findViewById(R.id.location_raiting);
		
		
		nameText.setText(this.getArguments().getString("name"));
		descriptionText.setText(this.getArguments().getString("description"));
		ratingBar.setRating(this.getArguments().getFloat("rating"));
		
		return view;
	}
}