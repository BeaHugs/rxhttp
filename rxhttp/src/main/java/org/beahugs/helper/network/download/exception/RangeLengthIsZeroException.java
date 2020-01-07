package org.beahugs.helper.network.download.exception;

/**
 * @Author: wangyibo
 * @Version: 1.0
 */
public class RangeLengthIsZeroException extends RuntimeException {
    public RangeLengthIsZeroException() {
        super("断点处请求长度为0");
    }
}
