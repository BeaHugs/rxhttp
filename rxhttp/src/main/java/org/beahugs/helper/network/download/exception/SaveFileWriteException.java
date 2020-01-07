package org.beahugs.helper.network.download.exception;

/**
 * @Author: wangyibo
 * @Version: 1.0
 */
public class SaveFileWriteException extends RuntimeException {
    public SaveFileWriteException() {
        super("下载保存的文件写入失败");
    }
}
