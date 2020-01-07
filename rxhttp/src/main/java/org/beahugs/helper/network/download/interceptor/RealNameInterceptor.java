package org.beahugs.helper.network.download.interceptor;


import android.text.TextUtils;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * 描述:
 * @Author: wangyibo
 * @Version: 1.0
 */
public class RealNameInterceptor implements Interceptor {

    public static void addTo(@NonNull OkHttpClient.Builder builder) {
        builder.addInterceptor(new RealNameInterceptor());
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response =  chain.proceed(chain.request());
        String disposition = response.header("Content-Disposition");
        if (!TextUtils.isEmpty(disposition)) {
            int index = disposition.indexOf("filename=");
            if (index >= 0) {
                String name = disposition.substring(index + 9, disposition.length());
                name = name.replace("UTF-8", "");
                name = name.replace("\"", "");
                if (!TextUtils.isEmpty(name)){
                    DownloadResponseBody responseBody = new DownloadResponseBody(response.body());
                    responseBody.setRealName(name);
                    return response.newBuilder()
                            .body(responseBody)
                            .build();
                }
            }
        }
        return response;
    }
}