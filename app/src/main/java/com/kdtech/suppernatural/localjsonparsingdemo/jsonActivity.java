package com.kdtech.suppernatural.localjsonparsingdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class jsonActivity extends AppCompatActivity {

    public static List favouriteSeries = new ArrayList();
    public static List favouriteFood = new ArrayList();
    public static List selectedArray = new ArrayList();

    //this function will basically use inputStream to get the json file from assets folder and give that to us in string
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("favourite.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    // this is for extracting series array from json file
    private static List extractSeriesFromJson(String favouriteJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(favouriteJSON)) {
            return null;
        }

        favouriteSeries = new ArrayList<>();

        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {
            //first we create base json objectt
            JSONObject baseJsonResponse = new JSONObject(favouriteJSON);
            //inside it there is an object named "my favourates" so we will take it in another object
            JSONObject favourite = baseJsonResponse.getJSONObject("my favourates");
            //there are 2 arrays inside favourite object,from this we want series array so we will parse there
            JSONArray series = favourite.getJSONArray("series");

            //this will check that the array is not null
            if (series != null) {
                //using this for loop we will add all the strings in the array to other array which we declared above as it is global
                for (int i = 0; i < series.length(); i++) {
                    favouriteSeries.add(series.getString(i));
                }
            }
        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
        }

        // Log.e("kushal", "extractFeatureFromJson: ", (Throwable) eventArray);

        return favouriteSeries;
    }

    private static List extractFoodFromJson(String favouriteJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(favouriteJSON)) {
            return null;
        }

        favouriteFood = new ArrayList<>();

        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {
            //first we create base json objectt
            JSONObject baseJsonResponse = new JSONObject(favouriteJSON);
            //inside it there is an object named "my favourates" so we will take it in another object
            JSONObject favourite = baseJsonResponse.getJSONObject("my favourates");
            //there are 2 arrays inside favourite object,from this we want food array so we will parse there
            JSONArray food = favourite.getJSONArray("food");

            //this will check that the array is not null
            if (food != null) {
                //using this for loop we will add all the strings in the array to other array which we declared above as it is global
                for (int i = 0; i < food.length(); i++) {
                    favouriteFood.add(food.getString(i));
                }
            }
        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
        }

        // Log.e("kushal", "extractFeatureFromJson: ", (Throwable) eventArray);
        return favouriteFood;
    }

    public int gotdetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        ListView list = (ListView)findViewById(R.id.list);

        //getting the integer from previous activity, 0 is for series and 1 is for food
        Bundle detail = getIntent().getExtras();
        gotdetail = detail.getInt("pos");

        if (gotdetail==0){
            //if recived integer is 0 ie. series we will use extractSeriesFromJson method and pass loadJSONFromAsset as argument
            selectedArray=extractSeriesFromJson(loadJSONFromAsset());
        }
        else if (gotdetail==1){
            //if recived integer is 1 ie. food we will use extractFoodFromJson method and pass loadJSONFromAsset as argument
            selectedArray=extractFoodFromJson(loadJSONFromAsset());
        }

        final ArrayAdapter arrayAdapter1 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, selectedArray);
        list.setAdapter(arrayAdapter1);

    }
}
