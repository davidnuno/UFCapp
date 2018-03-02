package com.example.android.ufcapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by david on 4/2/17.
 */

public class FighterAdapter extends ArrayAdapter<Fighter> {

    //The log tag used for tracking purposes.
    private final static String LOG_TAG = " Steps => " + FightersActivity.class.getSimpleName();

    /**
     * Constructs a new {@link FighterAdapter}.
     *
     * @param context  of the app.
     * @param fighters is the list of articles, which is the data source of the adapter.
     */
    public FighterAdapter(Context context, List<Fighter> fighters) {

        super(context, 0, fighters);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_fighter, parent, false);
        }

        Fighter currentFighter = getItem(position);

        //Find the fighter name ID
        TextView fighterName = (TextView) listItemView.findViewById(R.id.fighter_name);

        //Find the fighter record ID
        TextView fighterRecord = (TextView) listItemView.findViewById(R.id.fighter_record);

        //Find the thumbnail ImageView ID
        ImageView thumbnail = (ImageView) listItemView.findViewById(R.id.fighter_thumbnail);

        //Set the fighter name
        fighterName.setText(concatenateName(currentFighter.getFirstName(), currentFighter.getLastName(), currentFighter.getNickname()));

        //Set the fighter record
        fighterRecord.setText(concatenateFighterRecord(currentFighter.getWins(), currentFighter.getLosses()));

        //Display thumbnail using the Picasso library
        Picasso.with(getContext()).load(currentFighter.getThumbnail())
                .transform(new RoundedCornersTransformation(10, 5))
                .into(thumbnail);

        return listItemView;
    }

    /**
     * This method is used to concatenate a {@link Fighter} name along with nickname, if any.
     *
     * @param first    The {@link Fighter} first name, of type {@Link String}.
     * @param last     The {@link Fighter} last name, of type {@Link String}.
     * @param nickname The {@link Fighter} nickname, of type {@Link String}.
     */
    private String concatenateName(String first, String last, String nickname) {

        String fullName;

        if (nickname != null && !nickname.isEmpty() && nickname != "null") {
            fullName = first + " " + "\"" + nickname + "\"" + last;
            return fullName;
        } else {
            fullName = first + " " + last;
            return fullName;
        }
    }

    private String concatenateFighterRecord(int wins, int losses) {

        String record;

        record = wins + "-" + losses;

        return record;
    }
}
