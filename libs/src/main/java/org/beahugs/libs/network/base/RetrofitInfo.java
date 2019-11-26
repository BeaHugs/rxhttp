package org.beahugs.libs.network.base;


import org.beahugs.libs.network.gson.GsonAdapter;
import org.beahugs.libs.network.utils.SSLUtils;
import org.beahugs.libs.network.interceptor.BaseLoggerInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @Author: wangyibo
 * @Version: 1.0
 */
public class RetrofitInfo {

    private String baseUrl;

    private CallAdapter.Factory[] callAdapterFactory;

    private Converter.Factory[] converterFactory;

    private OkHttpClient okHttpClient;

    public RetrofitInfo setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public RetrofitInfo setCallAdapterFactory(CallAdapter.Factory... callAdapterFactory) {
        this.callAdapterFactory = callAdapterFactory;
        return this;
    }

    public RetrofitInfo setConverterFactory(Converter.Factory... converterFactory) {
        this.converterFactory = converterFactory;
        return this;
    }

    public RetrofitInfo setOkHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
        return this;
    }


    public Retrofit build() {
        Retrofit.Builder builder = new Retrofit.Builder();

        builder.baseUrl(baseUrl);

        if (callAdapterFactory == null || callAdapterFactory.length <= 0) {
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        } else {
            for (CallAdapter.Factory factory : callAdapterFactory) {
                builder.addCallAdapterFactory(factory);
            }
        }

        if (converterFactory == null || converterFactory.length <= 0) {
            builder.addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(GsonAdapter.buildGson()));
        } else {
            for (Converter.Factory factory : converterFactory) {
                builder.addConverterFactory(factory);
            }
        }

        if (okHttpClient == null) {
            builder.client(createOkHttpClient());
        } else {
            builder.client(okHttpClient);
        }

        return builder.build();
    }

    private OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(10, TimeUnit.SECONDS);

        SSLUtils.SSLParams sslParams = SSLUtils.getSslSocketFactory();
        builder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new BaseLoggerInterceptor());
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);

        return builder.build();
    }
}
