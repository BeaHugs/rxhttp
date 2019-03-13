package com.mvp.demo.base;

import android.app.Application;

import com.mvp.demo.net.ApiService;
import com.mvp.demo.net.NetWorkRequest;

/**
 * @author Wang Yi Bo
 * @date 2018/5/25
 * 作用:
 */

public abstract class BaseModel<T> implements IBaseModel<T>{
    private Application application;
    protected NetWorkRequest netWorkRequest;
    protected ApiService apiSeriver;
    /**
     * 初始化网络
     */
    public BaseModel(Application application) {
        this.application = application;
        netWorkRequest = NetWorkRequest.getNetWorkRequest();
    }

}
