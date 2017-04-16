package com.qks.mylibrary.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qks.mylibrary.utils.EventCenter;
import com.qks.mylibrary.view.helper.VaryViewHelperController;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Field;

import butterknife.ButterKnife;

/**
 * Created by admin on 2016/1/14.
 */
public abstract class BaseLazyFragment extends Fragment {
    protected Context mContext = null;

    private boolean isFirstResume = true;
    private boolean isFirstVisible = true;
    private boolean isFirstInvisible = true;
    private boolean isPrepared;

    private VaryViewHelperController mVaryViewHelperController = null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isBindEventBusHere()){
            EventBus.getDefault().register(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(getContentViewLayoutID()!=0){
            return inflater.inflate(getContentViewLayoutID(), null);
        }else{
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        if(null != getLoadingTargetView()){
            mVaryViewHelperController = new VaryViewHelperController(getLoadingTargetView());
        }
        initViewsAndEvents();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isBindEventBusHere()) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //用来处理Fragment中使用Fragment的情况下获取getSupportFragmentManager报错
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPrepare();
    }

    @Override
    public void onResume() {
        super.onResume();
        //判断是否是第一次Resume
        if(isFirstResume){
            isFirstResume = false;
            return;
        }
        //判断用户是否可见
        if(getUserVisibleHint()){
            onUserVisible();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        //判断用户是否可见
        if(getUserVisibleHint()){
            onUserInvisible();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //isVisibleToUser 为True 表示用户可见 为False表示用户不可见
        if (isVisibleToUser){
            //是否是第一次可见
            if(isFirstVisible){
                isFirstVisible = false;
                initPrepare();
            }else{
                onUserVisible();
            }
        }else{
            if(isFirstInvisible){
                isFirstInvisible = false;
                onFirstUserInVisible();
            }else{
                onUserInvisible();
            }
        }
    }

    //初始准备 当Fragmnet第一次
    private synchronized void initPrepare(){
        if(isPrepared){
            onFirstUserVisible();
        }else{
            isPrepared = true;
        }
    }

    protected FragmentManager getSupportFragmentManager() {
        return getActivity().getSupportFragmentManager();
    }

    protected void readyGo(Class<?> clazz) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivity(intent);
    }

    protected void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivityForResult(intent, requestCode);
    }

    protected void readyGoForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * show toast
     *
     * @param msg
     */
    protected void showToast(String msg) {
        if (null != msg && !TextUtils.isEmpty(msg)) {
            Snackbar.make(((Activity) mContext).getWindow().getDecorView(), msg, Snackbar.LENGTH_SHORT).show();
        }
    }

    protected void toggleShowLoading(boolean toggle, String msg) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for common_loading");
        }
        if (toggle) {
            mVaryViewHelperController.showLoading(msg);
        } else {
            mVaryViewHelperController.restore();
        }
    }

    protected void toggleShowEmpty(boolean toggle, String msg, View.OnClickListener onClickListener) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for common_loading");
        }

        if (toggle) {
            mVaryViewHelperController.showEmpty(msg, onClickListener);
        } else {
            mVaryViewHelperController.restore();
        }
    }

    protected void toggleShowError(boolean toggle, String msg, View.OnClickListener onClickListener) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for common_loading");
        }

        if (toggle) {
            mVaryViewHelperController.showError(msg, onClickListener);
        } else {
            mVaryViewHelperController.restore();
        }
    }

    protected void toggleNetworkError(boolean toggle, View.OnClickListener onClickListener) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for common_loading");
        }

        if (toggle) {
            mVaryViewHelperController.showNetworkError(onClickListener);
        } else {
            mVaryViewHelperController.restore();
        }
    }

    public void onEventMainThread(EventCenter eventCenter) {
        if (null != eventCenter) {
//            onEventComming(eventCenter);
        }
    }

    /**
     * 当前Fragment不可见时调用
     */
    protected abstract void onUserInvisible();

    /**
     * 当前Fragment可见时调用
     */
    protected abstract void onUserVisible();

    /**
     * 当前Fragment第一次可见时调用
     */
    protected abstract void onFirstUserVisible();

    /**
     * 当前Fragment第一次不可见时调用
     */
    protected abstract void onFirstUserInVisible();

    /**
     * 是否需要绑定EventBus
     * @return
     */
    protected abstract boolean isBindEventBusHere();

    /**
     * 获取布局文件ID
     * @return
     */
    protected abstract int getContentViewLayoutID();

    /**
     * 获取加载View
     * @return
     */
    protected abstract View getLoadingTargetView();

    /**
     * 初始化操作
     */
    protected abstract void initViewsAndEvents();

    /**
     * 估计跟EventBus
     * @param eventCenter
     */
//    protected abstract void onEventComming(EventCenter eventCenter);

}
