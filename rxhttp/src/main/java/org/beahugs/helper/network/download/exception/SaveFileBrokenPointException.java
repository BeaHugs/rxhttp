package org.beahugs.helper.network.download.exception;

/**
 * 断点下载保存异常
 * @Author: wangyibo
 * @Version: 1.0
 */
public class SaveFileBrokenPointException extends RuntimeException {
    public SaveFileBrokenPointException() {
        super("文件已下载部分与断点续传不符");
    }
}
