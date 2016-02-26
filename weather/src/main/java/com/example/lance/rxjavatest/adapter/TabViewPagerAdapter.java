package com.example.lance.rxjavatest.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Lance
 * time: 2016/2/25 17:52
 * e-mail: lance.cao@anarry.com
 */
public class TabViewPagerAdapter extends FragmentStatePagerAdapter{

    private Context context;
    private List<String> listTitle = new ArrayList<>();
    private List<Fragment> listFragment = new ArrayList<>();

    public TabViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public TabViewPagerAdapter(FragmentManager fm, Context context, List<String> listTitle, List<Fragment> listFragment) {
        super(fm);
        this.context = context;
        this.listTitle = listTitle;
        this.listFragment = listFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle.get(position);
    }

    /**
     * fragment重新加载时，fragment页面控件点击无反应
     * 决解办法：
     * 1，在destroyItem中，注释掉super.destroyItem(container,position,object)
     *    这样Fragment在加载后不会注销掉。（适用于Fragment每次记载无需刷新）
     * 2，在Fragment中，每次fragment销毁时，将fragment从Activity中去除
     *    在onDestroyView()中添加
     *    if (null != view) {
     *       ((ViewGroup) view.getParent()).removeView(view);
     *      }
     *
     * */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
