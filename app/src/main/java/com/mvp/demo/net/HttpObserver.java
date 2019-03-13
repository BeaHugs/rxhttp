package com.mvp.demo.net;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mvp.demo.bean.BaseBean;

import io.reactivex.subscribers.DisposableSubscriber;
import okhttp3.ResponseBody;

/**
 * @author wangyibo
 * @date 2019/3/12DisposableSubscriber
 */
public abstract class HttpObserver<T extends BaseBean> extends DisposableSubscriber<ResponseBody> {

    public T t;
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("start","开始");
    }

    @Override
    public void onNext(ResponseBody responseBody) {
        try {
            String boby = responseBody.string();
            Log.i("user", boby);
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            t = (T) gson.fromJson(boby, BaseBean.class);

            responseBody.close();

            if (t.getStatus_code() == 0) {
                successResponse((T) t);
            } else {
                errorResponse(t.getMessage());
            }
        } catch (Exception e) {
            errorResponse("数据异常!!!");
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {
        errorResponse(e.getMessage());
    }

    @Override
    public void onComplete() {
        Log.i("start","结束");
    }

    public abstract void successResponse(T bean);

    public abstract void errorResponse(String bean);
}
