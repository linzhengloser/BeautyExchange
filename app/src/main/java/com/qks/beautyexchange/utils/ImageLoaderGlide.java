package com.qks.beautyexchange.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * 实现GLide
 */
public class ImageLoaderGlide extends ImageLoaderLogic {

    /**
     * 根据Url加载图片
     * @param context
     * @param imageView
     * @param imageUrl
     * @param options
     */
    @Override
    void commonLoadImage(Context context, ImageView imageView, String imageUrl, ImageLoaderOptions options) {
        Glide.with(context)
                .load(imageUrl)
                .placeholder(options.loadingResId)
                .thumbnail(options.loadThumbnail)
                .error(options.loadErrorResId)
                .into(imageView);
    }

    /**
     * 根据Uri加载图片
     * @param context
     * @param imageView
     * @param imageUri
     * @param options
     */
    @Override
    void commonLoadImage(Context context, ImageView imageView, Uri imageUri, ImageLoaderOptions options) {
        Glide.with(context)
                .load(imageUri)
                .placeholder(options.loadingResId)
                .thumbnail(options.loadThumbnail)
                .error(options.loadErrorResId)
                .into(imageView);
    }

    /**
     * 使用Glide 的 SimpleTarget 加载图片,在回调中返回图片的Bitmap对象,
     * 使用CircleImageView 加载网络图片的时候,需要用到此方法.
     * @param context
     * @param imageUrl
     * @param options
     * @param simpleTarget
     */
    void loadImage(Context context, String imageUrl, ImageLoaderOptions options, SimpleTarget<GlideDrawable> simpleTarget) {

        options = getImageLoaderOptions(options);

        Glide.with(context)
                .load(imageUrl)
                .placeholder(options.loadingResId)
                .thumbnail(options.loadThumbnail)
                .error(options.loadErrorResId)
                .into(simpleTarget);
    }

    /**
     * 使用Glide 的 SimpleTarget 加载图片,在回调中返回图片的Bitmap对象,
     * 使用CircleImageView 加载网络图片的时候,需要用到此方法.
     * @param context
     * @param imageUrl
     * @param simpleTarget
     */
    void loadImage(Context context, String imageUrl, SimpleTarget<GlideDrawable> simpleTarget) {
        loadImage(context, imageUrl, null, simpleTarget);
    }
}
