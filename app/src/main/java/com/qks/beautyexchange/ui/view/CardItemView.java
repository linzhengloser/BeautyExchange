package com.qks.beautyexchange.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.qks.beautyexchange.R;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.utils.AutoLayoutHelper;

/**
 * 卡片View项
 * @author xmuSistone
 */
@SuppressLint("NewApi")
public class CardItemView extends FrameLayout {

    private final AutoLayoutHelper mHelper = new AutoLayoutHelper(this);

    //显示大图
    public ImageView imageView;

    public CardItemView(Context context) {
        this(context, null);
    }

    public CardItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CardItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        //填充布局
//        addView(LayoutInflater.from(context).inflate(R.layout.find_card_item,this,false));
        inflate(context,R.layout.find_card_item,this);
        imageView = (ImageView) findViewById(R.id.iv_find_card_item_image);
    }

    //暂时不需要次方法
//    public void fillData(CardDataItem itemData) {
//    }

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
