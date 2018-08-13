package com.mvp.demo.activity;

import android.view.LayoutInflater;
import android.view.View;

import com.mvp.demo.R;
import com.mvp.demo.base.BaseFragment;

/**
 * @author Wang Yi Bo
 * @date 2018/8/1
 * 作用:
 */

public class DemoFragment extends BaseFragment{

    private View inflate;

    @Override
    public void showSuccess(Object o) {

    }

    @Override
    public void showError(String t) {

    }

    @Override
    protected View initLayout(LayoutInflater inflater) {
        inflate = inflater.inflate(R.layout.demo_fragment,null);
        return inflate;
    }

    @Override
    protected void showData() {

    }

    @Override
    protected Object getPresenter() {
        return null;
    }
}
