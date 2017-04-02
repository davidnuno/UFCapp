package com.example.android.ufcapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FightersActivity extends AppCompatActivity {

    private final static String LOG_TAG = FightersActivity.class.getSimpleName() + " Steps => ";

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
    }
}
