package com.iepl.pathapp;
//{{ imports
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.iepl.pathapp.event.BusProvider;
import com.iepl.pathapp.event.LocationChangeEvent;
import com.iepl.pathapp.fragment.ListViewFragment;
import com.iepl.pathapp.fragment.MenuFragment;
import com.iepl.pathapp.fragment.SlideHeaderFragment;
import com.iepl.pathapp.fragment.SlideHeaderFragment_;
import com.iepl.pathapp.model.Header;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelSlideListener;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
//}}

/**
 * The Class SlideUpActivity.
 */
@EActivity
public class MapActivity extends FragmentActivity  {
	
	@Bean
	BusProvider bus;
	
	/** The Constant TAG. */
	protected static final String TAG = "SlideUpActivity";
	
	/** The title. */
	protected String mTitle;
	
	/** The drawer title. */
	protected String mDrawerTitle = "PathApp";
	
	/** The snap view. */
	protected CameraPosition[] snapView = new CameraPosition[2];
	
	/** The SlidingUp layout. */
	protected SlidingUpPanelLayout mLayout;	
	
	/** The drawer. */
	protected DrawerLayout mDrawer;
	
	/** The drawer list. */
	protected ListView mDrawerList;
	
	/** The map. */
	protected GoogleMap map;
	
	/** The map positions. */
	protected LatLng[] mapPositions = new LatLng[7];
	
	/** The map markers. */
	protected Marker[] mapMarkers = new Marker[7];
	
	/** The current selected item. */
	protected int currentSelectedItem;
	
	/** The drawer toggle action. */
	protected ActionBarDrawerToggle mDrawerToggle;
	
	/** The metrics. */
	protected DisplayMetrics metrics;

	/*
	 * (non-Java doc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 bus.register(this);
		setContentView(R.layout.drawer_layout);
		
		// {{ mDrawer
		mDrawer = (DrawerLayout)findViewById(R.id.drawer_layout);
		setLeftMenuItems();	

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			getActionBar().setDisplayHomeAsUpEnabled(true);	       
		}        
		// }} mDrawer

		this.setupSlideUpPanel();
		this.initMap();
	}
	
	@Override
	protected void onDestroy() {		
		super.onDestroy();
		bus.unregister(this);
	}	
	
	@Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the navigation drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawer.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
	
	
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.menu,  menu);
	        return true;
	    }

	/**
	 * Setup slide up panel.
	 */
	protected void setupSlideUpPanel() {
		mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
		mLayout.setAnchorPoint(.7f);
		mLayout.setOverlayed(true);
		mLayout.setPanelSlideListener(new SlidePanelActionListener());
		mLayout.hidePanel();
	}

	/**
	 * Initializes the map.
	 */
	protected void initMap() {
		
		metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		
		map = ((MapFragment) getFragmentManager().findFragmentById(
				R.id.map_fragment)).getMap();

		map.setMyLocationEnabled(true);

		mapPositions[0] = new LatLng(-33.867, 151.206);
		mapPositions[1] = new LatLng(-33.867, 161.206);
		mapPositions[2] = new LatLng(-33.867, 171.206);
		mapPositions[3] = new LatLng(-33.867, 151.206);
		mapPositions[4] = new LatLng(-32.867, 151.206);
		mapPositions[5] = new LatLng(-31.867, 151.206);
		mapPositions[6] = new LatLng(-34.867, 151.206);
		this.setMap(0);
		
		Header header = new Header(mapMarkers[0].getTitle(), mapMarkers[0].getSnippet(), 2.5f);
		SlideHeaderFragment fragment = SlideHeaderFragment_.newInstance(header);
		changeViewPanel(R.id.slide_header, fragment);
		
	}

	protected void setMap(int position) {
		currentSelectedItem = position;

		Marker mMarker = map.addMarker(new MarkerOptions()
				.title("location " + position)
				.snippet("The most populous city in Australia.")
				.position(mapPositions[position]));
		mapMarkers[position] = mMarker;
		
		bus.post(
				new LocationChangeEvent(
						new Header(
								mMarker.getTitle(),
								mMarker.getSnippet(),
								3.5f)));
		
		map.setOnMarkerClickListener(new OnMarkerClickListener() {

			@Override
			public boolean onMarkerClick(Marker marker) {
				Log.d("marker", marker.getTitle());
				Header header = new Header(marker.getTitle(), marker.getSnippet(), 4.5f);
				SlideHeaderFragment fragment = SlideHeaderFragment_.newInstance(header);
				changeViewPanel(R.id.slide_header, fragment);
				mLayout.showPanel();
				return false;
			}
		});
		snapView[0] = new CameraPosition.Builder()
		   .target(mapPositions[position])
		   .zoom(13)
		   .bearing(0)
		   .tilt(25)
		   .build();

		map.moveCamera(CameraUpdateFactory.newCameraPosition(snapView[0]));
	}

	protected void setLeftMenuItems() {
		mDrawerList = (ListView) findViewById(R.id.drawer_layout_list);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getBaseContext(), R.layout.drawer_list_item, getResources()
						.getStringArray(R.array.menus));

		// Setting the adapter on mDrawerList
		mDrawerList.setAdapter(adapter);

		mDrawerList.setOnItemClickListener(new ItemClickListener());
		
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer,
	                R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {				

				/** Called when a drawer has settled in a completely closed state. */
	            public void onDrawerClosed(View view) {
	                super.onDrawerClosed(view);
	                getActionBar().setTitle(mTitle);
	                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
	            }

	            /** Called when a drawer has settled in a completely open state. */
	            public void onDrawerOpened(View drawerView) {
	                super.onDrawerOpened(drawerView);
	                getActionBar().setTitle(mDrawerTitle);
	                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
	                mLayout.hidePanel();
	            }
	        };

	        // Set the drawer toggle as the DrawerListener
	        mDrawer.setDrawerListener(mDrawerToggle);

	}


	/**
	 * Change view panel.
	 *
	 * @param the resource
	 * @param the fragment
	 */
	protected void changeViewPanel(final int resource, final Fragment fragment) {
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(resource, fragment).commit();
	}	

	/* 
	 * Home button pressed action.
	 */
	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
	          return true;
	        }
		return super.onOptionsItemSelected(item);
	}

	/**
	 * The listener interface for receiving itemClick events.
	 * The class that is interested in processing a itemClick
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addItemClickListener<code> method. When
	 * the itemClick event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see ItemClickEvent
	 */
	private class ItemClickListener implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int pos,
				long id) {
			mTitle = getResources().getStringArray(R.array.menus)[pos];
			setMap(pos);
			if (pos == 1) {
				Log.i(TAG, "Changing view to List");
				ListViewFragment fragment = new ListViewFragment();				
				changeViewPanel(R.id.content_panel,fragment);
				changeViewPanel(R.id.content_panel_bottom,new MenuFragment());
			} else {
				changeViewPanel(R.id.content_panel,new MenuFragment());
			}
			mDrawer.closeDrawers();
			
		}
	}

	private class SlidePanelActionListener implements PanelSlideListener {
		ListViewFragment fragment = new ListViewFragment();

		@Override
		public void onPanelSlide(View panel, float slideOffset) {
			Log.i(TAG, "onPanelSlide, offset " + slideOffset);
			map.getUiSettings().setScrollGesturesEnabled(false);
		}

		@Override
		public void onPanelExpanded(View panel) {
			Log.i(TAG, "onPanelExpanded");
			map.getUiSettings().setScrollGesturesEnabled(true);
			changeViewPanel(R.id.content_panel,fragment);

		}

		@Override
		public void onPanelCollapsed(View panel) {
			map.getUiSettings().setScrollGesturesEnabled(true);
			moveCamera(-(0),-(metrics.heightPixels/8));
			Log.i(TAG, "onPanelCollapsed");
			changeViewPanel(R.id.content_panel,new MenuFragment());
		}

		/* (non-Javadoc)
		 * @see com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelSlideListener#onPanelAnchored(android.view.View)
		 */
		@Override
		public void onPanelAnchored(View panel) {
					
			map.getUiSettings().setScrollGesturesEnabled(true);			
			moveCamera(0, metrics.heightPixels/4);
			Log.i(TAG, "onPanelAnchored");
			changeViewPanel(R.id.content_panel,fragment);

		}

		@Override
		public void onPanelHidden(View panel) {
			Log.i(TAG, "onPanelHidden");

		}
	}
	
	/**
	 * Move camera.
	 *
	 * @param the x offset
	 * @param the y offset
	 */
	public void moveCamera(int xOffset, int yOffset)
	{		
		//LatLng center = map.getCameraPosition().target;
		Point mappoint = map.getProjection().toScreenLocation(mapMarkers[currentSelectedItem].getPosition());
        mappoint.set(mappoint.x +xOffset , mappoint.y + yOffset);
        map.animateCamera(CameraUpdateFactory.newLatLng(map.getProjection().fromScreenLocation(mappoint)));
	}
	

}
