package com.mvp.demo.bean;

/**
 * @author wangyibo
 * @date 2019/3/13
 */
public class BaseBean {
    private String message;
    private int status_code;


    public String getMessage() {
        return message == null ? "" : message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }
}
