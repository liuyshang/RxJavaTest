package com.example.lance.rxjavatest.ui.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
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
import com.example.lance.rxjavatest.model.bean.IDCardInfo;
import com.example.lance.rxjavatest.presenter.impl.IDCardInquiryPresenterImpl;
import com.example.lance.rxjavatest.ui.view.FragmentView;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

/**
 * author: Lance
 * time: 2016/2/23 14:19
 * e-mail: lance.cao@anarry.com
 */
public class IDCardInquiryFragment extends Fragment implements FragmentView {

    private static final String TAG = "IDCardInquiryFragment";

    @ViewInject(id = R.id.et, click = "onClick")
    private EditText et;
    @ViewInject(id = R.id.bt_get, click = "onClick")
    private Button btGet;
    @ViewInject(id = R.id.tv_info)
    private TextView tvInfo;

    private Context mContext;
    private Dialog mDialog;
    private IDCardInquiryPresenterImpl impl;
    private View view;

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_get:
                if (!TextUtils.isEmpty(et.getText().toString())) {
                    impl.onIDCard(et.getText().toString());
                }
                break;
            case R.id.et:
                et.setText("");
                break;
            default:
                break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(">>>","IDCardInquiryFragment onCreateView");
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_detail, null);
        }
        FinalActivity.initInjectedView(this, view);
        mContext = getActivity();
        mDialog = new ProgressDialog(mContext);
        impl = new IDCardInquiryPresenterImpl(this);
        et.setHint("请输入身份证号码");
        return view;
    }

    @Override
    public void onResume() {
        Log.i(">>>", "IDCardInquiryFragment onResume");
        super.onResume();
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
    public <T> void showInfo(T infomation) {
        //隐藏键盘
        InputMethodManager manager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(et.getWindowToken(), 0);

        IDCardInfo entity = (IDCardInfo)infomation;
        IDCardInfo.RetDataEntity retData = entity.getRetData();

        StringBuilder builder = new StringBuilder();
        builder.append("性别: " + retData.getSex() + "\n");
        builder.append("出生日期: " + retData.getBirthday() + "\n");
        builder.append("身份证归属地: " + retData.getAddress() + "\n");

        tvInfo.setText(builder.toString());
    }

    @Override
    public void onPause() {
        Log.i(">>>","IDCardInquiryFragment onPause");
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        Log.i(">>>","IDCardInquiryFragment onDestroyView");
        super.onDestroyView();
        if (null != view) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }
}
