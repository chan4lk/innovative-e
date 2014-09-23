package com.iepl.pathapp;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.MenuDrawer.OnDrawerStateChangeListener;
import net.simonvt.menudrawer.Position;
import net.simonvt.menudrawer.MenuDrawer.Type;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.iepl.pathapp.event.BusProvider;
import com.iepl.pathapp.event.LocationChangeEvent;
import com.iepl.pathapp.fragment.ListViewFragment;
import com.iepl.pathapp.fragment.MenuFragment;
import com.iepl.pathapp.fragment.SlideHeaderFragment;
import com.iepl.pathapp.model.Header;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelSlideListener;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * The Class SlideUpActivity.
 */
@EActivity
public class SlideUpActivity extends SherlockActivity {
	
	@Bean
	BusProvider bus;
	
	protected static final String TAG = "SlideUpActivity";
	protected SlidingUpPanelLayout mLayout;
	protected MenuDrawer mDrawer;
	protected ListView mDrawerList;
	protected GoogleMap map;
	protected LatLng[] mapPositions = new LatLng[7];
	protected Marker[] mapMarkers = new Marker[7];

	/*
	 * (non-Java doc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		bus.register(this);
		// {{ mDrawer
		mDrawer = MenuDrawer.attach(this, Type.OVERLAY, Position.LEFT,
				MenuDrawer.MENU_DRAG_WINDOW);
		mDrawer.setContentView(R.layout.slideup_layout);
		mDrawer.setMenuView(R.layout.left_menu);
		mDrawer.setOnDrawerStateChangeListener(new OnDrawerStateChangeListener() {
			@Override
			public void onDrawerStateChange(int oldState, int newState) {
			}

			@Override
			public void onDrawerSlide(float openRatio, int offsetPixels) {
				mLayout.collapsePanel();
			}
		});

		setLeftMenuItems();
		mDrawer.setSlideDrawable(R.drawable.ic_drawer);
		mDrawer.setDrawerIndicatorEnabled(true);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
		// }} mDrawer

		this.setupSlideUpPanel();
		this.initMap();
	}

	/**
	 * Setup slide up panel.
	 */
	protected void setupSlideUpPanel() {
		mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
		mLayout.setAnchorPoint(.7f);
		mLayout.setOverlayed(true);
		mLayout.setPanelSlideListener(new SlidePanelActionListener());
	}

	/**
	 * Sets the up drawer.
	 */
	protected void setUpDrawer() {

	}

	/**
	 * Initializes the map.
	 */
	protected void initMap() {
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
		SlideHeaderFragment fragment = SlideHeaderFragment.newInstance(header);
		changeViewPanel(R.id.slide_header, fragment);
		
	}

	protected void setMap(int position) {
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(
				mapPositions[position], 13));

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
				SlideHeaderFragment fragment = SlideHeaderFragment.newInstance(header);
				changeViewPanel(R.id.slide_header, fragment);
				return false;
			}
		});
	}

	protected void setLeftMenuItems() {
		mDrawerList = (ListView) findViewById(R.id.drawer_list);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getBaseContext(), R.layout.drawer_list_item, getResources()
						.getStringArray(R.array.menus));

		// Setting the adapter on mDrawerList
		mDrawerList.setAdapter(adapter);

		mDrawerList.setOnItemClickListener(new ItemClickListener());

	}


	protected void changeViewPanel(int resource, Fragment fragment) {
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(resource, fragment).commit();
	}	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			mDrawer.toggleMenu();
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	private class ItemClickListener implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int pos,
				long id) {
			mDrawer.closeMenu();
			setMap(pos);
			if (pos == 1) {
				Log.i(TAG, "Changing view to List");
				ListViewFragment fragment = new ListViewFragment();				
				changeViewPanel(R.id.content_panel,fragment);
				changeViewPanel(R.id.content_panel_bottom,new MenuFragment());
			} else {
				changeViewPanel(R.id.content_panel,new MenuFragment());
			}
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
			Log.i(TAG, "onPanelCollapsed");
			changeViewPanel(R.id.content_panel,new MenuFragment());
		}

		@Override
		public void onPanelAnchored(View panel) {
			map.getUiSettings().setScrollGesturesEnabled(true);
			Log.i(TAG, "onPanelAnchored");
			changeViewPanel(R.id.content_panel,fragment);

		}

		@Override
		public void onPanelHidden(View panel) {
			Log.i(TAG, "onPanelHidden");

		}
	}
	
	
	@Override
	protected void onDestroy() {		
		super.onDestroy();
		bus.unregister(this);
	}
}
