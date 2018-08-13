package com.mvp.demo.base;

/**
 * @author Wang Yi Bo
 * @date 2018/5/24
 * 作用:
 */

public interface IBaseDataBack<T> {
     void showSuccess(T t);
     void showError(String t);
}
