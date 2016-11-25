package com.qks.beautyexchange.adapter.mine;

import android.content.Context;

import com.qks.beautyexchange.adapter.base.BaseRecyclerViewAdapter;
import com.qks.beautyexchange.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by admin on 2016/3/11.
 */
public class MineStoreRecyclerViewAdapter extends BaseRecyclerViewAdapter<String>{

    public MineStoreRecyclerViewAdapter(Context context, List<String> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(BaseViewHolder holder, String data) {

    }
}
