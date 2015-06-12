package com.developer.abhishek.weather_report;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    public String getCurrentDate(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(c.getTime());
    }

    public String formatDate(String raw_date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfs = new SimpleDateFormat("MMM dd");
        Date dt = null;
        try {
            dt = sdf.parse(raw_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdfs.format(dt);
    }
    public String formatTime(String raw_value){

        if(raw_value.equalsIgnoreCase("12:00:00"))
            return "12:00 PM";
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        SimpleDateFormat sdfs = new SimpleDateFormat("hh:mm a");
        Date dt = null;
        try {
            dt = sdf.parse(raw_value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(dt != null)
            return sdfs.format(dt);
        return null;
    }
}
