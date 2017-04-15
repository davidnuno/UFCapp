package com.example.android.ufcapp;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.LoaderManager.LoaderCallbacks;

import java.util.List;

public class FightersActivity extends AppCompatActivity implements LoaderCallbacks<List<Fighter>> {

    private final static String LOG_TAG = FightersActivity.class.getSimpleName() + " Steps => ";

    /** The {@link FighterAdapter} is used to view the various articles through a ListView. */
    private FighterAdapter mAdapter;

    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int FIGHTER_LOADER_ID = 1;

    private static final String FIGHER_REQUEST_URL = "http://ufc-data-api.ufc.com/api/v3/iphone/fighters";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fighters);

        LoaderManager loaderManager = getLoaderManager();

        loaderManager.initLoader(FIGHTER_LOADER_ID, null, this);
    }

    @Override
    public Loader<List<Fighter>> onCreateLoader(int id, Bundle args) {
        return new FighterLoader(this, FIGHER_REQUEST_URL);
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
