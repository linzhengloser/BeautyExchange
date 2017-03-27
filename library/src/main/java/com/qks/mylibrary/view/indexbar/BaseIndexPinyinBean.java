package com.qks.mylibrary.view.indexbar;

/**
 * Created by linzheng on 2016/10/12.
 * 基于PinYin排序 需要继承此类。
 */

public abstract class BaseIndexPinyinBean extends BaseIndexTagBean implements IIndexTargetInterface{

    /**
     * 需要转换成拼音的字段转换后的拼音
     * 比如武汉对应的值就是 wh
     * 此字段主要是用来排序
     */
    private String baseIndexPinyin;

    public String getBaseIndexPinyin() {
        return baseIndexPinyin;
    }

    public BaseIndexPinyinBean setBaseIndexPinyin(String baseIndexPinyin) {
        this.baseIndexPinyin = baseIndexPinyin;
        return this;
    }

}
