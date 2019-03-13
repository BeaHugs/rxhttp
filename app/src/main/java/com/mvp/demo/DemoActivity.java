package com.mvp.demo;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.mvp.demo.activity.Demo2Activity;
import com.mvp.demo.base.BaseActivity;
import com.mvp.demo.utils.Event;

import butterknife.BindView;


public class DemoActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.btn_click)
    Button btn_click;
    @BindView(R.id.web_view)
    WebView webView;


    @Override
    public void showSuccess(Object o) {

    }

    @Override
    public void showError(String t) {

    }

    @Override
    protected int initView() {
        return R.layout.activity_demo;
    }

    @Override
    protected void initUI() {
        webView.loadUrl("http://kemi.bj.bcebos.com/kemi/1533285332380.doc");
    }

    @Override
    protected void initData() {
        new Thread(()->Log.i("Lambda","Lambda表达式")).start();
    }

    @Override
    protected void initListener() {
        btn_click.setOnClickListener(this);
    }


    /**
     * 是否注册
     *
     * @return
     */
    @Override
    public boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onReceiveEvent(Event event) {
        super.onReceiveEvent(event);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_click:
                startActivity(new Intent(this, Demo2Activity.class));
                break;
        }
    }

}
