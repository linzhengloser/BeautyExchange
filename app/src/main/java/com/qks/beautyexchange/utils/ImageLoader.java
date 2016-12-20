package com.qks.beautyexchange.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

/**
 * Created by admin on 2016/3/28.
 */
public interface ImageLoader {

    /**
     * 加载网络图片
     * @param imageView 要显示图片的ImageView
     * @param imageUrl 图片的URL
     * @param options 加载图片的相关参数
     */
    void loadImage(Context context, ImageView imageView, String imageUrl, ImageLoaderOptions options);


    /**
     * 使用默认方式加载网络图片
     * @param context
     * @param imageView
     * @param imageUrl
     */
    void loadImage(Context context, ImageView imageView, String imageUrl);

    /**
     * 通过Uri加载图片
     * @param context
     * @param imageView
     * @param imageUri
     * @param options
     */
    void loadImage(Context context, ImageView imageView, Uri imageUri, ImageLoaderOptions options);

    /**
     * 使用默认的方式加载Uri图片
     * @param context
     * @param imageView
     * @param imageUri
     */
    void loadImage(Context context, ImageView imageView, Uri imageUri);

    class ImageLoaderOptions {
        /**
         * 图片加载中要显示的图片
         */
        public int loadingResId;

        /**
         * 图片加载出错要显示的图片
         */
        public int loadErrorResId;

        /**
         * 显示缩略图的比例
         */
        public int loadThumbnail;
    }

}
