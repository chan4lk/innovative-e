package com.iepl.pathapp;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import com.iepl.pathapp.fragment.SampleFragmentSecond;
import com.iepl.pathapp.fragment.SampleFragment;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

@EActivity
public class DualFragmentActivity extends FragmentActivity{	
	
	/** The drawer layout. */
	@ViewById(R.id.dual_layout)
	protected DrawerLayout mDrawer;
	
	/** The navigation list. */
	@ViewById(R.id.dual_layout_list)
	protected ListView navigationList;
	
	/** The Constant TAG. */
	protected final static String TAG = "DualFragmentActivity";
	
	/** The drawer toggle action. */
	protected ActionBarDrawerToggle mDrawerToggle;
	
	/** The fragment. */
	protected SampleFragment fragment;
	
	/** The fragmet_new. */
	protected SampleFragmentSecond fragmet_new;
	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle bundle) {		
		super.onCreate(bundle);
		setContentView(R.layout.dual_layout);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getBaseContext(), R.layout.drawer_list_item, getResources()
						.getStringArray(R.array.menus));
		navigationList.setAdapter(adapter);

		navigationList.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos,
					long id) {
				Toast.makeText(getApplicationContext(), "You clicked " +getResources()
						.getStringArray(R.array.menus)[pos] , Toast.LENGTH_SHORT).show();
				mDrawer.closeDrawers();
			}
			
		});
		
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer,
                R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {				

			/** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);               
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(final View drawerView) {
                super.onDrawerOpened(drawerView);                
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()               
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawer.setDrawerListener(mDrawerToggle);
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			getActionBar().setDisplayHomeAsUpEnabled(true);	       
		} 
        fragment = new SampleFragment();
        fragmet_new = new SampleFragmentSecond();
        
        this.getFragmentManager()
		.beginTransaction()
		.replace(R.id.dual_fragment, fragment)		
		.commit();
        
	}
	
	/* 
	 * 
	 */
	@Override
    public boolean onPrepareOptionsMenu(final Menu menu) {
        // If the navigation drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawer.isDrawerOpen(navigationList);
        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
	
	
	 /* 
 	 * on Create Options Menu
 	 */
 	@Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.menu, menu);
	        return true;
	    }
	 
 	/* 
 	 * on Options Item Selected
 	 */
 	@Override
		public boolean onOptionsItemSelected(MenuItem item) {
 			boolean result = false;
			if (mDrawerToggle.onOptionsItemSelected(item)) {
				result= true;
		        }
			else {
				result = super.onOptionsItemSelected(item);
			}				
			return result;
		}
	
	/**
	 * Frag_btn_1.
	 */
	@Click(R.id.frag_btn_1)
	public void onFragFirstButton(){
		changeView(fragment);
	}
	
	/**
	 * Frag_btn_2.
	 */
	@Click(R.id.frag_btn_2)
	public void onFragSecondButton(){
		changeView(fragmet_new);
	}
	
	/**
	 * Change view.
	 *
	 * @param fragment the fragment
	 */
	protected void changeView(final Fragment fragment)
	{
		Log.i(TAG, "changing fragment");
		this.getFragmentManager()
			.beginTransaction()
			.replace(R.id.dual_fragment, fragment)
			.addToBackStack(null)
			.commit();
	}
	
	@Override
	public void onBackPressed() {
		if (getFragmentManager().getBackStackEntryCount()>0) {
			getFragmentManager().popBackStack();
		}
		else
		{
			super.onBackPressed();
		}
	}
	
}
