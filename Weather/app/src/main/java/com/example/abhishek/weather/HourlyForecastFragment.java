package com.example.abhishek.weather;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by ABHISHEK on 18-03-2015.
 */
public class HourlyForecastFragment extends Fragment{

    private ArrayList<JSONObject> mListJsonHourly = WeatherConditionsActivity.mListJson;
    private JSONObject mHourlyJson = null;
    View view;
    GridView grid;
    TextView mDayDateGrid1,mDayDateGrid2,mDayDateGrid3;
    ImageView mImage;
    CustomGridView grid1_desc,grid1_data,grid2_data,grid2_desc,grid3_data,grid3_desc;
    boolean mGrid1_isHidden = false;
    boolean mGrid2_isHidden = false;
    boolean mGrid3_isHidden = false;
    private ParseJson mParse = null;
    private AddImages mAddImage = null;
    String[] time_grid1 = new String[8];
    String[] time_grid2 = new String[8];
    String[] time_grid3 = new String[8];
    String[] temp_grid1 = new String[8];
    String[] temp_grid2 = new String[8];
    String[] temp_grid3 = new String[8];
    int[] mImage_grid1 = new int[8];
    int[] mImage_grid2 = new int[8];
    int[] mImage_grid3 = new int[8];
    private FindDate mDate;
    private String mDebug = HourlyForecastFragment.class.getName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_hourly_forecast_fragment, container, false);
        mHourlyJson = mListJsonHourly.get(2);

        grid1_data = (CustomGridView) view.findViewById(R.id.gridView_data_first_child);
        grid1_desc = (CustomGridView) view.findViewById(R.id.gridView_data_first_child_animate);
        grid2_data = (CustomGridView) view.findViewById(R.id.gridView_data_second_child);
        grid2_desc = (CustomGridView) view.findViewById(R.id.gridView_data_second_child_animate);
        grid3_data = (CustomGridView) view.findViewById(R.id.gridView_data_third_child);
        grid3_desc = (CustomGridView) view.findViewById(R.id.gridView_data_third_child_animate);
        mDayDateGrid1 = (TextView) view.findViewById(R.id.textView_day_date_hourly_forecast_1);
        mDayDateGrid2 = (TextView) view.findViewById(R.id.textView_day_date_hourly_forecast_2);
        mDayDateGrid3 = (TextView) view.findViewById(R.id.textView_day_date_hourly_forecast_3);
        mImage = (ImageView)view.findViewById(R.id.background_hourly_forecast);

        mDate = new FindDate();
        mParse = new ParseJson();
        mAddImage = new AddImages(getResources(),mImage);
        fill_values_hourly();

        GridAdapter mAdapter_data1 = new GridAdapter(getActivity(),R.layout.single_grid_child_hourly_forecast,time_grid1);
        GridAdapter mAdapter_data2 = new GridAdapter(getActivity(),R.layout.single_grid_child_hourly_forecast,time_grid2);
        GridAdapter mAdapter_data3 = new GridAdapter(getActivity(),R.layout.single_grid_child_hourly_forecast,time_grid3);
        GridAdapterAnimate mAdapter_temp1 = new GridAdapterAnimate(getActivity(),R.layout.single_grid_child_description_hourly_forecast,temp_grid1,mImage_grid1);
        GridAdapterAnimate mAdapter_temp2 = new GridAdapterAnimate(getActivity(),R.layout.single_grid_child_description_hourly_forecast,temp_grid2,mImage_grid2);
        GridAdapterAnimate mAdapter_temp3 = new GridAdapterAnimate(getActivity(),R.layout.single_grid_child_description_hourly_forecast,temp_grid3,mImage_grid3);
        grid1_data.setAdapter(mAdapter_data1);
        grid2_data.setAdapter(mAdapter_data2);
        grid3_data.setAdapter(mAdapter_data3);
        grid1_desc.setAdapter(mAdapter_temp1);
        grid2_desc.setAdapter(mAdapter_temp2);
        grid3_desc.setAdapter(mAdapter_temp3);

        grid1_data.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Animation FadeIn = AnimationUtils.loadAnimation(getActivity(),R.anim.animation_fade_in);
                Animation FadeOut = AnimationUtils.loadAnimation(getActivity(),R.anim.animation_fade_out);

                if(!mGrid1_isHidden) {
                    for (int i = 0; i < grid1_desc.getChildCount(); i++) {
                        RelativeLayout mRelative_data = (RelativeLayout) grid1_data.getChildAt(i);
                        mRelative_data.getChildAt(0).setAnimation(FadeOut);
                        mRelative_data.getChildAt(0).setVisibility(View.INVISIBLE);
                        RelativeLayout mRelative_desc = (RelativeLayout) grid1_desc.getChildAt(i);
                        mRelative_desc.getChildAt(0).setAnimation(FadeIn);
                        mRelative_desc.getChildAt(0).setVisibility(View.VISIBLE);
                    }
                    mGrid1_isHidden = true;
                }
                else{
                    for(int i = 0; i < grid1_desc.getChildCount(); i++){
                        RelativeLayout mRelative_desc = (RelativeLayout) grid1_desc.getChildAt(i);
                        mRelative_desc.getChildAt(0).setAnimation(FadeOut);
                        mRelative_desc.getChildAt(0).setVisibility(View.INVISIBLE);
                        RelativeLayout mRelative_data = (RelativeLayout) grid1_data.getChildAt(i);
                        mRelative_data.getChildAt(0).setAnimation(FadeIn);
                        mRelative_data.getChildAt(0).setVisibility(View.VISIBLE);
                    }
                    mGrid1_isHidden = false;
                }
            }
        });

        grid2_data.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Animation FadeIn = AnimationUtils.loadAnimation(getActivity(),R.anim.animation_fade_in);
                Animation FadeOut = AnimationUtils.loadAnimation(getActivity(),R.anim.animation_fade_out);

                if(!mGrid2_isHidden) {
                    for (int i = 0; i < grid2_desc.getChildCount(); i++) {
                        RelativeLayout mRelative_data = (RelativeLayout) grid2_data.getChildAt(i);
                        mRelative_data.getChildAt(0).setAnimation(FadeOut);
                        mRelative_data.getChildAt(0).setVisibility(View.INVISIBLE);
                        RelativeLayout mRelative_desc = (RelativeLayout) grid2_desc.getChildAt(i);
                        mRelative_desc.getChildAt(0).setAnimation(FadeIn);
                        mRelative_desc.getChildAt(0).setVisibility(View.VISIBLE);
                    }
                    mGrid2_isHidden = true;
                }
                else{
                    for(int i = 0; i < grid2_desc.getChildCount(); i++){
                        RelativeLayout mRelative_desc = (RelativeLayout) grid2_desc.getChildAt(i);
                        mRelative_desc.getChildAt(0).setAnimation(FadeOut);
                        mRelative_desc.getChildAt(0).setVisibility(View.INVISIBLE);
                        RelativeLayout mRelative_data = (RelativeLayout) grid2_data.getChildAt(i);
                        mRelative_data.getChildAt(0).setAnimation(FadeIn);
                        mRelative_data.getChildAt(0).setVisibility(View.VISIBLE);
                    }
                    mGrid2_isHidden = false;
                }
            }
        });

        grid3_data.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Animation FadeIn = AnimationUtils.loadAnimation(getActivity(),R.anim.animation_fade_in);
                Animation FadeOut = AnimationUtils.loadAnimation(getActivity(),R.anim.animation_fade_out);

                if(!mGrid3_isHidden) {
                    for (int i = 0; i < grid3_desc.getChildCount(); i++) {
                        RelativeLayout mRelative_data = (RelativeLayout) grid3_data.getChildAt(i);
                        mRelative_data.getChildAt(0).setAnimation(FadeOut);
                        mRelative_data.getChildAt(0).setVisibility(View.INVISIBLE);
                        RelativeLayout mRelative_desc = (RelativeLayout) grid3_desc.getChildAt(i);
                        mRelative_desc.getChildAt(0).setAnimation(FadeIn);
                        mRelative_desc.getChildAt(0).setVisibility(View.VISIBLE);
                    }
                    mGrid3_isHidden = true;
                }
                else{
                    for(int i = 0; i < grid3_desc.getChildCount(); i++){
                        RelativeLayout mRelative_desc = (RelativeLayout) grid3_desc.getChildAt(i);
                        mRelative_desc.getChildAt(0).setAnimation(FadeOut);
                        mRelative_desc.getChildAt(0).setVisibility(View.INVISIBLE);
                        RelativeLayout mRelative_data = (RelativeLayout) grid3_data.getChildAt(i);
                        mRelative_data.getChildAt(0).setAnimation(FadeIn);
                        mRelative_data.getChildAt(0).setVisibility(View.VISIBLE);
                    }
                    mGrid3_isHidden = false;
                }
            }
        });

        return view;
    }

    private void fill_values_hourly(){

        try {
            int i = 0,k=0;
            int mGrid1_starting_position;
            int mGrid2_starting_position;
            int mGrid3_starting_position;
            String[] mDateForHeading = new String[3];

            String currDate = mDate.getCurrentDate();
            JSONArray mJsonArray = mHourlyJson.getJSONArray("list");
            JSONObject temp_mJson = null;

            /*Add background image according to the description id parsed in CurrentConditionFragment.
             * Set the background same as the background of CurrentConditionFragment */
            int id = CurrentConditionFragment.mIdImage;
            mAddImage.add_daily_forecast_images(id);

            while(i < mJsonArray.length()) {
                temp_mJson = mJsonArray.getJSONObject(i);
                if (temp_mJson != null) {
                    /* find the date from the epoch time fed as input to the class FindDate.java*/
                    if(!temp_mJson.getString("dt_txt").contains(currDate) && temp_mJson.getString("dt_txt").contains("03:00:00")){
                        k = 7;
                        i = k;
                        break;
                    }
                    else if(temp_mJson.getString("dt_txt").contains(currDate)){
                        k++;
                    }
                    else{
                        i = k;
                        break;
                    }
                }
                i++;
            }
            mGrid1_starting_position = k;
            mGrid2_starting_position = k+8;
            mGrid3_starting_position = k+16;
            int x = 0;
            while(i <= k+16){
                String[] flag_date = new String[2];
                flag_date = mJsonArray.getJSONObject(i).getString("dt_txt").split(" ");
                mDateForHeading[x] = flag_date[0];
                x++;
                i+=8;
            }
            i=k;
            mDayDateGrid1.setText(mDate.formatDate(mDateForHeading[0]));
            mDayDateGrid2.setText(mDate.formatDate(mDateForHeading[1]));
            mDayDateGrid3.setText(mDate.formatDate(mDateForHeading[2]));

            int j = 0;
            for(x = i; x < 24+k; x++) {
                if(j == 8 || j == 16)
                    j = 0;
                String[] flag_date = new String[2];
                flag_date = mJsonArray.getJSONObject(x).getString("dt_txt").split(" ");
                temp_mJson = mJsonArray.getJSONObject(x);
                if (x < mGrid2_starting_position) {
                    time_grid1[j] = mDate.formatTime(flag_date[1]);
                    double temp1 = mParse.getObjectJsonDouble(temp_mJson, "main", "temp");
                    int flag_temp1 = (int) (temp1-273.15+0.5);
                    temp_grid1[j] = String.valueOf(flag_temp1)+(char) 0x00B0+ 'C';
                    add_images(mParse.getArrayJSONInt(temp_mJson,"weather","id"),j,mImage_grid1);
                }
                else if (x < mGrid3_starting_position) {
                    time_grid2[j] = mDate.formatTime(flag_date[1]);
                    double temp2 = mParse.getObjectJsonDouble(temp_mJson, "main", "temp");
                    int flag_temp2 = (int) (temp2 - 273.15 + 0.5);
                    temp_grid2[j] = String.valueOf(flag_temp2) + (char) 0x00B0 + 'C';
                    add_images(mParse.getArrayJSONInt(temp_mJson,"weather","id"),j,mImage_grid2);
                }
                else {
                    time_grid3[j] = mDate.formatTime(flag_date[1]);
                    double temp3 = mParse.getObjectJsonDouble(temp_mJson,"main","temp");
                    int flag_temp3 = (int) (temp3-273.15+0.5);
                    temp_grid3[j] = String.valueOf(flag_temp3)+(char) 0x00B0+ 'C';
                    add_images(mParse.getArrayJSONInt(temp_mJson,"weather","id"),j,mImage_grid3);
                }
                j++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void add_images(int image_id, int position, int[] add_images){

        switch(image_id){
            case 201:
            case 210:
            case 211:
            case 212:
                add_images[position] = R.drawable.icon_thunderstorms;
                break;
            case 500:
                add_images[position] = R.drawable.icon_light_rain;
                break;
            case 501:
                add_images[position] = R.drawable.icon_moderate_rain;
                break;
            case 502:
            case 511:
                add_images[position] = R.drawable.icon_heavy_intnsity_rain;
                break;
            case 600:
            case 601:
            case 602:
                add_images[position] = R.drawable.icon_snow;
                break;
            case 615:
            case 616:
                add_images[position] = R.drawable.icon_rain_and_snow;
                break;
            case 721:
            case 761:
                add_images[position] = R.drawable.icon_dust;
                break;
            case 741:
                add_images[position] = R.drawable.icon_fog;
                break;
            case 781:
                add_images[position] = R.drawable.icon_tornado;
                break;
            case 800:
                add_images[position] = R.drawable.icon_clear_sky_day;
                break;
            case 801:
                add_images[position] = R.drawable.icon_few_clouds;
                break;
            case 802:
                add_images[position] = R.drawable.icon_scattered_clouds;
                break;
            case 803:
                add_images[position] = R.drawable.icon_broken_clouds;
                break;
            case 804:
                add_images[position] = R.drawable.icon_overcast_clouds;
                break;
            default:
                add_images[position] = R.drawable.icon_clear_sky_day;
                break;
        }
    }

    public class GridAdapter extends ArrayAdapter<String>{

        Context mContext;
        String[] test;
        int layout;
        public GridAdapter(Context context, int resource, String[] objects) {
            super(context, resource, objects);
            this.layout = resource;
            this.mContext = context;
            this.test = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(layout,parent,false);
            }
            TextView mText = (TextView)convertView.findViewById(R.id.textView_time_hourly_forecast);
            mText.setText(test[position]);
            return convertView;
        }
    }

    public class GridAdapterAnimate extends ArrayAdapter<String>{

        Context mContext;
        String[] mTemperature;
        int[] mImageDescription;
        int layout;
        public GridAdapterAnimate(Context context, int resource, String[] objects, int[] images) {
            super(context, resource, objects);
            this.layout = resource;
            this.mContext = context;
            this.mTemperature = objects;
            this.mImageDescription = images;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(layout,parent,false);
            }
            TextView mText = (TextView)convertView.findViewById(R.id.textView_temperature_hourly_forecast);
            ImageView mImage = (ImageView)convertView.findViewById(R.id.imageView_hourly_forecast);
            mText.setText(mTemperature[position]);
            mImage.setImageResource(mImageDescription[position]);
            return convertView;
        }
    }

}
