package com.qks.mylibrary.view.indexbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.github.promeg.pinyinhelper.Pinyin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * 实现类似微信 联系人界面 右边的索引界面
 *
 *
 */

public class IndexBar extends View {

    public static String[] INDEX_STRING = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#"};//#在最后面（默认的数据源）

    private List<String> mIndexDatas;//索引数据源

    private List<? extends BaseIndexPinyinBean> mSourceDatas; //需要添加所有的数据源

    private int mWidth;

    private int mHeight;

    private int mGapHeight;

    private int mPressedBackground;

    /**
     * 是否需要别的数据源 true 需要 false 不需要 如果为false 默认使用INDEX_STRING 这个数组做为数据源
     */
    private boolean isNeedRealIndex;

    private Paint mPaint;

    private LinearLayoutManager mLayoutManager;

    private TextView mPressedShowTextView;//用于特写显示正在被触摸的index值

    private OnIndexPressedListener mListener;

    public IndexBar(Context context) {
        this(context, null);
    }

    public IndexBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if (!isNeedRealIndex) {//不需要真实的索引数据源
            mIndexDatas = Arrays.asList(INDEX_STRING);
        }

        int textSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics());//默认的TextSize
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(textSize);
        mPaint.setColor(Color.BLACK);

        mPressedBackground = Color.parseColor("#39000000");

        setIndexPressedListener(new OnIndexPressedListener() {
            @Override
            public void onIndexPressed(int index, String text) {
                mPressedShowTextView.setVisibility(View.VISIBLE);
                mPressedShowTextView.setText(text);
                if (mLayoutManager != null) {
                    int postion = getPosByTag(text);
                    if (postion != -1) {
                        mLayoutManager.scrollToPositionWithOffset(postion, 0);
                    }
                }
            }

            @Override
            public void onMotionEventEnd() {
                //隐藏hintTextView
                if (mPressedShowTextView != null) {
                    mPressedShowTextView.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int measureWidth = 0, measureHeight = 0;//最终测量出来的宽高

        Rect indexBounds = new Rect();
        String index;
        for (int i = 0; i < mIndexDatas.size(); i++) {
            index = mIndexDatas.get(i);
            mPaint.getTextBounds(index, 0, index.length(), indexBounds);//测量计算文字所在矩形，可以得到宽高
            measureWidth = Math.max(indexBounds.width(), measureWidth);
            measureHeight = Math.max(indexBounds.width(), measureHeight);
        }
        measureHeight = measureHeight * mIndexDatas.size();

        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                measureWidth = widthSize;
                break;
            case MeasureSpec.AT_MOST:
                measureWidth = Math.min(measureWidth, widthSize);//widthSize此时是父控件能给子View分配的最大空间
                break;
        }

        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                measureHeight = heightSize;
                break;
            case MeasureSpec.AT_MOST:
                measureHeight = Math.min(measureHeight, heightSize);//heightSize此时是父控件能给子View分配的最大空间
                break;
        }
        setMeasuredDimension(measureWidth, measureHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int t = getPaddingTop();
        Rect indexRound = new Rect();
        String index;//每个要绘制的index内容
        for (int i = 0; i < mIndexDatas.size(); i++) {
            index = mIndexDatas.get(i);
            mPaint.getTextBounds(index, 0, index.length(), indexRound);
            Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
            int baseLine = (int) (mGapHeight - fontMetrics.bottom - fontMetrics.top) / 2;
            canvas.drawText(index, mWidth / 2 - indexRound.width() / 2, t + mGapHeight * i + baseLine, mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setBackgroundColor(mPressedBackground);
                break;
            case MotionEvent.ACTION_MOVE:
                float y = event.getY();
                //计算出手指拿下的区域是哪个Index
                int pressIndex = (int) ((y - getPaddingTop()) / mGapHeight);
                if (pressIndex < 0) {
                    pressIndex = 0;
                } else if (pressIndex >= mIndexDatas.size()) {
                    pressIndex = mIndexDatas.size() - 1;
                }
                if (mListener != null) {
                    mListener.onIndexPressed(pressIndex, mIndexDatas.get(pressIndex));
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
            default:
                setBackgroundResource(android.R.color.transparent);//手指抬起时背景恢复透明
                //回调监听器
                if (null != mListener) {
                    mListener.onMotionEventEnd();
                }
                break;
        }
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (null == mIndexDatas || mIndexDatas.isEmpty()) return;
        mWidth = w;
        mHeight = h;
        //每个所有的高度
        mGapHeight = (mHeight - getPaddingTop() - getPaddingBottom()) / mIndexDatas.size();
    }

    /**
     * 一定要在设置数据源{@link #setSourceDatas(List)}之前调用
     *
     * @param needRealIndex
     * @return
     */
    public IndexBar setNeedRealIndex(boolean needRealIndex) {
        isNeedRealIndex = needRealIndex;
        if (isNeedRealIndex) {
            if (mIndexDatas != null) {
                mIndexDatas = new ArrayList<String>();
            }
        }
        return this;
    }

    /**
     * 设置数据源
     * @param sourceDatas
     * @return
     */
    public IndexBar setSourceDatas(List<? extends BaseIndexPinyinBean> sourceDatas){
        this.mSourceDatas = sourceDatas;
        initSourceDatas();
        return this;
    }

    /**
     * 初始化数据源
     */
    private void initSourceDatas(){
        if (null == mSourceDatas || mSourceDatas.isEmpty()) return;
        int size = mSourceDatas.size();
        for (int i = 0; i < size; i++) {
            BaseIndexPinyinBean indexPinyinBean = mSourceDatas.get(i);
            StringBuilder pinyinStringBuilder = new StringBuilder();
            //取出需要被拼音化的字段段
            String target = indexPinyinBean.getTarget();
            for(int i1 = 0 ; i1<target.length();i1++){
                pinyinStringBuilder.append(Pinyin.toPinyin(target.charAt(i1)));
            }
            indexPinyinBean.setBaseIndexPinyin(pinyinStringBuilder.toString());

            //以下代码是设置城市拼音首字母
            String tagString = pinyinStringBuilder.toString().substring(0,1);
            if(tagString.matches("[A-Z]")){
                indexPinyinBean.setBaseIndexTag(tagString);
                if(isNeedRealIndex){
                    if(!mIndexDatas.contains(tagString)){
                        mIndexDatas.add(tagString);
                    }
                }
            }else{
                indexPinyinBean.setBaseIndexTag("#");
                if (isNeedRealIndex) {//如果需要真实的索引数据源
                    if (!mIndexDatas.contains("#")) {
                        mIndexDatas.add("#");
                    }
                }
            }
        }
        //排序
        sortData();
    }

    /**
     * 对数据源排序
     */
    private void sortData() {
        //对右侧栏进行排序 将 # 丢在最后
        Collections.sort(mIndexDatas, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                if (lhs.equals("#")) {
                    return 1;
                } else if (rhs.equals("#")) {
                    return -1;
                } else {
                    return lhs.compareTo(rhs);
                }
            }
        });

        //对数据源进行排序
        Collections.sort(mSourceDatas, new Comparator<BaseIndexPinyinBean>() {
            @Override
            public int compare(BaseIndexPinyinBean lhs, BaseIndexPinyinBean rhs) {
                if (lhs.getBaseIndexTag().equals("#")) {
                    return 1;
                } else if (rhs.getBaseIndexTag().equals("#")) {
                    return -1;
                } else {
                    return lhs.getBaseIndexPinyin().compareTo(rhs.getBaseIndexPinyin());
                }
            }
        });
    }

    /**
     * 通过首字母拼音获取在集合中position
     *
     * @param tag
     * @return
     */
    private int getPosByTag(String tag) {
        if (null == mSourceDatas || mSourceDatas.isEmpty()) {
            return -1;
        }
        if (TextUtils.isEmpty(tag)) {
            return -1;
        }
        for (int i = 0; i < mSourceDatas.size(); i++) {
            if(mSourceDatas.get(i).getBaseIndexTag().equals(tag)){
                return i;
            }
        }
        return -1;
    }

    /**
     * 设置RecyclerView的LayoutManager
     * @param linearLayoutManager
     * @return
     */
    public IndexBar setLinearLayoutManager(LinearLayoutManager linearLayoutManager) {
        this.mLayoutManager = linearLayoutManager;
        return this;
    }

    /**
     * 设置监听
     * @param onIndexPressedListener
     */
    public void setIndexPressedListener(OnIndexPressedListener onIndexPressedListener) {
        this.mListener = onIndexPressedListener;
    }

    /**
     * 设置按下之后显示在屏幕中央的
     * @param pressedShowTextView
     */
    public IndexBar setPressedShowTextView(TextView pressedShowTextView){
        mPressedShowTextView = pressedShowTextView;
        return this;
    }

    /**
     * 手指按下 和手指松开的监听
     */
    public interface OnIndexPressedListener {
        void onIndexPressed(int index, String text);//当某个index被按下

        void onMotionEventEnd();//
    }
}
