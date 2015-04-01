package com.example.abhishek.weather;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

/**
 * Created by ABHISHEK on 31-03-2015.
 */
public class AddImages {
    
    private ImageView image;
    Resources res;

    public AddImages(Resources res, ImageView image){
        this.image = image;
        this.res = res;
    }

    public void add_images(int image_id) {
        Bitmap bitmap = null;
        switch (image_id) {
            case 201:
            case 210:
            case 211:
            case 212:
                image.setImageResource(R.drawable.icon_thunderstorms);
                bitmap = BitmapFactory.decodeResource(res, R.drawable.background_thunderstorms);
                image.setImageBitmap(bitmap);
                break;
            case 500:
                image.setImageResource(R.drawable.icon_light_rain);
                bitmap = BitmapFactory.decodeResource(res, R.drawable.background_light_rain);
                image.setImageBitmap(bitmap);
                break;
            case 501:
                image.setImageResource(R.drawable.icon_moderate_rain);
                bitmap = BitmapFactory.decodeResource(res, R.drawable.background_moderate_rain);
                image.setImageBitmap(bitmap);
                break;
            case 502:
            case 511:
                image.setImageResource(R.drawable.icon_heavy_intnsity_rain);
                bitmap = BitmapFactory.decodeResource(res, R.drawable.background_heavy_rain);
                image.setImageBitmap(bitmap);
                break;
            case 600:
            case 601:
            case 602:
                image.setImageResource(R.drawable.icon_snow);
                bitmap = BitmapFactory.decodeResource(res, R.drawable.background_snow);
                image.setImageBitmap(bitmap);
                break;
            case 615:
            case 616:
                image.setImageResource(R.drawable.icon_rain_and_snow);
                bitmap = BitmapFactory.decodeResource(res, R.drawable.background_rain_snow);
                image.setImageBitmap(bitmap);
                break;
            case 721:
            case 761:
                image.setImageResource(R.drawable.icon_dust);
                bitmap = BitmapFactory.decodeResource(res, R.drawable.background_dust);
                image.setImageBitmap(bitmap);
                break;
            case 741:
                image.setImageResource(R.drawable.icon_fog);
                bitmap = BitmapFactory.decodeResource(res, R.drawable.background_fog);
                image.setImageBitmap(bitmap);
                break;
            case 781:
                image.setImageResource(R.drawable.icon_tornado);
                bitmap = BitmapFactory.decodeResource(res, R.drawable.background_tornado);
                image.setImageBitmap(bitmap);
                break;
            case 800:
                image.setImageResource(R.drawable.icon_clear_sky_day);
                bitmap = BitmapFactory.decodeResource(res, R.drawable.background_clear_sky);
                image.setImageBitmap(bitmap);
                break;
            case 801:
                image.setImageResource(R.drawable.icon_few_clouds);
                bitmap = BitmapFactory.decodeResource(res,R.drawable.background_few_clouds);
                image.setImageBitmap(bitmap);
                break;
            case 802:
                image.setImageResource(R.drawable.icon_scattered_clouds);
                bitmap = BitmapFactory.decodeResource(res,R.drawable.background_scattered_clouds);
                image.setImageBitmap(bitmap);
                break;
            case 803:
                image.setImageResource(R.drawable.icon_broken_clouds);
                bitmap = BitmapFactory.decodeResource(res,R.drawable.background_broken_clouds);
                image.setImageBitmap(bitmap);
                break;
            case 804:
                image.setImageResource(R.drawable.icon_overcast_clouds);
                bitmap = BitmapFactory.decodeResource(res,R.drawable.background_overcast_clouds);
                image.setImageBitmap(bitmap);
                break;
            default:
                image.setImageResource(R.drawable.icon_clear_sky_day);
                break;
        }
    }
}
