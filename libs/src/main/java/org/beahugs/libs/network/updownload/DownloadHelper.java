package org.beahugs.libs.network.updownload;


import org.beahugs.libs.network.base.BaseApiCacheFactory;
import org.beahugs.libs.network.interceptor.BaseObservableTransformer;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;

/**
 * @Author: wangyibo
 * @Version: 1.0
 */

public class DownloadHelper {
    public static Observable<ResponseBody> downloadFile(String fileUrl) {
        String DEFAULT_DOWNLOAD_KEY = "defaultDownloadUrlKey";
        String DEFAULT_BASE_URL = "https://api.github.com/";
        return BaseApiCacheFactory.getInstance()
                .setOkClient(new OkHttpClient.Builder().addInterceptor(new DownloadInterceptor()).build())
                .createApi(DEFAULT_DOWNLOAD_KEY, DEFAULT_BASE_URL, DownloadApi.class)
                .downloadFile(fileUrl)
                .compose(BaseObservableTransformer.<ResponseBody>switchSchedulers());
    }
}
