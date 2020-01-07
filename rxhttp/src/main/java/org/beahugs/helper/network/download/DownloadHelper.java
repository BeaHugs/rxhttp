package org.beahugs.helper.network.download;



import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import org.beahugs.helper.network.download.interceptor.RealNameInterceptor;
import org.beahugs.helper.request.base.BaseApiCacheFactory;

/**
 * @Author: wangyibo
 * @Version: 1.0
 */

public class DownloadHelper {

    public static Observable<ResponseBody> downloadFile(String range,String fileUrl) {
        String DEFAULT_DOWNLOAD_KEY = "defaultDownloadUrlKey";
        String DEFAULT_BASE_URL = "https://api.github.com/";
        return BaseApiCacheFactory.getInstance()
                .setOkClient(new OkHttpClient.Builder().addInterceptor(new RealNameInterceptor()).build())
                .createApi(DEFAULT_DOWNLOAD_KEY, DEFAULT_BASE_URL, DownloadApi.class)
                .download(range,fileUrl);
    }
}
