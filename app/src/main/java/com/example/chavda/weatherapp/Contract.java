package com.example.chavda.weatherapp;

import android.provider.BaseColumns;

/**
 * Created by mark on 5/7/15.
 */
//String id, owner, secret, server, farm, title;
//        Boolean isPublic, isFriend, isFamily;

public class Contract {
    public static final String DATABASE_NAME = "weatherapp1.db";
    WeatherEntry pe = new WeatherEntry();

    public static final class WeatherEntry implements BaseColumns{
        public int test = 17;

        public static final String TABLE_NAME = "weather_entry_1";
        public static final String NAME = "name";
        public static final String COUNTRY = "country";
        public static final String LON = "lon";
        public static final String LAT = "lat";
        public static final String HUMIDITY = "humidity";
        public static final String DAY = "day";
        public static final String MIN = "min";
        public static final String MAX = "max";
        public static final String NIGHT = "night";
        public static final String EVE = "eve";
        public static final String MORN = "morn";
        public static final String PRESSURE = "pressure";
        public static final String MAIN = "main";
        public static final String DESCRIPTION = "description";
        public static final String ICON = "icon";
        public static final String SPEED = "speed";
        public static final String DEG = "deg";
        public static final String CLOUDS = "clouds";



    }

}
