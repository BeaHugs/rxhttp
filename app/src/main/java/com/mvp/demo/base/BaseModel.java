package com.mvp.demo.base;

import android.app.Application;
import android.content.Context;

import com.mvp.demo.bean.LoginBean;
import com.mvp.demo.net.ApiService;
import com.mvp.demo.net.NetWorkRequest;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

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
        apiSeriver = netWorkRequest.getApiSeriver(ApiService.class);
    }

}
