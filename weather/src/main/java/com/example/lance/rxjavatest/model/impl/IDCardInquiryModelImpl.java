package com.example.lance.rxjavatest.model.impl;

import android.util.Log;

import com.example.lance.rxjavatest.model.LoadDataModel;
import com.example.lance.rxjavatest.model.bean.IDCardInfo;
import com.example.lance.rxjavatest.presenter.OnCompleteListener;
import com.example.lance.rxjavatest.util.Common;
import com.google.gson.Gson;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

/**
 * author: Lance
 * time: 2016/2/23 15:20
 * e-mail: lance.cao@anarry.com
 */
public class IDCardInquiryModelImpl implements LoadDataModel {
    @Override
    public void loadWeather(final String str, final OnCompleteListener listener) {
        AjaxParams params = new AjaxParams();
        params.put("id", str);

        FinalHttp http = new FinalHttp();
        http.addHeader("apikey", Common.APPKEY);
        http.get(Common.ID_CARD, params, new AjaxCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                super.onSuccess(o);
                Log.i(">", (String) o);
                Gson gson = new Gson();
                IDCardInfo entity = gson.fromJson((String) o, IDCardInfo.class);
                listener.onSuccess(entity);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                listener.onError(strMsg);
            }
        });
    }
}
