package com.example.chavda.weatherapp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Chavda on 6/3/15.
 */
public class temp {
    public double getDay() {
        return day;
    }

    public void setDay(double day) {
        this.day = day;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getNight() {
        return night;
    }

    public void setNight(double night) {
        this.night = night;
    }

    public double getEve() {
        return eve;
    }

    public void setEve(double eve) {
        this.eve = eve;
    }

    public double getMorn() {
        return morn;
    }

    public void setMorn(double morn) {
        this.morn = morn;
    }

    double day,min, max,night, eve,morn;

    public temp(JSONObject jsontemp) throws JSONException {
        this.day = (Double) jsontemp.optDouble("day");
        this.min = (Double) jsontemp.optDouble("min");
        this.max = (Double) jsontemp.optDouble("max");
        this.night = (Double) jsontemp.optDouble("night");
        this.eve = (Double) jsontemp.optDouble("eve");
        this.morn = (Double)jsontemp.optDouble("morn");


    }
}
