package com.mindful.app;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.mindful.app.fragment.GraphViewFragment;
import com.mindful.app.fragment.InstanceEntryFragment;

public class MainActivity extends Activity implements InstanceEntryFragment.Listener, ActionBar.TabListener {

    // The bundle key representing the current tab position
    private static final String STATE_SELECTED_TAB = "selected_tab";

    private static final int TAB_POSITION_INPUT = 0;
    private static final int TAB_POSITION_VIEW = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the action bar to show tabs.
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        actionBar.addTab(actionBar.newTab().setText(R.string.tab_header_input)
                .setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText(R.string.tab_header_view)
                .setTabListener(this));
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore the previously serialized tab position.
        if (savedInstanceState.containsKey(STATE_SELECTED_TAB)) {
            getActionBar().setSelectedNavigationItem(savedInstanceState.getInt(STATE_SELECTED_TAB));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Serialize the current tab position.
        outState.putInt(STATE_SELECTED_TAB, getActionBar().getSelectedNavigationIndex());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        Fragment fragment = null;

        // Show the tab contents in the container based on the selected tab.
        switch (tab.getPosition())
        {
            case TAB_POSITION_INPUT:
                fragment = InstanceEntryFragment.newInstance();
                break;
            case TAB_POSITION_VIEW:
                fragment = GraphViewFragment.newInstance();
                break;
        }

        getFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }
}