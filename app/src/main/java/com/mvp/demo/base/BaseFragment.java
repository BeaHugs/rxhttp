package com.mvp.demo.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Wang Yi Bo
 * @date 2018/6/12
 * 作用:
 */

public abstract class BaseFragment<D,P> extends Fragment implements IBaseDataBack<D>{

    private View view;
    protected P fpre;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = initLayout(inflater);
        fpre = getPresenter();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint()){
            showData();
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser&&isVisible()){
            showData();
        }
    }

    protected abstract View initLayout(LayoutInflater inflater);
    protected abstract void showData();
    public void loadData(){

    }
    protected abstract P getPresenter();

}
