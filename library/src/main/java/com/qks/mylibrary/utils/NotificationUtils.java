package com.qks.mylibrary.utils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by admin on 2016/5/18.
 */
public class NotificationUtils {


    /**
     * 用来获取通知的ID,防止重复
     */
    private final static AtomicInteger c = new AtomicInteger(0);

    public static int getID() {
        return c.incrementAndGet();
    }


}
