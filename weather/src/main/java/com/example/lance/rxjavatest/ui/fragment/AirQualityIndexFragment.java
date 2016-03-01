package com.example.lance.rxjavatest.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.example.lance.rxjavatest.R;
import com.example.lance.rxjavatest.db.City;
import com.example.lance.rxjavatest.model.bean.AirQualityInfo;
import com.example.lance.rxjavatest.model.bean.CityListInfo;
import com.example.lance.rxjavatest.model.impl.AirQualityModelImpl;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

import java.io.File;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author: Lance
 * time: 2016/2/26 17:31
 * e-mail: lance.cao@anarry.com
 */
public class AirQualityIndexFragment extends Fragment {

    @ViewInject(id = R.id.et, click = "onClick")
    private EditText et;
    @ViewInject(id = R.id.bt_get, click = "onClick")
    private Button btGet;
    @ViewInject(id = R.id.tv_info)
    private TextView tvInfo;

    private View view;
    private Context mContext;
    private SharedPreferences preferences;

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et:
                et.setText("");
                break;
            case R.id.bt_get:
                queryCity(et.getText().toString());
                break;
            default:
                break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(">>>", "AirQualityIndexFragment onCreateView");
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_detail, container, false);
        }
        FinalActivity.initInjectedView(this, view);
        mContext = getActivity();
        preferences = mContext.getSharedPreferences("city",Context.MODE_PRIVATE);
        ifDownLoadDat();
        return view;
    }

    /**
     * 判断是否已下载数据
     */
    private void ifDownLoadDat() {
        int count = preferences.getInt("exist",1);
        Log.i(">>>","count: " + count);
        if (count == 1){
            getCityList();
            preferences.edit().putInt("exist",0).apply();
        }
    }

    /**
     * 在使用ActiveAndroid，这种判断数据库中的表格是否存在不适用
     * 数据库，和表格在应用打开后就建立
     * */
    /**
     * 判断db是否存在
     */
    private void ifDBExist() {
        File file = mContext.getDatabasePath("city.db");
        Log.i(">>>", "path: " + file.getAbsolutePath());
        if (file.exists()) {
            ifTableExist(file);
            Log.i(">>>","file exist");
        } else {
            getCityList();
            Log.i(">>>", "file no exist");
        }
    }

    /**
     * 判断table是否存在
     * @param file
     */
    private void ifTableExist(File file) {
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(file,null);
        Cursor cursor = db.rawQuery("SELECT count(*) FROM sqlite_master WHERE type='table' AND name='City'", null);
        if (cursor.moveToNext()){
            int count = cursor.getInt(0);
            if (count == 0){
                getCityList();
                Log.i(">>>", "table no exist");
            }else{
                getCityList();
                Log.i(">>>","table exist");
            }
            return;
        }
        cursor.close();
        db.close();
    }

    @Override
    public void onResume() {
        Log.i(">>>", "AirQualityIndexFragment onResume");
        super.onResume();
    }

    /**
     * 获取城市列表
     */
    private void getCityList() {
        AirQualityModelImpl.getCityListData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CityListInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(">>>", e.toString());
                    }

                    @Override
                    public void onNext(CityListInfo cityListInfo) {
                        CityListInfo.RetDataEntity entity = cityListInfo.getRetData();
                        String city = entity.getCitylist();
                        String[] stringCity = city.split(",");
                        Log.i(">>>", city + "\n" + "length: " + stringCity.length);
                        ActiveAndroid.beginTransaction();
                        try {
                            for (int i = 0; i < stringCity.length; i++) {
                                City mCity = new City();
                                mCity.name = stringCity[i];
                                mCity.save();
                            }
                            ActiveAndroid.setTransactionSuccessful();
                        } finally {
                            ActiveAndroid.endTransaction();
                        }
                    }
                });
    }

    /**
     * 查询城市是否存在
     *
     * @param s
     */
    private void queryCity(String s) {
        City city = new Select().from(City.class).where("Name = ?", s).orderBy("RANDOM()").executeSingle();
        if (city == null) {
            Toast.makeText(mContext, "城市不在查询列表中", Toast.LENGTH_SHORT).show();
        } else {
            getAirInfo(s);
        }
    }

    /**
     * 获取 城市空气质量指数
     *
     * @param s
     */
    private void getAirInfo(String s) {
        AirQualityModelImpl.getAirQUalityData(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AirQualityInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(">>>", e.toString());
                    }

                    @Override
                    public void onNext(AirQualityInfo airQualityInfo) {
                        AirQualityInfo.RetDataEntity entity = airQualityInfo.getRetData();

                        StringBuilder builder = new StringBuilder();
                        builder.append("城市: " + entity.getCity() + "\n");
                        builder.append("采集时间: " + entity.getTime() + "\n");
                        builder.append("空气质量指数: " + entity.getAqi() + "\n");
                        builder.append("空气等级: " + entity.getLevel() + "\n");
                        builder.append("首要污染物: " + entity.getCore() + "\n");

                        tvInfo.setText(builder.toString());
                    }
                });
    }

    @Override
    public void onPause() {
        Log.i(">>>", "AirQualityIndexFragment onPause");
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        Log.i(">>>", "AirQualityIndexFragment onDestroyView");
        super.onDestroyView();
        if (view != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }
}
