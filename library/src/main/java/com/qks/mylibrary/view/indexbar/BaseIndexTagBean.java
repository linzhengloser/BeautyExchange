package com.qks.mylibrary.view.indexbar;

/**
 * Created by linzheng on 2016/10/12.
 */

public class BaseIndexTagBean {

    /**
     *  需要拼音化字段的第一个拼音
     *  比如 武汉  那值对应的就是 w
     */
    private String baseIndexTag;

    public String getBaseIndexTag() {
        return baseIndexTag;
    }

    public BaseIndexTagBean setBaseIndexTag(String baseIndexTag) {
        this.baseIndexTag = baseIndexTag;
        return this;
    }
}
