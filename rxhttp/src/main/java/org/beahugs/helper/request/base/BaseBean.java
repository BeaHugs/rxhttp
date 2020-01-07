package org.beahugs.helper.request.base;

import com.google.gson.Gson;

import java.io.Serializable;

import org.beahugs.helper.utils.JsonFormatUtils;

/**
 * 描述：网络请求的实体类基类
 * @Author: wangyibo
 * @Version: 1.0
 */
public class BaseBean implements Serializable {

    public String toJson() {
        return new Gson().toJson(this);
    }

    public String toFormatJson() {
        return JsonFormatUtils.format(toJson());
    }
}