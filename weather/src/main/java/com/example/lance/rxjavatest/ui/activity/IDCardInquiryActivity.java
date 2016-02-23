package com.example.lance.rxjavatest.ui.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lance.rxjavatest.R;
import com.example.lance.rxjavatest.model.bean.IDCardInfo;
import com.example.lance.rxjavatest.presenter.impl.IDCardInquiryPresenterImpl;
import com.example.lance.rxjavatest.ui.view.IDCardInquiryView;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

/**
 * author: Lance
 * time: 2016/2/23 14:19
 * e-mail: lance.cao@anarry.com
 */
public class IDCardInquiryActivity extends FinalActivity implements IDCardInquiryView {

    private static final String TAG = "IDCardInquiryActivity";

    @ViewInject(id = R.id.ib_back, click = "onClick")
    private ImageView ibBack;
    @ViewInject(id = R.id.et)
    private EditText et;
    @ViewInject(id = R.id.bt_get, click = "onClick")
    private Button btGet;
    @ViewInject(id = R.id.tv_info)
    private TextView tvInfo;

    private Context mContext;
    private Dialog mDialog;
    private IDCardInquiryPresenterImpl impl;

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                finish();
                break;
            case R.id.bt_get:
                if (!TextUtils.isEmpty(et.getText().toString())) {
                    impl.onIDCard(et.getText().toString());
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_card_inquiry);
        mContext = IDCardInquiryActivity.this;
        mDialog = new ProgressDialog(mContext);
        impl = new IDCardInquiryPresenterImpl(this);
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
        Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showIDCardInfo(IDCardInfo entity) {
        //隐藏键盘
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(et.getWindowToken(), 0);

        IDCardInfo.RetDataEntity retData = entity.getRetData();

        StringBuilder builder = new StringBuilder();
        builder.append("性别: " + retData.getSex() + "\n");
        builder.append("出生日期: " + retData.getBirthday() + "\n");
        builder.append("身份证归属地: " + retData.getAddress() + "\n");

        tvInfo.setText(builder.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
