package org.beahugs.helper.network.download.exception;

/**
 * @Author: wangyibo
 * @Version: 1.0
 */
public class SaveFileDirMakeException extends RuntimeException {
    public SaveFileDirMakeException() {
        super("下载保存的文件父文件夹创建失败");
    }
}
