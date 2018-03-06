package com.example.android.ufcapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by david on 4/2/17.
 */

public class FighterAdapter extends ArrayAdapter<Fighter> implements Filterable {

    //The log tag used for tracking purposes.
    private final static String LOG_TAG = " Steps => " + FightersActivity.class.getSimpleName();

    //Two data sources, the original data and filtered data
    private ArrayList<HashMap<String, String>> originalData;
    private ArrayList<HashMap<String, String>> filteredData;

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

        //Find the fighter weight class ID
        TextView fighterWeight = (TextView) listItemView.findViewById(R.id.fighter_weight_class);

        //Find the champion ID
        TextView isChamp = (TextView) listItemView.findViewById(R.id.fighter_champ);

        if (currentFighter.isChampion()) {

            isChamp.setText(R.string.champion);
        } else {
            isChamp.setVisibility(TextView.INVISIBLE);
        }

        //Set the fighter name
        fighterName.setText(concatenateName(currentFighter.getFirstName(), currentFighter.getLastName(), currentFighter.getNickname()));

        //Set the fighter record
        fighterRecord.setText(concatenateRecord(currentFighter.getWins(), currentFighter.getLosses(), currentFighter.getDraws()));

        //Set the fighter weight
        fighterWeight.setText(currentFighter.getWeight().replaceAll("_", " "));

        //Display fighter thumbnail using the Picasso library
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

    /**
     * This method is used to concatenate a {@link Fighter} record to proper display format.
     *
     * Ex. 10-2-1
     *
     * @param wins      The {@link Fighter} number of wins.
     * @param losses    The {@link Fighter} number of losses.
     * @param draws     The {@link Fighter} number of draws.
     */
    private String concatenateRecord(int wins, int losses, int draws) {

        String record;

        if (draws < 1) {
            record = wins + "-" + losses;
        } else {
            record = wins + "-" + losses + "-" + draws;
        }

        return record;
    }

    @NonNull
    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                FilterResults results = new FilterResults();

                if (charSequence == null || charSequence.length() == 0) {
                    results.values = originalData;
                    results.count = originalData.size();
                } else {
                    ArrayList<HashMap<String, String>> filterResultsData = new ArrayList<HashMap<String, String>>();

                    for (HashMap<String, String> data : originalData) {
                        //In this loop, you'll filter through originalData and compare each item to charSequence.
                        //If you find a match, add it to your new ArrayList
                        //I'm not sure how you're going to do comparison, so you'll need to fill out this conditional
                        if (/*data matches your filter criteria*/ true) {
                            filterResultsData.add(data);
                        }
                    }

                    results.values = filterResultsData;
                    results.count = filterResultsData.size();
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredData = (ArrayList<HashMap<String, String>>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
