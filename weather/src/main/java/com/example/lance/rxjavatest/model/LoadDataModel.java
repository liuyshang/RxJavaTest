package com.example.lance.rxjavatest.model;

import com.example.lance.rxjavatest.presenter.OnCompleteListener;

import java.util.List;

/**
 * author: Lance
 * time: 2016/1/23 16:15
 * e-mail: lance.cao@anarry.com
 */
public interface LoadDataModel {

    /**
     * 下载数据
     * */
    void loadWeather(String str,OnCompleteListener listener);
}
