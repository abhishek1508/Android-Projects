package com.example.abhishek.indoorlocalization;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

/**
 * Created by ABHISHEK on 31-03-2015.
 */
public class FindOnMap extends ActionBarActivity{

    private GetViewOfMap mGetViewOfMap;
    private float scale = 1f;
    private ScaleGestureDetector SGD;
    private GestureDetector SL;
    public float width;
    public float height;
    public static float x;
    public static float y;
    private long scaleTime;
    private long scrollTime;
    private long prevScrollTime;
    private float prevXTrans;
    private float prevYTrans;
    int image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_map);

        Intent intent= getIntent();

        String loc= intent.getStringExtra("coordinates");
        String floorNum= intent.getStringExtra("floor_number");

        if(floorNum.equals("Floor 2")){
            image=R.drawable.floor2;
        }
        if(floorNum.equals("Floor 6")){
            image=R.drawable.floor6;
        }

        String[] coordinates= loc.split(",");
        x= Float.parseFloat(coordinates[0]);
        y= Float.parseFloat(coordinates[1]);

        mGetViewOfMap = (GetViewOfMap) findViewById(R.id.floormap);

        mGetViewOfMap.setImageResource(image);
        SGD = new ScaleGestureDetector(this,new ScaleListener());
        SL = new GestureDetector(this, new ScrollListener());
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int actionBarHeight = getSupportActionBar().getHeight();

        height = metrics.heightPixels - actionBarHeight;
        width = metrics.widthPixels;

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        SGD.onTouchEvent(ev);
        SL.onTouchEvent(ev);

        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {


        @Override
        public boolean onScale(ScaleGestureDetector detector) {

            scale *= detector.getScaleFactor();
            scale = Math.max(0.1f, Math.min(scale, 3f));
            scaleTime = System.currentTimeMillis();

            if(width*scale >= width && height*scale >= height){
                mGetViewOfMap.setScaleX(scale);
                mGetViewOfMap.setScaleY(scale);

                x = (width*scale - width)/2;
                y = (height*scale- height)/2;
            }
            else
            {scale = 1;
                mGetViewOfMap.setScaleX(scale);
                mGetViewOfMap.setScaleY(scale);
                x = 0;
                y = 0;

                mGetViewOfMap.setX(0);
                mGetViewOfMap.setY(0);


            }

            return true;
        }

    }

    private class ScrollListener extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY){

            scrollTime = System.currentTimeMillis();
            System.out.println("Time Diff: "+Math.abs(scrollTime-scaleTime));

            if(Math.abs(scrollTime-scaleTime)>100){
                if(e2.getPointerCount()==1)
                {float transXdist = e2.getX()-e1.getX();
                    float transYdist = e2.getY()-e1.getY();

                    float edgeWidth = Math.max(x - Math.abs(transXdist),0);
                    float edgeHeight = Math.max(y - Math.abs(transYdist),0);

                    //System.out.println("edgeWidth: "+edgeWidth+", edgeHeight: "+edgeHeight+", transXdist: "+transXdist+", transYdist: "+transYdist);

                    if(edgeWidth > 0)
                        mGetViewOfMap.setTranslationX(transXdist);

                    if(edgeHeight > 0)
                        mGetViewOfMap.setTranslationY(transYdist);

                    prevXTrans = transXdist;
                    prevYTrans = transYdist;
                }

                else{
                    mGetViewOfMap.setX(0);
                    mGetViewOfMap.setY(0);
                }
            }

            prevScrollTime = scrollTime;
            return true;
        }

    }

    int[] getImageDetails(GetViewOfMap img){
        int[] result = new int[2];
        // Get image dimensions
        // Get image matrix values and place them in an array
        float[] f = new float[9];
        img.getImageMatrix().getValues(f);

        // Extract the scale values using the constants (if aspect ratio maintained, scaleX == scaleY)
        final float scaleX = f[Matrix.MSCALE_X];
        final float scaleY = f[Matrix.MSCALE_Y];

        // Get the drawable (could also get the bitmap behind the drawable and getWidth/getHeight)
        final Drawable d = img.getDrawable();
        final int origW = d.getIntrinsicWidth();
        final int origH = d.getIntrinsicHeight();

        // Calculate the actual dimensions
        final int actW = Math.round(origW * scaleX);
        final int actH = Math.round(origH * scaleY);

        result[0] = actW;
        result[1] = actH;

        return result;

    }
}
