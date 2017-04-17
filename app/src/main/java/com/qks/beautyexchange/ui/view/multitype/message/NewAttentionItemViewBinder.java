package com.qks.beautyexchange.ui.view.multitype.message;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qks.beautyexchange.R;
import com.qks.beautyexchange.api.entity.message.Attention;
import com.qks.beautyexchange.ui.view.multitype.BaseViewHolder;

import me.drakeet.multitype.ItemViewBinder;

/**
 * <pre>
 *     author : linzheng
 *     e-mail : 1007687534@qq.com
 *     time   : 2017/04/17
 *     desc   : 新关注的 ItemViewBinder
 *     version: 1.0
 * </pre>
 */
public class NewAttentionItemViewBinder extends ItemViewBinder<Attention,NewAttentionItemViewBinder.NewAttentionItemHolder>{

    @NonNull
    @Override
    protected NewAttentionItemHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup group) {
        return new NewAttentionItemHolder(inflater.inflate(R.layout.recyclerview_item_message_new_attention,group,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull NewAttentionItemHolder holder, @NonNull Attention attention) {

    }

    static class NewAttentionItemHolder extends BaseViewHolder {

        NewAttentionItemHolder(@NonNull View itemView) {
            super(itemView);
        }

    }


}
