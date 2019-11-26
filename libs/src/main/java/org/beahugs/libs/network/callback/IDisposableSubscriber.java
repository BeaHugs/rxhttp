package org.beahugs.libs.network.callback;

import io.reactivex.disposables.Disposable;

/**
 * @Author: wangyibo
 * @Version: 1.0
 */
public interface IDisposableSubscriber<T> {


    /**
     * 错误回调
     *
     * @param errorMsg 错误信息
     */
    void doOnError(String errorMsg);

    /**
     * 成功回调
     *
     * @param t 泛型
     */
    void doOnNext(T t);

    /**
     * 请求完成回调
     */
    void doOnCompleted();
}
