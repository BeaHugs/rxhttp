package org.beahugs.helper.presenter;

import android.util.Log;

import androidx.appcompat.view.menu.BaseMenuPresenter;

import org.beahugs.helper.api.WanAndroid;
import org.beahugs.helper.bean.PublicNumbersBean;
import org.beahugs.helper.control.MainControl;
import org.beahugs.libs.mvp.BasePresenter;
import org.beahugs.libs.mvp.IModel;
import org.beahugs.libs.network.RxUtils;
import org.beahugs.libs.network.callback.BaseDisposableSubscriber;
import org.beahugs.libs.network.callback.CommonObserver;
import org.beahugs.libs.network.interceptor.BaseFlowableTransFormer;
import org.beahugs.libs.network.interceptor.BaseObservableTransformer;
import org.beahugs.libs.network.updownload.DownloadObserver;

import okhttp3.ResponseBody;

/**
 * @Author: wangyibo
 * @Version: 1.0
 */
public class MainPresenter extends BasePresenter implements MainControl.IMainPresenter {

    @Override
    protected IModel createModel() {
        return null;
    }

    @Override
    public void dataInfo() {

        RxUtils.createApi(WanAndroid.class)
                .FgetListofPublicNumbers()
                .compose(BaseFlowableTransFormer.<PublicNumbersBean>switchSchedulers())
                .subscribeWith(new BaseDisposableSubscriber<PublicNumbersBean>() {

                    @Override
                    public void doOnError(String errorMsg) {
                    }

                    @Override
                    public void doOnNext(PublicNumbersBean publicNumbersBean) {
                    }

                    @Override
                    public void doOnCompleted() {

                    }
                });





    }
}
