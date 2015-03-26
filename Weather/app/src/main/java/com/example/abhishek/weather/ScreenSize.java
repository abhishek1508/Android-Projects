package com.example.abhishek.weather;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by ABHISHEK on 20-03-2015.
 */
public class ScreenSize extends EnterLocation{

    private WindowManager mWindowManager;
    private int mWidthPixels;
    private int mHeightPixels;

    public ScreenSize(WindowManager mWindowManager){
        this.mWindowManager = mWindowManager;
    }

    protected double getScreenSize(){
        setRealDeviceSizeInPixels();
        DisplayMetrics dm = new DisplayMetrics();
        mWindowManager.getDefaultDisplay().getMetrics(dm);
        double x = Math.pow(mWidthPixels/dm.xdpi,2);
        double y = Math.pow(mHeightPixels/dm.ydpi,2);
        return (Math.sqrt(x+y));
    }

    private void setRealDeviceSizeInPixels() {

        Display display = mWindowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);

        mWidthPixels = displayMetrics.widthPixels;
        mHeightPixels = displayMetrics.heightPixels;

        // includes window decorations (statusbar bar/menu bar)
        if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17) {
            try {
                mWidthPixels = (Integer) Display.class.getMethod("getRawWidth").invoke(display);
                mHeightPixels = (Integer) Display.class.getMethod("getRawHeight").invoke(display);
            } catch (Exception ignored) {
            }
        }
        // includes window decorations (statusbar bar/menu bar)
        if (Build.VERSION.SDK_INT >= 17) {
            try {
                Point realSize = new Point();
                Display.class.getMethod("getRealSize", Point.class).invoke(display, realSize);
                mWidthPixels = realSize.x;
                mHeightPixels = realSize.y;
            } catch (Exception ignored) {
            }
        }
    }
}
