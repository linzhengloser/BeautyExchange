package com.qks.beautyexchange.adapter.message;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by admin on 2016/3/9.
 */
public class MessageViewPagerAdapter extends FragmentPagerAdapter{

    private List<Fragment> mFragmentList ;

    private String [] mTitles = {"新关注","新评论","新私信"};

    public MessageViewPagerAdapter(FragmentManager fm,List<Fragment> fragmentList) {
        super(fm);
        mFragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
