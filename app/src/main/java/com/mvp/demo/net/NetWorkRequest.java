package com.mvp.demo.net;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.mvp.demo.utils.DigestUtils;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


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
    private ApiService apiService;

    public NetWorkRequest() {
    }

    public static NetWorkRequest getNetWorkRequest() {
        if (netWorkRequest == null) {
            synchronized (NetWorkRequest.class) {
                if (netWorkRequest == null) {
                    netWorkRequest = new NetWorkRequest();
                }
            }
        }
        return netWorkRequest;
    }

    private OkHttpClient okHttpClient = null;
    private Retrofit retrofit = null;
    private Context context;

    /**
     * 初始化  Okhttp 和 Retrofit
     *
     * @param context
     * @return
     */
    public NetWorkRequest init(Context context) {
        this.context = context;
        //okhttp拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("retrofit", message.toString());
            }
        });
        //拦截器日志分类
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)//15秒
//                .readTimeout(60000, TimeUnit.SECONDS)
//                .writeTimeout(60000, TimeUnit.SECONDS)
//                .retryOnConnectionFailure(false)
                .addInterceptor(httpLoggingInterceptor)    //日志拦截器
                //如果网络拦截器   一下要注释掉
                //.addInterceptor(new BaseInterceptor()) //统一请求头
                //.cookieJar(new CookieManager())  //同步cookie
                .build();

        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://api.qinjiakonggu.com/")
                .client(okHttpClient)//支持Okhttp
                .build();
        apiService = retrofit.create(ApiService.class);
        return this;
    }


    private String getNewString(String str) {
        String newStr = "{\"DesEncToDes\":\"" + str.trim() + "\"}";
        return newStr;
    }

    /**
     * 对Map 集合加密
     *
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
     *
     * @param map
     * @return
     */
    public void response(String url, Map<String, Object> map, final HttpObserver iRequestCallBack) {
        String desStr = getDesStr(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse(ContentType_Value), getNewString(desStr));
        Flowable<ResponseBody> responseBodyFlowable = apiService.requestFile(url, requestBody);
        response(responseBodyFlowable, iRequestCallBack);
    }

    private Map<String, Object> map;
    @Override
    public Map<String, Object> parameter() {
        if (map != null) {
            //添加请求中的必填参数
            map.put("ip", "192.168.1.93");
            map.put("sign", "5629d218db6ea97e2f594c2a008c0e19");
            map.put("equipment", "android");
            map.put("type_number", "OPPO_OPPOR11t_27");
            map.put("action_city", "北京市");
            map.put("version", "1.0.16");
            map.put("token", "cb414aaf0f5db8b17d56f54ae30fb4a8");
            map.put("uid", "36");
            map.put("action", "v1_0_12/shop/getshoplist");
            map.put("timestamp", "1552374493");
            return map;
        }
        return null;
    }

    @Override
    public void response(final Flowable<ResponseBody> responseBodyFlowable, HttpObserver iRequestCallBack) {
        responseBodyFlowable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(iRequestCallBack);
    }


    @Override
    public void upFile(String url, Map<String, Object> map, HttpObserver iRequestCallBack) {
        this.map = map;
        RequestBody requestBody = formBody();
        if (requestBody != null) {
            Flowable<ResponseBody> responseBodyFlowable = apiService.requestFile(url, requestBody);
            response(responseBodyFlowable, iRequestCallBack);
        }
    }

    @Override
    public void inRequest(String url, Map<String, Object> map, HttpObserver iRequestCallBack) {
        this.map = map;
        if (parameter() != null) {
            Log.i("inRequest", "请求参数不能为空!!!");
            Flowable<ResponseBody> responseBodyFlowable = apiService.requestParams(url, parameter());
            response(responseBodyFlowable, iRequestCallBack);
        }
    }

    private RequestBody formBody() {
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        if (parameter() != null) {
            for (Map.Entry<String, Object> entry : parameter().entrySet()) {
                Object value = entry.getValue();
                if (value instanceof File) {
                    File file = (File) value;
                    builder.addFormDataPart(entry.getKey().toString(), file.getName(), RequestBody.create(MediaType.parse("/*"), file));
                } else if (value instanceof List) {
                    List<File> fileList = (List<File>) value;
                    for (File files : fileList) {
                        builder.addFormDataPart(entry.getKey().toString()+"[]", files.getName(), RequestBody.create(MediaType.parse("/*"), files));
                    }
                } else if (value instanceof String) {
                    builder.addFormDataPart(entry.getKey().toString(), entry.getValue().toString());
                }
            }
        }
        return builder.build();
    }

}
