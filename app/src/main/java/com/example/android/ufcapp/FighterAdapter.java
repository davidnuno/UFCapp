package com.example.android.ufcapp;

import android.content.Context;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by david on 4/2/17.
 */

public class FighterAdapter extends ArrayAdapter<Fighter> implements Filterable {

    //The log tag used for tracking purposes.
    private final static String LOG_TAG = " Steps => " + FightersActivity.class.getSimpleName();

    //global list of fighters that never changes once its populated with data
    private List<Fighter> fighters = null;

    private Filter mikesAwesomeFilter;
    /**
     * Constructs a new {@link FighterAdapter}.
     *
     * @param context  of the app.
     * @param tempFightersList is the list of articles, which is the data source of the adapter.
     */
    public FighterAdapter(Context context, List<Fighter> tempFightersList) {

        super(context, 0, tempFightersList);


        mikesAwesomeFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                List<Fighter> tempList = new LinkedList<>();
                //constraint is the result from text you want to filter against.
                //objects is your data set you will filter from

                System.out.println("Constraint is "+constraint+" and fighters size is "+fighters.size());
                if (constraint != null) {

                    for (Fighter fighter : fighters) {

                        List<String> searchableInfo = getSearchableInfo(fighter);

                        for (String info : searchableInfo) {

                            if (info.toLowerCase().contains(constraint)) {
                                tempList.add(fighter);
                                break;
                            }
                        }
                    }

                    //following two lines is very important
                    //as publish result can only take FilterResults objects
                    filterResults.values = tempList;
                    filterResults.count = tempList.size();
                }
                return filterResults;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence contraint, FilterResults results) {

                if (results.values == null) {

                    notifyDataSetInvalidated();
                    return;
                }

                List<Fighter> filteredList = (List<Fighter>)results.values;
                clear();
                addAll(filteredList);

                if (results.count > 0) {

                    notifyDataSetChanged();
                } else {

                    notifyDataSetInvalidated();
                }
            }
        };
    }
    @Override
    public Filter getFilter() {
        return mikesAwesomeFilter;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null ) {
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
            fullName = first + " " + "\"" + nickname + "\" " + last;
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

    private List<String> getSearchableInfo(Fighter fighter)
    {
        List<String> searchCriteria =  new ArrayList<>( Arrays.asList(fighter.getFirstName()+" "+fighter.getLastName()));

        String [] stuffToAdd = {fighter.getNickname()+"",fighter.getDraws()+"",
                fighter.getLosses()+"", fighter.getWins()+"", fighter.getWeight()+""};

        for (String infoAboutFighter : stuffToAdd) {

            if (infoAboutFighter != null && !infoAboutFighter.isEmpty() && !infoAboutFighter.equals("null")) {
                searchCriteria.add(infoAboutFighter);
            }
        }
        return searchCriteria;
    }

    /**
     * Outside Classes wanting to add data should call this instead
     * @param data
     */

    public void myaddAll(List<Fighter> data) {

        //This was causing crash when clicking on fighter ListView and coming back to app.

        /*if(fighters != null) {

            throw new RuntimeException("BAD");
        }
        */
        addAll(data);

        fighters = new ArrayList<>(data);

        System.out.println("size is "+fighters.size());
    }

    /**
     * Outside Classes wanting to delete data should call this instead
     */
    public void myclear()
    {
        if(fighters!=null) {
            fighters.clear();

            this.clear();
        }
    }
}
