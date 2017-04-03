package com.example.android.ufcapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by david on 4/2/17.
 */

public class FighterAdapter extends ArrayAdapter<Fighter> {

    /**
     * Constructs a new {@link FighterAdapter}.
     *
     * @param context  of the app.
     * @param fighters is the list of articles, which is the data source of the adapter.
     */
    public FighterAdapter(Context context, List<Fighter> fighters) {

        super(context, 0, fighters);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {
            R.layout.activity_fighter, parent, false);
        }

        Fighter currentFighter = getItem(position);

        return super.getView(position, convertView, parent);
    }
}
