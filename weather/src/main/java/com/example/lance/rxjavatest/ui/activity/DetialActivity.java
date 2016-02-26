package com.example.lance.rxjavatest.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lance.rxjavatest.R;
import com.example.lance.rxjavatest.adapter.TabViewPagerAdapter;
import com.example.lance.rxjavatest.customview.SlidingTabLayout;
import com.example.lance.rxjavatest.ui.fragment.IDCardInquiryFragment;
import com.example.lance.rxjavatest.ui.fragment.IPAddressFragment;
import com.example.lance.rxjavatest.ui.fragment.MobileAttributionFragment;
import com.example.lance.rxjavatest.ui.fragment.WeatherFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Lance
 * time: 2016/2/25 17:26
 * e-mail: lance.cao@anarry.com
 */
public class DetialActivity extends FragmentActivity implements View.OnClickListener {

    /**
     * 返回按钮
     */
    private ImageView ivBack;
    /**
     * 标题
     */
    private TextView tvTitle;
    /**
     * SlidingTabLayout
     */
    private SlidingTabLayout slidingTab;
    /**
     * ViewPager
     */
    private ViewPager viewPager;

    private Context mContext;
    private List<String> mListTitle = new ArrayList<>();
    private List<Fragment> mListFragment = new ArrayList<>();
    private TabViewPagerAdapter mAdapter;
    private String type = "0";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mContext = DetialActivity.this;
        getIntentData();
        initView();
        initData();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (!TextUtils.isEmpty(intent.getStringExtra("type"))) {
            type = intent.getStringExtra("type");
        }
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        slidingTab = (SlidingTabLayout) findViewById(R.id.sliding_tab);
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        ivBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }

    private void initData() {
        mListTitle.add("天气");
        mListTitle.add("手机号归属地");
        mListTitle.add("身份证");
        mListTitle.add("ip地址");

        mListFragment.add(new WeatherFragment());
        mListFragment.add(new MobileAttributionFragment());
        mListFragment.add(new IDCardInquiryFragment());
        mListFragment.add(new IPAddressFragment());

        mAdapter = new TabViewPagerAdapter(getSupportFragmentManager(), mContext, mListTitle, mListFragment);
        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(Integer.parseInt(type));
        //是否填充满屏幕的宽度
        slidingTab.setDistributeEvenly(true);
        slidingTab.setViewPager(viewPager);
        slidingTab.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.black);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
