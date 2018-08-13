package com.mvp.demo.model;

import android.app.Application;

import com.mvp.demo.base.BaseData;
import com.mvp.demo.base.BaseModel;
import com.mvp.demo.bean.LoginBean;
import com.mvp.demo.bean.RedBean;

import java.util.List;
import java.util.Map;


import retrofit2.Call;
import retrofit2.Callback;

/**
 * @author Wang Yi Bo
 * @date 2018/5/25
 * 作用:
 */

public class LogonModel<T> extends BaseModel<T> {
    /**
     * 初始化网络
     *
     * @param application
     */
    public LogonModel(Application application) {
        super(application);
    }

    @Override
    public void loadData(Map<String, Object> map, Callback<T> callback) {
        apiSeriver.login(netWorkRequest.getRequestBody(map)).enqueue((Callback<BaseData<String>>) callback);
    }
    public void loadData2(Map<String, Object> map, Callback callback) {
        apiSeriver.selectRed(netWorkRequest.getRequestBody(map)).enqueue(callback);
    }
}
