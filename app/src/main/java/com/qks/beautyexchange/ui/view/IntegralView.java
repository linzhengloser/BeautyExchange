package com.qks.beautyexchange.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.qks.beautyexchange.R;

/**
 * 用于显示首页Item的积分
 */
public class IntegralView extends View {

	private int mWidth;
	private int mHeight;

	private Paint mPaint;

	private Rect mBound;

	private String text = "22"; //默认文字

	public IntegralView(Context context) {
		this(context, null);
	}

	public IntegralView(Context context, AttributeSet attrs) {
		super(context, attrs, 0);
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setTextSize(24);
		mBound = new Rect();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		this.mWidth = getMeasuredWidth();
		this.mHeight = getMeasuredHeight();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		//
		mPaint.setColor(getContext().getResources().getColor(R.color.main_item_simple_integral_number_bg));
		//画背景
		canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2, mPaint);
		//画文字
		mPaint.setColor(Color.WHITE);
		mPaint.getTextBounds(text, 0, text.length(), mBound);
		canvas.drawText(text, getWidth() / 2 - mBound.width() / 2, getHeight()/ 2 + mBound.height() / 2, mPaint);
	}

	public void setText(String text) {
		this.text = text;
		invalidate();
	}

}
