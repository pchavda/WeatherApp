package com.example.chavda.weatherapp;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Chavda on 6/3/15.
 */
public class weather {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    long id;
    String main,description,icon;

    public weather(JSONObject jsonweather) throws JSONException {
        this.id = (Long) jsonweather.optLong("id");
        this.main = (String) jsonweather.optString("main");
        this.description = (String) jsonweather.optString("description");
        this.icon = (String) jsonweather.optString("icon");


    }
}
