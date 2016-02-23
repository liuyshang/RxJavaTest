package com.example.lance.rxjavatest.presenter.impl;

import android.util.Log;

import com.example.lance.rxjavatest.model.LoadDataModel;
import com.example.lance.rxjavatest.model.bean.IDCardInfo;
import com.example.lance.rxjavatest.model.impl.IDCardInquiryModelImpl;
import com.example.lance.rxjavatest.presenter.OnCompleteListener;
import com.example.lance.rxjavatest.ui.view.IDCardInquiryView;

import java.util.List;

/**
 * author: Lance
 * time: 2016/2/23 15:13
 * e-mail: lance.cao@anarry.com
 */
public class IDCardInquiryPresenterImpl implements OnCompleteListener {

    private IDCardInquiryView idView;
    private LoadDataModel idModel;

    public IDCardInquiryPresenterImpl(IDCardInquiryView idView) {
        this.idView = idView;
        idModel = new IDCardInquiryModelImpl();
    }

    @Override
    public <T> void onSuccess(T t) {
        idView.hideDialog();
        idView.showIDCardInfo((IDCardInfo) t);
    }

    @Override
    public <T> void onSuccess(List<T> list) {

    }

    @Override
    public void onError(String str) {
        idView.hideDialog();
        idView.showError(str);
    }

    /**
     * 查询身份证信息
     * @param str 身份证id
     */
    public void onIDCard(String str){
        idView.showDialog();
        idModel.loadWeather(str,this);
    }
}
