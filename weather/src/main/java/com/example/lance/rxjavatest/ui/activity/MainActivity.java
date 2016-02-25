package com.example.lance.rxjavatest.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lance.rxjavatest.R;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

/**
 * author: Lance
 * time: 2016/1/28 17:24
 * e-mail: lance.cao@anarry.com
 */
public class MainActivity extends FinalActivity{

    @ViewInject(id = R.id.bt_weather, click = "onClick")
    private Button btWeather;
    @ViewInject(id = R.id.bt_mobile_attribution, click = "onClick")
    private Button btMobile;
    @ViewInject(id = R.id.bt_id_card, click = "onClick")
    private Button btIDCard;
    @ViewInject(id = R.id.bt_ip_address, click = "onClick")
    private Button btIPAdress;

    private Context mContext;

    public void onClick(View view){
        switch (view.getId()){
            case R.id.bt_weather:
                startActivity(new Intent(mContext,WeatherActivity.class));
                break;
            case R.id.bt_mobile_attribution:
                startActivity(new Intent(mContext,MobileAttributionActivity.class));
                break;
            case R.id.bt_id_card:
                startActivity(new Intent(mContext,IDCardInquiryActivity.class));
                break;
            case R.id.bt_ip_address:
                startActivity(new Intent(mContext,IPAddressActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
    }
}
