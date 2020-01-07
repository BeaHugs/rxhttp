package org.beahugs.helper.http;

import com.google.gson.annotations.SerializedName;

import org.beahugs.helper.request.base.*;

public class ResponsBean<E> implements BaseResponse<E> {

    @SerializedName(value = "code", alternate = {"status"})
    private int code;
    @SerializedName(value = "data", alternate = {"result"})
    private E data;
    @SerializedName(value = "msg", alternate = {"message"})
    private String message;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public E getData() {
        return data;
    }

    @Override
    public void setData(E data) {
        this.data = data;
    }

    @Override
    public String getMsg() {
        return message;
    }

    @Override
    public void setMsg(String msg) {
        this.message = msg;
    }
}
