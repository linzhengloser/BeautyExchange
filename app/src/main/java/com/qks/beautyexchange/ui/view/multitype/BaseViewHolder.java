package com.qks.beautyexchange.ui.view.multitype;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhy.autolayout.utils.AutoUtils;

import butterknife.ButterKnife;

/**
 * <pre>
 *     author : linzheng
 *     e-mail : 1007687534@qq.com
 *     time   : 2017/04/17
 *     desc   : 所有 ViewHolder 需要继承这个 BaseViewHolder
 *     version: 1.0
 * </pre>
 */
public class BaseViewHolder extends RecyclerView.ViewHolder{

    public BaseViewHolder(View itemView) {
        super(itemView);
        AutoUtils.auto(itemView);
        ButterKnife.bind(this,itemView);
    }
}
