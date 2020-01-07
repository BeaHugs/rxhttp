package org.beahugs.helper;

import android.app.Application;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import org.beahugs.helper.network.RxUtils;
import org.beahugs.helper.network.info.OkHttpInfo;
import org.beahugs.helper.network.interfaces.BaseHeadersListener;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
/**
 *统一请求头
 */
        BaseHeadersListener baseHeaderInterceptor = new BaseHeadersListener() {
            @Override
            public Map<String, String> buildHeaders() {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("token","token");
                return hashMap;
            }
        };


        //设置okhttp 参数
        OkHttpClient build = new OkHttpInfo.Builder(this)
                .setHeaders(baseHeaderInterceptor)//统一请求头
                .setCache(false)
                .setHasNetCacheTime(10)//默认有网络时候缓存60秒
                .setNoNetCacheTime(1)//默认无网络时候缓存3600秒
                .setResponseCode(200)// 用来判断后台api数据
                //全局持久话cookie,保存到内存（new MemoryCookieStore()）或者保存到本地（new SPCookieStore(this)）
                // .setCookieType(new SPCookieStore(this))
                //可以添加自己的拦截器(比如使用自己熟悉三方的缓存库等等)
                //.setAddInterceptor(null)
                //1、信任所有证书,不安全有风险（默认信任所有证书）
                // .setSslSocketFactory()
                //2、使用预埋证书，校验服务端证书（自签名证书）
                //.setSslSocketFactory(cerInputStream)
                //3、使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
                //.setSslSocketFactory(bksInputStream,"123456",cerInputStream)
                //全局超时配置
                .setReadTimeout(10)
                //全局超时配置
                .setWriteTimeout(10)
                //全局超时配置
                .setConnectTimeout(10)
                //全局是否打开请求log日志
                .setDebug(true)
                .build();


        //设置 retrofit参数
        RxUtils.getInstance().init(this).config()
                .setOkClient(build)//设置的OKhttp参数
                .setBaseUrl("https://www.wanandroid.com");//网络请求的baseUrl
    }
}
