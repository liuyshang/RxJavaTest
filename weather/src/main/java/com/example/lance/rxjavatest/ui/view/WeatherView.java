package com.example.lance.rxjavatest.ui.view;

import com.example.lance.rxjavatest.model.bean.WeatherInfo;

/**
 * author: Lance
 * time: 2016/1/23 16:18
 * e-mail: lance.cao@anarry.com
 */
public interface WeatherView {

    /**
     * 显示dialog
     * */
    void showLoading();

    /**
     * 隐藏dialog
     * */
    void hideLoading();

    /**
     * 显示错误信息
     * */
    void showError(String str);

    /**
     * 显示天气信息
     * */
    void showWeatherInfo(WeatherInfo weather);
}
