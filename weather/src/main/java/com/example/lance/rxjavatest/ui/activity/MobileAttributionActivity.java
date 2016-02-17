package com.example.lance.rxjavatest.ui.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lance.rxjavatest.R;
import com.example.lance.rxjavatest.adapter.CommomAdapter;
import com.example.lance.rxjavatest.adapter.ViewHolder;
import com.example.lance.rxjavatest.model.bean.MobileInfo;
import com.example.lance.rxjavatest.presenter.impl.MobileAttributionPresenterImpl;
import com.example.lance.rxjavatest.ui.view.MobileAttributionView;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

import java.util.List;

/**
 * author: Lance
 * time: 2016/1/28 17:30
 * e-mail: lance.cao@anarry.com
 */
public class MobileAttributionActivity extends FinalActivity implements MobileAttributionView{

    @ViewInject(id = R.id.ib_back, click = "onClick")
    private ImageView ibBack;
    @ViewInject(id = R.id.et)
    private EditText et;
    @ViewInject(id = R.id.bt_get, click = "onClick")
    private Button btGet;
    @ViewInject(id = R.id.list_view)
    private ListView listView;

    private Context mContext;
    private Dialog mDialog;
    private MobileAttributionPresenterImpl mobileImpl;

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                finish();
                break;
            case R.id.bt_get:
                et.clearFocus();
                mobileImpl.onMobile(et.getText().toString().trim());
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_attribution);
        mContext = MobileAttributionActivity.this;
        mDialog = new ProgressDialog(mContext);
        mobileImpl = new MobileAttributionPresenterImpl(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showDialog() {
        mDialog.show();
    }

    @Override
    public void hideDialog() {
        mDialog.dismiss();
    }

    @Override
    public void showError(String str) {
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMobileInfo(List<MobileInfo> list) {

        listView.setAdapter(new CommomAdapter<MobileInfo>(mContext,list,R.layout.item_mobile_attribution) {
            @Override
            protected void convert(ViewHolder holder, MobileInfo mobileInfo) {
                StringBuilder builder = new StringBuilder();
                builder.append("手机号： " + mobileInfo.getPhone() + "\n");
                builder.append("运营商： " + mobileInfo.getOperator() + "\n");
                builder.append("省份： " + mobileInfo.getProvince() + "\n");
                builder.append("城市： " + mobileInfo.getCity() + "\n");
                builder.append("地区号： " + mobileInfo.getAreaCode() + "\n");
                builder.append("邮编： " + mobileInfo.getPostCode( )+ "\n");

                holder.setText(R.id.tv_info, builder.toString());
                holder.setOnClickListener(R.id.tv_info, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        });


    }
}
