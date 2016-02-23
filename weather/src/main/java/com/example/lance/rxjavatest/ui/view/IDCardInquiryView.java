package com.example.lance.rxjavatest.ui.view;

import com.example.lance.rxjavatest.model.bean.IDCardInfo;

/**
 * author: Lance
 * time: 2016/2/23 14:23
 * e-mail: lance.cao@anarry.com
 */
public interface IDCardInquiryView {

    /**
     * 显示 dialog
     * */
    void showDialog();

    /**
     * 隐藏 dialog
     * */
    void hideDialog();

    /**
     * 显示错误信息
     * */
    void showError(String str);

    /**
     * 显示身份证信息
     * */
    void showIDCardInfo(IDCardInfo entity);
}
