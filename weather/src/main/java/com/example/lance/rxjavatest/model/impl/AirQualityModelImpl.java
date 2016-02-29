package com.example.lance.rxjavatest.model.impl;

import com.example.lance.rxjavatest.model.bean.AirQualityInfo;
import com.example.lance.rxjavatest.model.bean.CityListInfo;
import com.example.lance.rxjavatest.util.Common;
import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * author: Lance
 * time: 2016/2/26 17:52
 * e-mail: lance.cao@anarry.com
 */
public class AirQualityModelImpl {

    public interface AirQualityService{
        @Headers("apikey: 5befe01a38543280ce270befd5c6f952")
        @GET("apistore/aqiservice/citylist")
        Observable<CityListInfo> getCityList();

        @Headers("apikey: 5befe01a38543280ce270befd5c6f952")
        @GET("apistore/aqiservice/aqi")
        Observable<AirQualityInfo> getAirQuality(@Query("city") String city);
    }
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://apis.baidu.com/")
            .addConverterFactory(GsonConverterFactory.create())  //添加 json 转换器
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())  //添加 RxJava 适配器
            .build();
    private static AirQualityService apiManger = retrofit.create(AirQualityService.class);

    /**
     * 获取城市列表
     * @return
     */
    public static Observable<CityListInfo> getCityListData(){
        return apiManger.getCityList();
    }

    /**
     * 获取城市的空气质量指数
     * @param city
     * @return
     */
    public static Observable<AirQualityInfo> getAirQUalityData(String city){
        return apiManger.getAirQuality(city);
    }
}
