package com.example.abhishek.weather;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class EnterLocation extends ActionBarActivity implements View.OnClickListener,AdapterView.OnItemClickListener{

    AutoCompleteTextView mAuto;
    Button mSearch;
    Button mSave;
    ListView mSaveList;
    RelativeLayout mInstruction;
    RelativeLayout mCitySavedList;

    String[] mCity = null;
    private ArrayAdapter<String> mAdapter = null;
    private CustomSaveAdapter mSavedAdapter = null;
    private ArrayList<String> mSavedCity = null;
    private double mScreen;
    private WindowManager windowManager;
    private String mDebug = EnterLocation.class.getName();
    private Parcelable mListState = null;
    private String mCityEntered = null;
    private SharedPreferences prefs = null;
    private String mCityQuery = "http://api.openweathermap.org/data/2.5/weather?q=%s";
    private String mCityDaily = "http://api.openweathermap.org/data/2.5/forecast/daily?q=%s&mode=json&cnt=16";
    private String mCityHourly = "http://api.openweathermap.org/data/2.5/forecast?q=%s&mode=json";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_weather);

        /* Initially gets the preferences from the shared preferences by passing the Key
         * Saved_City_List. Now the preferences check for the existence of StringSet values
         * in the HashSet corresponding to the key passed. If values have been saved
         * previously then an object of ArrayList is made with that particular set object
         * else an ArrayList object is created. */
        prefs = this.getSharedPreferences("Saved_City_List",Context.MODE_PRIVATE);
        Set<String> set = prefs.getStringSet("Saved_City_List", null);
        if(set == null)
            mSavedCity = new ArrayList<String>();
        else
            mSavedCity = new ArrayList<String>(set);

        handleScreenSize();
        mAuto = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        mSearch = (Button)findViewById(R.id.button_search);
        mSave = (Button)findViewById(R.id.button_save);
        mSaveList = (ListView)findViewById(R.id.listView);
        mInstruction = (RelativeLayout)findViewById(R.id.relativeLayout_saved_instruction);
        mCitySavedList = (RelativeLayout)findViewById(R.id.relativeLayout_for_list_view);

        init();
        mSaveList.setOnItemClickListener(this);
        mSearch.setOnClickListener(this);
        mSave.setOnClickListener(this);
        mAuto.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_GO) {
                    mCityEntered = mAuto.getText().toString();
                    startAsyncTask(mCityEntered);
                }
                return false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        prefs = this.getSharedPreferences("Saved_City_List",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();

        Set<String> set = new HashSet<String>();
        set.addAll(mSavedCity);
        edit.putStringSet("Saved_City_List",set);
        edit.commit();
    }

    private void handleScreenSize(){
        /*
         * The method checks for the size of the screen. The app supports smart phones
         * with screen sizes less than or equal to 5.5 inches. If the screen size is
         * greater than 5.5 inch the app is closed and user is shown an appropriate message.
         */
        windowManager = getWindowManager();
        ScreenSize mSize = new ScreenSize(windowManager);
        mScreen = mSize.getScreenSize();
        if(mScreen > 5.5) {
            finish();
            Toast.makeText(EnterLocation.this, R.string.large_screen_size, Toast.LENGTH_LONG).show();
        }
    }

    private void init(){
        mSavedAdapter = new CustomSaveAdapter(getApplicationContext(),R.layout.layout_saved_cities,R.id.text_saved_city_name,mSavedCity);
        mSaveList.setAdapter(mSavedAdapter);
        if(mSavedCity.size() > 0)
            show_saved_list(true);
        mCity = getResources().getStringArray(R.array.list_of_cities);
        if(!mCity.equals(null))
            mAdapter = new ArrayAdapter<String>(EnterLocation.this, android.R.layout.simple_list_item_1, mCity);
        if(mAdapter != null)
            mAuto.setAdapter(mAdapter);
    }

    private void startAsyncTask(String str){
        if (!str.equals("")){
            DownloadWeather mDownload = new DownloadWeather(EnterLocation.this,str);
            mDownload.execute(mCityQuery, mCityDaily, mCityHourly);
            /* Clear the edit text and the variable once the user has searched for the weather
             * after entering the name of the city*/
            mAuto.setText("");
            mCityEntered = "";
        }
        else
            Toast.makeText(getApplicationContext(),R.string.no_city_alert, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_search:
                startAsyncTask(mCityEntered);
                break;
            case R.id.button_save:
                mCityEntered = mAuto.getText().toString();
                if (mCityEntered.equals("")){
                    Toast.makeText(getApplicationContext(),R.string.no_city_alert, Toast.LENGTH_LONG).show();
                }
                else {
                    /* Add the name of the city entered by the user to the array list */
                    mSavedCity.add(mCityEntered);
                    /* Notifies the observer that the data in the adapter has been changed and that any view
                     * holding the adapter should be refreshed to include the new data*/
                    mSavedAdapter.notifyDataSetChanged();
                    show_saved_list(true);
                    /* Clear the edit text and the variable once the user clicks on save button
                    * after entering the name of the city*/
                    mAuto.setText("");
                    mCityEntered = "";
                }
                break;
        }
    }

    private void show_saved_list(boolean value){
        if(value == true) {
            mInstruction.setVisibility(View.INVISIBLE);
            mCitySavedList.setVisibility(View.VISIBLE);
        }
        else {
            mInstruction.setVisibility(View.VISIBLE);
            mCitySavedList.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView mClickedText = (TextView)view.findViewById(R.id.text_saved_city_name);
        startAsyncTask(mClickedText.getText().toString());
    }

    public class CustomSaveAdapter extends ArrayAdapter<String> {

        private ArrayList<String> mList;
        private int mLayout;
        private int mResourceId;
        private Context mContext;

        public CustomSaveAdapter(Context context, int resource, int textViewResourceId, ArrayList<String> list) {
            super(context, resource, textViewResourceId, list);
            this.mContext = context;
            this.mLayout = resource;
            this.mResourceId = textViewResourceId;
            this.mList = list;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final int index = position;
            View row = convertView;
            if(row == null){
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(mLayout,parent,false);
            }
            TextView mText = (TextView)row.findViewById(R.id.text_saved_city_name);
            RelativeLayout mRelative = (RelativeLayout)row.findViewById(R.id.relativeLayout_delete_city);
            mRelative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSavedCity.remove(index);
                    mSavedAdapter.notifyDataSetChanged();
                    if(mSavedCity.size() == 0)
                        show_saved_list(false);
                }
            });
            mText.setText(mList.get(position));
            return row;
        }
    }

}
