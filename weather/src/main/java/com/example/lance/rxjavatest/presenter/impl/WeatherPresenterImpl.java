package com.example.lance.rxjavatest.presenter.impl;

import com.example.lance.rxjavatest.model.bean.WeatherInfo;
import com.example.lance.rxjavatest.model.LoadDataModel;
import com.example.lance.rxjavatest.model.impl.WeatherModelImpl;
import com.example.lance.rxjavatest.presenter.OnCompleteListener;
import com.example.lance.rxjavatest.ui.view.FragmentView;

import java.util.List;

/**
 * author: Lance
 * time: 2016/1/23 16:06
 * e-mail: lance.cao@anarry.com
 * 天气Prestener实现
 */
public class WeatherPresenterImpl implements OnCompleteListener {

    //Presenter作为中间层，持有View和Model的引用
    private FragmentView fragmentView;
    private LoadDataModel weatherModel;

    public WeatherPresenterImpl(FragmentView fragmentView) {
        this.fragmentView = fragmentView;
        weatherModel = new WeatherModelImpl();
    }

    //ui调用
    public void getWeatherInfo(String str) {
        fragmentView.showLoading();
        weatherModel.loadWeather(str, this);
    }

    @Override
    public <T> void onSuccess(T t) {
        fragmentView.hideLoading();
        fragmentView.showInfo(t);
    }

    @Override
    public <T> void onSuccess(List<T> list) {

    }

    //model调用
    @Override
    public void onError(String str) {
        fragmentView.hideLoading();
        fragmentView.showError(str);
    }
}
