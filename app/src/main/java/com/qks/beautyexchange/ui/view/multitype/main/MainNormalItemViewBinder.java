package com.qks.beautyexchange.ui.view.multitype.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qks.beautyexchange.R;
import com.qks.beautyexchange.api.entity.main.MainNormalItem;
import com.qks.beautyexchange.ui.view.multitype.BaseViewHolder;

import butterknife.BindView;
import me.drakeet.multitype.ItemViewBinder;

/**
 * <pre>
 *     author : linzheng
 *     e-mail : 1007687534@qq.com
 *     time   : 2017/04/17
 *     desc   : 首页普通 ItemViewBinder
 *     version: 1.0
 * </pre>
 */
public class MainNormalItemViewBinder extends ItemViewBinder<MainNormalItem,MainNormalItemViewBinder.MainNormalItemHolder> {

    @NonNull
    @Override
    protected MainNormalItemHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup group) {
        return new MainNormalItemHolder(inflater.inflate(R.layout.recyclerview_item_main_simple,group,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull MainNormalItemHolder holder, @NonNull MainNormalItem s) {
        holder.rv_item_main_simple_horizontal_images.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
        holder.rv_item_main_simple_horizontal_images.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new BaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_main_simple_horizontal,parent,false));
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 10;
            }
        });
    }

    static class MainNormalItemHolder extends BaseViewHolder {

        @BindView(R.id.rv_item_main_simple_horizontal_images)
        RecyclerView rv_item_main_simple_horizontal_images;

        MainNormalItemHolder(@NonNull View itemView) {
            super(itemView);
        }

    }


}
