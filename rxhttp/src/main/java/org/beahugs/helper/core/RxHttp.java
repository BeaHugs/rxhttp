package org.beahugs.helper.core;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;

import io.reactivex.Observable;
import org.beahugs.helper.request.base.BaseResponse;
import org.beahugs.helper.request.exception.RxHttpUninitializedException;
import org.beahugs.helper.network.download.DownloadInfo;
import org.beahugs.helper.network.download.RxDownload;
import org.beahugs.helper.network.download.setting.DefaultDownloadSetting;
import org.beahugs.helper.network.download.setting.DownloadSetting;
import org.beahugs.helper.request.RxRequest;

/**
 * 描述：网络请求
 * @Author: wangyibo
 * @Version: 1.0
 */
@SuppressLint("StaticFieldLeak")
public class RxHttp {

    private static RxHttp INSTANCE = null;

    private final Context mAppContext;
    private DownloadSetting mDownloadSetting = null;

    private RxHttp(Context context) {
        mAppContext = context;
    }

    public static void init(@NonNull Context context) {
        INSTANCE = new RxHttp(context.getApplicationContext());
    }

    public static RxHttp getInstance() {
        if (INSTANCE == null) {
            throw new RxHttpUninitializedException();
        }
        return INSTANCE;
    }


    @NonNull
    public static Context getAppContext() {
        return getInstance().mAppContext;
    }


    @NonNull
    public static DownloadSetting getDownloadSetting() {
        DownloadSetting setting = getInstance().mDownloadSetting;
        if (setting == null) {
            setting = new DefaultDownloadSetting();
        }
        return setting;
    }

    public static <T, R extends BaseResponse<T>> RxRequest<T, R> createApi(@NonNull Observable<R> observable) {
        return RxRequest.create(observable);
    }


    public static RxDownload download(@NonNull DownloadInfo info) {
        return RxDownload.create(info);
    }

}
