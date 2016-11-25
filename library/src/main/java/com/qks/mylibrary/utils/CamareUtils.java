package com.qks.mylibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;

/**
 * Created by admin on 2016/3/18.
 */
public class CamareUtils {

    /**
     * 打开相机
     * @param context
     */
    public static void startCamare(Context context){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        context.startActivity(intent);
    }

}
