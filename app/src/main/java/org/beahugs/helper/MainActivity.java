package org.beahugs.helper;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import org.beahugs.common.weiget.loadingview.XLoadingView;
import org.beahugs.helper.presenter.MainPresenter;
import org.beahugs.libs.base.BaseMvpActivity;

public class MainActivity extends BaseMvpActivity<MainPresenter>  {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void getIntent(Intent intent) {
        mPresenter.dataInfo();
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
        mPresenter.dataInfo();
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void showError(String msg) {

    }

}
