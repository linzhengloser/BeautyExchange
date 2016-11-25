package com.qks.beautyexchange.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.qks.beautyexchange.R;

public class ShadowView extends View {

	private Paint mPaint;

	private int mWidth;

	private int mHeight;
	
	private Rect mBound;

	private int mRadius = 180;
	
	private String text = "将相机拖到此处可以打开系统相机";

	public ShadowView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint = new Paint();
		mBound = new Rect();
		mPaint.setAntiAlias(true);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		this.mWidth = getMeasuredWidth();
		this.mHeight = getMeasuredHeight();
	}

	@SuppressLint("NewApi")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mPaint.setColor(Color.WHITE);
		canvas.drawCircle(mWidth / 2, mHeight / 2, mRadius, mPaint);
		mPaint.setColor(getContext().getResources().getColor(R.color.colorPrimary));
		mPaint.setTextSize(46f);
		mPaint.getTextBounds(text, 0, text.length(), mBound);
		canvas.drawText(text,getWidth()/2-mBound.width()/2, getHeight()/2+mRadius+mBound.height()+5, mPaint);
	}

}
