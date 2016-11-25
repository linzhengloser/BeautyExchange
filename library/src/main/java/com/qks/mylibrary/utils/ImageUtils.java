package com.qks.mylibrary.utils;

import android.media.ExifInterface;

import java.io.IOException;

/**
 * 图片相关工具类
 * Created by admin on 2016/3/25.
 */
public class ImageUtils {


    /**
     * 获取图片的exif的旋转角度
     * @param path 图片路径
     * @return 旋转的角度
     */
    public static int getImageExifRotateAngle(String path) {
        int angle = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    angle = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    angle = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    angle = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return angle;
    }


}
