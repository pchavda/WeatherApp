package com.example.chavda.weatherapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

/**
 * Created by mark on 5/7/15.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;

    private String[] projection = {
            Contract.WeatherEntry._ID,
            Contract.WeatherEntry.NAME,
            Contract.WeatherEntry.COUNTRY,
            Contract.WeatherEntry.LON,
            Contract.WeatherEntry.LAT,
            Contract.WeatherEntry.HUMIDITY,
            Contract.WeatherEntry.DAY,
            Contract.WeatherEntry.MIN,
            Contract.WeatherEntry.MAX,
            Contract.WeatherEntry.NIGHT,
            Contract.WeatherEntry.EVE,
            Contract.WeatherEntry.MORN,
            Contract.WeatherEntry.PRESSURE,
            Contract.WeatherEntry.MAIN,
            Contract.WeatherEntry.DESCRIPTION,
            Contract.WeatherEntry.ICON,
            Contract.WeatherEntry.SPEED,
            Contract.WeatherEntry.DEG,
            Contract.WeatherEntry.CLOUDS};


    private static final String DATABASE_CREATE =
            "CREATE TABLE " +
                    Contract.WeatherEntry.TABLE_NAME + " (" +
                    Contract.WeatherEntry._ID + " INTEGER PRIMARY KEY, " +
                    Contract.WeatherEntry.NAME + " TEXT NULL, " +
                    Contract.WeatherEntry.COUNTRY + " TEXT NULL, " +
                    Contract.WeatherEntry.LON + " DOUBLE NULL, " +
                    Contract.WeatherEntry.LAT + " DOUBLE NULL, " +
                    Contract.WeatherEntry.HUMIDITY + " DOUBLE NULL, " +
                    Contract.WeatherEntry.DAY + " DOUBLE NULL, " +
                    Contract.WeatherEntry.MIN + " DOUBLE NULL, " +
                    Contract.WeatherEntry.MAX + " DOUBLE NULL, " +
                    Contract.WeatherEntry.NIGHT + " DOUBLE NULL, " +
                    Contract.WeatherEntry.EVE + " DOUBLE NULL, " +
                    Contract.WeatherEntry.MORN + " DOUBLE NULL, " +
                    Contract.WeatherEntry.PRESSURE + " DOUBLE NULL, " +
                    Contract.WeatherEntry.MAIN + " TEXT NULL, " +
                    Contract.WeatherEntry.DESCRIPTION + " TEXT NULL, " +
                    Contract.WeatherEntry.ICON + " TEXT NULL, " +
                    Contract.WeatherEntry.SPEED + " DOUBLE NULL, " +
                    Contract.WeatherEntry.DEG + " DOUBLE NOT NULL, " +
                    Contract.WeatherEntry.CLOUDS + " DOUBLE NULL " +")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Contract.WeatherEntry.TABLE_NAME;

    public DataBaseHelper(Context context) {
        super(context, Contract.DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Log.i(Constants.TAG, "Create table command: " + DATABASE_CREATE);
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
    }

    public void insertPhotoEntry(list photo) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        //cv.put(Contract.WeatherEntry._ID,);
        cv.put(Contract.WeatherEntry.NAME, photo.getCity());
        cv.put(Contract.WeatherEntry.COUNTRY, photo.getCountry());
        cv.put(Contract.WeatherEntry.LON, photo.getLon());
        cv.put(Contract.WeatherEntry.LAT, photo.getLat());
        cv.put(Contract.WeatherEntry.HUMIDITY, photo.getHumidity());
        cv.put(Contract.WeatherEntry.DAY, photo.getTemp().get(0).getDay());
        cv.put(Contract.WeatherEntry.MIN, photo.getTemp().get(0).getMin());
        cv.put(Contract.WeatherEntry.MAX, photo.getTemp().get(0).getMax());
        cv.put(Contract.WeatherEntry.NIGHT, photo.getTemp().get(0).getNight());
        cv.put(Contract.WeatherEntry.EVE, photo.getTemp().get(0).getEve());
        cv.put(Contract.WeatherEntry.MORN, photo.getTemp().get(0).getMorn());
        cv.put(Contract.WeatherEntry.PRESSURE, photo.getPressure());
        cv.put(Contract.WeatherEntry.MAIN, photo.getMain());
        cv.put(Contract.WeatherEntry.DESCRIPTION, photo.getWeather().get(0).getDescription());
        cv.put(Contract.WeatherEntry.ICON, photo.getWeather().get(0).getIcon());
        cv.put(Contract.WeatherEntry.SPEED, photo.getSpeed());
        cv.put(Contract.WeatherEntry.DEG, photo.getDeg());
        cv.put(Contract.WeatherEntry.CLOUDS, photo.getClouds());


        db.insert(Contract.WeatherEntry.TABLE_NAME, null, cv);
    }

    public Cursor getAllRows() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(Contract.WeatherEntry.TABLE_NAME, projection, null, null, null, null, null);

    }

    public Cursor getRowByID(long id) {
        SQLiteDatabase db = getReadableDatabase();
        String[] ids = {String.valueOf(id)};
        return db.query(Contract.WeatherEntry.TABLE_NAME, projection, Contract.WeatherEntry._ID + "==?", ids, null, null, null);
    }

    public void deleteRow(long id) {
        SQLiteDatabase db = getWritableDatabase();
        String[] ids = {String.valueOf(id)};
        db.delete(Contract.WeatherEntry.TABLE_NAME, Contract.WeatherEntry._ID + "==?", ids);
    }

    public void addRows(List<list> photos) {
        for (list photo : photos) {
            insertPhotoEntry(photo);
        }
    }

    public void clearTable() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from " + Contract.WeatherEntry.TABLE_NAME);
    }

    public void dropTable() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(SQL_DELETE_ENTRIES);
    }


}
