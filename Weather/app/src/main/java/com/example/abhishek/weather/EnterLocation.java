package com.example.abhishek.weather;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

public class EnterLocation extends ActionBarActivity {

    AutoCompleteTextView mAuto;
    String[] mCity = null;
    private ArrayAdapter<String> mAdapter = null;
    private double mScreen;
    private WindowManager windowManager;
    private String mCityEntered = null;
    private String mCityQuery = "http://api.openweathermap.org/data/2.5/weather?q=%s";
    private String mCityDaily = "http://api.openweathermap.org/data/2.5/forecast/daily?q=%s&mode=json&cnt=16";
    private String mCityHourly = "http://api.openweathermap.org/data/2.5/forecast?q=%s&mode=json";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_weather);

        handleScreenSize();
        mAuto = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        init();

        mAuto.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_GO) {
                    mCityEntered = mAuto.getText().toString();
                    if (!mCityEntered.equals("")){
/*                        mCityQuery = String.format(mCityQuery, mCityEntered);
                        mCityDaily = String.format(mCityDaily, mCityEntered);
                        mCityHourly = String.format(mCityHourly, mCityEntered);*/
                        DownloadWeather mDownload = new DownloadWeather(EnterLocation.this,mCityEntered);
                        mDownload.execute(mCityQuery, mCityDaily, mCityHourly);
                    }
                    else
                        Toast.makeText(getApplicationContext(),R.string.no_city_alert, Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });
    }
    private void handleScreenSize(){
        /*
         * The method checks for the size of the screen. The app supports smart phones
         * with screen sizes less than or equal to 5.5 inches. If the screen size is
         * greater than 5.5 inch the app is closed and user is shown an appropriate message.
         */
        windowManager = getWindowManager();
        ScreenSize mSize = new ScreenSize(windowManager);
        mScreen = mSize.getScreenSize();
        if(mScreen > 5.5) {
            finish();
            Toast.makeText(EnterLocation.this, R.string.large_screen_size, Toast.LENGTH_LONG).show();
        }
    }

    private void init(){
        mCity = getResources().getStringArray(R.array.list_of_cities);
        if(!mCity.equals(null))
            mAdapter = new ArrayAdapter<String>(EnterLocation.this, android.R.layout.simple_list_item_1, mCity);
        if(mAdapter != null)
            mAuto.setAdapter(mAdapter);
    }

}
