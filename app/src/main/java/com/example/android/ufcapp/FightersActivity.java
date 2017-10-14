package com.example.android.ufcapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.LoaderManager.LoaderCallbacks;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FightersActivity extends AppCompatActivity implements LoaderCallbacks<List<Fighter>> {

    //The log tag used for tracking purposes.
    private final static String LOG_TAG = " Steps => " + FightersActivity.class.getSimpleName();
    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int FIGHTER_LOADER_ID = 1;
    /**
     * This is the {@link String} URL used to fetch the JSON data.
     */
    private static final String FIGHTER_REQUEST_URL = "http://ufc-data-api.ufc.com/api/v3/iphone/fighters";
    /**
     * The {@link FighterAdapter} is used to view the various articles through a ListView.
     */
    private FighterAdapter mAdapter;
    /**
     * The empty state {@link TextView} when there are no fighters found.
     */
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fighters);

        mAdapter = new FighterAdapter(FightersActivity.this, new ArrayList<Fighter>());

        ListView fighterListView = (ListView) findViewById(R.id.fighter_list);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);

        fighterListView.setEmptyView(mEmptyStateTextView);

        if (fighterListView != null)
            fighterListView.setAdapter(mAdapter);

        //Start Connectivity Manager
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        //Get the network activity
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if(isConnected) {
            //Internet connection good, initialize Loader
            Log.v(LOG_TAG, "Initializing Loader");
            LoaderManager loaderManager = getLoaderManager();

            loaderManager.initLoader(FIGHTER_LOADER_ID, null, this);
        } else {
            //Display no internet connection.
            //This is causing it to crash.
            //mEmptyStateTextView.setText(R.string.no_internet_connection);
        }

    }

    @Override
    public Loader<List<Fighter>> onCreateLoader(int id, Bundle args) {
        return new FighterLoader(this, FIGHTER_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Fighter>> loader, List<Fighter> data) {
        mAdapter.clear();
    }

    @Override
    public void onLoaderReset(Loader<List<Fighter>> loader) {
        mAdapter.clear();
    }
}
