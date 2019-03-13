package com.mvp.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.mvp.demo.R;
import com.mvp.demo.base.BaseActivity;
import com.mvp.demo.bean.User;
import com.mvp.demo.net.HttpObserver;
import com.mvp.demo.net.NetWorkRequest;
import com.mvp.demo.utils.Event;
import com.mvp.demo.utils.EventBusUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class Demo2Activity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.tv_click)
    TextView tv_click;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Map map = new HashMap();


        NetWorkRequest.getNetWorkRequest().init(this).inRequest("http://api.qinjiakonggu.com/v1_0_12/shop/getshoplist", map, new HttpObserver<User>() {

            @Override
            public void successResponse(User bean) {
                Log.i("user", bean.toString());
            }

            @Override
            public void errorResponse(String message) {
                Log.i("user", message);
                Toast.makeText(Demo2Activity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void showSuccess(Object o) {

    }

    @Override
    public void showError(String t) {

    }

    @Override
    protected int initView() {
        return R.layout.activity_demo2;
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        tv_click.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_click:
                finish();
                break;
        }
    }
}
