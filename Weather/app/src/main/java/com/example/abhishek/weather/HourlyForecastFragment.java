package com.example.abhishek.weather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ABHISHEK on 18-03-2015.
 */
public class HourlyForecastFragment extends Fragment {

    private ArrayList<JSONObject> mListJsonHourly = WeatherConditionsActivity.mListJson;
    private JSONObject mHourlyJson = null;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_hourly_forecast_fragment, container, false);

        mHourlyJson = mListJsonHourly.get(2);
        return view;
    }
}
