package org.beahugs.libs.mvp;

/**
 * @Author: wangyibo
 * @Version: 1.0
 */
public interface IView {


    //显示loading
    void showLoading();

    //隐藏loading
    void hideLoading();

    //显示吐司
    void showError(String msg);
}
