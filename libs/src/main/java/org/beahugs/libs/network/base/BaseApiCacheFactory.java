package org.beahugs.libs.network.base;


import org.beahugs.libs.network.manage.RxUrlManager;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @Author: wangyibo
 * @Version: 1.0
 */
public class BaseApiCacheFactory {

    private volatile static BaseApiCacheFactory instance;

    /**
     * 缓存retrofit针对同一个域名下相同的ApiService不会重复创建retrofit对象
     */
    private static HashMap<String, Object> apiServiceCache;

    private CallAdapter.Factory[] callAdapterFactory;

    private Converter.Factory[] converterFactory;

    private OkHttpClient okHttpClient;

    public static BaseApiCacheFactory getInstance() {
        if (instance == null) {
            synchronized (BaseApiCacheFactory.class) {
                if (instance == null) {
                    instance = new BaseApiCacheFactory();
                }
            }
        }
        return instance;
    }

    private BaseApiCacheFactory() {
        apiServiceCache = new HashMap<>();
    }

    /**
     * 清空所有api缓存（用于切换环境时候使用）
     */
    public void clearAllApi() {
        apiServiceCache.clear();
    }

    public BaseApiCacheFactory setCallAdapterFactory(CallAdapter.Factory... callAdapterFactory) {
        this.callAdapterFactory = callAdapterFactory;
        return this;
    }

    public BaseApiCacheFactory setConverterFactory(Converter.Factory... converterFactory) {
        this.converterFactory = converterFactory;
        return this;
    }

    public BaseApiCacheFactory setOkClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
        return this;
    }

    public BaseApiCacheFactory setBaseUrl(String baseUrl) {
        RxUrlManager.getInstance().setUrl(baseUrl);
        return this;
    }

    public <A> A createApi(Class<A> apiClass) {
        String urlKey = RxUrlManager.DEFAULT_URL_KEY;
        String urlValue = RxUrlManager.getInstance().getUrl();
        return createApi(urlKey, urlValue, apiClass);
    }

    public <A> A createApi(String baseUrlKey, String baseUrlValue, Class<A> apiClass) {
        String key = getApiKey(baseUrlKey, apiClass);
        A api = (A) apiServiceCache.get(key);
        if (api == null) {
            Retrofit retrofit = new RetrofitInfo()
                    .setBaseUrl(baseUrlValue)
                    .setCallAdapterFactory(callAdapterFactory)
                    .setConverterFactory(converterFactory)
                    .setOkHttpClient(okHttpClient)
                    .build();

            api = retrofit.create(apiClass);

            apiServiceCache.put(key, api);
        }

        return api;
    }

    private static <A> String getApiKey(String baseUrlKey, Class<A> apiClass) {
        return String.format("%s_%s", baseUrlKey, apiClass);
    }

}
