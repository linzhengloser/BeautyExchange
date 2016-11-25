package com.qks.beautyexchange.ui.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Point;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.qks.mylibrary.utils.CamareUtils;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.utils.AutoLayoutHelper;

public class AutoBackLayout extends FrameLayout {

    private final AutoLayoutHelper mHelper = new AutoLayoutHelper(this);

    private ViewDragHelper mDragger;

    private Point mAutoBackOriginPos = new Point();

    private View mAutoBackView;

    private View mShadowView;

    private GestureDetectorCompat mMoveDetector;

    private int mRadius = 180;

    public AutoBackLayout(Context context) {
        this(context, null);
    }

    public AutoBackLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mMoveDetector = new GestureDetectorCompat(context, new MoveDetector());
        mDragger = ViewDragHelper.create(this, 1.0f,
                new ViewDragHelper.Callback() {

                    @Override
                    public boolean tryCaptureView(View child, int pointerId) {
                        //只拦截mAutoBackView这个View的Tounch事件
                        return child == mAutoBackView;
                    }

                    @Override
                    public int clampViewPositionHorizontal(View child,
                                                           int left, int dx) {

                        final int leftBound = getPaddingLeft();
                        final int rightBound = getWidth() - child.getWidth()
                                - leftBound;
                        final int newLeft = Math.min(Math.max(left, leftBound),
                                rightBound);
                        return newLeft;
                    }

                    @Override
                    public int clampViewPositionVertical(View child, int top,
                                                         int dy) {
                        final int topBound = getPaddingTop();
                        final int bottomBound = getHeight() - child.getHeight()
                                - topBound;
                        final int newTop = Math.min(Math.max(top, topBound),
                                bottomBound);
                        return newTop;
                    }

                    @Override
                    public int getViewHorizontalDragRange(View child) {
                        //这个返回值是用来确定手指放开View回弹的速度
                        return getMeasuredWidth() - child.getMeasuredWidth();
                    }

                    @Override
                    public int getViewVerticalDragRange(View child) {
                        return getMeasuredHeight() - child.getMeasuredHeight();
                    }

                    // ��ָ�ͷŵĻص�
                    @Override
                    public void onViewReleased(View releasedChild, float xvel,
                                               float yvel) {
                        cameraIsDisplauCenter(mAutoBackView.getX(),
                                mAutoBackView.getY());
                        mDragger.settleCapturedViewAt(mAutoBackOriginPos.x,
                                mAutoBackOriginPos.y);
                        ObjectAnimator
                                .ofFloat(mShadowView, "alpha", 0.7f, 0.0f)
                                .setDuration(500).start();
                        invalidate();
                    }

                    @Override
                    public void onViewCaptured(View capturedChild,
                                               int activePointerId) {
                        ObjectAnimator
                                .ofFloat(mShadowView, "alpha", 0.0f, 0.7f)
                                .setDuration(500).start();
                    }

                });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean shouldIntercept = mDragger.shouldInterceptTouchEvent(ev);
        //只有当速度大于一定的值的时候才会拦截事件
        boolean moveFlag = mMoveDetector.onTouchEvent(ev);
        return shouldIntercept && moveFlag;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragger.processTouchEvent(event);
        return mDragger.isCapturedViewUnder((int) event.getX(), (int) event.getY());
    }

    @Override
    public void computeScroll() {
        if (mDragger.continueSettling(true)) {
            invalidate();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mAutoBackOriginPos.x = mAutoBackView.getLeft();
        mAutoBackOriginPos.y = mAutoBackView.getTop();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mShadowView = getChildAt(0);
        this.mAutoBackView = getChildAt(1);
    }

    private void cameraIsDisplauCenter(float x, float y) {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        int minX = outMetrics.widthPixels / 2 - mRadius;
        int minY = outMetrics.heightPixels / 2 - mRadius;
        int maxX = outMetrics.widthPixels / 2 + mRadius - mAutoBackView.getWidth();
        int maxY = outMetrics.heightPixels / 2 + mRadius - mAutoBackView.getHeight();
//		System.out.println("maxX = " + maxX +"maxY = "+maxY);
//		System.out.println("minX = " + minX +"minY = "+minY);
//		System.out.println("x = "+ x+"y = "+y);
//		System.out.println("width = " + outMetrics.widthPixels);
//		System.out.println("height = " + outMetrics.heightPixels);
//		System.out.println("imageViewWidth = " + mAutoBackView.getWidth()+"imageViewHeight = "+ mAutoBackView.getHeight());
        if (x > minX && y > minY && x < maxX && y < maxY) {
            //启动相机
            CamareUtils.startCamare(getContext());
        }
    }


    class MoveDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return Math.abs(distanceX) + Math.abs(distanceY) > 8;
        }
    }


    /**
     * 下面代码是为了实现AutoLayout的根据PX自动适配
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isInEditMode()) {
            mHelper.adjustChildren();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public AutoFrameLayout.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new AutoFrameLayout.LayoutParams(getContext(), attrs);
    }
}
