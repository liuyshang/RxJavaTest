package com.example.lance.rxjavatest.ui.view;

import com.example.lance.rxjavatest.model.bean.MobileInfo;

import java.util.List;

/**
 * author: Lance
 * time: 2016/1/29 14:42
 * e-mail: lance.cao@anarry.com
 */
public interface MobileAttributionView {

    /**
     * 显示dialog
     * */
   void showDialog();

    /**
     * 隐藏dialog
     * */
    void hideDialog();

    /**
     * 显示错误信息
     * */
    void showError(String str);

    /**
     * 显示手机号信息
     * */
    void showMobileInfo(List<MobileInfo> list);
}
