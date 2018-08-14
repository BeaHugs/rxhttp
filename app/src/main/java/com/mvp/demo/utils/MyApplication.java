package com.mvp.demo.utils;

import android.app.Application;


import com.mvp.demo.net.ApiUrl;
import com.mvp.demo.net.NetWorkRequest;
import com.mvp.demo.utils.auto.AutoLayoutConifg;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator Wang  on 2018/3/9.
 */

public class MyApplication extends Application{

    static volatile MyApplication defaultInstance;

    public static MyApplication getDefault() {
        if (defaultInstance == null) {
            synchronized (MyApplication.class) {
                if (defaultInstance == null) {
                    defaultInstance = new MyApplication();
                }
            }
        }
        return defaultInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //CrashReport.initCrashReport(getApplicationContext(), "7ee1d17deb", false);
        NetWorkRequest.getNetWorkRequest().init(this, ApiUrl.HTTPHOST());
        JPushInterface.setDebugMode (true);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init (this);            // 初始化 JPush
        //CrashHandler.getInstance().init(this);

    }

}
