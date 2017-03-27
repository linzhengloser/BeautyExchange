package com.qks.mylibrary.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 作者:linzheng
 * 日期:2016/10/24
 * 功能:Toast工具类 防止重复弹出Toast
 */

public class ToastUtils {

    private static Toast sToast;

    public static void showToast(Context context, String msg){
        if(sToast == null){
           sToast = Toast.makeText(context,msg, Toast.LENGTH_SHORT);
        }else{
            sToast.setText(msg);
        }
        sToast.show();
    }

}

