package com.qks.beautyexchange.ui.view.multitype.message;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qks.beautyexchange.R;
import com.qks.beautyexchange.api.entity.message.Comment;
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
public class NewCommentItemViewBinder extends ItemViewBinder<Comment,NewCommentItemViewBinder.NewCommentItemHolder> {

    @NonNull
    @Override
    protected NewCommentItemHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup group) {
        return new NewCommentItemHolder(inflater.inflate(R.layout.recyclerview_item_message_new_comment,group,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull NewCommentItemHolder holder, @NonNull Comment comment) {

    }

    static class NewCommentItemHolder extends BaseViewHolder{

        public NewCommentItemHolder(View itemView) {
            super(itemView);
        }
    }

}
