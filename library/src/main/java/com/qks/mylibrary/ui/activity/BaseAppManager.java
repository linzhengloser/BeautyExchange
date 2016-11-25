package com.qks.mylibrary.ui.activity;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

public class BaseAppManager {

	private static BaseAppManager mInstance = null;
	private static List<Activity> mActivites = new LinkedList<Activity>();
	
	private BaseAppManager(){}
	
	public static class SingletonHolder{
		private static final  BaseAppManager INSTANCE = new BaseAppManager();
	}

	public static BaseAppManager getInstance(){
		return SingletonHolder.INSTANCE;
	}
	
	public int size(){return mActivites.size();}
	
	public synchronized Activity getForwardActivity(){
		return size()>0?mActivites.get(size()-1):null;
	}
	
	public synchronized void add (Activity activity){mActivites.add(activity);}
	
	public synchronized void removeActivity(Activity activity){
		if(mActivites.contains(activity)){
			mActivites.remove(activity);
		}
	}

	/**
	 * 清空
	 */
	public synchronized void clear(){
		for(int i = mActivites.size()-1 ;i >-1;i--){
			Activity activity = mActivites.get(i);
			removeActivity(activity);
			activity.finish();
			i = mActivites.size();
		}
	}

	/**
	 * 清除第一个
	 */
	public synchronized void clearTop(){
		for(int i = mActivites .size() -2; i>-1;i--){
			Activity activity = mActivites.get(i);
			removeActivity(activity);
			activity.finish();
			i = mActivites.size()-1;
		}
	}
}
