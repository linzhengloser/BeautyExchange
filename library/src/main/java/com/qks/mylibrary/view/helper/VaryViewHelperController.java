package com.qks.mylibrary.view.helper;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.qks.mylibrary.R;


public class VaryViewHelperController {

    private IVaryViewHelper helper;

    public VaryViewHelperController(View view) {
        this(new VaryViewHelper(view));
    }

    public VaryViewHelperController(IVaryViewHelper helper) {
        super();
        this.helper = helper;
    }

    public void showNetworkError(View.OnClickListener onClickListener) {
        View layout = helper.inflate(R.layout.common_message);
        TextView textview = (TextView) layout.findViewById(R.id.message_info);
        textview.setText("没有网络,请链接网络后重试...");
        if (null != onClickListener) {
            layout.setOnClickListener(onClickListener);
        }
        helper.showLayout(layout);
    }

    public void showError(String errorMsg, View.OnClickListener onClickListener) {
        View layout = helper.inflate(R.layout.common_message);
        TextView textview = (TextView) layout.findViewById(R.id.message_info);
        if (!TextUtils.isEmpty(errorMsg)) {
            textview.setText(errorMsg);
        } else {
            textview.setText("系统出错!请点击屏幕重试...");
        }
        if (null != onClickListener) {
            layout.setOnClickListener(onClickListener);
        }
        helper.showLayout(layout);
    }

    public void showEmpty(String emptyMsg, View.OnClickListener onClickListener) {
        View layout = helper.inflate(R.layout.common_message);
        TextView textview = (TextView) layout.findViewById(R.id.message_info);
        if (!TextUtils.isEmpty(emptyMsg)) {
            textview.setText(emptyMsg);
        } else {
            textview.setText("抱歉!暂未获取到数据...");
        }
        if (null != onClickListener) {
            layout.setOnClickListener(onClickListener);
        }
        helper.showLayout(layout);
    }

    public void showLoading(String msg) {
        View layout = helper.inflate(R.layout.common_loading);
        if (!TextUtils.isEmpty(msg)) {
            TextView textview = (TextView) layout.findViewById(R.id.loading_msg);
            textview.setText(msg);
        }
        helper.showLayout(layout);
    }

    public void restore() {
        helper.restoreView();
    }
}
