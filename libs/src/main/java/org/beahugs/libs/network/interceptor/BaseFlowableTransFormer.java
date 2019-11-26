package org.beahugs.libs.network.interceptor;

import org.beahugs.libs.network.interfaces.ILoadingView;
import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author: wangyibo
 * @Version: 1.0
 */
public class BaseFlowableTransFormer {
    /**
     * 无参数
     *
     * @param <T> 泛型
     * @return 返回Observable
     */
    public static <T> FlowableTransformer<T, T> switchSchedulers() {
        return switchSchedulers(null);
    }

    /**
     * 带参数  显示loading对话框
     *
     * @param loadingView loading
     * @param <T>         泛型
     * @return 返回Observable
     */
    public static <T> FlowableTransformer<T, T> switchSchedulers(final ILoadingView loadingView) {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream.observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io());
            }
        };
    }
}
