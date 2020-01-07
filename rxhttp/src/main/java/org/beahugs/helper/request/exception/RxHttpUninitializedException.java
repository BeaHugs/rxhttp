package org.beahugs.helper.request.exception;



public class RxHttpUninitializedException extends RuntimeException {
    public RxHttpUninitializedException() {
        super("RxHttp未初始化");
    }
}
