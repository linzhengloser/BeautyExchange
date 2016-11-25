package com.qks.beautyexchange.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 适用于制作优惠券列表的优惠券背景
 * Created by admin on 2016/5/20.
 */
public class CouponDisplayView extends LinearLayout{

    /**
     * 画笔对象
     */
    private Paint mPaint;

    /**
     * 圆间距
     */
    private float gap = 20;

    /**
     * 圆半径
     */
    private float radius = 20;


    /**
     * 圆的数量  = 间隔数量 - 1
     */
    private int circleNum;

    /**
     * 剩余的长度
     */
    private float remain;


    public CouponDisplayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
    }

    public CouponDisplayView(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public CouponDisplayView(Context context) {
        this(context,null);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(remain == 0){
            remain = (int)(w - gap)%(2*radius + gap);
        }
        circleNum = (int)((w - gap)/(2*radius + gap));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i = 0; i < circleNum ; i++){
            float x = gap + radius +remain /2 +((gap + radius*2)*i);
            canvas.drawCircle(x,0,radius,mPaint);
            canvas.drawCircle(x,getHeight(),radius,mPaint);
        }
    }
}
