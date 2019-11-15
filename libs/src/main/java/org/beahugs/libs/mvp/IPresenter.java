package org.beahugs.libs.mvp;

/**
 * @Author: wangyibo
 * @Version: 1.0
 */
public interface IPresenter<V extends IView> {

    /**
     * 绑定 View
     */
    void attachView(V mView);

    /**
     * 解除 View
     */
    void detachView();
}
