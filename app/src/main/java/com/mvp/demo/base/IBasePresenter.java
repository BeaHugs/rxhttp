package com.mvp.demo.base;

import android.app.Application;

import java.util.Map;

/**
 * @author Wang Yi Bo
 * @date 2018/5/24
 * 作用:
 */

public interface IBasePresenter {
        void loadData(Map<String,Object> map, Application application);
        void loadData2(Map<String,Object> map, Application application);
}
