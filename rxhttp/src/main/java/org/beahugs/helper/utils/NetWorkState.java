package org.beahugs.helper.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.beahugs.helper.network.RxUtils;


/**
 * @Author: wangyibo
 * @Version: 1.0
 */
public class NetWorkState {
    /**
     * 判断是否有网络
     *
     * @return 返回值
     */
    public static boolean isNetworkConnected() {
        Context context = RxUtils.getContext();
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
}
