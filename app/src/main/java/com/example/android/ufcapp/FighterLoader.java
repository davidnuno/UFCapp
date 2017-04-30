package com.example.android.ufcapp;

import java.util.List;

import android.content.AsyncTaskLoader;
import android.content.Context;

/**
 * Created by david on 4/2/17.
 */

public class FighterLoader extends AsyncTaskLoader<List<Fighter>> {

    //The log tag used for tracking purposes.
    private final static String LOG_TAG = " Steps => " + FightersActivity.class.getSimpleName();

    /** Query url. */
    private String mUrl;

    public FighterLoader(Context context, String url) {
        super(context);

        mUrl = url;
    }

    @Override
    protected void onStartLoading() {

        forceLoad();
    }

    @Override
    public List<Fighter> loadInBackground() {

        if(mUrl == null){
            return null;
        }

        // Perform the network request, parse the response, and extract a list of fighters.
        List<Fighter> fighters = QueryUtils.fetchFighterData(mUrl);

        return fighters;
    }
}
