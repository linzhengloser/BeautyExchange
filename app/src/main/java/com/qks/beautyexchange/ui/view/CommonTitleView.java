package com.qks.beautyexchange.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qks.beautyexchange.R;
import com.zhy.autolayout.AutoRelativeLayout;
import com.zhy.autolayout.utils.AutoLayoutHelper;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by admin on 2016/3/29.
 */
public class CommonTitleView extends RelativeLayout {

    private final AutoLayoutHelper mHelper = new AutoLayoutHelper(this);


    private String mTitleText;

    private int mTitleTextColor;

    private int mTitleLeftImage;

    private int mTitleRightImage;

    private boolean mTitleIsShowLeftImage = true;

    private boolean mTitleIsShowRightImage = true;

    private boolean mTitleIsShowText = true;

    private TextView mTextView;

    private ImageView mLeftImageView;

    private ImageView mRightImageView;

    private CommonTitleOnClickListener mListener;

    public interface CommonTitleOnClickListener {

        void onLeftClick();

        void onRightClick();

    }

    public CommonTitleView(Context context) {
        this(context, null);
    }

    public CommonTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
        initViews();
    }


    private void initAttrs(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.common_title_view);
        if (ta != null) {
            mTitleText = ta.getString(R.styleable.common_title_view_commonTitleText);
            mTitleIsShowLeftImage = ta.getBoolean(R.styleable.common_title_view_commonTitleIsShowLeftImage, true);
            mTitleIsShowRightImage = ta.getBoolean(R.styleable.common_title_view_commonTitleIsShowRightImage, true);
            mTitleIsShowText = ta.getBoolean(R.styleable.common_title_view_commonTitleIsShowTitle, true);
            mTitleTextColor = ta.getColor(R.styleable.common_title_view_commonTitleTextColor, -1);
            if (mTitleIsShowLeftImage)
                mTitleLeftImage = ta.getResourceId(R.styleable.common_title_view_commonTitleLeftImage, R.mipmap.ic_launcher);
            if (mTitleIsShowRightImage)
                mTitleRightImage = ta.getResourceId(R.styleable.common_title_view_commonTitleRightImage, R.mipmap.ic_launcher);
            ta.recycle();
        }
    }

    private void initViews() {
        //中间标题
        if (mTitleIsShowText) {
            LayoutParams lpTitle = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            lpTitle.addRule(RelativeLayout.CENTER_IN_PARENT);
            mTextView = new TextView(getContext());
            mTextView.setText(mTitleText);
            //可能会改
            mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,40);
            mTextView.setTextColor(mTitleTextColor);
            addView(mTextView, lpTitle);

            AutoUtils.autoTextSize(mTextView);
        }

        //左边图标
        if (mTitleIsShowLeftImage) {
            //可能会改
            LayoutParams lpLeftImage = new LayoutParams(AutoUtils.getPercentWidthSize(40), AutoUtils.getPercentWidthSize(50));
            lpLeftImage.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            lpLeftImage.addRule(RelativeLayout.CENTER_VERTICAL);
            //可能会改
            lpLeftImage.leftMargin = AutoUtils.getPercentWidthSize(35);
            mLeftImageView = new ImageView(getContext());
            mLeftImageView.setImageResource(mTitleLeftImage);
            addView(mLeftImageView, lpLeftImage);

            mLeftImageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onLeftClick();
                    }
                }
            });

        }

        //右边图标
        if (mTitleIsShowRightImage) {
            LayoutParams lpRightImage = new LayoutParams(AutoUtils.getPercentWidthSize(40), AutoUtils.getPercentWidthSize(50));
            lpRightImage.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            lpRightImage.addRule(RelativeLayout.CENTER_VERTICAL);
            lpRightImage.rightMargin = AutoUtils.getPercentWidthSize(35);
            mRightImageView = new ImageView(getContext());
            mRightImageView.setImageResource(mTitleRightImage);
            addView(mRightImageView, lpRightImage);

            mRightImageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onRightClick();
                    }
                }
            });
        }

        setBackgroundColor(getResources().getColor(R.color.colorPrimary));


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
    public AutoRelativeLayout.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new AutoRelativeLayout.LayoutParams(getContext(), attrs);
    }

    /**
     * 设置左边和右边图标的点击事件
     *
     * @param listenr
     */
    public void setOnClickListener(CommonTitleOnClickListener listenr) {
        this.mListener = listenr;
    }
}
