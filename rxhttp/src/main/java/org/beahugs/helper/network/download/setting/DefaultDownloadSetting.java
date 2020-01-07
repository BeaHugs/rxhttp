package org.beahugs.helper.network.download.setting;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.beahugs.helper.network.download.DownloadInfo;

/**
 * 描述:
 * @Author: wangyibo
 * @Version: 1.0
 */
public class DefaultDownloadSetting implements DownloadSetting {

    @NonNull
    @Override
    public String getBaseUrl() {
        return "http://api.rxhttp.download/";
    }

    @Override
    public long getTimeout() {
        return 60000;
    }

    @Override
    public long getConnectTimeout() {
        return 0;
    }

    @Override
    public long getReadTimeout() {
        return 0;
    }

    @Override
    public long getWriteTimeout() {
        return 0;
    }

    @Nullable
    @Override
    public String getSaveDirPath() {
        return null;
    }

    @NonNull
    @Override
    public DownloadInfo.Mode getDefaultDownloadMode() {
        return DownloadInfo.Mode.APPEND;
    }
}
