package org.beahugs.libs.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import org.beahugs.libs.mvp.BasePresenter;
import org.beahugs.libs.mvp.IView;

/**
 * @Author: wangyibo
 * @Version: 1.0
 */
public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements IView{
    protected T mPresenter;

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onEmpty() {
        showEmpty();
    }

    @Override
    public void onError() {
        showError();
    }

    @Override
    public void onHide() {
        hideLayout();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = createPresenter();
        super.onCreate(savedInstanceState);
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }







    protected abstract T createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

}
