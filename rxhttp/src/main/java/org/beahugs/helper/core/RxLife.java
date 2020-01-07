package org.beahugs.helper.core;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 描述：管理生命周期
 * @Author: wangyibo
 * @Version: 1.0
 */
public class RxLife {

    private CompositeDisposable mCompositeDisposable = null;

    private RxLife() {
        mCompositeDisposable = new CompositeDisposable();
    }

    public static RxLife create() {
        return new RxLife();
    }

    public void destroy() {
        if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()) {
            return;
        }
        mCompositeDisposable.dispose();
    }

    public void add(Disposable d) {
        if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(d);
    }
}
