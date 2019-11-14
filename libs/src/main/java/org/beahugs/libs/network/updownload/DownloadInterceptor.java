package org.beahugs.libs.network.updownload;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author: wangyibo
 * @Version: 1.0
 */
public class DownloadInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request.newBuilder()
                .addHeader("Accept-Encoding", "identity")
                .build());
        return response;
    }
}
