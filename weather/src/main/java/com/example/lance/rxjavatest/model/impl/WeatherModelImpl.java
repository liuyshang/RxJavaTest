package com.example.lance.rxjavatest.model.impl;

import android.util.Log;

import com.example.lance.rxjavatest.model.bean.WeatherInfo;
import com.example.lance.rxjavatest.model.LoadDataModel;
import com.example.lance.rxjavatest.presenter.OnCompleteListener;
import com.example.lance.rxjavatest.util.Common;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * author: Lance
 * time: 2016/1/23 16:22
 * e-mail: lance.cao@anarry.com
 * 天气Model实现
 */
public class WeatherModelImpl implements LoadDataModel {

    @Override
    public void loadWeather(String str, final OnCompleteListener listener) {
        //数据层操作
        AjaxParams params = new AjaxParams();
        params.put("cityname",str);
        FinalHttp http = new FinalHttp();
        //cityid 城市代码；citypinyin 城市拼音；cityname城市名称
        http.get(Common.WEATHER,params, new AjaxCallBack<String>() {
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
                    Log.e(">>>",object.toString());
                    if (!object.optString("errNum").equals("0")){
                        listener.onError(object.optString("errMsg"));
                        return;
                    }
                    JSONObject dataObject = object.optJSONObject("retData");
                    WeatherInfo weatherInfo = new WeatherInfo();
                    weatherInfo.setCity(dataObject.optString("City"));
                    weatherInfo.setPinyin(dataObject.optString("pinyin"));
                    weatherInfo.setCitycode(dataObject.optString("citycode"));
                    weatherInfo.setDate(dataObject.optString("date"));
                    weatherInfo.setTime(dataObject.optString("time"));
                    weatherInfo.setPostCode(dataObject.optString("postCode"));
                    weatherInfo.setLongitude(dataObject.optString("longitude"));
                    weatherInfo.setLatitude(dataObject.optString("latitude"));
                    weatherInfo.setAltitude(dataObject.optString("altitude"));
                    weatherInfo.setWeather(dataObject.optString("weather"));
                    weatherInfo.setTemp(dataObject.optString("temp"));
                    weatherInfo.setL_tmp(dataObject.optString("l_tmp"));
                    weatherInfo.setH_tmp(dataObject.optString("h_tmp"));
                    weatherInfo.setWD(dataObject.optString("WD"));
                    weatherInfo.setWS(dataObject.optString("WS"));
                    weatherInfo.setSunrise(dataObject.optString("sunrise"));
                    weatherInfo.setSunset(dataObject.optString("sunset"));
                    listener.onSuccess(weatherInfo);
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
}
