package com.example.lance.rxjavatest.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

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
}
