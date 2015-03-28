package com.example.abhishek.weather;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ABHISHEK on 18-03-2015.
 */
public class CurrentConditionFragment extends Fragment implements View.OnClickListener{

    ViewFlipper mVFlipper_press, mVFlipper_humid, mVFlipper_wind;
    RelativeLayout mPressImage, mPressValue, mHumidImage, mHumidValue, mWindImage, mWindValue;
    TextView mCityName, mCountryName, mWeatherDesc, mWindChill, mPressureVal, mHumidityVal, mWindVal, mTempVal, mMaxVal, mMinVal;
    ImageView mBackground, mImageDescription;
    View view;

    private ArrayList<JSONObject> mListJsonCurrent = WeatherConditionsActivity.mListJson;
    private JSONObject mJsonCurrent = null;
    private String mCityEntered = null;
    private ParseJson mParse = null;
    private CountryAbbreviation mAbbreviate = null;
    private String mDebug = CurrentConditionFragment.class.getName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_current_condition_fragment, container, false);

        /*initializing the variables to implement view flipper for 3 circles
         * visible in the UI shown by pressure, humidity and wind.*/
        mPressImage = (RelativeLayout) view.findViewById(R.id.relativeLayout_circle1_image);
        mPressValue = (RelativeLayout) view.findViewById(R.id.relativeLayout_circle1_value);
        mHumidImage = (RelativeLayout) view.findViewById(R.id.relativeLayout_circle2_image);
        mHumidValue = (RelativeLayout) view.findViewById(R.id.relativeLayout_circle2_value);
        mWindImage = (RelativeLayout) view.findViewById(R.id.relativeLayout_circle3_image);
        mWindValue = (RelativeLayout) view.findViewById(R.id.relativeLayout_circle3_value);
        mVFlipper_press = (ViewFlipper) view.findViewById(R.id.view_flipper_pressure);
        mVFlipper_humid = (ViewFlipper) view.findViewById(R.id.view_flipper_humidity);
        mVFlipper_wind = (ViewFlipper) view.findViewById(R.id.view_flipper_wind);

        /* initializing variables to set all the textviews and imageviews */
        mCityName = (TextView) view.findViewById(R.id.text_cityname);
        mCountryName = (TextView) view.findViewById(R.id.text_country_name);
        mWeatherDesc = (TextView) view.findViewById(R.id.text_description);
        mWindChill = (TextView) view.findViewById(R.id.text_windchill);
        mPressureVal = (TextView) view.findViewById(R.id.text_value_pressure);
        mHumidityVal = (TextView) view.findViewById(R.id.text_value_humidity);
        mWindVal = (TextView) view.findViewById(R.id.text_value_wind);
        mTempVal = (TextView) view.findViewById(R.id.text_value_temperature);
        mMaxVal = (TextView) view.findViewById(R.id.text_value_max);
        mMinVal = (TextView) view.findViewById(R.id.text_value_min);
        mBackground = (ImageView) view.findViewById(R.id.background);
        mImageDescription = (ImageView) view.findViewById(R.id.image_description);

        initialize();
        fill_values();
        return view;
    }

    private void initialize(){

        /* The list contains all the results for current weather conditions,
         * hourly weather forecast and daily weather forecast.
         * The first one being current weather conditions with index being 0*/
        mJsonCurrent = mListJsonCurrent.get(0);
        mParse = new ParseJson();
        mCityEntered = WeatherConditionsActivity.WeatherDataTransfer.get_mCityName();
        mAbbreviate = new CountryAbbreviation();
        /*
         * Set the OnClickListener to change the image and value for pressure,
         * wind and humidity when clicked by the user.
         */
        mPressImage.setOnClickListener(this);
        mPressValue.setOnClickListener(this);
        mHumidImage.setOnClickListener(this);
        mHumidValue.setOnClickListener(this);
        mWindImage.setOnClickListener(this);
        mWindValue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        /*
         * Add animations to interchanging image and value between the pressure,
         * wind and humidity circles with the help of view flipper.
         */
        switch(v.getId()){
            case R.id.relativeLayout_circle1_image:
                mVFlipper_press.setAnimation(AnimationUtils.loadAnimation(v.getContext(), R.anim.left_to_right));
                mVFlipper_press.showNext();
                break;
            case R.id.relativeLayout_circle2_image:
                mVFlipper_humid.setAnimation(AnimationUtils.loadAnimation(v.getContext(),R.anim.left_to_right));
                mVFlipper_humid.showNext();
                break;
            case R.id.relativeLayout_circle3_image:
                mVFlipper_wind.setAnimation(AnimationUtils.loadAnimation(v.getContext(),R.anim.left_to_right));
                mVFlipper_wind.showNext();
                break;
            case R.id.relativeLayout_circle1_value:
                mVFlipper_press.setAnimation(AnimationUtils.loadAnimation(v.getContext(),R.anim.right_to_left));
                mVFlipper_press.showPrevious();
                break;
            case R.id.relativeLayout_circle2_value:
                mVFlipper_humid.setAnimation(AnimationUtils.loadAnimation(v.getContext(),R.anim.right_to_left));
                mVFlipper_humid.showPrevious();
                break;
            case R.id.relativeLayout_circle3_value:
                mVFlipper_wind.setAnimation(AnimationUtils.loadAnimation(v.getContext(),R.anim.right_to_left));
                mVFlipper_wind.showPrevious();
                break;
        }
    }

    private void fill_values(){

        /*Set the city name*/
        if(mCityEntered != null)
            mCityName.setText(mCityEntered);

        if(mParse != null && mJsonCurrent != null) {
            try {

                /*Set the country name*/
                String mFillCountry = mParse.getObjectJsonString(mJsonCurrent, "sys", "country");
                if(mFillCountry.length() <= 2) {
                    if (mAbbreviate != null)
                        mCountryName.setText(mAbbreviate.getCountryName(mFillCountry));
                    else
                        Log.d(mDebug,"The object mAbbreviate is not set and its value is null.");
                }
                else
                    mCountryName.setText(mFillCountry);

                int image_id = mParse.getArrayJSONInt(mJsonCurrent, "weather", "id");
                add_images(image_id);

                /*Set the weather description for current conditions in the city*/
                mWeatherDesc.setText((mParse.getArrayJSON(mJsonCurrent,"weather","description")).toUpperCase());

                /*Set the current temperature for current conditions in the city*/
                double mTempCurrTemperature = mParse.getObjectJsonDouble(mJsonCurrent,"main","temp");
                int mTemperature = (int) (mTempCurrTemperature-273.15+0.5);
                mTempVal.setText(String.valueOf(mTemperature)+(char) 0x00B0+ 'C');

                /*Set the minimum temperature for current conditions in the city*/
                mTempCurrTemperature = mParse.getObjectJsonDouble(mJsonCurrent,"main","temp_min");
                mTemperature = (int) (mTempCurrTemperature-273.15+0.5);
                mMinVal.setText(String.valueOf(mTemperature)+(char) 0x00B0+ 'C');

                /*Set the maximum temperature for current conditions in the city*/
                mTempCurrTemperature = mParse.getObjectJsonDouble(mJsonCurrent,"main","temp_max");
                mTemperature = (int) (mTempCurrTemperature-273.15+0.5);
                mMaxVal.setText(String.valueOf(mTemperature)+(char) 0x00B0+ 'C');

                mPressureVal.setText(mParse.getObjectJsonInteger(mJsonCurrent,"main","pressure")+" hPa");

                mHumidityVal.setText(mParse.getObjectJsonInteger(mJsonCurrent,"main","humidity")+" %");

                mWindVal.setText(String.valueOf(mParse.getObjectJsonDouble(mJsonCurrent,"wind","speed"))+" mps");

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

    private void add_images(int image_id) {
        switch (image_id) {
            case 201:
            case 210:
            case 211:
            case 212:
                mImageDescription.setImageResource(R.drawable.icon_thunderstorms);
                break;
            case 500:
                mImageDescription.setImageResource(R.drawable.icon_light_rain);
                break;
            case 501:
                mImageDescription.setImageResource(R.drawable.icon_moderate_rain);
                break;
            case 502:
            case 511:
                mImageDescription.setImageResource(R.drawable.icon_heavy_intnsity_rain);
                break;
            case 600:
            case 601:
            case 602:
                mImageDescription.setImageResource(R.drawable.icon_snow);
                break;
            case 615:
            case 616:
                mImageDescription.setImageResource(R.drawable.icon_rain_and_snow);
                break;
            case 721:
            case 761:
                mImageDescription.setImageResource(R.drawable.icon_dust);
                break;
            case 741:
                mImageDescription.setImageResource(R.drawable.icon_fog);
                break;
            case 781:
                mImageDescription.setImageResource(R.drawable.icon_tornado);
                break;
            case 800:
                mImageDescription.setImageResource(R.drawable.icon_clear_sky_day);
                break;
            case 801:
                mImageDescription.setImageResource(R.drawable.icon_few_clouds);
                break;
            case 802:
                mImageDescription.setImageResource(R.drawable.icon_scattered_clouds);
                break;
            case 803:
                mImageDescription.setImageResource(R.drawable.icon_broken_clouds);
                break;
            case 804:
                mImageDescription.setImageResource(R.drawable.icon_overcast_clouds);
                break;
            default:
                mImageDescription.setImageResource(R.drawable.icon_clear_sky_day);
                break;

        }
    }
}
