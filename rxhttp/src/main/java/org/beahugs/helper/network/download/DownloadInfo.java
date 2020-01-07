package org.beahugs.helper.network.download;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.beahugs.helper.core.RxHttp;

/**
 * 描述: 文件下载 实体类
 * @Author: wangyibo
 * @Version: 1.0
 */
public class DownloadInfo {

    @NonNull
    public String url;
    @Nullable
    public String saveDirPath;
    @Nullable
    public String saveFileName;
    @IntRange(from = 0)
    public long downloadLength;
    @IntRange(from = 0)
    public long contentLength;
    @NonNull
    public State state;
    @NonNull
    public Mode mode;

    private DownloadInfo(@NonNull String url, @Nullable String saveDirPath, @Nullable String saveFileName,
                         @IntRange(from = 0) long downloadLength, @IntRange(from = 0) long contentLength) {
        this.url = url;
        this.saveDirPath = saveDirPath;
        this.saveFileName = saveFileName;
        this.downloadLength = downloadLength;
        this.contentLength = contentLength;
        this.state = State.STOPPED;
        this.mode = RxHttp.getDownloadSetting().getDefaultDownloadMode();
    }

    public static DownloadInfo create(@NonNull String url){
        return create(url, null, null);
    }

    public static DownloadInfo create(@NonNull String url,
                                      @Nullable String saveDirPath,
                                      @Nullable String saveFileName){
        return create(url, saveDirPath, saveFileName, 0, 0);
    }


    /**
     * 用来判断是否存在断点下载文件
     * @param context
     * @return
     */
    public static DownloadInfo getDownloadInfo(Context context){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String url = sp.getString("url", "");
        String saveDirName = sp.getString("saveDirPath", "");
        String saveFileName = sp.getString("saveFileName", "");
        long downloadLength = sp.getLong("downloadLength", 0);
        long contentLength = sp.getLong("contentLength", 0);
        if (TextUtils.isEmpty(url) || TextUtils.isEmpty(saveDirName) || TextUtils.isEmpty(saveFileName)){
            return null;
        }
        if (downloadLength == 0){
            return null;
        }
        if (contentLength < downloadLength){
            return null;
        }
        return DownloadInfo.create(url, saveDirName, saveFileName, downloadLength, contentLength);
    }


    public static DownloadInfo create(@NonNull String url,
                                      @Nullable String saveDirPath,
                                      @Nullable String saveFileName,
                                      @IntRange(from = 0) long downloadLength,
                                      @IntRange(from = 0) long contentLength){
        return new DownloadInfo(url, saveDirPath, saveFileName, downloadLength, contentLength);
    }

    /**
     * 如果路径文件存在，但是断点续传时未传入已下载长度信息，此时的写入模式
     */
    public enum Mode{
        /**
         * 追加
         */
        APPEND,
        /**
         * 替换
         */
        REPLACE,
        /**
         * 重命名
         */
        RENAME
    }

    public enum State{
        /**
         * 正在开始
         */
        STARTING,
        /**
         * 正在下载
         */
        DOWNLOADING,
        /**
         * 已停止
         */
        STOPPED,
        /**
         * 下载出错
         */
        ERROR,
        /**
         * 下载完成
         */
        COMPLETION
    }
}
