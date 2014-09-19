package com.iepl.pathadd.adapters;

import java.util.ArrayList;

import com.iepl.pathapp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * The Class CustomListAdapter.
 */
public class CustomListAdapter extends ArrayAdapter<RowItem> {
	
	//{{ private members
	private final Context context;
	private final ArrayList<RowItem> items;
	//}} 
	/**
	 * Instantiates a new custom list adapter.
	 *
	 * @param context of the application
	 * @param items in list
	 */
	public CustomListAdapter(Context context, ArrayList<RowItem> items)
	{
		super(context, R.layout.row_layout, items);
		this.context = context;
		this.items = items;		
	}
	
	/* (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(R.layout.row_layout, parent, false);
		
		TextView labelView = (TextView) rowView.findViewById(R.id.row_label);
		TextView valueView = (TextView) rowView.findViewById(R.id.row_value);
		
		labelView.setText(items.get(position).getTitle());
		valueView.setText(items.get(position).getDescription());
		
		return rowView;
	}
	
}












