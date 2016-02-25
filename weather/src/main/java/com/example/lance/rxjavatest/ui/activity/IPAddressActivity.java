package com.example.lance.rxjavatest.ui.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lance.rxjavatest.R;
import com.example.lance.rxjavatest.model.bean.IDCardInfo;
import com.example.lance.rxjavatest.model.bean.IPAddress;
import com.example.lance.rxjavatest.model.impl.IPAddressModelImpl;
import com.example.lance.rxjavatest.util.Common;
import com.google.gson.Gson;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.annotation.view.ViewInject;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * author: Lance
 * time: 2016/2/24 11:12
 * e-mail: lance.cao@anarry.com
 */
public class IPAddressActivity extends FinalActivity implements Callback<IPAddress> {

    @ViewInject(id = R.id.et)
    private EditText et;
    @ViewInject(id = R.id.ib_back, click = "onClick")
    private ImageView ibBack;
    @ViewInject(id = R.id.bt_get, click = "onClick")
    private Button btn;
    @ViewInject(id = R.id.tv_info)
    private TextView tv;

    private Context mContext;
    private Call<IPAddress> mCall;

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                finish();
                break;
            case R.id.bt_get:
                getInfo(et.getText().toString());
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip_address);
        mContext = IPAddressActivity.this;
    }

    @Override
    public void onResponse(Call<IPAddress> call, Response<IPAddress> response) {
        String str = response.body().getRetData().getCountry() + response.body().getRetData().getProvince()
                + response.body().getRetData().getCity() + response.body().getRetData().getDistrict()
                + response.body().getRetData().getCarrier();
        Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Call<IPAddress> call, Throwable t) {
        Toast.makeText(mContext, t.toString(), Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取ip地址信息
     */
    private void getInfo(final String s) {
        IPAddressModelImpl.getDataObdervable(s)
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<IPAddress>() {
                    @Override
                    public void call(IPAddress ipAddress) {
                        showInfo(ipAddress);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("LOG_TAG", throwable.toString());
                    }
                });

//        mCall = IPAddressModelImpl.getDataCall(s);
//        mCall.enqueue(this);

//        IPAddressModelImpl.getDataCall(s).enqueue(this);
    }

    /**
     * 显示ip地址信息
     */
    private void showInfo(IPAddress ipAddress) {
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(et.getWindowToken(), 0);
        IPAddress.RetDataEntity retData = ipAddress.getRetData();
        StringBuilder builder = new StringBuilder();
        builder.append("ip: " + retData.getIp() + "\n");
        builder.append("国家: " + retData.getCountry() + "\n");
        builder.append("省份: " + retData.getProvince() + "\n");
        builder.append("城市: " + retData.getCity() + "\n");
        builder.append("区: " + retData.getDistrict() + "\n");
        builder.append("运营商: " + retData.getCarrier() + "\n");
        tv.setText(builder.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
