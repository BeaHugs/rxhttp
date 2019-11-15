package org.beahugs.helper.app;

import android.app.Application;

import org.beahugs.libs.network.RxUtils;
import org.beahugs.libs.network.base.OkHttpInfo;

import okhttp3.OkHttpClient;

/**
 * @Author: wangyibo
 * @Version: 1.0
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        //设置okhttp 参数
        OkHttpClient build = new OkHttpInfo.Builder(this).build();


        //设置 retrofit参数
        RxUtils.getInstance().init(this).config().setOkClient(build);


    }
}
