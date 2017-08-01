package com.shenni.torontofoods.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by Administrator on 2017/4/10.
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {

        Glide.with(context)
                .load((String) path)
                .into(imageView);


    }
}
