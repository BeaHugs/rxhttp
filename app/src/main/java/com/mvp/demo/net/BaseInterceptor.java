package com.mvp.demo.net;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * @author Wang Yi Bo
 * @date 2018/5/23
 * 作用:  Retrofit统一请求头
 */

public class BaseInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        HttpUrl url=original.url().newBuilder()
                .build();
        //添加请求头
        Request request = original.newBuilder()
                .header("X-Equipment", "Android")
                .header("Accept","application/json")
                //.header("Cookie","yupen=06C1001395E37236F0DD432102D481DDF83BF3F6F8C9202C238355B62B99E3F171D5C76201DB333E9AFAE8F3790C067DE44188748E60C9E1E388329E9235F86D7CF37B2EC669555AB9F5BDF747A29D45EA53683714E39ADE45FA8D125C48148BC61C65D0F47FB1AD26028DF747645E5EE1ADA9652A50F68E2824AAB2EA1B0EDA0500B6C06DDD96320124CE4C242759DD7E5CF2FF57BBEDF1FBB6E591EDE19AA271CE0083643E420CB8C5A783B6B75336339216CD525EBAF9BE1F9E207F9BF736E4F40E09041F7D977B99F40B764F80B4E9A9E74E0CF015F6E1151497EAA032D7;")
                .method(original.method(), original.body())
                .url(url)
                .build();
        return chain.proceed(request);
    }
}
