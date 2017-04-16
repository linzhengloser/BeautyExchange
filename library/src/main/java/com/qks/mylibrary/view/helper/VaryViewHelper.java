package com.qks.mylibrary.view.helper;

import android.view.View;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;


public class VaryViewHelper implements IVaryViewHelper {
    private View mLoadingTargetView;
    private ViewGroup parentView;
    private int viewIndex;
    private ViewGroup.LayoutParams params;
    private View currentView;

    public VaryViewHelper(View view) {
        this.mLoadingTargetView = view;
    }

    private void init() {
        params = mLoadingTargetView.getLayoutParams();
        if (mLoadingTargetView.getParent() != null) {
            parentView = (ViewGroup) mLoadingTargetView.getParent();
        } else {
            parentView = (ViewGroup) mLoadingTargetView.getRootView().findViewById(android.R.id.content);
        }
        int count = parentView.getChildCount();
        for (int index = 0; index < count; index++) {
            if (mLoadingTargetView == parentView.getChildAt(index)) {
                viewIndex = index;
                break;
            }
        }
        currentView = mLoadingTargetView;
    }

    @Override
    public View getCurrentLayout() {
        return currentView;
    }

    @Override
    public void restoreView() {
        showLayout(mLoadingTargetView);
    }

    @Override
    public void showLayout(View view) {
        if (parentView == null) {
            init();
        }
        this.currentView = view;
        // 如果已经是那个view，那就不需要再进行替换操作了
        if (parentView.getChildAt(viewIndex) != view) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            parentView.removeViewAt(viewIndex);
            parentView.addView(view, viewIndex, params);
        }
    }

    @Override
    public View inflate(int layoutId) {
        return LayoutInflater.from(mLoadingTargetView.getContext()).inflate(layoutId, null);
    }

    @Override
    public Context getContext() {
        return mLoadingTargetView.getContext();
    }

    @Override
    public View getLoadingTargetView() {
        return mLoadingTargetView;
    }
}
