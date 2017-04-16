package com.qks.beautyexchange.adapter.mine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qks.beautyexchange.R;
import com.zhy.autolayout.utils.AutoUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的 -> 商城 -> 商城内页 -> 商品详情适配器
 * Created by admin on 2016/3/16.
 */
public class MineStoreCommodityRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int HEADER = 1;

    private static final int IMAGE = 2;

    private Context mContext;

    private LayoutInflater mInflater;

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? HEADER : IMAGE;
    }

    public MineStoreCommodityRecyclerViewAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == HEADER){
            return new MineStoreCommodityHeaderViewHolder(mInflater.from(mContext).inflate(R.layout.recyclerview_item_mine_store_commodity_detail_header, parent, false));
        }else if(viewType == IMAGE){
            return new MineStoreCommodityImageViewHolder(mInflater.from(mContext).inflate(R.layout.recyclerview_item_mine_store_commodity_detail_image, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MineStoreCommodityHeaderViewHolder){
            List<String> sizeTag = new ArrayList<String>();
            List<String> colorTag = new ArrayList<String>();
            sizeTag.add("M");
            sizeTag.add("L");
            sizeTag.add("XL");
            sizeTag.add("XXL");
            sizeTag.add("XXXL");
            colorTag.add("基佬紫");
            colorTag.add("蛋碎黄");
            colorTag.add("高端黑");
            colorTag.add("高能红");
            colorTag.add("脑残粉");
            ((MineStoreCommodityHeaderViewHolder)holder).colorTag.setAdapter(new TagAdapter<String>(colorTag) {
                @Override
                public View getView(FlowLayout parent, int position, String o) {
                    TextView tv = (TextView) mInflater.inflate(R.layout.custom_view_mine_store_commodity_detail_tag,((MineStoreCommodityHeaderViewHolder)holder).colorTag,false);
                    tv.setText(o);
                    AutoUtils.autoMargin(tv);
                    return tv;
                }
            });
            ((MineStoreCommodityHeaderViewHolder)holder).sizeTag.setAdapter(new TagAdapter<String>(sizeTag) {
                @Override
                public View getView(FlowLayout parent, int position, String o) {
                    TextView tv = (TextView) mInflater.inflate(R.layout.custom_view_mine_store_commodity_detail_tag,(((MineStoreCommodityHeaderViewHolder)holder)).sizeTag,false);
                    tv.setText(o);
                    AutoUtils.autoMargin(tv);
                    return tv;
                }
            });
        }else if(holder instanceof MineStoreCommodityImageViewHolder){

        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public final class MineStoreCommodityHeaderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tfl_mine_store_commodity_detail_size)
        TagFlowLayout sizeTag;

        @BindView(R.id.tfl_mine_store_commodity_detail_color)
        TagFlowLayout colorTag;

        public MineStoreCommodityHeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            AutoUtils.auto(itemView);
        }
    }


    public final class MineStoreCommodityImageViewHolder extends RecyclerView.ViewHolder{

        public MineStoreCommodityImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            AutoUtils.auto(itemView);
        }
    }

}
