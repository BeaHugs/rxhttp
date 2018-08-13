package com.mvp.demo.utils;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.mvp.demo.R;
import com.mvp.demo.base.BaseFragment;
import com.mvp.demo.bean.LoginBean;
import com.mvp.demo.presenter.LogonPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Wang Yi Bo
 * @date 2018/6/12
 * 作用:
 */

public class DemoFragment extends BaseFragment<LoginBean, LogonPresenter> {
    Unbinder unbinder;
    private View view;

    @Override
    protected View initLayout(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.demo_fragment, null);
        return view;
    }


    /**
     * 用来初始化数据
     */
    @Override
    protected void showData() {

    }

    @Override
    protected LogonPresenter getPresenter() {
        return new LogonPresenter(this);
    }


    @Override
    public void showSuccess(LoginBean loginBean) {

    }


    @Override
    public void showError(String t) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
