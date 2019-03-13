package com.mvp.demo.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.mvp.demo.utils.Event;
import com.mvp.demo.utils.EventBusUtils;
import com.mvp.demo.utils.auto.AutoLayoutConifg;
import com.mvp.demo.utils.auto.AutoUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;


/**
 * Created by Administrator Wang  on 2018/3/9.
 */

public abstract class BaseActivity<T,P> extends Activity implements IBaseDataBack<T>{
    private ViewGroup view;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        AutoLayoutConifg.getInstance().init(this);
        view = (ViewGroup) getLayoutInflater().inflate(initView(), null);
        AutoUtils.initLayoutSize(view, this);

        setContentView(view);
        if (isRegisterEventBus()){
            EventBusUtils.getDefault().register(this);
        }
        ButterKnife.bind(this);
        initUI();
        initData();
        initListener();

    }

    /**
     * 用于P V 关联
     */
    protected P mpre;
    public void setContentView(int view){
        super.setContentView(view);
    };

    /***
     * 获取View
     * @return
     */
    protected abstract int initView();
    /** 初始化ui **/
    protected abstract void initUI();

    /** 初始化数据 **/
    protected abstract void initData();

    /** 初始化监听 **/
    protected abstract void initListener();


    public  boolean isRegisterEventBus(){
        return false;
    };
    /**
     * 事件处理
     * 主线程
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEventBus(Event event){
        if (event!=null){
            onReceiveEvent(event);
        }
    }
    public void onReceiveEvent(Event event){};

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消订阅
        if (isRegisterEventBus()){
            EventBusUtils.getDefault().unregister(this);
        }
    }
}
