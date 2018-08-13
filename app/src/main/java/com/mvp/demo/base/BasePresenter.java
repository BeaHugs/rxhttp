package com.mvp.demo.base;

import com.mvp.demo.net.ApiService;
import com.mvp.demo.net.NetWorkRequest;

/**
 * @author Wang Yi Bo
 * @date 2018/5/24
 * 作用:
 */

public abstract class BasePresenter<V extends IBaseDataBack> implements IBasePresenter{

    protected V mview;
    /**
     * 初始化
     * @param mview
     */
    public BasePresenter(V mview) {
        this.mview = mview;
    }
}
