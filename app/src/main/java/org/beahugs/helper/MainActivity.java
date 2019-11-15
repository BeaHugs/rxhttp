package org.beahugs.helper;

import android.content.Intent;
import android.os.Bundle;

import org.beahugs.libs.base.BaseMvpActivity;
import org.beahugs.libs.mvp.BasePresenter;

public class MainActivity extends BaseMvpActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void getIntent(Intent intent) {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void showError(String msg) {

    }
}
