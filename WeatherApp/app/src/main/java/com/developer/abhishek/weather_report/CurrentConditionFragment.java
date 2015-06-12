package com.developer.abhishek.weather_report;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ABHISHEK on 18-03-2015.
 */
public class CurrentConditionFragment extends Fragment implements View.OnClickListener{

    RelativeLayout mPressImage, mPressValue, mHumidImage, mHumidValue, mWindImage, mWindValue,mTemperatureImage,mTemperatureValue;
    TextView mCityName, mCountryName, mWeatherDesc, mPressureVal, mHumidityVal, mWindVal, mTempVal, mMaxVal, mMinVal;
    ImageView mBackground, mImageDescription;
    View view;

    private ArrayList<JSONObject> mListJsonCurrent = WeatherConditionsActivity.mListJson;
    private JSONObject mJsonCurrent = null;
    private String mCityEntered = null;
    private ParseJson mParse = null;
    private CountryAbbreviation mAbbreviate = null;
    private String mDebug = CurrentConditionFragment.class.getName();
    private final int mTo_mph = 3600;
    private AddImages mAddImage = null;
    public static int mIdImage = 0;
    Animation FadeIn,FadeOut;

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
        mTemperatureImage = (RelativeLayout) view.findViewById(R.id.relativeLayout_temp_symbol);
        mTemperatureValue = (RelativeLayout) view.findViewById(R.id.relativeLayout_temp_value);

        /* initializing variables to set all the textviews and imageviews */
        mCityName = (TextView) view.findViewById(R.id.text_cityname);
        mCountryName = (TextView) view.findViewById(R.id.text_country_name);
        mWeatherDesc = (TextView) view.findViewById(R.id.text_description);
        mPressureVal = (TextView) view.findViewById(R.id.text_value_pressure);
        mHumidityVal = (TextView) view.findViewById(R.id.text_value_humidity);
        mWindVal = (TextView) view.findViewById(R.id.text_value_wind);
        mTempVal = (TextView) view.findViewById(R.id.text_temp_current_condition_value);
        mMaxVal = (TextView) view.findViewById(R.id.text_max_temp_current_condition_value);
        mMinVal = (TextView) view.findViewById(R.id.text_min_temp_current_condition_value);
        mBackground = (ImageView) view.findViewById(R.id.background);
        mImageDescription = (ImageView) view.findViewById(R.id.image_description);

        initialize();
        fill_values();

        return view;
    }

    private void initialize(){

        /*mAddImage = new AddImages(getResources(),mBackground);*/
        mAddImage = new AddImages(getResources(),mBackground,mImageDescription);
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
        mTemperatureImage.setOnClickListener(this);
        mTemperatureValue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        FadeIn = AnimationUtils.loadAnimation(getActivity(),R.anim.animation_fade_in);
        FadeOut = AnimationUtils.loadAnimation(getActivity(),R.anim.animation_fade_out);
        Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.fly_out_animation);
        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.fly_in_animation);
        /*
         * Add animations to interchanging image and value between the pressure,
         * wind and humidity circles with the help of view flipper.
         */
        switch(v.getId()){

            case R.id.relativeLayout_circle1_image:
                mPressValue.setVisibility(View.VISIBLE);
                mPressValue.setAnimation(FadeIn);
                mPressImage.setAnimation(FadeOut);
                mPressImage.setVisibility(View.INVISIBLE);
                break;
            case R.id.relativeLayout_circle2_image:
                mHumidValue.setVisibility(View.VISIBLE);
                mHumidValue.setAnimation(FadeIn);
                mHumidImage.setAnimation(FadeOut);
                mHumidImage.setVisibility(View.INVISIBLE);
                break;
            case R.id.relativeLayout_circle3_image:
                mWindValue.setVisibility(View.VISIBLE);
                mWindValue.setAnimation(FadeIn);
                mWindImage.setAnimation(FadeOut);
                mWindImage.setVisibility(View.INVISIBLE);
                break;
            case R.id.relativeLayout_circle1_value:
                mPressImage.setVisibility(View.VISIBLE);
                mPressImage.setAnimation(FadeIn);
                mPressValue.setAnimation(FadeOut);
                mPressValue.setVisibility(View.INVISIBLE);
                break;
            case R.id.relativeLayout_circle2_value:
                mHumidImage.setVisibility(View.VISIBLE);
                mHumidImage.setAnimation(FadeIn);
                mHumidValue.setAnimation(FadeOut);
                mHumidValue.setVisibility(View.INVISIBLE);
                break;
            case R.id.relativeLayout_circle3_value:
                mWindImage.setVisibility(View.VISIBLE);
                mWindImage.setAnimation(FadeIn);
                mWindValue.setAnimation(FadeOut);
                mWindValue.setVisibility(View.INVISIBLE);
                break;
            case R.id.relativeLayout_temp_symbol:
                mTemperatureImage.startAnimation(animation);
                mTemperatureImage.setVisibility(View.INVISIBLE);
                mTemperatureValue.setVisibility(View.VISIBLE);
                mTemperatureValue.startAnimation(anim);
                break;
            case R.id.relativeLayout_temp_value:
                mTemperatureValue.startAnimation(animation);
                mTemperatureValue.setVisibility(View.INVISIBLE);
                mTemperatureImage.setVisibility(View.VISIBLE);
                mTemperatureImage.startAnimation(anim);
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

                /*Set the weather description icon for current conditions in the city*/
                mIdImage = mParse.getArrayJSONInt(mJsonCurrent, "weather", "id");
                if(mAddImage != null)
                    mAddImage.add_images(mIdImage);

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
}
