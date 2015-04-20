package com.example.abhishek.weather;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ABHISHEK on 18-03-2015.
 */
public class DailyForecastFragment extends Fragment{

    View view;
    private ArrayList<JSONObject> mListJsonDaily = WeatherConditionsActivity.mListJson;
    private FindDate mDate = null;
    private JSONObject mDailyJson = null;
    private ParseJson mParse = null;
    private String mDebug = DailyForecastFragment.class.getName();
    private String[] description = null;
    private String[] temperature = null;
    private String[] day_date = null;
    private int[] images = null;
    private AddImages mAddImage = null;

    private ListView mDailyForecastList;
    private ImageView mImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_daily_forecast_fragment, container, false);

        mDailyForecastList = (ListView) view.findViewById(R.id.listView);
        mImage = (ImageView)view.findViewById(R.id.background);
        initialize();
        fill_values();

        if(!description.equals("null")) {
            CustomListAdapter mAdapter = new CustomListAdapter(getActivity().getApplicationContext(), description, temperature, day_date, images);
            mDailyForecastList.setAdapter(mAdapter);
        }
        else
            Toast.makeText(getActivity().getApplicationContext(),"Weather information could not be retrieved",Toast.LENGTH_SHORT).show();

        return view;
    }

    private void initialize(){

        mAddImage = new AddImages(getResources(),mImage);
        /* The list contains all the results for current weather conditions,
         * hourly weather forecast and daily weather forecast.
         * The second one being current weather conditions with index being 1*/
        mDailyJson = mListJsonDaily.get(1);
        mParse = new ParseJson();
        mDate = new FindDate();
    }

    private void fill_values(){
        try {

            int i = 0;
            /* Get all the items in the list JSONArray */
            JSONArray mJsonArray = mDailyJson.getJSONArray("list");
            JSONObject temp_mJson = null;
            /*Add background image according to the description id parsed CurrentConditionFragment.
             * Set the background same as the background of CurrentConditionFragment */
            int id = CurrentConditionFragment.mIdImage;
            mAddImage.add_daily_forecast_images(id);
            /* Initialize all the String array with length same as the length of JSONArray
             * initialized above. */
            description = new String[mJsonArray.length()];
            temperature = new String[mJsonArray.length()];
            day_date = new String[mJsonArray.length()];
            images = new int[mJsonArray.length()];

            while(i < mJsonArray.length()){
                temp_mJson = mJsonArray.getJSONObject(i);

                if(temp_mJson != null) {

                    /* find the date from the epoch time fed as input to the class FindDate.java*/
                    day_date[i] = mDate.getDate(temp_mJson.getLong("dt"));

                    /* find the temperature for entire day, convert it into celsius by subtracting
                     * 273.15 from it and then rounding it to the greatest integer by adding 0.5.
                     * Once the temperature is calculated it is then stored in the array */
                    double mMinTemp = mParse.getObjectJsonDouble(temp_mJson, "temp", "min");
                    int mMinimum = (int) (mMinTemp - 273.15 + 0.5);
                    double mMaxTemp = mParse.getObjectJsonDouble(temp_mJson, "temp", "max");
                    int mMaximum = (int) (mMaxTemp - 273.15 + 0.5);
                    temperature[i] = String.valueOf(mMinimum) + (char) 0x00B0 + 'C' + "/" + String.valueOf(mMaximum) + (char) 0x00B0 + 'C';

                    /* find the description for entire day and then it is stored in the array */
                    description[i] = mParse.getArrayJSON(temp_mJson, "weather", "description");
                    Log.d(mDebug, "The value of description array is: " + description);

                    /* add appropriate image to the weather description*/
                    add_images(temp_mJson.getJSONArray("weather").getJSONObject(0).getInt("id"), i);
                }
                i++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void add_images(int image_id, int position){

        switch(image_id){
            case 201:
            case 210:
            case 211:
            case 212:
                images[position] = R.drawable.icon_thunderstorms;
                break;
            case 500:
                images[position] = R.drawable.icon_light_rain;
                break;
            case 501:
                images[position] = R.drawable.icon_moderate_rain;
                break;
            case 502:
            case 511:
                images[position] = R.drawable.icon_heavy_intnsity_rain;
                break;
            case 600:
            case 601:
            case 602:
                images[position] = R.drawable.icon_snow;
                break;
            case 615:
            case 616:
                images[position] = R.drawable.icon_rain_and_snow;
                break;
            case 721:
            case 761:
                images[position] = R.drawable.icon_dust;
                break;
            case 741:
                images[position] = R.drawable.icon_fog;
                break;
            case 781:
                images[position] = R.drawable.icon_tornado;
                break;
            case 800:
                images[position] = R.drawable.icon_clear_sky_day;
                break;
            case 801:
                images[position] = R.drawable.icon_few_clouds;
                break;
            case 802:
                images[position] = R.drawable.icon_scattered_clouds;
                break;
            case 803:
                images[position] = R.drawable.icon_broken_clouds;
                break;
            case 804:
                images[position] = R.drawable.icon_overcast_clouds;
                break;
            default:
                images[position] = R.drawable.icon_clear_sky_day;
                break;
        }
    }

    public class CustomListAdapter extends ArrayAdapter<String>{

        private Context mContext;
        private String[] mDesc;
        private String[] mTemp;
        private String[] mDate;
        private int[] mImages;

        public CustomListAdapter(Context mContext, String[] mDesc, String[] mTemp, String[] mDate, int[] mImages) {
            super(mContext, R.layout.single_row_list_daily_forecast, R.id.text_description_value_daily_forecast, mDesc);
            Log.d(mDebug, "The value of mContext is: " + mContext);
            Log.d(mDebug,"The value of mDesc is: " + mDesc);
            this.mContext = mContext;
            this.mDesc = mDesc;
            this.mTemp = mTemp;
            this.mDate = mDate;
            this.mImages = mImages;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View row = convertView;
            if(row == null){
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.single_row_list_daily_forecast,parent,false);
            }
            TextView mDescription = (TextView) row.findViewById(R.id.text_description_value_daily_forecast);
            TextView mTemperature = (TextView) row.findViewById(R.id.text_value_temperature_daily_forecast);
            TextView date = (TextView) row.findViewById(R.id.text_date_daily_forecast);
            ImageView mWeatherImage = (ImageView) row.findViewById(R.id.image_description_daily_forecast);
            mWeatherImage.setImageResource(mImages[position]);
            date.setText(mDate[position]);
            mDescription.setText(mDesc[position]);
            mTemperature.setText(mTemp[position]);

            return row;
        }
    }
}
