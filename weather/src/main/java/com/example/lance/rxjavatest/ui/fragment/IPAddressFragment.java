package com.example.lance.rxjavatest.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lance.rxjavatest.R;
import com.example.lance.rxjavatest.model.bean.IPAddress;
import com.example.lance.rxjavatest.model.impl.IPAddressModelImpl;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * author: Lance
 * time: 2016/2/24 11:12
 * e-mail: lance.cao@anarry.com
 */
public class IPAddressFragment extends Fragment implements Callback<IPAddress> {

    @ViewInject(id = R.id.et)
    private EditText et;
    @ViewInject(id = R.id.bt_get, click = "onClick")
    private Button btn;
    @ViewInject(id = R.id.tv_info)
    private TextView tvInfo;

    private Context mContext;
    private Call<IPAddress> mCall;

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_get:
                getInfo(et.getText().toString());
                break;
            default:
                break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(">>>","IPAddressFragment onCreateView");
        View view = inflater.inflate(R.layout.fragment_detail, container,false);
        FinalActivity.initInjectedView(this, view);
        mContext = getActivity();
        et.setHint("请输入IP地址");
        return view;
    }

    @Override
    public void onResume() {
        Log.i(">>>","IPAddressFragment onResume");
        super.onResume();
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
//
//        IPAddressModelImpl.getDataCall(s).enqueue(this);
    }

    /**
     * 显示ip地址信息
     */
    private void showInfo(IPAddress ipAddress) {
        InputMethodManager manager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(et.getWindowToken(), 0);
        IPAddress.RetDataEntity retData = ipAddress.getRetData();
        StringBuilder builder = new StringBuilder();
        builder.append("ip: " + retData.getIp() + "\n");
        builder.append("国家: " + retData.getCountry() + "\n");
        builder.append("省份: " + retData.getProvince() + "\n");
        builder.append("城市: " + retData.getCity() + "\n");
        builder.append("区: " + retData.getDistrict() + "\n");
        builder.append("运营商: " + retData.getCarrier() + "\n");
        tvInfo.setText(builder.toString());
    }

    @Override
    public void onPause() {
        Log.i(">>>","IPAddressFragment onPause");
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        Log.i(">>>","IPAddressFragment onDestroyView");
        super.onDestroyView();
    }
}
