package com.example.lance.rxjavatest.ui.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
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
public class MobileAttributionFragment extends Fragment implements MobileAttributionView {

    @ViewInject(id = R.id.et, click = "onClick")
    private EditText et;
    @ViewInject(id = R.id.bt_get, click = "onClick")
    private Button btGet;
    @ViewInject(id = R.id.list_view)
    private ListView listView;
    @ViewInject(id = R.id.tv_info)
    private TextView tvInfo;

    private Context mContext;
    private Dialog mDialog;
    private MobileAttributionPresenterImpl mobileImpl;
    private View view;

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_get:
                et.clearFocus();
                mobileImpl.onMobile(et.getText().toString().trim());
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
        Log.i(">>>", "MobileAttributionFragment onCreateView");
        if (view == null) {
//        View view = inflater.inflate(R.layout.fragment_mobile_attribution,container,false);
            view = inflater.inflate(R.layout.fragment_detail, null);
        }
        FinalActivity.initInjectedView(this, view);
        mContext = getActivity();
        mDialog = new ProgressDialog(mContext);
        mobileImpl = new MobileAttributionPresenterImpl(this);
        listView.setVisibility(View.VISIBLE);
        tvInfo.setVisibility(View.GONE);
        et.setHint("请输入手机号");

        return view;
    }

    @Override
    public void onResume() {
        Log.i(">>>", "MobileAttributionFragment onResume");
        super.onResume();
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
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMobileInfo(List<MobileInfo> list) {

        listView.setAdapter(new CommomAdapter<MobileInfo>(mContext, list, R.layout.item_mobile_attribution) {
            @Override
            protected void convert(ViewHolder holder, MobileInfo mobileInfo) {
                StringBuilder builder = new StringBuilder();
                builder.append("手机号： " + mobileInfo.getPhone() + "\n");
                builder.append("运营商： " + mobileInfo.getOperator() + "\n");
                builder.append("省份： " + mobileInfo.getProvince() + "\n");
                builder.append("城市： " + mobileInfo.getCity() + "\n");
                builder.append("地区号： " + mobileInfo.getAreaCode() + "\n");
                builder.append("邮编： " + mobileInfo.getPostCode() + "\n");

                holder.setText(R.id.tv_info, builder.toString());
                holder.setOnClickListener(R.id.tv_info, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        });
    }

    @Override
    public void onPause() {
        Log.i(">>>", "MobileAttributionFragment onPause");
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        Log.i(">>>", "MobileAttributionFragment onDestroyView");
        super.onDestroyView();
        if (null != view) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }
}
