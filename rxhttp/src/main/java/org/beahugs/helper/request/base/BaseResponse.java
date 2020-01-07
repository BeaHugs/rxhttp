package org.beahugs.helper.request.base;

/**
 * 描述：网络接口返回json格式对应的实体类
 * @Author: wangyibo
 * @Version: 1.0
 */
public interface BaseResponse<E> {

    int getCode();

    void setCode(int code);

    E getData();

    void setData(E data);

    String getMsg();

    void setMsg(String msg);
}
