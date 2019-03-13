package com.mvp.demo.model;

import android.app.Application;

import com.mvp.demo.base.BaseModel;

import java.util.Map;


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
    }
    public void loadData2(Map<String, Object> map, Callback callback) {
    }
}
