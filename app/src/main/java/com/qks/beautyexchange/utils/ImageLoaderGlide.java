package com.qks.beautyexchange.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * 实现GLide
 *
 */
public class ImageLoaderGlide extends ImageLoaderLogic {

    @Override
    void commonLoadImage(Context context, ImageView imageView, String imageUrl, ImageLoaderOptions options) {
        Glide.with(context)
                .load(imageUrl)
                .placeholder(options.loadingResId)
                .thumbnail(options.loadThumbnail)
                .error(options.loadErrorResId)
                .into(imageView);
    }

    @Override
    void commonLoadImage(Context context, ImageView imageView, Uri imageUri, ImageLoaderOptions options) {
        Glide.with(context)
                .load(imageUri)
                .placeholder(options.loadingResId)
                .thumbnail(options.loadThumbnail)
                .error(options.loadErrorResId)
                .into(imageView);
    }


}
