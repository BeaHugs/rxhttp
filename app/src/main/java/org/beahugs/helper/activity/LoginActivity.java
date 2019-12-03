package org.beahugs.helper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import org.beahugs.helper.R;
import org.beahugs.libs.base.BaseMvpActivity;
import org.beahugs.libs.mvp.BasePresenter;

public class LoginActivity extends BaseMvpActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
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
    protected void onRetry() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
