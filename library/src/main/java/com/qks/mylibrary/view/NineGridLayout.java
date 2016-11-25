package com.qks.mylibrary.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.List;

/**
 * 作者:linzheng
 * 日期:2016/10/12
 * 功能:实现类似微博的 九宫格展示图片 使用简单的复用机制 可方便在RecyclerView中使用
 */

public class NineGridLayout extends ViewGroup{


    /**
     * 间距
     */
    private int gap = 5;

    /**
     * 列
     */
    private int columns;

    /**
     * 行
     */
    private int rows;

    /**
     * 图片链接集合
     */
    private List<String> mImageUrlList;
    /**
     *
     */
    private int totalWidth;


    public NineGridLayout(Context context) {
        this(context,null);
    }

    public NineGridLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NineGridLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //此处需要明确TotalWidth
        totalWidth = ((WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    /**
     * 无需在外层做判断 也不用担心复用的问题
     * 设置数据源
     */
    public void setImageList(List<String> imageUrlList){
        if(imageUrlList ==null || imageUrlList.isEmpty()) {
            this.setVisibility(View.GONE);
        }else{
            //防止在RecyclerView中使用的时候 复用导致隐藏
            if(getVisibility() == View.GONE) setVisibility(View.VISIBLE);
        }

        //初始化 行和列
        generateChildrenLayout(imageUrlList.size());

        //此处 做一个优化处理  控件多则减 控件少则 增加 达到节约内存的目的
        if(mImageUrlList == null){
            int i = 0;
            while(i<imageUrlList.size()){
                ImageView imageView = generateImageView();
                addView(imageView,generateDefaultLayoutParams());
                i++;
            }
        }else{
            int oldImageViewCout = getChildCount();
            int newImageViewCount = imageUrlList.size();
            if(oldImageViewCout>newImageViewCount){
                removeViews(newImageViewCount - 1, oldImageViewCout - newImageViewCount);
            }else if(oldImageViewCout<newImageViewCount){
                for (int i = 0; i < newImageViewCount - oldImageViewCout; i++) {
                    ImageView iv = generateImageView();
                    addView(iv,generateDefaultLayoutParams());
                }
            }
        }

        mImageUrlList = imageUrlList;

        layoutChildrenView();

    }

    /**
     * 根据 Row 和 Column 对 ImageView 进行布局
     */
    private void layoutChildrenView(){
        int childCount  = mImageUrlList.size();
        int singleWidth = (totalWidth - gap * 2) / 3;
        int singleHeight = singleWidth;
        LayoutParams params = getLayoutParams();
        params.height = singleHeight*rows+gap*(rows-1);
        setLayoutParams(params);

        for(int i = 0; i< childCount ; i++){
            ImageView imageView = (ImageView) getChildAt(i);
//            imageView.setImageResource(R.mipmap.ic_launcher);
            int [] position = findPosition(i);
            int left = (singleWidth + gap) * position[1];
            int top = (singleHeight + gap) * position[0];
            int right = left + singleWidth;
            int bottom = top + singleHeight;
            imageView.layout(left, top, right, bottom);
        }

    }

    private int[] findPosition(int index){
        int []position = new int[2];
        for(int i = 0; i< rows; i++){
            for (int j = 0; j < columns;j++){
                if((i*columns+j)==index){
                    position[0] = i;//行
                    position[1] = j;//列
                    return position;
                }
            }
        }
        return position;
    }


    /**
     * 用来计算 行和列
     * 对应关系如下
     * num = 图片个数  row = 行数 column = 列数
     * num	row	column
     * 1	   1	1
     * 2	   1	2
     * 3	   1	3
     * 4	   2	2
     * 5	   2	3
     * 6	   2	3
     * 7	   3	3
     * 8	   3	3
     * 9	   3	3
     * @param size
     */
    private void generateChildrenLayout(int size){
        if (size <= 3) {
            rows = 1;
            columns = size;
        } else if (size <= 6) {
            rows = 2;
            columns = 3;
            if (size == 4) {
                columns = 2;
            }
        } else {
            rows = 3;
            columns = 3;
        }
    }

    private ImageView generateImageView() {
        ImageView iv = new ImageView(getContext());
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setBackgroundColor(Color.parseColor("#f5f5f5"));
        return iv;
    }

}
