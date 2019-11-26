package org.beahugs.libs.network.callback;

import org.beahugs.libs.network.base.BaseException;

import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @Author: wangyibo
 * @Version: 1.0
 */
public abstract class BaseDisposableSubscriber<T> extends DisposableSubscriber<T> implements IDisposableSubscriber<T> {


    @Override
    public void onNext(T o) {
        doOnNext(o);
    }

    @Override
    public void onError(Throwable e) {
        String error = BaseException.handleException(e).getMessage();
        doOnError(error);
    }

    @Override
    public void onComplete() {
         doOnCompleted();
    }

}
