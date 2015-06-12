package com.developer.abhishek.weather_report;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ABHISHEK on 23-03-2015.
 */
public class ParseJson {

    /*
     * The method takes 3 parameters. The first parameter is a JSONObject and the
     * remaining two parameters are Strings. After finding the appropriate value for
     * the passed JSONObject and String arguments, the method returns the String value
     * to the file from where the method is called.
     */
    public String getObjectJsonString(JSONObject json, String str1, String str2) throws JSONException {
        return json.getJSONObject(str1).getString(str2);
    }

    /*
     * The method takes 3 parameters. The first parameter is a JSONObject and the
     * remaining two parameters are Strings. After finding the appropriate value for
     * the passed JSONObject and String arguments, the method returns the String value
     * to the file from where the method is called.
     */
    public double getObjectJsonDouble(JSONObject json, String str1, String str2) throws JSONException {
        return json.getJSONObject(str1).getDouble(str2);
    }

    /*
     * The method takes 3 parameters. The first parameter is a JSONObject and the
     * remaining two parameters are Strings. After finding the appropriate value for
     * the passed JSONObject and String arguments, the method returns the String value
     * to the file from where the method is called.
     */
    public int getObjectJsonInteger(JSONObject json, String str1, String str2) throws JSONException {
        return json.getJSONObject(str1).getInt(str2);
    }
    /*
     * The method takes 3 parameters. The first parameter is a JSONObject and the
     * remaining two parameters are Strings. After finding the appropriate value for
     * the passed JSONObject and String arguments, the method returns the String value
     * to the file from where the method is called.
     */
    public String getArrayJSON(JSONObject json, String str1, String str2) throws JSONException {
        return json.getJSONArray(str1).getJSONObject(0).getString(str2);
    }

    public int getArrayJSONInt(JSONObject json, String str1, String str2) throws JSONException {
        return json.getJSONArray(str1).getJSONObject(0).getInt(str2);
    }
}
