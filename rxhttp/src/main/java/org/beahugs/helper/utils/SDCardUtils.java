package org.beahugs.helper.utils;

import android.os.Environment;

import java.io.File;

import org.beahugs.helper.core.RxHttp;

/**
 * 描述: 检测SD卡
 * @Author: wangyibo
 * @Version: 1.0
 */
public class SDCardUtils {

    public static String getCacheDir() {
        File cacheFile = null;
        if (isSDCardAlive()) {
            cacheFile = RxHttp.getAppContext().getExternalCacheDir();
        }
        if (cacheFile == null) {
            cacheFile = RxHttp.getAppContext().getCacheDir();
        }
        return cacheFile.getAbsolutePath();
    }

    public static String getDownloadCacheDir() {
        File dir = null;
        if (isSDCardAlive()) {
            dir = RxHttp.getAppContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        }
        if (dir == null) {
            dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        }
        return dir.getAbsolutePath();
    }

    private static boolean isSDCardAlive() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

}