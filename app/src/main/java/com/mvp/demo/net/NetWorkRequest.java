package com.mvp.demo.net;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.mvp.demo.utils.DigestUtils;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author Wang Yi Bo
 * @date 2018/5/22
 * 作用:
 */
//    Okhttp依赖
//    compile 'com.squareup.okio:okio:1.5.0'
//    compile 'com.squareup.okhttp3:okhttp:3.2.0'
//    拦截器
//    compile 'com.squareup.okhttp3:logging-interceptor:3.4.1'

public class NetWorkRequest implements INetWorkRequest {

    private static NetWorkRequest netWorkRequest = null;
    public NetWorkRequest() {
    }
    public static NetWorkRequest getNetWorkRequest(){
        if (netWorkRequest==null){
            synchronized (NetWorkRequest.class){
                if (netWorkRequest==null){
                    netWorkRequest = new NetWorkRequest();
                }
            }
        }
        return netWorkRequest;
    }

    private  OkHttpClient okHttpClient = null;
    private  Retrofit retrofit = null;
    private Context context;
    private String  baseUrl;

    /**
     * 初始化  Okhttp 和 Retrofit
     * @param context
     * @param baseUrl
     * @return
     */
    public NetWorkRequest init(Context context,String baseUrl){
        this.context = context;
        this.baseUrl = baseUrl;
        File sdcache = new File(Environment.getExternalStorageDirectory(), "demo");
        int cacheSize = 10 * 1024 * 1024;
        //okhttp拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("Log", message.toString());
            }
        });
        //拦截器日志分类
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder().connectTimeout(15, TimeUnit.MINUTES)
                .addInterceptor(httpLoggingInterceptor)
                //如果网络拦截器   一下要注释掉
                .cache(new Cache(sdcache.getAbsoluteFile(), cacheSize))
                .addInterceptor(new BaseInterceptor())
                //.cookieJar(new CookieManager())
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())  //string
                .addConverterFactory(GsonConverterFactory.create()) //Gson解析
                //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)//支持Okhttp
                .build();
        return this;
    }


    /**
     * 获取接口Api
     * @param tClass
     * @param <T>
     * @return 网络接口对象
     */
    public <T> T getApiSeriver(Class<T> tClass) {
        if (retrofit==null){
            throw new NullPointerException("retrofit Can't be empty 请在Application初始化");
        }
        return retrofit.create(tClass);
    }

    /**
     * 请求方式   Post     Okhttp使用
     * @param url         接口地址
     * @param map         Map 集合
     * @param callback   接口回调
     */
    public void doPostJson(String url, Map<Object,String> map, Callback callback) {
        Gson gson = new Gson();
        String strJson = gson.toJson(map);
        String encrypt = null;
        try {
            encrypt = DigestUtils.encrypt(strJson, DigestUtils.encryptKey, true);
            Request request = requestInfo(url,encrypt);
            Call call = okHttpClient.newCall(request);
            call.enqueue(callback);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Request requestInfo(String url, String jsonParams){
        RequestBody requestBody = RequestBody.create(MediaType.parse(getContentType()), getNewString(jsonParams));
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .header("X-Equipment", getXEquipment())
                .header("Accept",getAccept())
                .build();
        return request;
    }

    private String getNewString(String str) {
        String newStr = "{\"DesEncToDes\":\"" + str.trim() + "\"}";
        return newStr;
    }

    @Override
    public String getAccept() {
        return "application/json";
    }

    @Override
    public String getContentType() {
        return "application/json; charset=utf-8";
    }

    @Override
    public String getXEquipment() {
        return "Android";
    }

    /**
     * 对Map 集合加密
     * @param map
     * @return
     */
    @Override
    public String getDesStr(Map<String, Object> map) {
        Gson gson = new Gson();
        String strJson = gson.toJson(map);
        String encrypt = null;
        try {
            encrypt = DigestUtils.encrypt(strJson, DigestUtils.encryptKey, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encrypt;
    }

    /**
     * 用于 Retrofit 上传Json
     * @param map
     * @return
     */
    @Override
    public RequestBody getRequestBody(Map<String, Object> map) {
        String desStr = getDesStr(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse(getContentType()), getNewString(desStr));
        return requestBody;
    }
}
