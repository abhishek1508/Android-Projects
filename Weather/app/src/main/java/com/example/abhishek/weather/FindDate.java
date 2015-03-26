package com.example.abhishek.weather;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ABHISHEK on 25-03-2015.
 */

public class FindDate {

    private String mDebug = FindDate.class.getName();

    public String getDate(long mDate){

        SimpleDateFormat sdf = new SimpleDateFormat("E, MMM dd");
        String date = sdf.format(new Date(mDate * 1000));
        Log.d(mDebug,"The date found is: "+ date);
        return date;
    }
}
