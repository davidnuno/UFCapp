package com.example.android.ufcapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

/**
 * Created by david on 4/2/17.
 */

public class QueryUtils {

    //The log tag used for tracking purposes.
    private final static String LOG_TAG = " Steps => " + QueryUtils.class.getSimpleName();


    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {}

    public static List<Fighter> extractFeatureFromJson(String fighterJSON) {

        //Check if the JSON string is empty
        if(TextUtils.isEmpty(fighterJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding fighters to
        List<Fighter> fighters = new ArrayList<>();

        // Try to parse the JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            JSONArray fightersArray = new JSONArray(fighterJSON);

            for (int index = 0; index < 10 /*fightersArray.length()*/; index++) {

                Log.v(LOG_TAG, "Retrieving fighter info from JSON..");
                //Retrieve the fighters first name
                String fName = fightersArray.getJSONObject(index).getString("first_name");

                //Retrieve the fighters last name
                String lName = fightersArray.getJSONObject(index).getString("last_name");

                //Retrieve the fighters last name
                String nName = fightersArray.getJSONObject(index).getString("nickname");

                //Retrieve the fighters nickname
                int wins = fightersArray.getJSONObject(index).getInt("wins");

                //Retrieve the fighters nickname
                int losses = fightersArray.getJSONObject(index).getInt("losses");

                //Retrieve the fighters nickname
                int draws = fightersArray.getJSONObject(index).getInt("draws");

                //Is the fighter a champion
                boolean isChamp = fightersArray.getJSONObject(index).getBoolean("title_holder");

                //Retrieve fighter thumbnail
                String thumbnail = fightersArray.getJSONObject(index).getString("thumbnail");

                Log.v(LOG_TAG, "Creating a new fighter object");
                fighters.add(new Fighter(fName, lName, nName, wins, losses, draws, isChamp, thumbnail));
            }
        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }
        return fighters;
    }
    /**
     * Returns new {@link URL} object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {

        URL url = null;

        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }

        return url;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {

        StringBuilder output = new StringBuilder();

        if (inputStream != null) {

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }//END OF readFromStream METHOD

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {

        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }//END OF makeHttpRequest METHOD

    public static List<Fighter> fetchFighterData(String requestURL) {
        //Crete the URL from String
        URL url = createUrl(requestURL);

        String jsonResponse = null;

        try {
            jsonResponse = makeHttpRequest(url);
        } catch(IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request" , e);
        }

        //Extract the fighters from JSON and save them in a List.
        List<Fighter> fighters = extractFeatureFromJson(jsonResponse);

        return fighters;
    }//END OF fetchEarthquakeData METHOD
}