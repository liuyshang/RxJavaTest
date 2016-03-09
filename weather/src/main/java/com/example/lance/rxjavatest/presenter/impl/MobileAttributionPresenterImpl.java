package com.example.lance.rxjavatest.presenter.impl;

import com.example.lance.rxjavatest.model.LoadDataModel;
import com.example.lance.rxjavatest.model.bean.MobileInfo;
import com.example.lance.rxjavatest.model.impl.MobileAttributionModelImpl;
import com.example.lance.rxjavatest.presenter.OnCompleteListener;
import com.example.lance.rxjavatest.ui.view.FragmentView;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Lance
 * time: 2016/1/29 15:19
 * e-mail: lance.cao@anarry.com
 */
public class MobileAttributionPresenterImpl implements OnCompleteListener {

    private FragmentView mobileView;
    private LoadDataModel mobileModel;

    public MobileAttributionPresenterImpl(FragmentView mobileView) {
        this.mobileView = mobileView;
        mobileModel = new MobileAttributionModelImpl();
    }

    /**
     * 下载数据成功后 Model 调用
     */
    @Override
    public <T> void onSuccess(T t) {
        List<MobileInfo> list = new ArrayList<>();
        list.add((MobileInfo) t);
        mobileView.hideLoading();
        mobileView.showInfo(list);
    }

    @Override
    public <T> void onSuccess(List<T> list) {
        mobileView.hideLoading();
        mobileView.showInfo(list);
    }

    /**
     * 下载数据失败后 Model 调用
     */
    @Override
    public void onError(String str) {
        mobileView.hideLoading();
        mobileView.showError(str);
    }

    /**
     * 获取手机信息， ui 调用
     */
    public void onMobile(String str) {
        mobileView.showLoading();
        mobileModel.loadWeather(str, this);
    }
}
