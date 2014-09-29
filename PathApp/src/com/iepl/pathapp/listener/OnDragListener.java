package com.iepl.pathapp.listener;

import android.content.ClipData;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;

/**
 * The listener interface for receiving onDrag events.
 * The class that is interested in processing a onDrag
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addOnDragListener<code> method. When
 * the onDrag event occurs, that object's appropriate
 * method is invoked.
 *
 * @see OnDragEvent
 */
public class OnDragListener extends OnSwipeTouchListener{
	
	/**
	 * Instantiates a new on drag listener.
	 *
	 * @param context the context
	 */
	public OnDragListener(Context context)
	{
		super(context);
	}
	
	@Override
	public boolean onTouch(View view, MotionEvent motionEvent) {
		 ClipData data = ClipData.newPlainText("", "");
	     DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
	     view.startDrag(data, shadowBuilder, view, 0);	     
		return super.onTouch(view, motionEvent);
	}

}
