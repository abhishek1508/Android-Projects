package com.developer.abhishek.weather_report;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ABHISHEK on 20-03-2015.
 */

public class DownloadWeather extends AsyncTask<String, Void, WrapperWeatherData> {

    private final static String mDebug = DownloadWeather.class.getName();
    private Context mContext;
    private String mCityEntered;
    private int count = 0;
    URL mUrl = null;
    HttpURLConnection mConnection = null;
    JSONObject mJson = null;
    private ProgressDialog mProgress = null;

    /*
     * Constructor to pass the context of Activity calling the DownloadWeather
     * class.
     */
    public DownloadWeather(Context mContext, String mCityEntered){
        this.mCityEntered = mCityEntered;
        this.mContext = mContext;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        /*
         * Show the progress dialog and appropriate messages prior to starting
         * another thread via AsyncTask.
         */
        mProgress = ProgressDialog.show(mContext,"Downloading","Please Wait");
    }

    @Override
    protected WrapperWeatherData doInBackground(String... params) {

        /*
         * Object of Wrapper class being created to store all the JSOBObject
         * returned as a result of the http call made.
         */
        WrapperWeatherData mWrapper = new WrapperWeatherData();
        mWrapper.mCity = mCityEntered;
        for(String mWeatherUpdates : params) {
            Log.d(mDebug,"mWeatherUpdates: "+ mWeatherUpdates);
            try {
                mUrl = new URL(String.format(mWeatherUpdates,mCityEntered));
            } catch (MalformedURLException e) {
                Log.d(mDebug, "Code jumped to catch due to malformed URL passed " + e);
            }
            Log.d(mDebug, "The object mUrl formed is: " + mUrl);
            if (mUrl != null) {
                try {
                    mConnection = (HttpURLConnection) mUrl.openConnection();
                    Log.d(mDebug, "The object mConnection formed is: " + mConnection);
                    if (mConnection != null)
                        mConnection.addRequestProperty("x-api-key", "2222");
                    else
                        Log.d(mDebug, "Check for proper initialization of mConnection as it is null");
                    BufferedReader inputReader = new BufferedReader(new InputStreamReader(mConnection.getInputStream()));
                    StringBuffer buf = new StringBuffer(2048);
                    String temp = "";
                    while ((temp = inputReader.readLine()) != null) {
                        buf.append(temp).append("\n");
                    }
                    inputReader.close();
                    try {
                        mJson = new JSONObject(buf.toString());
                        Log.d(mDebug, "The object mJson formed is: " + mJson);
                        if (mJson != null) {
                            if (mJson.getInt("cod") != 200) {
                                Log.d(mDebug, "Checking the value of cod: The value of cod in the response received is: "
                                        + mJson.getInt("cod"));
                                mWrapper = null;
                                break;
                            }
                        } else {
                            Log.d(mDebug, "Check for proper initialization of mJson as it is null");
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Log.d(mDebug, "Check for proper initialization of mUrl as it is null");
            }
            Log.d(mDebug, "The value of mJson is: " + mJson);
            if(count == 0)
                mWrapper.mCurrentCondition = mJson;
            else if(count == 1)
                mWrapper.mDailyForecast = mJson;
            else if(count == 2)
                mWrapper.mHourlyForecast = mJson;
            count++;
        }
        return mWrapper;
    }

    @Override
    protected void onPostExecute(WrapperWeatherData mWrapper) {
        super.onPostExecute(mWrapper);
        if(mWrapper != null) {
            Log.d(mDebug, "Value of mWrapper.mCurrentCondition is: " + mWrapper.mCurrentCondition);
            Log.d(mDebug, "Value of mWrapper.mDailyForecast is: " + mWrapper.mDailyForecast);
            Log.d(mDebug, "Value of mWrapper.mHourlyForecast is: " + mWrapper.mHourlyForecast);
            mProgress.dismiss();

            /*
             * Intent to start the Activity WeatherConditionsActivity by passing all the
             * returned JSONObject in the form of strings.
             */
            Intent intent = new Intent(mContext, WeatherConditionsActivity.class);
            if(mWrapper.mCurrentCondition != null && mWrapper.mDailyForecast != null && mWrapper.mHourlyForecast != null) {
                intent.putExtra("current", (mWrapper.mCurrentCondition).toString());
                intent.putExtra("daily", (mWrapper.mDailyForecast).toString());
                intent.putExtra("hourly", (mWrapper.mHourlyForecast).toString());
                intent.putExtra("city_name", mWrapper.mCity);
                mContext.startActivity(intent);
            }
            else
                Toast.makeText(mContext,"Weather for the city could not be retrieved",Toast.LENGTH_SHORT).show();
        }
        else {
            mProgress.dismiss();
            Toast.makeText(mContext, "City not found", Toast.LENGTH_LONG).show();
        }
    }
}
