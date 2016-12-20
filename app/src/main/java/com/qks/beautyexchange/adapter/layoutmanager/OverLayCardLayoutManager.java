package com.qks.beautyexchange.adapter.layoutmanager;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者:linzheng 日期:2016/12/19 功能:
 */

public class OverLayCardLayoutManager extends RecyclerView.LayoutManager {
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {

        detachAndScrapAttachedViews(recycler);

        int itemCount = getItemCount();
        if (getItemCount() >= 4) {
            for (int position = itemCount - 4; position < itemCount; position++) {
                View view = recycler.getViewForPosition(position);
                addView(view);
                measureChildWithMargins(view,0,0);
                int widthSpace = getWidth() - getDecoratedMeasuredWidth(view);
                int heightSpace = getHeight() - getDecoratedMeasuredHeight(view);
                //居中显示
                layoutDecoratedWithMargins(view,widthSpace/2,heightSpace/2,widthSpace/2+getDecoratedMeasuredWidth(view),widthSpace/2);

                int level = itemCount - position -1;
                if(level > 0){
                    view.setScaleX(1-0.05f*level);
                    if(level<4-1){
                        view.setTranslationX((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, Resources.getSystem().getDisplayMetrics()));
                        view.setScaleX(1-0.05f*level);
                    }else{
                        view.setTranslationX((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, Resources.getSystem().getDisplayMetrics())*(level-1));
                        view.setScaleX((1-0.05f)*(level-1));
                    }
                }



            }
        }


    }
}
