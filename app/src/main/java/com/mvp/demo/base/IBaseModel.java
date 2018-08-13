package com.mvp.demo.base;

import java.util.List;
import java.util.Map;

import retrofit2.Callback;


/**
 * @author Wang Yi Bo
 * @date 2018/5/25
 * 作用:
 */

public interface IBaseModel<T> {
    void loadData(Map<String,Object> map, Callback<T> callback);
}
