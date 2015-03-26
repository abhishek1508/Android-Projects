package com.example.abhishek.weather;

import org.json.JSONObject;

/**
 * Created by ABHISHEK on 22-03-2015.
 */

/*
 * A class created to store all the json data returned by making the
 * http calls for current weather conditions, daily forecast and
 * hourly forecast from the doInBackground method of AsyncTask
 * DownloadWeather.java. Once the value is stored, object of wrapper
 * class is then returned to onPostExecute method of the AsyncTask.
 */
public class WrapperWeatherData {

    public JSONObject mCurrentCondition;
    public JSONObject mDailyForecast;
    public JSONObject mHourlyForecast;
    public String mCity;

}
