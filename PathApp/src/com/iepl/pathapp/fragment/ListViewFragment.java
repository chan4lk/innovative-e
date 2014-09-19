package com.iepl.pathapp.fragment;

import java.util.ArrayList;

import com.iepl.pathadd.adapters.CustomListAdapter;
import com.iepl.pathadd.adapters.RowItem;
import com.iepl.pathapp.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class ListViewFragment extends Fragment {

	private final String[] lables = new String[] { "Phone", "Phone", "Phone",
	        "Phone", "OS", "OS", "OS", "OS",		        
	        "OS", "OS" };
	
	private final String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
		        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",		        
		        "Linux", "OS/2" };
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.sample_list_view, container, false);
		setBottomPanelItems(view);
		return view;
	}	

	protected void setBottomPanelItems(View view) {
		final ListView sampleList;
		sampleList = (ListView) view.findViewById(R.id.sample_list);
	
		   
		   CustomListAdapter adapter = new CustomListAdapter(getActivity(),getData());
		   
	       sampleList.setAdapter(adapter);
	       
	       sampleList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int pos,
					long id) {
				
				Toast.makeText(getActivity(), "Item clicked "+ values[pos], Toast.LENGTH_SHORT).show();
				
			}
	       });
	       
	       sampleList.setOnTouchListener(new OnTouchListener() {

	            @Override
	            public boolean onTouch(View v, MotionEvent event) {
	            	sampleList.requestDisallowInterceptTouchEvent(true);
	                return false;
	            }				

	        });
	}
	
	protected ArrayList<RowItem> getData() {
		ArrayList<RowItem> items = new ArrayList<RowItem>();
		for (int index = 0; index < values.length; index++) {
			items.add(new RowItem(lables[index], values[index]));
		}
		return items;
	}
}
