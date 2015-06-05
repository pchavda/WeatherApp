package com.example.chavda.weatherapp;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Chavda on 6/3/15.
 */
public class city {
    long id;
    String name,country;
double lat,lon;
    public city(JSONObject jsoncity) throws JSONException {
        this.id = (Long) jsoncity.optLong("id");
        this.name = (String) jsoncity.optString("name");
        this.country = (String) jsoncity.optString("country");


        JSONObject objlatlon = jsoncity.getJSONObject("coord");

        this.lat = objlatlon.getDouble("lat");
        this.lon = objlatlon.getDouble("lon");



    }
}
