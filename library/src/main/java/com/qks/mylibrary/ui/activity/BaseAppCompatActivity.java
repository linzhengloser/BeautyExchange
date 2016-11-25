package com.qks.mylibrary.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.qks.mylibrary.receiver.NetChangeObserver;
import com.qks.mylibrary.receiver.NetStateReceiver;
import com.qks.mylibrary.utils.EventCenter;
import com.qks.mylibrary.utils.NetUtils;
import com.qks.mylibrary.utils.SmartBarUtils;
import com.qks.mylibrary.view.helper.VaryViewHelperController;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.zhy.autolayout.AutoLayoutActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

@SuppressLint("NewApi")
public abstract class BaseAppCompatActivity extends AutoLayoutActivity {

	/**
	 * LogTag
	 */
	protected static String TAG_LOG = null;
	/**
	 * 上下文对象
	 */
	protected Context mContext;
	
	/**
	 *
	 */
	private VaryViewHelperController mVaryViewHelperController;

	/**
	 * 网络
	 */
	private NetChangeObserver mNetChangeObserver;


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		//获取Bundle
		if(null != extras){
			getBundleExtras(extras);
		}
		//EventBus
		if(isBindEventBusHere()){
			EventBus.getDefault().register(this);
		}
		//隐藏SmartBar:SmarBar 是魅族手机特有的,但是在魅族最新出来的手机里面取消了SmartBar
		SmartBarUtils.hide(getWindow().getDecorView());

		setTranslucentStatus(isApplyStatusBarTranslucency());

		mContext = this;
		TAG_LOG  = getClass().getSimpleName();
		BaseAppManager.getInstance().add(this);

		if(getContentViewLayoutID()!=0){
			setContentView(getContentViewLayoutID());
		}else{
			throw new IllegalArgumentException("必须设置布局文件的ID");
		}
		//网络改变观察者
		mNetChangeObserver = new NetChangeObserver() {
			@Override
			public void onNetDisConnect() { onNetworkDisConnected(); }
			
			@Override
			public void onNetConnected(NetUtils.NetType netType) {
				onNetworkConnected(netType);
			}
		};
 		NetStateReceiver.registerObserver(mNetChangeObserver);
		//初始化操作
		initViewsAndEvents();

	}

	
	/**
	 * 设置是否透明状态栏
	 * @param on
	 */
	protected void setTranslucentStatus(boolean on){
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
			Window win = getWindow();
			WindowManager.LayoutParams winParams = win.getAttributes();
			final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
			if(on){
				winParams.flags |= bits;
			}else{
				winParams.flags &= ~bits;
			}
			win.setAttributes(winParams);
		}
	}
	
	@Override
	public void setContentView(@LayoutRes int layoutResID) {
		super.setContentView(layoutResID);
		ButterKnife.bind(this);
		if(null != getLoadingTargetView()){
			mVaryViewHelperController = new VaryViewHelperController(getLoadingTargetView());
		}
	}

	@Override
	public void finish() {
		super.finish();
		BaseAppManager.getInstance().removeActivity(this);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.unbind(this);
		NetStateReceiver.removeRegisterObserver(mNetChangeObserver);
		if(isBindEventBusHere()){
			EventBus.getDefault().unregister(this);
		}
	}
	
	protected void readyGo(Class<?> clazz){
		Intent intent = new Intent(this,clazz);
		startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
	}
	
	protected void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
	
	protected void readyGoThenKill(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        finish();
    }
	
	protected void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }
	
	protected void readyGoThenKill(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        finish();
    }
	
	protected void readyGoForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }
	
	protected void showToast(String msg){
		if(!TextUtils.isEmpty(msg)){
			Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
		}
	}

	protected void toggleShowLoading(boolean toggle ,String msg){
		if(null == mVaryViewHelperController){
			throw new IllegalArgumentException("You must return a right target view for common_loading");
		}
		if(toggle){
			mVaryViewHelperController.showLoading(msg);
		}else{
			mVaryViewHelperController.restore();
		}
	}
	
	protected void toggleShowEmpty(boolean toggle,String msg,View.OnClickListener onClickListener){
		if(null == mVaryViewHelperController){
			throw new IllegalArgumentException("You must return a right target view for common_loading");
		}
		if(toggle){
			mVaryViewHelperController.showEmpty(msg, onClickListener);
		}else{
			mVaryViewHelperController.restore();
		}
	}
	
	protected void toggleShowError(boolean toggle,String msg,View.OnClickListener onClickListener){
		if(null == mVaryViewHelperController){
			throw new IllegalArgumentException("You must return a right target view for common_loading");
		}
		if(toggle){
			mVaryViewHelperController.showError(msg,onClickListener);
		}else{
			mVaryViewHelperController.restore();
		}
	}
	
	protected void toggleNetworkError(boolean toggle,View.OnClickListener onClickListener){
		if(null == mVaryViewHelperController){
			throw new IllegalArgumentException("You must return a right target view for common_loading");
		}
		if(toggle){
			mVaryViewHelperController.showNetworkError(onClickListener);
		}else{
			mVaryViewHelperController.restore();
		}
	}


    /**
	 * EventBus在主线程中要执行的操作
	 * @param eventCenter
     */
	public void onEventMainThread(EventCenter<?> eventCenter){
		if(null != eventCenter){
			onEventComming(eventCenter);
		}
	}
	
	/**
     * use SytemBarTintManager
     *
     * @param tintDrawable
     */
    protected void setSystemBarTintDrawable(Drawable tintDrawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager mTintManager = new SystemBarTintManager(this);
            if (tintDrawable != null) {
                mTintManager.setStatusBarTintEnabled(true);
                mTintManager.setTintDrawable(tintDrawable);
            } else {
                mTintManager.setStatusBarTintEnabled(false);
                mTintManager.setTintDrawable(null);
            }
        }

    }
	
	/**
	 * �η������ܸ�EventBus�й�
	 * @param eventCenter
	 */
	protected abstract void onEventComming(EventCenter<?> eventCenter);

	/**
	 * 获取Bundle
	 * @param extras
	 */
	protected abstract void getBundleExtras(Bundle extras);
	
	/**
	 * �Ƿ���Ҫ��EventBus
	 * @return
	 */
	protected abstract boolean isBindEventBusHere();
	/**
	 * 是否需要半透明状态栏
	 * @return
	 */
	protected abstract boolean isApplyStatusBarTranslucency();
	
	/**
	 * 获取布局文件ID
	 * @return
	 */
	protected abstract int getContentViewLayoutID();
	/**
	 * 初始化操作
	 */
	protected abstract void initViewsAndEvents();
	/**
	 * 获取加载View
	 * @return
	 */
	protected abstract View getLoadingTargetView();
	
	/**
	 * 网络以连接
	 * @param type
	 */
	protected abstract void onNetworkConnected(NetUtils.NetType type);
	
	/**
	 * 网络断开连接
	 */
	protected abstract void onNetworkDisConnected();
}
