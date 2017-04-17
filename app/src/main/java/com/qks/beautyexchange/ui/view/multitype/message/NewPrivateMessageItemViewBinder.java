package com.qks.beautyexchange.ui.view.multitype.message;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qks.beautyexchange.R;
import com.qks.beautyexchange.api.entity.message.PrivateMessage;
import com.qks.beautyexchange.ui.view.multitype.BaseViewHolder;

import me.drakeet.multitype.ItemViewBinder;

/**
 * <pre>
 *     author : linzheng
 *     e-mail : 1007687534@qq.com
 *     time   : 2017/04/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class NewPrivateMessageItemViewBinder extends ItemViewBinder<PrivateMessage,NewPrivateMessageItemViewBinder.NewPrivateMessageItemHolder> {

    @NonNull
    @Override
    protected NewPrivateMessageItemHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup group) {
        return new NewPrivateMessageItemHolder(inflater.inflate(R.layout.recyclerview_item_message_new_private_message,group,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull NewPrivateMessageItemHolder holder, @NonNull PrivateMessage message) {

    }

    static class NewPrivateMessageItemHolder extends BaseViewHolder{

        public NewPrivateMessageItemHolder(View itemView) {
            super(itemView);
        }
    }

}
