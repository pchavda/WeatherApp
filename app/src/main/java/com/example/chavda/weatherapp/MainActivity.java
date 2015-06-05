package com.example.chavda.weatherapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private double latitude;
    private double longitude;
    LocationManager locationManager;
    LocationListener locationListener;
    ProgressBar progress;
    ArrayList<list> mwetherlist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progress = (ProgressBar) findViewById(R.id.progressBar);
        getLocation();
//        if (isOnline()) {
//            LoadData task = new LoadData();
//            task.execute();
//        } else {
//            progress.setVisibility(View.GONE);
//            Toast.makeText(this, "Not online", Toast.LENGTH_LONG).show();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setmPhotos(ArrayList<list> mPhotos) {
        this.mwetherlist = mPhotos;
    }

    public ArrayList<list> getmPhotos() {
        return mwetherlist;
    }


    private class LoadData extends AsyncTask<String, Long, Long> {
        HttpURLConnection connection = null;
        ArrayList<list> photos;

        @Override
        protected void onPreExecute() {
            progress.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Long... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Long doInBackground(String... strings) {
            String dataString = "http://api.openweathermap.org/data/2.5/forecast/daily?lat=34.052231&lon=-118.243683&cnt=10&mode=json";

//            String dataString = "https://api.flickr.com/services/rest/?method=flickr.photos.getRecent&api+key=" +
//                   Constants.API_KEY + "&per_page=" + Constants.NUM_PHOTOS + "&format=json&nojsoncallback=1";
            //Log.i(Constants.TAG, dataString);
            try {
                URL dataUrl = new URL(dataString);
                connection = (HttpURLConnection) dataUrl.openConnection();
                connection.connect();
                int status = connection.getResponseCode();
                Log.d("TAG", "status " + status);
                //if it is successful
                if (status == 200) {
                    InputStream is = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    String responseString;
                    StringBuilder sb = new StringBuilder();

                    while ((responseString = reader.readLine()) != null) {
                        sb = sb.append(responseString);
                    }
                    String photoData = sb.toString();

                    photos = list.makeweatherList(photoData);
                    //Log.d(Constants.TAG, photoData);

                    return 0l;
                } else {
                    return 1l;
                }
            } catch (MalformedURLException e) {
                //Log.i(Constants.TAG, "Malformed Url");
                e.printStackTrace();
                return 1l;
            } catch (IOException e) {
                e.printStackTrace();
                return 1l;
            } catch (JSONException e) {
                e.printStackTrace();
                return 1l;
            } finally {
                if (connection != null)
                    connection.disconnect();
            }



        }


        @Override
        protected void onPostExecute(Long result) {
//            if (result != 1l) {
//                setmPhotos(photos);
//                showList();
//
//            } else {
//                Toast.makeText(getApplicationContext(), "AsyncTask didn't complete", Toast.LENGTH_LONG).show();
//            }
//            progress.setVisibility(View.GONE);

            if (result != 1l) {
                DataBaseHelper dbHelper = new DataBaseHelper(getApplicationContext());


                dbHelper.clearTable();
                dbHelper.addRows(photos);
                dbHelper.close();
                if(locationManager != null && locationListener != null){
                    locationManager.removeUpdates(locationListener);
                }
                showList();

            } else {
                Toast.makeText(getApplicationContext(), "AsyncTask didn't complete", Toast.LENGTH_LONG).show();
            }
            progress.setVisibility(View.GONE);

        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        if(locationManager != null && locationListener != null){
            locationManager.removeUpdates(locationListener);
        }
    }

    public void showList() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.container, new WeatherFragment());
        ft.commit();
    }

    public boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public void getLocation(){

        //latitude = location.getLatitude();
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                Toast.makeText(MainActivity.this, "latitude: " + latitude + "longitude: " + longitude, Toast.LENGTH_LONG).show();
                startLoadTask(MainActivity.this);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {}

            @Override
            public void onProviderEnabled(String s) {}

            @Override
            public void onProviderDisabled(String s) {}
        };

        locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, locationListener);

    }

    public void startLoadTask(Context c){
        if (isOnline()) {
            LoadData task = new LoadData();
            task.execute();
        } else {
            Toast.makeText(c, "Not online", Toast.LENGTH_LONG).show();
        }
    }
}
