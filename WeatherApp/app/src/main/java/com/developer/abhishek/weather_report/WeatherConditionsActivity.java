package com.developer.abhishek.weather_report;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ABHISHEK on 16-03-2015.
 */
public class WeatherConditionsActivity extends FragmentActivity {

    int check = 0;
    ViewPager mPager;
    WeatherFragmentPageViewAdapter mPageAdapter;
    private static String mDebug = WeatherConditionsActivity.class.getName();
    private JSONObject mJsonCurrent = null;
    private JSONObject mJsonDaily = null;
    private JSONObject mJsonHourly = null;
    public static ArrayList<JSONObject> mListJson = null;
    int currentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_weather_conditions);
        mListJson = new ArrayList<>();
        /*
         * Get all the data from the intent passed by the Activity EnterLocation.java
         * in String format and then converts them to json format. Once converted to
         * json format it is then added to an ArrayList of the type JSONObject
         * from which the data is then retrieved in individual fragments respectively.
         */
        Intent intent = getIntent();
        String mTempCurrent = intent.getStringExtra("current");
        String mTempDaily = intent.getStringExtra("daily");
        String mTempHourly = intent.getStringExtra("hourly");
        try {
            mJsonCurrent = new JSONObject(mTempCurrent);
            mJsonDaily = new JSONObject(mTempDaily);
            mJsonHourly = new JSONObject(mTempHourly);
            if(mListJson != null) {
                mListJson.add(mJsonCurrent);
                mListJson.add(mJsonDaily);
                mListJson.add(mJsonHourly);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        WeatherDataTransfer wdt = new WeatherDataTransfer(intent.getStringExtra("city_name"));

        mPager = (ViewPager) findViewById(R.id.viewpager);
        mPager.setPageTransformer(true, new DepthPageTransformer());
        FragmentManager fm = getSupportFragmentManager();
        mPageAdapter = new WeatherFragmentPageViewAdapter(fm);
        mPager.setAdapter(mPageAdapter);


        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                check = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if(check > 0)
            mPager.setCurrentItem(check-1,true);
        else {
            mListJson = null;
            super.onBackPressed();
        }
    }


    public static class WeatherDataTransfer{

        static String mCityName;

        public WeatherDataTransfer(String mCityName){
            this.mCityName = mCityName;
        }

        public static String get_mCityName(){
            return mCityName;
        }
    }


}
