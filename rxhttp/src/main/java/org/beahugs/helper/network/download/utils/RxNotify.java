package org.beahugs.helper.network.download.utils;



import androidx.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 描述: 更新到Ui线程
 * @Author: wangyibo
 * @Version: 1.0
 */
public class RxNotify {

    public static void runOnUiThread(@NonNull Action action){
        Observable.empty()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Object o) {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        action.run();
                    }
                });
    }

    public interface Action{
        void run();
    }
}
