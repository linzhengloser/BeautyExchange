package com.qks.beautyexchange.utils;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;


/**
 * 实现GLide
 */
public class ImageLoaderGlide extends ImageLoaderLogic {

    @Override
    void commonLoadImage(ImageView imageView, String imageUrl, ImageLoaderOptions options) {

        Glide.with(imageView.getContext())
                .load(imageUrl)
                .placeholder(options.loadingResId)
                .error(options.loadErrorResId)
                .into(imageView);
    }

    @Override
    void commonLoadImage(ImageView imageView, Uri imageUri, ImageLoaderOptions options) {
        Glide.with(imageView.getContext())
                .load(imageUri)
                .placeholder(options.loadingResId)
                .error(options.loadErrorResId)
                .into(imageView);
    }

    @Override
    void commonLoadImage2SimpleTarget(final ImageView imageView, String imageUrl, ImageLoaderOptions options) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .placeholder(options.loadingResId)
                .error(options.loadErrorResId)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        imageView.setImageDrawable(resource);
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        imageView.setImageDrawable(errorDrawable);
                    }
                });
    }

//    反复思考，觉得没必要过渡封装
//    private DrawableRequestBuilder buildDrawableRequestBuilder(Context context,Uri imageUri,String imageUrl,ImageLoaderOptions options){
//
//        DrawableTypeRequest drawableTypeRequest = null;
//
//        if(imageUri != null){
//            drawableTypeRequest = Glide.with(context).load(imageUri);
//        }else{
//            drawableTypeRequest = Glide.with(context).load(imageUrl);
//        }
//        return drawableTypeRequest.placeholder(options.loadingResId)
//                .error(options.loadErrorResId);
//
//    }


}
