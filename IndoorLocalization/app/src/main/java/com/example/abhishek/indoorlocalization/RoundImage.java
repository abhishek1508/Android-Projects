package com.example.abhishek.indoorlocalization;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.Log;

/**
 * Created by ABHISHEK on 14-03-2015.
 */
public class RoundImage extends Drawable {
    private final Bitmap mBitmap;
    private final Paint mPaint;
    private final Paint mPaintBorder;
    private final int mBitmapWidth;
    private final int mBitmapHeight;

    public RoundImage(Bitmap bitmap, int color) {

        mBitmap = bitmap;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);

        mPaintBorder = new Paint();
        mPaintBorder.setAntiAlias(true);
        mPaintBorder.setColor(color);
        mPaintBorder.setStyle(Paint.Style.STROKE);
        mPaintBorder.setStrokeWidth(25.0f);

        final BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(shader);
        mBitmapWidth = mBitmap.getWidth();
        mBitmapHeight = mBitmap.getHeight();
    }
    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(mBitmapWidth / 2, mBitmapHeight / 2, 130, mPaint);
        canvas.drawCircle(mBitmapWidth / 2, mBitmapHeight / 2, 130, mPaintBorder);
    }
    @Override
    public void setAlpha(int alpha) {
        if (mPaint.getAlpha() != alpha) {
            mPaint.setAlpha(alpha);
            invalidateSelf();
        }
    }
    @Override
    public void setColorFilter(ColorFilter cf) {
        mPaint.setColorFilter(cf);
    }
    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
    @Override
    public int getIntrinsicWidth() {
        return mBitmapWidth;
    }
    @Override
    public int getIntrinsicHeight() {
        return mBitmapHeight;
    }
    public void setAntiAlias(boolean aa) {
        mPaint.setAntiAlias(aa);
        invalidateSelf();
    }
    @Override
    public void setFilterBitmap(boolean filter) {
        mPaint.setFilterBitmap(filter);
        invalidateSelf();
    }
    @Override
    public void setDither(boolean dither) {
        mPaint.setDither(dither);
        invalidateSelf();
    }
    public Bitmap getBitmap() {
        return mBitmap;
    }
}
