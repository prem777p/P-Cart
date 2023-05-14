package com.prem.p_cart.Utilities;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class LoadImage {

    public void glideLoadImage(Context context, String url, ImageView imageView){
        Glide.with(context)
                .load(url)
                .into(imageView);
    }
}
