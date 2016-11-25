package com.qks.mylibrary.utils;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by admin on 2016/3/31.
 */
public class StringUtils {


    /**
     * 设置一个字符串中关键字高亮
     * @param color
     * @param text
     * @param keyword
     * @return
     */
    public static Spannable matcherSearchTitle(int color ,String text,String keyword){
        SpannableString s = new SpannableString(text);
        Pattern p = Pattern.compile(keyword);
        Matcher m = p.matcher(s);
        while(m.find()){
            int start = m.start();
            int end = m.end();
            s.setSpan(new ForegroundColorSpan(color),start,end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return s;
    }

    /**
     * 设置多个字符串在一个字符串中高亮显示
     * @param color
     * @param text
     * @param keyword
     * @return
     */
    public static Spannable matcherSearchTitle(int color,String text,String[] keyword){
        SpannableString s= new SpannableString(text);
        for(int i = 0;i<=keyword.length;i++){
            Pattern p = Pattern.compile(keyword[i]);
            Matcher m = p.matcher(s);
            while (m.find()){
                int start = m.start();
                int end = m.end();
                s.setSpan(new ForegroundColorSpan(color),start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return s;
    }


    /**
     * 获取一个带有超链接的文本
     * @param str 要设置 String
     * @param start 超链接的开始下标
     * @param end 超链接的结束下标
     * @param color 超链接的前景色
     * @param clickableSpan 超链接的点击事件
     * @return
     */
    public static Spannable getHyperlinkString(String str,int start,int end,int color,ClickableSpan clickableSpan){
        SpannableString spanStr = new SpannableString(str);
        //设置下划线文字
        spanStr.setSpan(new UnderlineSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanStr.setSpan(clickableSpan,start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置文字的前景色
        spanStr.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanStr;
    }

    /**
     * 设置字符串中部分文字大小  可大 可小
     * @param str 要设置的字符串
     * @param start 要改变大小的开始位置
     * @param end 要改变大小的结束位置
     * @param changedSize 要改变的大小
     * @param defaultSize 原本的大小
     * @return
     */
    public static Spannable getBigString(String str , int start,int end,int changedSize,int defaultSize){
        Spannable textSpan = new SpannableStringBuilder(str);
        textSpan.setSpan(new AbsoluteSizeSpan(defaultSize),0,start,Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textSpan.setSpan(new AbsoluteSizeSpan(changedSize), start, end - 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textSpan.setSpan(new AbsoluteSizeSpan(defaultSize), end - 1, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return textSpan;
    }


}
