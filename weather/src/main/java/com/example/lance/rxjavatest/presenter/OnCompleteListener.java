package com.example.lance.rxjavatest.presenter;

import java.util.List;

/**
 * author: Lance
 * time: 2016/1/28 15:08
 * e-mail: lance.cao@anarry.com
 */

/**
 * 监听数据下载
 * */
public interface OnCompleteListener {
    /**
     * 数据下载完成后
     * */
    <T> void onSuccess(T t);

    <T> void onSuccess(List<T> list);
    /**
     * 数据下载失败
     * */
    void onError(String str);
}
