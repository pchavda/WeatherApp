package com.example.chavda.weatherapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Chavda on 6/3/15.
 */
public class list {

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDeg() {
        return deg;
    }

    public void setDeg(double deg) {
        this.deg = deg;
    }

    public double getClouds() {
        return clouds;
    }

    public void setClouds(double clouds) {
        this.clouds = clouds;
    }

    public ArrayList<com.example.chavda.weatherapp.temp> getTemp() {
        return temp;
    }

    public void setTemp(ArrayList<com.example.chavda.weatherapp.temp> temp) {
        this.temp = temp;
    }

    public ArrayList<com.example.chavda.weatherapp.weather> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<com.example.chavda.weatherapp.weather> weather) {
        this.weather = weather;
    }

    String dt;
    String city,country;
    double pressure,humidity,speed,deg,clouds;
    double lon;
    String main;

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    double lat;
    ArrayList<temp> temp = new ArrayList<>();
    ArrayList<weather> weather =new ArrayList<>();

    public list(JSONObject jsonWeather, JSONObject jsonCity) throws JSONException {
        this.dt = (String) jsonWeather.optString("dt");
        this.pressure = (Double) jsonWeather.optDouble("pressure");
        this.humidity = (Double) jsonWeather.optDouble("humidity");
        this.speed = (Double) jsonWeather.optDouble("speed");
        this.deg = (Double) jsonWeather.optDouble("deg");
        this.clouds = (Double)jsonWeather.optDouble("clouds");

        temp objtemp = new temp(jsonWeather.optJSONObject("temp"));
        this.temp.add(objtemp);

        JSONArray obj = jsonWeather.getJSONArray("weather");

        weather objWeather = new weather(obj.getJSONObject(0));
        this.weather.add(objWeather);
this.main = objWeather.main;
        JSONObject objlatlon = jsonCity.getJSONObject("coord");;
        //JSONObject objJSOnCity = objlatlon.getJSONObject("coord");

        this.lat = objlatlon.getDouble("lat");
        this.lon = objlatlon.getDouble("lon");

        city objCity = new city(jsonCity);
        city = objCity.name;
        country = objCity.country;

    }

    public static ArrayList<list> makeweatherList(String weatherData) throws JSONException{
        ArrayList<list> weatherlist = new ArrayList<>();
        JSONObject data = new JSONObject(weatherData);
        JSONArray list = data.optJSONArray("list");
        JSONObject objjcity = data.optJSONObject("city");



        for(int i = 0; i < list.length(); i++){
            JSONObject forecast = (JSONObject)list.get(i);
            list objList = new list(forecast,objjcity);

            weatherlist.add(objList);

        }
        return weatherlist;
    }
}
