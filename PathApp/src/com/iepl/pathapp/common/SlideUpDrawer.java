package com.iepl.pathapp.common;

import com.iepl.pathapp.listener.OnDragListener;
import com.iepl.pathapp.listener.OnSwipeTouchListener;
import android.app.Activity;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

public class SlideUpDrawer {
	
	/** The Constant TAG. */
	private static final String TAG = "SlidingDrawer";
	
	/** The drawer. */
	private ViewGroup drawer;
	
	/** The state. */
	private static MenuState state = MenuState.Collapsed;	
	
	/** The default panel height. */
	private static final int DEFAULT_PANEL_HEIGHT = 68;
	
	/** The panel height. */
	private static int panelHeight = DEFAULT_PANEL_HEIGHT;
	
	/** The panel anchor point. */
	private static float panelAnchorPoint;
	
	/** The height. */
	private int height;
	
	/** The max height. */
	private static int maxHeight;
	
	
	public SlideUpDrawer(Activity context, ViewGroup layout)
	{
		this.drawer = layout;		
		
		final DisplayMetrics metrics = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        
		SlideUpDrawer.maxHeight = metrics.heightPixels - context.getActionBar().getHeight();
        
        final LayoutParams params = layout.getLayoutParams(); 
        
        Rect delegateArea = new Rect();
        drawer.getHitRect(delegateArea);
        delegateArea.top += 100;        
        TouchDelegate touchDelegate = new TouchDelegate(delegateArea, 
                drawer);       
        if (View.class.isInstance(drawer.getParent())) {
            ((View) drawer.getParent()).setTouchDelegate(touchDelegate);
        }       
        
        View handler = layout.getChildAt(0);
        handler.setOnTouchListener(new OnDragListener(context){
        	ResizeAnimation anim = new ResizeAnimation(drawer);
        	@Override
        	public void onTap() {
        		Log.i(TAG, "tap up");   
       		 
       		 switch (state) {
   				case Collapsed:
    				{
    					if (SlideUpDrawer.panelAnchorPoint > 0) {
   						height = (int) (SlideUpDrawer.maxHeight * SlideUpDrawer.panelAnchorPoint);
   						setState(MenuState.Anchored);
   					}
   					else {
   						height = SlideUpDrawer.maxHeight;
   						setState(MenuState.Expanded);
   					}	   
    				}
    				break;
   			case Anchored:
   			{					
   				height = SlideUpDrawer.panelHeight;
   				setState(MenuState.Collapsed);
   				        		
   			}
   				break;
   			case Expanded:
   			{					
   				height = SlideUpDrawer.panelHeight;
   				setState(MenuState.Collapsed);
   				        		
   			}
   				break;
   			case Hidden:
   				break;
   			default:
   				break;
       		 }
       		anim.setDuration(500);      	
        		anim.setParams(params.height, height);
        		drawer.startAnimation(anim);       	
       	        
       	    }
        	
        	@Override
        	public void onSwipeUp() {        		
        		switch (state) {
				case Collapsed:
				{
					if (SlideUpDrawer.panelAnchorPoint > 0) {
						height = (int) (SlideUpDrawer.maxHeight * SlideUpDrawer.panelAnchorPoint);
						setState(MenuState.Anchored);
					}
					else {
						height = SlideUpDrawer.maxHeight;
						setState(MenuState.Expanded);
					}	        		
				}
					break;
				case Anchored:
				{					
						height = SlideUpDrawer.maxHeight;
						setState(MenuState.Expanded);
						        		
				}
					break;

				default:
					break;
				}
        		
        		Log.i(TAG, "swipe up");   
        		      
        		anim.setDuration(500);      	
        		anim.setParams(params.height, height);
        		drawer.startAnimation(anim);        		
        		
        	}
        	
        	@Override
        	public void onSwipeDown() {  
        		
        		switch (state) {
				case Expanded:
				{
					if (SlideUpDrawer.panelAnchorPoint > 0) {
						height = (int) (SlideUpDrawer.maxHeight * SlideUpDrawer.panelAnchorPoint);
						setState(MenuState.Anchored);
					}
					else {
						height = SlideUpDrawer.panelHeight;
						setState(MenuState.Collapsed);
					}
				}
					break;
					
				case Anchored:
				{					
						height = SlideUpDrawer.panelHeight;
						setState(MenuState.Collapsed);					
				}
					break;	

				default:
					break;
				}
        		Log.i(TAG, "swipe down");    
        		anim.setDuration(500);      	
        		anim.setParams(params.height, height);
        		drawer.startAnimation(anim);  
        	}
        });
        
        layout.setOnTouchListener(new OnSwipeTouchListener(context){
    		ResizeAnimation anim = new ResizeAnimation(drawer);
        	@Override
        	public void onSwipeUp() {        		
        		switch (state) {
				case Collapsed:
				{
					if (SlideUpDrawer.panelAnchorPoint > 0) {
						height = (int) (SlideUpDrawer.maxHeight * SlideUpDrawer.panelAnchorPoint);
						setState(MenuState.Anchored);
					}
					else {
						height = SlideUpDrawer.maxHeight;
						setState(MenuState.Expanded);
					}	        		
				}
					break;
				case Anchored:
				{					
						height = SlideUpDrawer.maxHeight;
						setState(MenuState.Expanded);
						        		
				}
					break;

				default:
					break;
				}
        		
        		Log.i(TAG, "swipe up");   
        		      
        		anim.setDuration(500);      	
        		anim.setParams(params.height, height);
        		drawer.startAnimation(anim);        		
        		
        	}
        	
        	@Override
        	public void onSwipeDown() {  
        		
        		switch (state) {
				case Expanded:
				{
					if (SlideUpDrawer.panelAnchorPoint > 0) {
						height = (int) (SlideUpDrawer.maxHeight * SlideUpDrawer.panelAnchorPoint);
						setState(MenuState.Anchored);
					}
					else {
						height = SlideUpDrawer.panelHeight;
						setState(MenuState.Collapsed);
					}
				}
					break;
					
				case Anchored:
				{					
						height = SlideUpDrawer.panelHeight;
						setState(MenuState.Collapsed);					
				}
					break;	

				default:
					break;
				}
        		Log.i(TAG, "swipe down");    
        		anim.setDuration(500);      	
        		anim.setParams(params.height, height);
        		drawer.startAnimation(anim);  
        	}
        	
        	@Override
        	public void onSwipeLeft() {       
        		Log.i(TAG, "swipe left");        		
        	}
        	
        	@Override
        	public void onSwipeRight() {
        		Log.i(TAG, "swipe right");        		
        		
        	}
        	
        	@Override
    	    public void onTap() {
    		 
        	}
        });
	}
	

	/**
	 * @return the state
	 */
	public static MenuState getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public static void setState(MenuState state) {
		SlideUpDrawer.state = state;
	}
	
	/**
	 * Sets the panel height.
	 *
	 * @param sets the new panel height
	 */
	public void setPanelHeight(int height)
	{
		SlideUpDrawer.panelHeight = height;
	}
	
	/**
	 * @param panelAnchorPoint the panelAnchorPoint to set
	 */
	public void setAnchorPoint(float panelAnchorPoint) {
		SlideUpDrawer.panelAnchorPoint = panelAnchorPoint;
	}
}
