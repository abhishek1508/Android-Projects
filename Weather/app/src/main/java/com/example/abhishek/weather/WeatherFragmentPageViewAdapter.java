package com.example.abhishek.weather;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

/**
 * Created by ABHISHEK on 18-03-2015.
 */
public class WeatherFragmentPageViewAdapter extends FragmentPagerAdapter{

    public static int mCurrentPage;
    private static int mPageCount = 3;
    String str;

    public WeatherFragmentPageViewAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int mPageNumber) {
        mCurrentPage = mPageNumber;
        if(mPageNumber == 0) {
            CurrentConditionFragment mFragment = new CurrentConditionFragment();
            return mFragment;
        }
        else if(mPageNumber == 1){
            DailyForecastFragment mFragment = new DailyForecastFragment();
            return mFragment;
        }
        else if(mPageNumber == 2){
            HourlyForecastFragment mFragment = new HourlyForecastFragment();
            return mFragment;
        }
        else
            return null;
    }

    @Override
    public int getCount() {
        return mPageCount;
    }

}
