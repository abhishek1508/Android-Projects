package com.developer.abhishek.weather_report;

import java.util.Locale;

/**
 * Created by ABHISHEK on 23-03-2015.
 */
public class CountryAbbreviation {

    public String getCountryName(String str){
        Locale locale = new Locale("en",str);
        return locale.getDisplayCountry();
    }
}
