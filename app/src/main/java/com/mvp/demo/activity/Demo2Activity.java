package com.mvp.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;


import com.mvp.demo.R;
import com.mvp.demo.base.BaseActivity;
import com.mvp.demo.bean.LoginBean;
import com.mvp.demo.utils.Event;
import com.mvp.demo.utils.EventBusUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Demo2Activity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.tv_click)
    TextView tv_click;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    public Object getPresenter() {
        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_click:
                EventBusUtils.getDefault().sendEvent(new Event<LoginBean>(0,new LoginBean("北京市")));
                finish();
                break;
        }
    }
}
