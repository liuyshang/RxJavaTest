package com.example.lance.rxjavatest.model.impl;

import com.example.lance.rxjavatest.model.bean.IPAddressInfo;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * author: Lance
 * time: 2016/2/24 11:19
 * e-mail: lance.cao@anarry.com
 */
public class IPAddressModelImpl {

    /**
     * 服务接口
     * */
    public interface IPAddressService {

        @Headers("apikey: 5befe01a38543280ce270befd5c6f952")
        @GET("apistore/iplookupservice/iplookup/")
        Call<IPAddressInfo> getIPCall(@Query("ip") String s);

        @Headers("apikey: 5befe01a38543280ce270befd5c6f952")
        @GET("apistore/iplookupservice/iplookup/")
        Observable<IPAddressInfo> getIPObservable(@Query("ip") String s);
    }

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://apis.baidu.com/")
            .addConverterFactory(GsonConverterFactory.create())  //添加 json 转换器
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())  //添加 RxJava 适配器
            .build();

    private static IPAddressService apiManger = retrofit.create(IPAddressService.class);

    /**
     * 获取ip数据，返回Call类型
     */
    public static Call<IPAddressInfo> getDataCall(String ip){
        return apiManger.getIPCall(ip);
    }

    /**
     * 获取ip数据,返回Observable类型
     * @param ip 参数
     * @return  Observable
     */
    public static Observable<IPAddressInfo> getDataObdervable(final String ip){
        return apiManger.getIPObservable(ip);
    }
}
