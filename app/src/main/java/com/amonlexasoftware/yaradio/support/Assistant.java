package com.amonlexasoftware.yaradio.support;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

public class Assistant {

    public static void runActivity(Activity from ,   Class<?> to) {
        Intent intent = new Intent(from,to);
        from.startActivity(intent);
        from.finish();
    }

    public static void showToast(Context context,String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void loadUrlGlide(Context activity, int url, int circle, ImageView view) {
        Glide
                .with(activity)
                .load(url)
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(circle)))
                .into(view);
    }

}
