package com.example.lance.rxjavatest.model.impl;

import android.util.Log;

import com.example.lance.rxjavatest.model.LoadDataModel;
import com.example.lance.rxjavatest.model.bean.MobileInfo;
import com.example.lance.rxjavatest.presenter.OnCompleteListener;
import com.example.lance.rxjavatest.util.Common;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Lance
 * time: 2016/1/29 15:20
 * e-mail: lance.cao@anarry.com
 */
public class MobileAttributionModelImpl implements LoadDataModel {

    private List<MobileInfo> mList = new ArrayList<>();
    private MobileInfo mobileInfo;

    @Override
    public void loadWeather(final String str, final OnCompleteListener listener) {
        mList.clear();
        AjaxParams params = new AjaxParams();
        params.put("phone", str);
        FinalHttp http = new FinalHttp();
        http.addHeader("apikey", Common.APPKEY);
        http.get(Common.MOBILE_ATTRIBUTION, params, new AjaxCallBack<String>() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                JSONObject object;
                try {
                    object = new JSONObject(s);
                    Log.i("mobile", object.toString());

                    if (!object.optString("error").equals("0")) {
                        listener.onError(object.optString("msg"));
                        return;
                    }
                    if (getNumber(str)) {
                        JSONArray dataArray = object.optJSONArray("data");
                        for (int i = 0; i < dataArray.length(); i++) {
                            JSONObject ob = dataArray.optJSONObject(i);
                            mobileInfo = new MobileInfo();
                            mobileInfo.setAreaCode(ob.optString("areacode"));
                            mobileInfo.setCity(ob.optString("City"));
                            mobileInfo.setOperator(ob.optString("operator"));
                            mobileInfo.setPhone(ob.optString("phone"));
                            mobileInfo.setPostCode(ob.optString("postcode"));
                            mobileInfo.setProvince(ob.optString("province"));
                            mList.add(mobileInfo);
                        }
                        listener.onSuccess(mList);
                    } else {
                        JSONObject dataObject = object.optJSONObject("data");
                        mobileInfo = new MobileInfo();
                        mobileInfo.setAreaCode(dataObject.optString("areacode"));
                        mobileInfo.setCity(dataObject.optString("City"));
                        mobileInfo.setOperator(dataObject.optString("operator"));
                        mobileInfo.setPhone(dataObject.optString("phone"));
                        mobileInfo.setPostCode(dataObject.optString("postcode"));
                        mobileInfo.setProvince(dataObject.optString("province"));
                        listener.onSuccess(mobileInfo);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                listener.onError(strMsg);
            }
        });
    }

    /**
     * 获取查询的手机号码个数
     */
    private boolean getNumber(String str) {
        String[] a = str.split(",");
        if (a.length > 1) {
            return true;
        } else {
            return false;
        }
    }
}
