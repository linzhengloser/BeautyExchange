package com.qks.beautyexchange.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.qks.beautyexchange.R;
import com.qks.beautyexchange.application.Constant;

/**
 * 作者:linzheng
 * 日期:2016/11/17
 * 功能:图片加载逻辑封装
 */

public abstract class ImageLoaderLogic implements ImageLoader{

    /**
     * 默认Options
     */
    protected static ImageLoader.ImageLoaderOptions defaultOptions;

    static {
        defaultOptions = new ImageLoaderOptions();
        defaultOptions.loadingResId = R.drawable.image_loading;
        defaultOptions.loadErrorResId = R.drawable.not_image;
    }

    @Override
    public void loadImage(ImageView imageView, Uri imageUri) {
        loadImage(imageView,imageUri,null);
    }

    @Override
    public void loadImage(ImageView imageView, Uri imageUri, ImageLoaderOptions options) {

        if (!Constant.App.IS_WIFI_LOAD_IMAGE) {
            imageView.setImageResource(options.loadingResId);
            return;
        }

        commonLoadImage(imageView.getContext(),imageView,imageUri,getImageLoaderOptions(options));
    }

    @Override
    public void loadImage(ImageView imageView, String imageUrl) {
        loadImage(imageView,imageUrl,null);
    }

    @Override
    public void loadImage(ImageView imageView, String imageUrl, ImageLoaderOptions options) {

        if (!Constant.App.IS_WIFI_LOAD_IMAGE) {
            imageView.setImageResource(options.loadingResId);
            return;
        }

        commonLoadImage(imageView.getContext(),imageView,imageUrl,getImageLoaderOptions(options));
    }


    //每个不用框架加载图片的逻辑
    abstract void commonLoadImage(Context context , ImageView imageView, String imageUrl, ImageLoaderOptions options);

    //每个不用框架加载图片的逻辑
    abstract void commonLoadImage(Context context , ImageView imageView, Uri imageUri, ImageLoaderOptions options);

    protected ImageLoaderOptions getImageLoaderOptions(ImageLoaderOptions options){
        return options == null ? defaultOptions : options;
    }

}
