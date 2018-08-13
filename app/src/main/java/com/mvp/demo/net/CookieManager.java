package com.mvp.demo.net;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * @author Wang Yi Bo
 * @date 2018/5/23
 * 作用: Cookie统一管理
 */

public class CookieManager implements CookieJar{
    //cookie存储
    private final ConcurrentHashMap<String, List<Cookie>> cookieStore = new ConcurrentHashMap<>();
    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        cookieStore.put(url.host(), cookies);
        String cook = null;
        for (int i=0;i<cookies.size();i++){
            if (cookies.get(i).name().equals("yupen")){
                cook = cookies.get(i).name()+"="+cookies.get(i).value()+";";
            }else {
                cook =cookies.get(0).name()+"="+cookies.get(0).value()+";";
            }
        }
        Log.i("cook",cook);

    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        Log.i("xxx1",url.host());
        List<Cookie> cookies = cookieStore.get(url.host());
        String cook = null;
        if (cookies!=null){
        for (int i=0;i<cookies.size();i++){
                if (cookies.get(i).name().equals("yupen")){
                    cook = cookies.get(i).name()+"="+cookies.get(i).value()+";";
                }else {
                    cook =cookies.get(0).name()+"="+cookies.get(0).value()+";";
                }
            }
            Log.i("cook",cook);
        }

        return cookies != null ? cookies : new ArrayList<Cookie>();
    }
}
