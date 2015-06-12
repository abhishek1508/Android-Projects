package com.developer.abhishek.weather_report;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;


/**
 * Created by ABHISHEK on 15-05-2015.
 */
public class ConnectionManager {

    public Context mContext;
    public ConnectionManager(Context context){
        this.mContext = context;
    }

    public boolean getConnectionDetails() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null && info.isConnectedOrConnecting())
            return true;
        else {
            Toast.makeText(mContext, R.string.check_connection, Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
