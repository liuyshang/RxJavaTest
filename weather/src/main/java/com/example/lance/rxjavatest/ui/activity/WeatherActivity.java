package com.example.lance.rxjavatest.ui.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lance.rxjavatest.R;
import com.example.lance.rxjavatest.model.bean.WeatherInfo;
import com.example.lance.rxjavatest.presenter.impl.WeatherPresenterImpl;
import com.example.lance.rxjavatest.ui.view.WeatherView;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Lance
 * time: 2016/1/23 16:18
 * e-mail: lance.cao@anarry.com
 */
public class WeatherActivity extends FinalActivity implements WeatherView, View.OnClickListener{

    @ViewInject(id = R.id.ib_back)
    private ImageView ibBack;
    @ViewInject(id = R.id.et)
    private EditText et;
    @ViewInject(id = R.id.bt_get, click = "onClick")
    private Button btGet;
    @ViewInject(id = R.id.tv_info)
    private TextView tvInfo;

    private WeatherPresenterImpl weatherPresenter;
    private Context mContext;
    private Dialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        mContext = WeatherActivity.this;
        weatherPresenter = new WeatherPresenterImpl(this);
        mDialog = new ProgressDialog(this);
        mDialog.setTitle("加载天气中...");
        setListener();
    }

    private void setListener() {
        et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et.setText("");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_get:
                et.clearFocus();
                weatherPresenter.getWeatherInfo(et.getText().toString().trim());
                break;
            case R.id.ib_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void showLoading() {
        mDialog.show();
    }

    @Override
    public void hideLoading() {
        mDialog.dismiss();
    }

    @Override
    public void showError(String str) {
        Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showWeatherInfo(WeatherInfo info) {
        //隐藏键盘
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(et.getWindowToken(),0);

        StringBuilder builder = new StringBuilder();
        builder.append("城市: \t\t\t\t" + info.getCity() + "\n");
        builder.append("城市拼音: " + info.getPinyin() + "\n");
        builder.append("城市编码: " + info.getCitycode() + "\n");
        builder.append("日期: \t\t\t\t" + info.getDate() + "\n");
        builder.append("发布时间: " + info.getTime() + "\n");
        builder.append("邮编: \t\t\t\t" + info.getPostCode() + "\n");
        builder.append("经度: \t\t\t\t" + info.getLongitude() + "\n");
        builder.append("维度: \t\t\t\t" + info.getLatitude() + "\n");
        builder.append("海拔: \t\t\t\t" + info.getAltitude() + "\n");
        builder.append("天气情况: " + info.getWeather() + "\n");
        builder.append("气温: \t\t\t\t" + info.getTemp() + "\n");
        builder.append("最低气温: " + info.getL_tmp() + "\n");
        builder.append("最高气温: " + info.getH_tmp() + "\n");
        builder.append("风向: \t\t\t\t" + info.getWD() + "\n");
        builder.append("风力: \t\t\t\t" + info.getWS() + "\n");
        builder.append("日出时间: " + info.getSunrise() + "\n");
        builder.append("日落时间: " + info.getSunset() + "\n");
        tvInfo.setText(builder.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
