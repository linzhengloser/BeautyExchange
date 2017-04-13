package com.qks.mylibrary.view.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;

import com.qks.mylibrary.view.indexbar.BaseIndexTagBean;

import java.util.List;

/**
 *
 * 使用ItemDecoration 实现类似 微信联系人列表
 *
 * 方法介绍
 *  onDraw 用来绘制Title
 *  onDrawOver 用来绘制吸附Title
 *  getItemOffsets 用来设置绘制范围
 *  方法具体作用 百度ItemDecoration 的用法
 *
 */

public class TitleItemDecoration extends RecyclerView.ItemDecoration {

    /**
     * 数据对象
     */
    private List<? extends BaseIndexTagBean> mCityList ;

    /**
     * 用来绘制文字
     */
    private Paint mPaint;

    /**
     * 用来计算文字BaseLine
     */
    private Rect mFontRect;

    /**
     * 标题高度
     */
    private int mTitleHeight;//title的高

    /**
     * 标题背景
     */
    private static int COLOR_TITLE_BG = Color.parseColor("#FFDFDFDF");
    /**
     * 标题字体颜色
     */
    private static int COLOR_TITLE_FONT = Color.parseColor("#FF000000");
    /**
     * 标题字体颜色
     */
    private static int mTitleFontSize;


    public TitleItemDecoration(Context context, List<? extends BaseIndexTagBean> cityList){
        this.mCityList = cityList;
        mPaint = new Paint();
        mFontRect = new Rect();
        mTitleHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, context.getResources().getDisplayMetrics());
        mTitleFontSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, context.getResources().getDisplayMetrics());
        mPaint.setTextSize(mTitleFontSize);//设置文字大小
        mPaint.setAntiAlias(true);//抗锯齿
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth()-parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            int position = params.getViewLayoutPosition();
            if(position>-1){
                if(position == 0){
                    drawTitle(c,left,right,child,params,position);
                }else{
                    String currentTag = mCityList.get(position).getBaseIndexTag();
                    String lastTag = mCityList.get(position-1).getBaseIndexTag();
                    if(TextUtils.isEmpty(currentTag)||TextUtils.isEmpty(lastTag))return;
                    if(!currentTag.equals(lastTag)){
                        drawTitle(c,left,right,child,params,position);
                    }
                }
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int pos = ((LinearLayoutManager)(parent.getLayoutManager())).findFirstVisibleItemPosition();
        String tag = mCityList.get(pos).getBaseIndexTag();
        View child = parent.findViewHolderForLayoutPosition(pos).itemView;
        boolean flag = false;
        if(pos+1 < mCityList.size()){
            if (null != tag && !tag.equals(mCityList.get(pos + 1).getBaseIndexTag())){
                //当这个条件为True 的时候 表示 需要执行动画
                if (child.getHeight() + child.getTop() < mTitleHeight) {
                    c.save();
                    flag = true;
                    c.translate(0,child.getHeight()+child.getTop()-mTitleHeight);
                }
            }
        }

        mPaint.setColor(COLOR_TITLE_BG);
        c.drawRect(parent.getPaddingLeft(),parent.getPaddingTop(),parent.getRight()-parent.getPaddingRight(),parent.getPaddingTop()+mTitleHeight,mPaint);
        mPaint.setColor(COLOR_TITLE_FONT);
        mPaint.getTextBounds(tag, 0, tag.length(), mFontRect);
            c.drawText(tag, child.getPaddingLeft(),
                    parent.getPaddingTop() + mTitleHeight - (mTitleHeight / 2 - mFontRect.height() / 2),
                    mPaint);
        if(flag)
            c.restore();
    }

    /**
     * 绘制的标题
     */
    private void drawTitle(Canvas canvas , int left, int right, View child , RecyclerView.LayoutParams params , int position){
        mPaint.setColor(COLOR_TITLE_BG);
        canvas.drawRect(left,child.getTop()-params.topMargin-mTitleHeight,right,child.getTop()-params.topMargin,mPaint);
        mPaint.setColor(COLOR_TITLE_FONT);
        mPaint.getTextBounds(mCityList.get(position).getBaseIndexTag(), 0, mCityList.get(position).getBaseIndexTag().length(), mFontRect);
        canvas.drawText(mCityList.get(position).getBaseIndexTag(),child.getPaddingLeft(),child.getTop()-params.topMargin-(mTitleHeight/2-mFontRect.height()/2),mPaint);
    }



    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //设置范围
        int position = ((RecyclerView.LayoutParams)view.getLayoutParams()).getViewLayoutPosition();
        if(position> -1){
            if(position == 0){
                outRect.set(0,mTitleHeight,0,0);
            }else{
                //判断当前Item 和 上一个Item 的 首字母 是否是一样的
                String curentTag = mCityList.get(position).getBaseIndexTag();
                String lastTag = mCityList.get(position-1).getBaseIndexTag();
                if(TextUtils.isEmpty(curentTag)||TextUtils.isEmpty(lastTag))return;
                if(!curentTag.equals(lastTag)){
                    outRect.set(0,mTitleHeight,0,0);
                }else{
                    outRect.set(0,0,0,0);
                }
            }
        }
    }
}
