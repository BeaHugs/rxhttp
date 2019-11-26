package org.beahugs.libs.mvp;

/**
 * @Author: wangyibo
 * @Version: 1.0
 */
public interface IView {


    /**
     * 加载中
     */
    void onLoading();

    /**
     * 加载完成
     */
    void onComplete();

    /**
     * 用于请求的数据为空的状态
     */
    void onEmpty();

    /**
     * 用于请求数据出错
     */
    void onError();


    /**
     * 用于隐藏错误页面
     */
    void onHide();

    //显示吐司
    void showError(String msg);
}
